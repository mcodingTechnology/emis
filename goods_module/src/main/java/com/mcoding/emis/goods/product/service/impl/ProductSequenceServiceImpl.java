package com.mcoding.emis.goods.product.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.product.bean.ProductSequence;
import com.mcoding.emis.goods.product.persistence.ProductSequenceMapper;
import com.mcoding.emis.goods.product.service.ProductSequenceService;
import com.mcoding.emis.member.common.CommonResult;

@Service
public class ProductSequenceServiceImpl implements ProductSequenceService {

	@Autowired
	private DefaultTransactionDefinition def;
	@Autowired
	private PlatformTransactionManager transactionManager;
	@Autowired
	private ProductSequenceMapper productSequenceMapper;

	private Logger log = Logger.getLogger(ProductSequenceServiceImpl.class);

	@Override
	public CommonResult<HashMap<String, Object>> getProSeqListByProductId(
			Integer productId) {
		CommonResult<HashMap<String, Object>> result = new CommonResult<HashMap<String, Object>>();
		try {
			List<ProductSequence> list = productSequenceMapper
					.getProSeqListByProductId(productId);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("list", list);
			result.setData(map);
			result.setMsg("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(1);
			result.setData(null);
			result.setMsg("处理出错");
		}
		return result;
	}

	@Override
	public CommonResult<String> addObj(ProductSequence t) {
		CommonResult<String> result = new CommonResult<String>();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			Date date = new Date();
			if (t.getId() == null) {
				t.setCreateTime(date);
				t.setUpdateTime(date);
				productSequenceMapper.insertSelective(t);
			} else {
				// t.setUpdateTime(date);
				// productSequenceMapper.updateByPrimaryKeySelective(t);
			}

			transactionManager.commit(status);
			result.setCode(0);
			result.setData("ok");
			result.setMsg("ok");
		} catch (Exception e) {
			transactionManager.rollback(status);
			log.error("操作失败：", e);
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
	public CommonResult<String> modifyObj(ProductSequence t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<ProductSequence> queryObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<ArrayList<ProductSequence>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<ProductSequence>> queryObjByPage(int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<ProductSequence> queryObjByPage(String iDisplayStart,
			String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}

}
