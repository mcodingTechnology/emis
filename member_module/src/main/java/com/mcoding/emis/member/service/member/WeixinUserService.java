package com.mcoding.emis.member.service.member;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.BaseService;
import com.mcoding.base.core.JsonResult;
import com.mcoding.emis.member.bean.member.WeixinUser;
import com.mcoding.emis.member.bean.member.WeixinUserExample;

import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;

public interface WeixinUserService extends BaseService<WeixinUser, WeixinUserExample> {
	
	//根据openid查微信用户
    WeixinUser selectByOpenid(String openid);
    
    //微信个人中心跳转页面
    public ModelAndView wechatPersonalCenterView(HttpSession session,String openid,String brandCode);

    //微商城API---获取微信会员信息
    public JsonResult<WeixinUser> getWxuserInfo(String openid, String brandCode);
    
    //查询会员的所有下级
    public JsonResult<ArrayList<WeixinUser>> getMemberAllys(String openid,Integer pageNo,Integer pageSize);

    //根据openid新增或编辑微信用户
    public WeixinUser addOrEditWeixinUserByOpenid(String openid,String parentOpenid,String brandCode);

    //新增或编辑完整微信用户信息
    public WeixinUser addOrEditWeixinUser(WxMpUser wxMpUser,String parentOpenId,String brandCode);
    
    //批量更新微信用户
    public void updateWeixinUserList(WxMpUserList wxMpUserList);
    
}