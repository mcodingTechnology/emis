package com.mcoding.emis.goods.healthCriteria.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.game.service.impl.GameServiceImpl;
import com.mcoding.emis.goods.healthCriteria.bean.HealthCriteria;
import com.mcoding.emis.goods.healthCriteria.bean.HealthCriteriaExample;
import com.mcoding.emis.goods.healthCriteria.persistence.HealthCriteriaMapper;
import com.mcoding.emis.goods.healthCriteria.service.HealthCriteriaService;
import com.mcoding.emis.member.common.CommonResult;

/**
 * 
 * @author Leiming
 *
 */
@Service
public class HealthCriteriaServiceImpl implements HealthCriteriaService {

	private static final Logger log = Logger.getLogger(GameServiceImpl.class);
	public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	protected HealthCriteriaMapper healthCriteriaMapper;

	@Override
	public CommonResult<String> addObj(HealthCriteria t) {
		CommonResult<String> result = new CommonResult<String>();
		try {
			if (t.getId() == null) {
				// 新增
				healthCriteriaMapper.insert(t);
			} else {
				// 修改
				healthCriteriaMapper.updateByPrimaryKey(t);

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
		this.healthCriteriaMapper.deleteByPrimaryKey(Integer.valueOf(id));

		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData("ok");
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<String> modifyObj(HealthCriteria t) {
		this.healthCriteriaMapper.updateByPrimaryKeySelective(t);

		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData("ok");
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<HealthCriteria> queryObjById(String id) {
		HealthCriteria bean = this.healthCriteriaMapper.selectByPrimaryKey(Integer.valueOf(id));

		CommonResult<HealthCriteria> result = new CommonResult<HealthCriteria>();
		result.setCode(0);
		result.setData(bean);
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<ArrayList<HealthCriteria>> getData() {

		HealthCriteriaExample example = new HealthCriteriaExample();
		// HealthCriteriaExample.Criteria cri= example.createCriteria();

		List<HealthCriteria> list = healthCriteriaMapper.selectByExample(example);

		ArrayList<HealthCriteria> tmp = new ArrayList<HealthCriteria>();
		CollectionUtils.addAll(tmp, list.iterator());

		CommonResult<ArrayList<HealthCriteria>> result = new CommonResult<ArrayList<HealthCriteria>>();
		result.setData(tmp);
		result.setCode(0);
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<ArrayList<HealthCriteria>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<HealthCriteria>> queryObjByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<HealthCriteria> queryObjByPage(String iDisplayStart, String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<HealthCriteria> queryHealthCriteriaData(HttpServletRequest request, String iDisplayStart,
			String iDisplayLength) {
		PageView<HealthCriteria> pageView = new PageView<HealthCriteria>(iDisplayStart, iDisplayLength);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pageView", pageView);
		String sSearch = request.getParameter("sSearch");
		param.put("testitem", sSearch);
		List<HealthCriteria> healthCriterias = healthCriteriaMapper.queryHealthCriteriaByPage(param);
		pageView.setQueryResult(healthCriterias);
		return pageView;
	}

	@Override
	public ModelAndView addHealthCriteriaView(String id) {
		ModelAndView mav = new ModelAndView();
		if (StringUtils.isNotBlank(id)) {
			HealthCriteria healthCriteria = queryObjById(id).getData();
			mav.addObject("healthCriteria", healthCriteria);
			mav.addObject("edit", "edit");
		}
		mav.setViewName("healthCriteria/addHealthCriteria");
		return mav;
	}

	@Override
	public List<HealthCriteria> queryResultByTestitem(String testitem) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("testitem", testitem);
		// CommonResult<ArrayList<HealthCriteria>> result = new
		// CommonResult<ArrayList<HealthCriteria>>();
		List<HealthCriteria> healthCriterias = healthCriteriaMapper.queryResultByTestitem(param);
		// result.setData((ArrayList)healthCriterias);
		return healthCriterias;
	}

	@Override
	public List<HealthCriteria> queryHealthCriteriaList() {
		// HealthCriteriaExample healthCriteriaExample = new
		// HealthCriteriaExample();
		List<HealthCriteria> healthCriteriaList = healthCriteriaMapper.selectGroupBy();
		return healthCriteriaList;
	}

	@Override
	public List<HealthCriteria> queryHealthCriteriaByIds(String ids) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ids", ids);
		List<HealthCriteria> healthCriterias = healthCriteriaMapper.queryResultByIds(param);
		return healthCriterias;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public CommonResult<ArrayList<HealthCriteria>> queryHealthCriteriaByIds2(String ids) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ids", ids);
		List<HealthCriteria> healthCriterias = new ArrayList<HealthCriteria>();
		if (ids != null && !"".equals(ids.trim())) {
			healthCriterias = healthCriteriaMapper.queryResultByIds(param);
		}
		CommonResult<ArrayList<HealthCriteria>> result = new CommonResult<ArrayList<HealthCriteria>>();
		result.setData((ArrayList) healthCriterias);
		result.setCode(0);
		result.setMsg("ok");
		return result;
	}

}
