package com.mcoding.emis.goods.securityQrcode.service.impl;

import com.mcoding.base.ui.utils.spring.SpringContextHolder;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.emis.goods.common.api.EsbApi;
import com.mcoding.emis.goods.common.utils.IpUtil;
import com.mcoding.emis.member.bean.member.MemberPoint;
import com.mcoding.emis.member.bean.member.MemberPointExample;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.EmisMemberPointService;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 批量更新会员扫码积分的供应商信息
 * @author Benson
 *
 */
public class MemberPointDealerBatchUpdater {
	private static Logger logger = LoggerFactory.getLogger(MemberPointDealerBatchUpdater.class);

	private boolean isCollect = false;
	private boolean isUpdate = false;

	private BlockingQueue<MemberPoint> blockingQueue = new LinkedBlockingQueue<>(200);

	private AccountConfig accountConfig;

	private static Map<Integer, MemberPointDealerBatchUpdater> table = new Hashtable<>();

	public static MemberPointDealerBatchUpdater getInstance(AccountConfig accountConfig) {
		MemberPointDealerBatchUpdater updater = table.get(accountConfig.getId());
		if (updater != null) {
			return updater;
		}

		synchronized (table) {
			if (table.get(accountConfig.getId()) == null) {
				table.put(accountConfig.getId(), new MemberPointDealerBatchUpdater(accountConfig));
			}
		}

		return table.get(accountConfig.getId());

	}

	private MemberPointDealerBatchUpdater(AccountConfig accountConfig){
		super();
		this.accountConfig = accountConfig;
	}
	
	public void start() {
		if (isCollect || isUpdate) {
			throw new RuntimeException("已经在更新了，请稍候");
		}

		isCollect = true;
		
		Thread memberPointCollectThread = new Thread(new MemberPointCollector(this.blockingQueue));
		memberPointCollectThread.start();

		isUpdate = true;
		ThreadPoolTaskExecutor defaultThreadPool = SpringContextHolder.getOneBean(ThreadPoolTaskExecutor.class);
		
		for (int i = 0; i < 100; i++) {
			defaultThreadPool.execute(new UpdateCollector(this.blockingQueue));
		}
		
	}
	
	public void end() {
		this.isCollect = false;
		this.isUpdate =false;
		this.blockingQueue.clear();
	}
	
	
	protected class MemberPointCollector implements Runnable{
		private BlockingQueue<MemberPoint> queue;
		
		public MemberPointCollector(BlockingQueue<MemberPoint> queue) {
			this.queue = queue;
		}

		@Override
		public void run() {
			String next = null;
			int Count = 0;
			try {

				while (isCollect) {
					/*WxMpService wxMpService = WxMpServiceUtils.getWxMpServiceByAccount(accountConfig);
					WxMpUserList userList;
					userList = wxMpService.getUserService().userList(next);

					next = userList.getNextOpenId();
					List<String> openids = userList.getOpenIds();
					Count = Count + openids.size();
					logger.info("拉取用户openid成功，共拉取["+openids.size()+"]条openid,next openid["+next+"]");*/
					EmisMemberPointService memberPointService = SpringContextHolder.getOneBean(EmisMemberPointService.class);
					MemberPointExample example = new MemberPointExample();
					MemberPointExample.Criteria criteria = example.createCriteria();
					criteria.andPointsTypeEqualTo("A").andBrandCodeEqualTo("JLD");
					List<MemberPoint> list = memberPointService.queryAllObjByExample(example);

					for(int i=0; CollectionUtils.isNotEmpty(list) && i<list.size(); i++){
						this.queue.put(list.get(i));
					}
					
					if (StringUtils.isBlank(next)) {
						isCollect = false;
						break;
					}
					
				}
				
				logger.info("更新结束，共拉取["+Count+"]条openid");
			} catch (Exception e) {
				logger.error("出现异常，停止更新");
				isCollect = false;
				
				e.printStackTrace();
			}
		}
		
		
	}
	
	protected class UpdateCollector implements Runnable{
		private BlockingQueue<MemberPoint> queue;
		
		public UpdateCollector(BlockingQueue<MemberPoint> queue) {
			this.queue = queue;
		}
		
		@Override
		public void run() {
			logger.info("更新会员开始["+ Thread.currentThread().getName()+"]");
			while (isUpdate) {
				
				if (!isCollect && this.queue.isEmpty()) {
					isUpdate = false;
					return;
				}
				
				String fakeCode = null;
				try {
					MemberPoint t = this.queue.take();
                    fakeCode = t.getFakeCheckCode();
					//获取IP地址
					String IpAdress = IpUtil.getRealIp();
					//获取access_token
					String accessToken = EsbApi.getAccessToken();
					//调用校验真伪的接口
					CommonResult<JSONObject> jsonObject = EsbApi.getProductInfoByCode(accessToken, fakeCode, IpAdress);
					JSONObject returnObject =  jsonObject.getData();
					if(returnObject.getString("systemState").equals("000")||returnObject.getString("systemState").equals("005")
							||returnObject.getString("systemState").equals("006")){
					}else {
						//防伪码正确
						if(returnObject.getString("systemState").equals("001")||returnObject.getString("systemState").equals("002")){
                            EmisMemberPointService memberPointService = SpringContextHolder.getOneBean(EmisMemberPointService.class);
                            t.setDeal(returnObject.getString("dealer"));
							memberPointService.modifyObj(t);
						}
					}

					logger.info("更新["+t.getRelatedTransactionNo()+"]成功");
					
				} catch (Exception e) {
					System.out.println("更新资料失败，["+fakeCode+"]");
					logger.error("更新资料失败，原因是:" + e.getMessage());
					e.printStackTrace();
				}
				
			}
			
			logger.info("更新结束["+ Thread.currentThread().getName()+"]");
		}
		
	}
	
	

}
