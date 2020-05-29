package com.mcoding.emis.goods.wechat.service;

import com.mcoding.base.core.BaseService;
import com.mcoding.emis.goods.wechat.bean.WxUserTags;
import com.mcoding.emis.goods.wechat.bean.WxUserTagsExample;
import com.mcoding.emis.member.bean.member.Member;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;

public interface WxUserTagsService extends BaseService<WxUserTags, WxUserTagsExample> {

    //给加盟商的微信用户打标签
    public void makeWxuserTags(Member member, WxMpService wxMpService) throws WxErrorException;
}