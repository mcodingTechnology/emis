/**
 * @filename: QRCodeUserServiceImpl.java
 * @date: 2015-11-27
 * @author: Leiming
 */
package com.mcoding.emis.goods.qrcode.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.qrcode.bean.QRCodeUser;
import com.mcoding.emis.goods.qrcode.persistence.QRCodeUserMapper;
import com.mcoding.emis.goods.qrcode.service.QRCodeUserService;
import com.mcoding.emis.member.common.CommonResult;

/**
 * <p>Title: QRCodeUserServiceImpl<p>
 * <p>Description: TODO<p>
 * @author Leiming
 * @date 2015-11-27
 */

@Service
public class QRCodeUserServiceImpl implements QRCodeUserService{
	private static final Logger log = Logger.getLogger(QRCodeUserService.class);
	
	@Autowired
	private QRCodeUserMapper qrcodeUserMapper;

	/* (non-Javadoc)
	 * @see com.mcoding.emis.goods.common.service.BaseService#addObj(java.io.Serializable)
	 */
	@Override
	public CommonResult<String> addObj(QRCodeUser t) {
		CommonResult<String> result = new CommonResult<String>();
        try {
            if(t.getId()==null){
            	//新增
        		t.setCreatedate(new Date());
            	qrcodeUserMapper.insert(t);
            }else {
            	//修改
            	qrcodeUserMapper.updateByPrimaryKey(t);
            	
			}
            result.setCode(0);
            result.setData("ok");
            result.setMsg("ok");
        } catch (Exception e) {
            log.error("增加失败：", e);
            result.setCode(1);
            result.setData("ok");
            result.setMsg(e.getMessage());
        }
       
		return result;
	}

	/* (non-Javadoc)
	 * @see com.mcoding.emis.goods.common.service.BaseService#deleteObjById(java.lang.String)
	 */
	@Override
	public CommonResult<String> deleteObjById(String id) {
		this.qrcodeUserMapper.deleteByPrimaryKey(Integer.valueOf(id));
		
		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData("ok");
		result.setMsg("ok");
		return result;
	}

	/* (non-Javadoc)
	 * @see com.mcoding.emis.goods.common.service.BaseService#modifyObj(java.io.Serializable)
	 */
	@Override
	public CommonResult<String> modifyObj(QRCodeUser t) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mcoding.emis.goods.common.service.BaseService#queryObjById(java.lang.String)
	 */
	@Override
	public CommonResult<QRCodeUser> queryObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mcoding.emis.goods.common.service.BaseService#queryListObj(java.lang.String[])
	 */
	@Override
	public CommonResult<ArrayList<QRCodeUser>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mcoding.emis.goods.common.service.BaseService#queryObjByPage(int, int)
	 */
	@Override
	public CommonResult<PageView<QRCodeUser>> queryObjByPage(int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mcoding.emis.goods.common.service.BaseService#queryObjByPage(java.lang.String, java.lang.String)
	 */
	@Override
	public PageView<QRCodeUser> queryObjByPage(String iDisplayStart,
			String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public QRCodeUser queryObjByKeywordAndOpendid(String keyword,String openid){
		/*QRCodeUserExample example = new QRCodeUserExample();
		QRCodeUserExample.Criteria criteria= example.createCriteria();
		criteria.andKeywordEqualTo(keyword);
		criteria.andUseropenidEqualTo(openid);*/
		//List<QRCodeUser> qrcodeUsers = qrcodeUserMapper.selectByExample(example);
		//List<QRCodeUser> qrcodeUsers = qrcodeUserMapper.selectByExample(example);
		
		Map<String, Object> param = new HashMap<String, Object>();
        param.put("keyword", keyword);
        param.put("openid", openid);
		List<QRCodeUser> qrcodeUsers = qrcodeUserMapper.queryResultByKeyAndOpenid(param);
		if(qrcodeUsers.size()>0){
			return qrcodeUsers.get(0);
		}else{
			return null;
		}
	}

	@Override
	public int updateByPrimaryKey(QRCodeUser record){
		return qrcodeUserMapper.updateByPrimaryKey(record);
	}

	@Override
	public void deleteObjByOpenId(String useropenid) {
		Map<String, Object> param = new HashMap<String, Object>();
        param.put("useropenid", useropenid);
		qrcodeUserMapper.deleteObjByOpenId(param);
	}

	@Override
	public void updateObjByOpenId(String useropenid,String ext1) {
		Map<String, Object> param = new HashMap<String, Object>();
        param.put("useropenid", useropenid);
        param.put("ext1", ext1);
		qrcodeUserMapper.updateObjByOpenId(param);
		
	}
}
