package com.mcoding.emis.goods.dealer.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.utils.StringHelper;
import com.mcoding.emis.goods.dealer.bean.Dealer;
import com.mcoding.emis.goods.dealer.bean.DealerExample;
import com.mcoding.emis.goods.dealer.persistence.DealerMapper;
import com.mcoding.emis.goods.dealer.service.DealerService;
import com.mcoding.emis.member.common.CommonResult;

@Service
public class DealerServiceImpl implements DealerService {
	private static final Logger log = Logger
			.getLogger(DealerServiceImpl.class);
	
	@Autowired
	private DealerMapper dealerMapper;

	@Override
	public CommonResult<String> addObj(Dealer t) {
		CommonResult<String> result = new CommonResult<String>();
        try {
            Date date = new Date();
            if(t.getId()==null){
            	//新增
                t.setCreatetime(date);
                dealerMapper.insert(t);
            }else {
            	t.setUpdatetime(date);
            	//修改
            	dealerMapper.updateByPrimaryKey(t);
            	
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
	public CommonResult<String> modifyObj(Dealer t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<Dealer> queryObjById(String id) {
		CommonResult<Dealer> commonResult = new CommonResult<Dealer>();
		if(StringHelper.isNotBlank(id)){
			commonResult.setData(dealerMapper.selectByPrimaryKey(Integer.parseInt(id)));
			commonResult.setCode(0);
			commonResult.setMsg("success");
		}
		return commonResult;
	}

	@Override
	public CommonResult<ArrayList<Dealer>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<Dealer>> queryObjByPage(int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<Dealer> queryObjByPage(String iDisplayStart,
			String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<Dealer> queryDealerData(HttpServletRequest request,
			String iDisplayStart, String iDisplayLength) {
		PageView<Dealer> pageView = new PageView<Dealer>(iDisplayStart, iDisplayLength);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pageView", pageView);
        List<Dealer> dealerliset = dealerMapper.queryDealerByPage(param);
        pageView.setQueryResult(dealerliset);
        return pageView;
	}

	@Override
	public CommonResult<ArrayList<Dealer>> getDealerByDealerNo(Dealer dealer) {
		CommonResult<ArrayList<Dealer>> result = new CommonResult<ArrayList<Dealer>>();
		try {
			DealerExample example = new DealerExample();
			DealerExample.Criteria criteria = example.createCriteria();
			criteria.andDealernoEqualTo(dealer.getDealerno());
			List<Dealer> dealer2 = dealerMapper.selectByExample(example);
			result.setData((ArrayList<Dealer>) dealer2);
			result.setCode(0);
			result.setMsg("success");
		} catch (Exception e) {
			e.getStackTrace();
			result.setData(null);
			result.setCode(1);
			result.setMsg("fail");
		}
		
		return result;
	}

}
