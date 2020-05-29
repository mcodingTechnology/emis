package com.mcoding.emis.member.service.member.impl;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.account.utils.WxMpServiceUtils;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.WeixinUser;
import com.mcoding.emis.member.bean.member.WeixinUserExample;
import com.mcoding.emis.member.persistence.member.EmisWeixinUserMapper;
import com.mcoding.emis.member.service.member.MemberService;
import com.mcoding.emis.member.service.member.WeixinUserService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("weixinUserService")
public class WeixinUserServiceImpl implements WeixinUserService {
	
	private static Logger logger = LoggerFactory.getLogger(WeixinUserServiceImpl.class);
	
	@Autowired
	protected ThreadPoolTaskExecutor defaultThreadPool;
	
    @Resource
    protected EmisWeixinUserMapper emisWeixinUserMapper;
    
    @Autowired
    protected StoreWxRefService storeWxRefService;
    
    @Resource(name="memberServiceFromEmis")
    protected MemberService memberService;

    @CacheEvict(value={"weixinUser"}, allEntries=true)
    @Override
    public void addObj(WeixinUser t) {
        this.emisWeixinUserMapper.insertSelective(t);
    }

    @CacheEvict(value={"weixinUser"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.emisWeixinUserMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"weixinUser"}, allEntries=true)
    @Override
    public void modifyObj(WeixinUser t) {
        if (t.getWxuserid() == null || t.getWxuserid() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.emisWeixinUserMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="weixinUser", key="'WeixinUserService_' + #root.methodName + '_' +#id")
    @Override
    public WeixinUser queryObjById(int id) {
        return this.emisWeixinUserMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="weixinUser", key="'WeixinUserService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<WeixinUser> queryAllObjByExample(WeixinUserExample example) {
        return this.emisWeixinUserMapper.selectByExample(example);
    }

    @Cacheable(value="weixinUser", key="'WeixinUserService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<WeixinUser> queryObjByPage(WeixinUserExample example) {
        PageView<WeixinUser> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.emisWeixinUserMapper.selectByExampleByPage(example));
        return pageView;
    }

    @Cacheable(value="weixinUser", key="'WeixinUserService_' + #root.methodName + '_'+ #openid")
	@Override
	public WeixinUser selectByOpenid(String openid) {
    	if (StringUtils.isBlank(openid)) {
			throw new NullPointerException("openid could not be null");
		}
    	
    	WeixinUserExample example = new WeixinUserExample();
    	WeixinUserExample.Criteria criteria = example.createCriteria();
    	criteria.andOpenidEqualTo(openid);
    	
    	List<WeixinUser> list = this.queryAllObjByExample(example);
    	if (CollectionUtils.isEmpty(list)) {
			return null;
		}
    	
		return list.get(0);
	}

	@Override
	public ModelAndView wechatPersonalCenterView(HttpSession session, String openid, String brandCode) {
		ModelAndView mav = new ModelAndView();
		try {
			if(openid==null){
				if(session.getAttribute("openid")!=null){
					openid  = session.getAttribute("openid").toString();
				}
			}
			//更新微信会员信息
//			wechatService.updateWxUserinfo(brandCode, openid);
			
			mav.addObject("openid", openid);
			System.out.println("openid=========="+openid);
			System.out.println("brandCode=========="+brandCode);
			System.out.println("jumping=========="+brandCode);
			
			//根据Openid获取会员信息
//			Member member = memberMapper.queryMemberByOpenidAndBrandCode(openid,brandCode);
			Member member = this.memberService.queryMemberByOpenid(openid, brandCode);
			//获取微信会员头像
			mav.addObject("headimgurl", member.getHeadimgurl());
			mav.addObject("pointSum", member.getPointSum());
			mav.addObject("mobilePhone", member.getMobilePhone());
			mav.addObject("fullName", member.getFullName());
			if(member!=null && member.getBrandCode().equals("MRMJ")){
				mav.setViewName("frontPageView/wechat/personalCenter");
			}else {
				mav.setViewName("frontPageView/gymmax/wechat/personalCenter");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	@Override
	public JsonResult<WeixinUser> getWxuserInfo(String openid, String brandCode) {
		JsonResult<WeixinUser> jsonResult = new JsonResult<>();
		
		if (brandCode == null) {
			throw new NullPointerException("brandCode can't be null");
		}
		System.out.println("=============OPENID=============="+openid);
		Member member = this.memberService.queryMemberByOpenid(openid);
		if(member.getMobilePhone()==null){
			jsonResult.setStatus("01");
			jsonResult.setMsg("无手机号码，未完善资料");
			jsonResult.setSize(0);
			return jsonResult;
		}
//		WeixinUser weixinUser = emisWeixinUserMapper.selectByOpenid(openid, brandCode);
		
		WeixinUser weixinUser = null;
		WeixinUserExample example = new WeixinUserExample();
		example.createCriteria().andBrandCodeEqualTo(brandCode).andOpenidEqualTo(openid);
		
		List<WeixinUser> list = this.emisWeixinUserMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(list)) {
			weixinUser = list.get(0);
		}
		
		jsonResult.setStatus("00");
		jsonResult.setMsg("操作成功");
		jsonResult.setSize(1);
		jsonResult.setData(weixinUser);
		
		return jsonResult;
	}

	@Override
	public JsonResult<ArrayList<WeixinUser>> getMemberAllys(String openid, Integer pageNo, Integer pageSize) {
		JsonResult<ArrayList<WeixinUser>> result = new JsonResult<ArrayList<WeixinUser>>();
		try {
			PageView<WeixinUser> pageView = new PageView<>(pageNo, pageSize);
			
			WeixinUserExample example = new WeixinUserExample();
			example.createCriteria().andShareIdEqualTo(openid);
			example.setPageView(pageView);
			
			pageView = this.queryObjByPage(example);
			
			if (pageView.getPageCount() < pageNo) {
				result.setData(null);
				return result;
			} 
			ArrayList<WeixinUser> list = new ArrayList<>();
			CollectionUtils.addAll(list, pageView.getQueryResult().iterator());
			
			result.setData(list);
			result.setStatus("0");
	        result.setMsg("ok");
	        result.setSize(pageView.getRowCount());
		} catch (Exception e) {
            logger.error("查询openid获取会员数据异常：", e);
            result.setStatus("1");
            result.setData(null);
            result.setMsg(e.getMessage());
        }
		
		return result;
	}

	@Override
	public WeixinUser addOrEditWeixinUserByOpenid(String openid,String parentOpenId, String brandCode) {
		Date now = new Date();
		WeixinUser weixinUserNew = this.selectByOpenid(openid);
		if(weixinUserNew == null){
			//如果没有该用户就添加一条新消息
			
			weixinUserNew = new WeixinUser();
			weixinUserNew.setOpenid(openid);
			weixinUserNew.setBrandCode(brandCode);
			weixinUserNew.setCreatetime(now);
			weixinUserNew.setUpdatetime(now);
			if(StringUtils.isNotBlank(parentOpenId)){
				weixinUserNew.setShareId(parentOpenId);
			}
			this.addObj(weixinUserNew);
			
		}else{
			//如果已经存在的，检查是否有上级，已经是否通过上级的连接进来
			if(StringUtils.isBlank(weixinUserNew.getShareId()) 
					&& StringUtils.isNotBlank(parentOpenId)){
				//更新上级的连接
				WeixinUser tmp = new WeixinUser();
				tmp.setWxuserid(weixinUserNew.getWxuserid());
				tmp.setShareId(parentOpenId);
				tmp.setUpdatetime(now);
				this.modifyObj(tmp);
			}
		}
		return weixinUserNew;
	}

	@Override
	public WeixinUser addOrEditWeixinUser(WxMpUser wxMpUser, String parentOpenId, String brandCode) {
		Date now = new Date();
		String openid = wxMpUser.getOpenId();
		WeixinUser weixinUserNew = this.selectByOpenid(openid);
		if(weixinUserNew == null){
			//如果没有该用户就添加一条新消息
			
			weixinUserNew = new WeixinUser();
			weixinUserNew.setOpenid(openid);
			weixinUserNew.setBrandCode(brandCode);
			weixinUserNew.setCreatetime(now);
			weixinUserNew.setUpdatetime(now);
			weixinUserNew.setCity(wxMpUser.getCity());
			weixinUserNew.setCountry(wxMpUser.getCountry());
			/*if(StringUtils.isNotBlank(wxMpUser.getHeadImgUrl())){
				weixinUserNew.setHeadimgurl(wxMpUser.getHeadImgUrl());
			}else{
				weixinUserNew.setHeadimgurl("http://v.gymmaxer.com/resources/images/headImg_gmx.png");
			}*/
			weixinUserNew.setHeadimgurl(wxMpUser.getHeadImgUrl());
			if(StringUtils.isNotBlank(wxMpUser.getNickname())){
				weixinUserNew.setNickname(wxMpUser.getNickname());
			}else{
				weixinUserNew.setNickname("未知");
			}
			weixinUserNew.setProvince(wxMpUser.getProvince());
			weixinUserNew.setSex(wxMpUser.getSex());
			weixinUserNew.setUnionid(wxMpUser.getUnionId());
			if(wxMpUser.getSubscribe()!=null){
				weixinUserNew.setSubscribe(wxMpUser.getSubscribe()? "1" : "0");
			}
			
			if(wxMpUser.getSubscribe().equals(true) &&
					wxMpUser.getSubscribeTime() != null){
				weixinUserNew.setSubscribetime(new Date(wxMpUser.getSubscribeTime() * 1000));
				weixinUserNew.setFirstSubscribeTime(new Date(wxMpUser.getSubscribeTime() * 1000));
			}
			
			if(StringUtils.isNotBlank(parentOpenId)){
				weixinUserNew.setShareId(parentOpenId);
			}
			this.addObj(weixinUserNew);
			
		}else{
			WeixinUser tmp = new WeixinUser();
			tmp.setWxuserid(weixinUserNew.getWxuserid());
			
			tmp.setCity(wxMpUser.getCity());
			tmp.setCountry(wxMpUser.getCountry());
			tmp.setHeadimgurl(wxMpUser.getHeadImgUrl());
			tmp.setNickname(wxMpUser.getNickname());
			tmp.setProvince(wxMpUser.getProvince());
			tmp.setSex(wxMpUser.getSex());
			tmp.setUnionid(wxMpUser.getUnionId());
			if(wxMpUser.getSubscribe()!=null){
				tmp.setSubscribe(wxMpUser.getSubscribe()? "1" : "0");
			}
			tmp.setSubscribetime(new Date(wxMpUser.getSubscribeTime() * 1000));
			tmp.setUpdatetime(now);
			
			//如果已经存在的，检查是否有上级，已经是否通过上级的连接进来
			if(StringUtils.isBlank(weixinUserNew.getShareId()) 
					&& StringUtils.isNotBlank(parentOpenId)){
				//更新上级的连接
				tmp.setShareId(parentOpenId);
			}
			
			this.modifyObj(tmp);
		}
		return weixinUserNew;
	}

	@Override
	public void updateWeixinUserList(WxMpUserList wxMpUserList) {
		List<String> openids = wxMpUserList.getOpenIds();
		AccountConfig accountConfig = this.storeWxRefService.queryWxAccountByStoreId(StoreUtils.getStoreFromThreadLocal().getId());
		final WxMpService wxMpService = WxMpServiceUtils.getWxMpServiceByAccount(accountConfig);
		
		for(final String openid : openids){
			this.defaultThreadPool.execute(new Runnable() {
				
				@Override
				public void run() {    
					WxMpUser wxMpUser = null;
					try {
						wxMpUser = wxMpService.getUserService().userInfo(openid, null);
						addOrEditWeixinUser(wxMpUser, null, null);
						
					} catch (Exception e) {
						System.out.println("更新会员资料失败，["+openid+"]["+wxMpUser+"]");
						logger.error("更新会员资料失败，原因是:" + e.getMessage());
						e.printStackTrace();
					}
				}
			});
		}
		
	}
}