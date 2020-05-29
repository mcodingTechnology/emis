package com.mcoding.emis.goods.qrcode.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mcoding.base.core.IMapper;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.qrcode.bean.QRCodeUser;
import com.mcoding.emis.goods.qrcode.bean.QRCodeUserExample;
import com.mcoding.emis.member.bean.member.WeixinUser;

public interface QRCodeUserMapper extends IMapper<QRCodeUser, QRCodeUserExample> {
	
	List<QRCodeUser> queryResultByKeyAndOpenid(Map<String, Object> param);

	void deleteObjByOpenId(Map<String, Object> param);

	void updateObjByOpenId(Map<String, Object> param);
	
	List<WeixinUser> selectInvitedWxUserByPage(@Param("keyword") String keyword, @Param("pageView") PageView<WeixinUser> pageView);

}