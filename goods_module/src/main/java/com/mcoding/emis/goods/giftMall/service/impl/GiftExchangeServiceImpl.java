package com.mcoding.emis.goods.giftMall.service.impl;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.giftMall.bean.GiftExchange;
import com.mcoding.emis.goods.giftMall.persistence.GiftExchangeMapper;
import com.mcoding.emis.goods.giftMall.service.GiftExchangeService;
import com.mcoding.emis.member.common.CommonResult;

@Service
public class GiftExchangeServiceImpl implements GiftExchangeService {
	private static final Logger log = Logger
			.getLogger(GiftExchangeServiceImpl.class);
	
	@Autowired
	private GiftExchangeMapper giftExchangeMapper;

	@Override
	public CommonResult<String> addObj(GiftExchange t) {
		CommonResult<String> result = new CommonResult<String>();
        try {
            Date date = new Date();
            if(t.getId()==null){
            	//新增
                t.setCreatetime(date);
                giftExchangeMapper.insert(t);
            }else {
            	//修改
            	giftExchangeMapper.updateByPrimaryKey(t);
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

	@Override
	public CommonResult<String> deleteObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<String> modifyObj(GiftExchange t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<GiftExchange> queryObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<ArrayList<GiftExchange>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<GiftExchange>> queryObjByPage(int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<GiftExchange> queryObjByPage(String iDisplayStart,
			String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<GiftExchange> queryGiftExchangeData(
			HttpServletRequest request, String iDisplayStart,
			String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}

}
