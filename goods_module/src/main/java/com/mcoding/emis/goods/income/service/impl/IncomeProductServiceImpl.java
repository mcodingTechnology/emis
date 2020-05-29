package com.mcoding.emis.goods.income.service.impl;

import java.math.BigDecimal;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.utils.StringHelper;
import com.mcoding.emis.goods.income.bean.ChannelDealer;
import com.mcoding.emis.goods.income.bean.IncomeProduct;
import com.mcoding.emis.goods.income.bean.IncomeProductExample;
import com.mcoding.emis.goods.income.persistence.IncomeProductMapper;
import com.mcoding.emis.goods.income.service.IncomeProductService;
import com.mcoding.emis.member.bean.member.MemberLevel;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberLevelService;

@Service
public class IncomeProductServiceImpl implements IncomeProductService {
	private static final Logger log = Logger.getLogger(IncomeProductServiceImpl.class);
	
	@Autowired
	protected IncomeProductMapper incomeProductMapper;
	
	@Autowired
	protected MemberLevelService memberLevelService;

	CommonResult<IncomeProduct> resultIncomProduct = new CommonResult<IncomeProduct>();
	
	@Override
	public CommonResult<String> deleteObjById(String id) {
		CommonResult<String> result = new CommonResult<String>();
        try {
        	//删除产品
        	incomeProductMapper.deleteByPrimaryKey(Integer.valueOf(id));
            
            result.setCode(0);
            result.setData("ok");
            result.setMsg("ok");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(1);
            result.setData("ok");
            result.setMsg(e.getMessage());
        }
        return result;
	}

	@Override
	public CommonResult<String> modifyObj(IncomeProduct t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<IncomeProduct> queryObjById(String id) {
		try {
			IncomeProduct incomeProduct = incomeProductMapper.selectByPrimaryKey(Integer.parseInt(id));
			resultIncomProduct.setCode(0);
			resultIncomProduct.setData(incomeProduct);
			resultIncomProduct.setMsg("ok");
        } catch (Exception e) {
        	resultIncomProduct.setCode(1);
        	resultIncomProduct.setData(null);
        	resultIncomProduct.setMsg(e.getMessage());
        }
        return resultIncomProduct;
	}
	
	@Override
	public CommonResult<IncomeProduct> getIncomeProductByProductId(Integer productId) {
		try {
			IncomeProductExample ex = new IncomeProductExample();
        	IncomeProductExample.Criteria cri = ex.createCriteria();
        	cri.andProductidEqualTo(productId);
        	IncomeProduct incomeProduct = incomeProductMapper.selectByExample(ex).get(0);
			resultIncomProduct.setCode(0);
			resultIncomProduct.setData(incomeProduct);
			resultIncomProduct.setMsg("ok");
		} catch (Exception e) {
			resultIncomProduct.setCode(1);
			resultIncomProduct.setData(null);
			resultIncomProduct.setMsg(e.getMessage());
		}
		return resultIncomProduct;
	}

	@Override
	public CommonResult<ArrayList<IncomeProduct>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<IncomeProduct>> queryObjByPage(int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<IncomeProduct> queryObjByPage(String iDisplayStart,
			String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getIncomeByProductAndLevel(int productId, int levelId, int channelsId) {
		IncomeProductExample ex = new IncomeProductExample();
		ex.createCriteria().andProductidEqualTo(productId).andChannelsidEqualTo(channelsId);

		List<IncomeProduct> list = this.incomeProductMapper.selectByExample(ex);
		if(list.size() == 0){
			System.out.println("======该产品["+productId+"]没有设置返利=====");
			return 0;
		}else if(list.size() > 1){
			System.out.println("======该产品["+productId+"]设置返利 过多，有["+list.size()+"]个=====");
		}
		
		IncomeProduct incomeProduct = list.get(0);
		MemberLevel level = this.memberLevelService.queryObjById(levelId);
		 
		if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_1)){
			return incomeProduct.getLevel1();
			
		}else if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_2)){  
			return incomeProduct.getLevel2();
			
		}else if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_3)){
			return incomeProduct.getLevel3();
			
		}else if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_4)){
			return incomeProduct.getLevel4();
			
		}else {
			return 0;
		} 
	}

	@Override
	public Integer getPointByProductAndLevel(int productId, int levelId, int channelsId) {
		IncomeProductExample ex = new IncomeProductExample();
		ex.createCriteria().andProductidEqualTo(productId).andChannelsidEqualTo(channelsId);

		List<IncomeProduct> list = this.incomeProductMapper.selectByExample(ex);
		if(list.size() == 0){
			System.out.println("======该产品["+productId+"]没有设置返利=====");
			return 0;
		}else if(list.size() > 1){
			System.out.println("======该产品["+productId+"]设置返利 过多，有["+list.size()+"]个=====");
		}

		IncomeProduct incomeProduct = list.get(0);
		MemberLevel level = this.memberLevelService.queryObjById(levelId);

		if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_1)){
			return incomeProduct.getLevel1point();

		}else if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_2)){
			return incomeProduct.getLevel2point();

		}else if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_3)){
			return incomeProduct.getLevel3point();

		}else if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_4)){
			return incomeProduct.getLevel4point();

		}else {
			return 0;
		}
	}

	@Override
	public Integer getIncomeByOrderAndLevel(int orderId,Integer memberid, int levelId,
			Integer channelsId,Integer parentMemberIdTmp) {
		Map<String, Integer> params = new Hashtable<String, Integer>();
		params.put("orderId", orderId);
		if(channelsId==null){
			channelsId=1;
		}
		params.put("channelsId", channelsId);
		BigDecimal income = null;
		Map<String, Object> result = this.incomeProductMapper.sumIncomeProductProfitForOrder(params);
		if(result==null){
			return 0;
		}
		MemberLevel level = this.memberLevelService.queryObjById(levelId);
		
		if(parentMemberIdTmp.equals(memberid)){
			//每级都可以卖商品给客户，非第四级的佣金=下线佣金的总和
			if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_1)){
				BigDecimal income1 = (BigDecimal) result.get("level1Sum");
				BigDecimal income2 = (BigDecimal) result.get("level2Sum");
				BigDecimal income3 = (BigDecimal) result.get("level3Sum");
				BigDecimal income4 = (BigDecimal) result.get("level4Sum");
				income= income1.add(income2).add(income3).add(income4);
				
			}else if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_2)){

				BigDecimal income2 = (BigDecimal) result.get("level2Sum");
				BigDecimal income3 = (BigDecimal) result.get("level3Sum");
				BigDecimal income4 = (BigDecimal) result.get("level4Sum");
				income= income2.add(income3).add(income4);
				
			}else if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_3)){

				BigDecimal income3 = (BigDecimal) result.get("level3Sum");
				BigDecimal income4 = (BigDecimal) result.get("level4Sum");
				income= income3.add(income4);
				
			}else if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_4)){
				BigDecimal income4 = (BigDecimal) result.get("level4Sum");
				income= income4;
			}
		}else{
			if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_1)){
				income = (BigDecimal) result.get("level1Sum");
				
			}else if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_2)){
				income = (BigDecimal) result.get("level2Sum");
				
			}else if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_3)){
				income = (BigDecimal) result.get("level3Sum");
				
			}else if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_4)){
				System.out.println(result.get("level4Sum"));
				income = (BigDecimal) result.get("level4Sum");
			}
		}
		
		
		if(income != null){
			return income.intValue();
		}else{
			return 0;
		}
	}
	
	@Override
	public Integer getIncomeByOrderAndLevelMemberJoin(int orderId,Integer memberid, int levelId,
			Integer channelsId,Integer parentMemberIdTmp,Integer orderMemberid) {
		Map<String, Integer> params = new Hashtable<String, Integer>();
		params.put("orderId", orderId);
		if(channelsId==null){
			channelsId=1;
		}
		params.put("channelsId", channelsId);
		BigDecimal income = null;
		Map<String, Object> result = this.incomeProductMapper.sumIncomeProductProfitForOrder(params);
		if(result==null){
			return 0;
		}
		MemberLevel level = this.memberLevelService.queryObjById(levelId);
		
		if(orderMemberid.equals(memberid)){
			//每级都可以卖商品给客户，非第四级的佣金=下线佣金的总和
			if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_1)){
				BigDecimal income1 = (BigDecimal) result.get("level1Sum");
				BigDecimal income2 = (BigDecimal) result.get("level2Sum");
				BigDecimal income3 = (BigDecimal) result.get("level3Sum");
				BigDecimal income4 = (BigDecimal) result.get("level4Sum");
				income= income1.add(income2).add(income3).add(income4);
				
			}else if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_2)){

				BigDecimal income2 = (BigDecimal) result.get("level2Sum");
				BigDecimal income3 = (BigDecimal) result.get("level3Sum");
				BigDecimal income4 = (BigDecimal) result.get("level4Sum");
				income= income2.add(income3).add(income4);
				
			}else if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_3)){

				BigDecimal income3 = (BigDecimal) result.get("level3Sum");
				BigDecimal income4 = (BigDecimal) result.get("level4Sum");
				income= income3.add(income4);
				
			}else if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_4)){
				BigDecimal income4 = (BigDecimal) result.get("level4Sum");
				income= income4;
			}
		}else{
			if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_1)){
				income = (BigDecimal) result.get("level1Sum");
				
			}else if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_2)){
				income = (BigDecimal) result.get("level2Sum");
				
			}else if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_3)){
				income = (BigDecimal) result.get("level3Sum");
				
			}else if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_4)){
				System.out.println(result.get("level4Sum"));
				income = (BigDecimal) result.get("level4Sum");
			}
		}
		
		
		if(income != null){
			return income.intValue();
		}else{
			return 0;
		}
	}
	
	@Override
	public CommonResult<String> addObj(IncomeProduct t) {
		CommonResult<String> result = new CommonResult<String>();
        try {
            Date date = new Date();
            //产品列表中根据产品id新增或编辑产品提成收入
            if(t.getProductid()!=null){
            	IncomeProductExample ex = new IncomeProductExample();
            	IncomeProductExample.Criteria cri = ex.createCriteria();
            	cri.andProductidEqualTo(t.getProductid());
            	cri.andChannelsidEqualTo(t.getChannelsid());
            	List<IncomeProduct> list = incomeProductMapper.selectByExample(ex);
            	IncomeProduct incomeProduct = new IncomeProduct();
            	if(list.size()>0){
            		incomeProduct = list.get(0);
            	}
            	t.setId(incomeProduct.getId());
            }
            if(t.getId()==null){
            	//新增产品提成
                t.setCreatetime(date);
                incomeProductMapper.insert(t);
            }else {
            	//修改产品提成
            	t.setCreatetime(date);
            	incomeProductMapper.updateByPrimaryKey(t);
			}
            result.setCode(0);
            result.setData("ok");
            result.setMsg("ok");
        } catch (Exception e) {
        	log.error("失败：", e);
            result.setCode(1);
            result.setData("error");
            result.setMsg(e.getMessage());
        }
        return result;
	}

	@Override
	public PageView<IncomeProduct> queryIncomeProductData(String iDisplayStart,
			String iDisplayLength, String sSearch,HttpServletRequest request) {
		PageView<IncomeProduct> pageView = new PageView<IncomeProduct>(iDisplayStart, iDisplayLength);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pageView", pageView);
        System.out.println(request.getParameter("channelsid"));
        if(StringHelper.isNotBlank(request.getParameter("channelsid"))){
        	param.put("channelsid", request.getParameter("channelsid"));
        }
        if(StringHelper.isNotBlank(sSearch)){
        	param.put("productName", sSearch);
        }
        List<IncomeProduct> incomeProducts = incomeProductMapper.queryListByPage(param);
        pageView.setQueryResult(incomeProducts);
        return pageView;
	}

	@Override
	public CommonResult<IncomeProduct> queryIncomeProductByChannelIdAndProductId(Integer productId, Integer channelsId) {
		CommonResult<IncomeProduct> result = new CommonResult<IncomeProduct>();
        try {
        	 Map<String, Object> param = new HashMap<String, Object>();
    		if(channelsId==null){
    			channelsId= 1;
    		}
    		if(channelsId!=null){
    			param.put("channelsid", channelsId);
            }
            if(productId!=null){
            	param.put("productId", productId);
            }
            List<IncomeProduct> incomeProducts = incomeProductMapper.queryListByPage(param);
            
            
            result.setCode(0);
            IncomeProduct incomeProduct = null; 
            if(incomeProducts.size()>0){
            	incomeProduct = incomeProducts.get(0);
            }
            result.setData(incomeProduct);
            result.setMsg("ok");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(1);
            result.setData(null);
            result.setMsg(e.getMessage());
        }
        return result;
	}

	@Override
	public Integer getPointByOrderAndLevel(int orderId,Integer memberid, int levelId,
											Integer channelsId,Integer parentMemberIdTmp) {
		Map<String, Integer> params = new Hashtable<String, Integer>();
		params.put("orderId", orderId);
		if(channelsId==null){
			channelsId=1;
		}
		params.put("channelsId", channelsId);
		BigDecimal point = null;
		Map<String, Object> result = this.incomeProductMapper.sumIncomeProductPointForOrder(params);
		if(result==null){
			return 0;
		}
		MemberLevel level = this.memberLevelService.queryObjById(levelId);

		if(parentMemberIdTmp.equals(memberid)){
			//每级都可以卖商品给客户，非第四级的佣金=下线佣金的总和
			if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_1)){
				BigDecimal point1 = (BigDecimal) result.get("level1Sum");
				BigDecimal point2 = (BigDecimal) result.get("level2Sum");
				BigDecimal point3 = (BigDecimal) result.get("level3Sum");
				BigDecimal point4 = (BigDecimal) result.get("level4Sum");
				point= point1.add(point2).add(point3).add(point4);

			}else if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_2)){

				BigDecimal point2 = (BigDecimal) result.get("level2Sum");
				BigDecimal point3 = (BigDecimal) result.get("level3Sum");
				BigDecimal point4 = (BigDecimal) result.get("level4Sum");
				point= point2.add(point3).add(point4);

			}else if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_3)){

				BigDecimal point3 = (BigDecimal) result.get("level3Sum");
				BigDecimal point4 = (BigDecimal) result.get("level4Sum");
				point= point3.add(point4);

			}else if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_4)){
				BigDecimal point4 = (BigDecimal) result.get("level4Sum");
				point= point4;
			}
		}else{
			if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_1)){
				point = (BigDecimal) result.get("level1Sum");

			}else if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_2)){
				point = (BigDecimal) result.get("level2Sum");

			}else if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_3)){
				point = (BigDecimal) result.get("level3Sum");

			}else if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_4)){
				System.out.println(result.get("level4Sum"));
				point = (BigDecimal) result.get("level4Sum");
			}
		}


		if(point != null){
			return point.intValue();
		}else{
			return 0;
		}
	}

	@Override
	public Integer getPointByOrderAndLevelMemberJoin(int orderId,Integer memberid, int levelId,
													  Integer channelsId,Integer parentMemberIdTmp,Integer orderMemberid) {
		Map<String, Integer> params = new Hashtable<String, Integer>();
		params.put("orderId", orderId);
		if(channelsId==null){
			channelsId=1;
		}
		params.put("channelsId", channelsId);
		BigDecimal point = null;
		Map<String, Object> result = this.incomeProductMapper.sumIncomeProductPointForOrder(params);
		if(result==null){
			return 0;
		}
		MemberLevel level = this.memberLevelService.queryObjById(levelId);

		if(orderMemberid.equals(memberid)){
			//每级都可以卖商品给客户，非第四级的佣金=下线佣金的总和
			if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_1)){
				BigDecimal point1 = (BigDecimal) result.get("level1Sum");
				BigDecimal point2 = (BigDecimal) result.get("level2Sum");
				BigDecimal point3 = (BigDecimal) result.get("level3Sum");
				BigDecimal point4 = (BigDecimal) result.get("level4Sum");
				point= point1.add(point2).add(point3).add(point4);

			}else if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_2)){

				BigDecimal point2 = (BigDecimal) result.get("level2Sum");
				BigDecimal point3 = (BigDecimal) result.get("level3Sum");
				BigDecimal point4 = (BigDecimal) result.get("level4Sum");
				point= point2.add(point3).add(point4);

			}else if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_3)){

				BigDecimal point3 = (BigDecimal) result.get("level3Sum");
				BigDecimal point4 = (BigDecimal) result.get("level4Sum");
				point= point3.add(point4);

			}else if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_4)){
				BigDecimal point4 = (BigDecimal) result.get("level4Sum");
				point= point4;
			}
		}else{
			if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_1)){
				point = (BigDecimal) result.get("level1Sum");

			}else if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_2)){
				point = (BigDecimal) result.get("level2Sum");

			}else if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_3)){
				point = (BigDecimal) result.get("level3Sum");

			}else if(level.getPriority().equals(MemberLevel.PRIORITY_LEVEL_4)){
				System.out.println(result.get("level4Sum"));
				point = (BigDecimal) result.get("level4Sum");
			}
		}


		if(point != null){
			return point.intValue();
		}else{
			return 0;
		}
	}

	@Override
	public PageView<ChannelDealer> queryChanneldealerListByPage(String iDisplayStart,String iDisplayLength) {
		PageView<ChannelDealer> pageView = new PageView<ChannelDealer>(iDisplayStart, iDisplayLength);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("pageView", pageView);
			List<ChannelDealer>  com = incomeProductMapper.selectUserTagsListById();
		    pageView.setQueryResult(com);
		    return pageView;
		}

}
