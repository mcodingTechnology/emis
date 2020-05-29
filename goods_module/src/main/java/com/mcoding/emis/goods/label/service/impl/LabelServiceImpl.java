package com.mcoding.emis.goods.label.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.label.bean.Label;
import com.mcoding.emis.goods.label.bean.LabelExample;
import com.mcoding.emis.goods.label.bean.LabelExample.Criteria;
import com.mcoding.emis.goods.label.common.LabelType;
import com.mcoding.emis.goods.label.persistence.LabelMapper;
import com.mcoding.emis.goods.label.service.LabelService;
import com.mcoding.emis.member.common.CommonResult;

@Service
public class LabelServiceImpl implements LabelService {

	@Resource
	private LabelMapper labelMapper;

	@Override
	public CommonResult<String> addLabel(Label label) {
		CommonResult<String> commonResult = new CommonResult<>();
		try {
			int code = 0;
			String description = "";

			String mark = label.getMark();
			int correlationId = label.getCorrelationId();
			// 判断内容标签
			if (StringUtils.isEmpty(mark)) {
				code = -1;
				description = "标签内容为空";
				// 判断关联外键ID
			} else if (correlationId == -1) {
				code = -2;
				description = "关联外键ID为空";
			} else {
				labelMapper.insert(label);
				description = "success";
			}
			commonResult.setCode(code);
			commonResult.setMsg(description);
		} catch (Exception e) {
			commonResult.setCode(-1);
			commonResult.setMsg("fail: " + e.getMessage());
			e.printStackTrace();
		}
		return commonResult;
	}

	@Override
	public CommonResult<String> updateLabel(Label label) {
		CommonResult<String> commonResult = new CommonResult<>();
		try {
			labelMapper.updateByPrimaryKey(label);
			commonResult.setCode(0);
			commonResult.setMsg("success");
		} catch (Exception e) {
			commonResult.setCode(-1);
			commonResult.setMsg("fail: " + e.getMessage());
			e.printStackTrace();
		}

		return commonResult;
	}

	@Override
	public CommonResult<String> deleteLabel(int labelId) {
		CommonResult<String> commonResult = new CommonResult<>();
		try {
			labelMapper.deleteByPrimaryKey(labelId);
			commonResult.setCode(0);
			commonResult.setMsg("success");
		} catch (Exception e) {
			commonResult.setCode(-1);
			commonResult.setMsg("fail: " + e.getMessage());
			e.printStackTrace();
		}

		return commonResult;
	}

	@Override
	public CommonResult<Label> selectByLabelId(int labelId) {
		CommonResult<Label> commonResult = new CommonResult<>();
		try {
			Label label = labelMapper.selectByPrimaryKey(labelId);
			commonResult.setCode(0);
			commonResult.setMsg("success");
			commonResult.setData(label);
		} catch (Exception e) {
			commonResult.setCode(-1);
			commonResult.setMsg("fail: " + e.getMessage());
			e.printStackTrace();
		}

		return commonResult;
	}

	@Override
	public CommonResult<ArrayList<Label>> queryLabelByTypeAndCorrelationId(LabelType type, int correlationId) {
		CommonResult<ArrayList<Label>> commonResult = new CommonResult<>();
		try {
			if (type != null) {
				LabelExample example = new LabelExample();
				Criteria criteria = example.createCriteria();
				criteria.andCorrelationIdEqualTo(correlationId);
				criteria.andTypeEqualTo(type.getValue());
				List<Label> labels = labelMapper.selectByExample(example);
				commonResult.setData((ArrayList<Label>) labels);
			}

			commonResult.setCode(0);
			commonResult.setMsg("success");
		} catch (Exception e) {
			commonResult.setCode(-1);
			commonResult.setMsg("fail: " + e.getMessage());
			e.printStackTrace();
		}

		return commonResult;
	}

	@Override
	public PageView<Label> queryHitWithTypeByPage(LabelType type, String iDisplayStart, String iDisplayLength) {
		Map<String, Object> param = new HashMap<>();
		PageView<Label> pageView = new PageView<>(iDisplayStart, iDisplayLength);
		try {
			if (type != null) {
				param.put("pageView", pageView);
				param.put("type", type.getValue());

				List<Label> labels = labelMapper.selectHitWithTypeByPage(param);
				pageView.setQueryResult(labels);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageView;
	}

	@Override
	public CommonResult<String> addOneHitOnLabel(LabelType type, int correlationId) {
		CommonResult<String> commonResult = new CommonResult<>();
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("correlationId", correlationId);
			param.put("type", type.getValue());

			labelMapper.addOneHit(param);
			commonResult.setCode(0);
			commonResult.setMsg("success");
		} catch (Exception e) {
			commonResult.setCode(-1);
			commonResult.setMsg("fail: " + e.getMessage());
			e.printStackTrace();
		}

		return commonResult;
	}

	@Override
	public PageView<Label> queryLabelsWithMarkByPage(LabelType type, String mark, String iDisplayStart,
			String iDisplayLength) {
		Map<String, Object> param = new HashMap<>();
		PageView<Label> pageView = new PageView<>(iDisplayStart, iDisplayLength);
		try {
			if (type != null) {
				param.put("pageView", pageView);
				param.put("type", type.getValue());
				param.put("mark", mark.split(" "));
				System.out.println(new Gson().toJson(mark));
				
				List<Label> labels = labelMapper.selectLabelsWithMarkByPage(param);
				pageView.setQueryResult(labels);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageView;
	}

	@Override
	public CommonResult<String> queryLabelsByMemberId(LabelType type, int memberId) {
		CommonResult<String> commonResult = new CommonResult<>();
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("type", type.getValue());
			param.put("correlationId", memberId);
			ArrayList<String> labels = (ArrayList<String>) labelMapper.selectLabelsByMemberId(param);

			String result = "";
			for (int i = 0; i < labels.size(); i++) {
				if (i + 1 == labels.size()) {
					result += labels.get(i);
				} else {
					result += labels.get(i);
					result += " ";
				}
			}

			commonResult.setCode(0);
			commonResult.setMsg("success");
			commonResult.setData(result);
		} catch (Exception e) {
			commonResult.setCode(-1);
			commonResult.setMsg("fail: " + e.getMessage());
			e.printStackTrace();
		}
		return commonResult;
	}

}
