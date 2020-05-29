/**
 * @filename: QRCodeUserService.java
 * @date: 2015-11-27
 * @author: Leiming
 */
package com.mcoding.emis.goods.qrcode.service;

import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.qrcode.bean.QRCodeUser;

/**
 * <p>Title: QRCodeUserService<p>
 * <p>Description: 带参数二维码与用户对应关系<p>
 * @author Leiming
 * @date 2015-11-27
 */
public interface QRCodeUserService extends BaseService<QRCodeUser,String>{

	/**
	 * 
	 * @Description: 根据标签id和用户opendid来查询数据
	 * @param keyword
	 * @param openid
	 * @return
	 * @return: QRCodeUser
	 * @author Leiming
	 */
	public QRCodeUser queryObjByKeywordAndOpendid(String keyword,String openid);

	int updateByPrimaryKey(QRCodeUser record);
	
	void deleteObjByOpenId(String openid);
	
	void updateObjByOpenId(String openid,String ext1);
}
