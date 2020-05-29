package com.mcoding.emis.member.service.member.impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.mcoding.emis.member.service.member.MemberService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.mcoding.base.ui.utils.spring.SpringContextHolder;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.account.utils.WxMpServiceUtils;
import com.mcoding.emis.member.service.member.WeixinUserService;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;

/**
 * 批量更新公众号会员信息
 * @author hzy
 *
 */
public class WeixinUserBatchUpdater {
	private static Logger logger = LoggerFactory.getLogger(WeixinUserBatchUpdater.class);
	
	private boolean isCollectOpenid = false;
	private boolean isUpdateWeixinUser = false;
	
	private BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(200);
	
	private AccountConfig accountConfig;
	
	private static Map<Integer, WeixinUserBatchUpdater> table = new Hashtable<>();
	
	public static WeixinUserBatchUpdater getInstance(AccountConfig accountConfig) {
		WeixinUserBatchUpdater updater = table.get(accountConfig.getId());
		if (updater != null) {
			return updater;
		}
		
		synchronized (table) {
			if (table.get(accountConfig.getId()) == null) {
				table.put(accountConfig.getId(), new WeixinUserBatchUpdater(accountConfig));
			}
		}
		
		return table.get(accountConfig.getId());
		
	}
	
	private WeixinUserBatchUpdater(AccountConfig accountConfig){
		super();
		this.accountConfig = accountConfig;
	}
	
	public void start() {
		if (isCollectOpenid || isUpdateWeixinUser) {
			throw new RuntimeException("已经在更新了，请稍候");
		}
		
		isCollectOpenid = true;
		
		Thread openidCollectThread = new Thread(new OpenidCollector(this.blockingQueue));
		openidCollectThread.start();
		
		isUpdateWeixinUser = true;
		ThreadPoolTaskExecutor defaultThreadPool = SpringContextHolder.getOneBean(ThreadPoolTaskExecutor.class);
		
		for (int i = 0; i < 100; i++) {
			defaultThreadPool.execute(new WeixinInfoCollector(this.blockingQueue));
		}
		
	}
	
	public void end() {
		this.isCollectOpenid = false;
		this.isUpdateWeixinUser =false;
		this.blockingQueue.clear();
	}
	
	
	protected class OpenidCollector implements Runnable{
		private BlockingQueue<String> queue;
		
		public OpenidCollector(BlockingQueue<String> queue) {
			this.queue = queue;
		}

		@Override
		public void run() {
			String nextOpenid = null;
			int openidCount = 0;
			try {

				while (isCollectOpenid) {
					WxMpService wxMpService = WxMpServiceUtils.getWxMpServiceByAccount(accountConfig);
					WxMpUserList userList;
					userList = wxMpService.getUserService().userList(nextOpenid);

					nextOpenid = userList.getNextOpenId();
					List<String> openids = userList.getOpenIds();
					openidCount = openidCount + openids.size();
					logger.info("拉取用户openid成功，共拉取["+openids.size()+"]条openid,next openid["+nextOpenid+"]");
					
					for(int i=0; CollectionUtils.isNotEmpty(openids) && i<openids.size(); i++){
						this.queue.put(openids.get(i));
					}
					
					if (StringUtils.isBlank(nextOpenid)) {
						isCollectOpenid = false;
						break;
					}
					
				}
				
				logger.info("拉取用户openid结束，共拉取["+openidCount+"]条openid");
			} catch (Exception e) {
				logger.error("拉取用户治疗异常，停止会员更新");
				isCollectOpenid = false;
				
				e.printStackTrace();
			}
		}
		
		
	}
	
	protected class WeixinInfoCollector implements Runnable{
		private BlockingQueue<String> queue;
		
		public WeixinInfoCollector(BlockingQueue<String> queue) {
			this.queue = queue;
		}
		
		@Override
		public void run() {
			logger.info("更新会员开始["+ Thread.currentThread().getName()+"]");
			while (isUpdateWeixinUser) {
				
				if (!isCollectOpenid && this.queue.isEmpty()) {
					isUpdateWeixinUser = false;
					return;
				}
				
				WxMpUser wxMpUser = null;
				String openid = null;
				try {
					openid = this.queue.take();
					
					WxMpService wxMpService = WxMpServiceUtils.getWxMpServiceByAccount(accountConfig);
					wxMpUser = wxMpService.getUserService().userInfo(openid, null);

					MemberService memberService = SpringContextHolder.getOneBean(MemberService.class);
					memberService.addOrEditMemberByWxMpUser(wxMpUser,null , null, accountConfig.getName());

					logger.info("更新会员["+openid+"]成功");
					
				} catch (Exception e) {
					System.out.println("更新会员资料失败，["+openid+"]["+wxMpUser+"]");
					logger.error("更新会员资料失败，原因是:" + e.getMessage());
					e.printStackTrace();
				}
				
			}
			
			logger.info("更新会员结束["+ Thread.currentThread().getName()+"]");
		}
		
	}
	
	

}
