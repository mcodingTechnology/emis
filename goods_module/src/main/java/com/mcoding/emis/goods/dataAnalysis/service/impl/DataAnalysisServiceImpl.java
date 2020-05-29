package com.mcoding.emis.goods.dataAnalysis.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcoding.emis.goods.dataAnalysis.service.DataAnalysisService;
import com.mcoding.emis.goods.order.bean.OrderExample;
import com.mcoding.emis.goods.order.persistence.OrderMapper;
import com.mcoding.emis.member.service.member.MemberService;


/**
 * Created by Benson on 2014-08-01  17:54
 */
@Service
@Transactional
public class DataAnalysisServiceImpl implements DataAnalysisService {

    private static final Logger log = Logger.getLogger(DataAnalysisServiceImpl.class);
    private static final int MAX_CALLCOUNT = 10;

    @Autowired
    private OrderMapper orderMapper;
    
	@Autowired
	private MemberService memberService;
	
    
	@Override
	public ArrayList<Integer> getDataAnalysis() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		try {
			OrderExample orderExample = new OrderExample();
			OrderExample.Criteria criteria = orderExample.createCriteria();
			criteria.andMalltypeEqualTo("wMall");
			// criteria.andExt1EqualTo("Y");   // 20171121  wangzizhan  取消erp是否提交条件
			List<String> strings = new ArrayList<String>();
			strings.add("待发货");
			strings.add("已发货");
			strings.add("已完成");
			strings.add("退换货");
			criteria.andIsPay(strings);  // 20171121  wangzizhan  增加支付状态做统计条件
			int sum1 = orderMapper.countByExample(orderExample);
			list.add(sum1);
			
			OrderExample orderExample2 = new OrderExample();
			OrderExample.Criteria criteria2 = orderExample2.createCriteria();
			criteria2.andMalltypeEqualTo("giftMall");
			criteria.andExt1EqualTo("Y");
			int sum2 = orderMapper.countByExample(orderExample2);
			list.add(sum2);

			OrderExample orderExample3 = new OrderExample();
			OrderExample.Criteria criteria3 = orderExample3.createCriteria();
			criteria3.andMalltypeEqualTo("giftMall");
			criteria3.andOrdertypeEqualTo("giftgameorder");
			criteria.andExt1EqualTo("Y");
			int sum3 = orderMapper.countByExample(orderExample3);
			list.add(sum3);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return list;
	}
	


}

