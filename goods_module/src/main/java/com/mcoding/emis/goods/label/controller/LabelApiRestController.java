package com.mcoding.emis.goods.label.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.label.bean.Label;
import com.mcoding.emis.goods.label.common.LabelType;
import com.mcoding.emis.goods.label.service.LabelService;
import com.mcoding.emis.member.common.CommonResult;

@Controller
public class LabelApiRestController {

	@Resource
	private LabelService labelService;

	@RequestMapping(value = "/labelApi/queryHitWithTypeByPage")
	@ResponseBody
	public PageView<Label> queryHitWithTypeByPage(@RequestParam(required = true) int labelTypeId,
			@RequestParam(required = true) int pageNo, @RequestParam(required = true) int pageSize) {
		LabelType labelType = null;
		LabelType[] labels = LabelType.values();
		for (LabelType label : labels) {
			if (label.getValue() == labelTypeId) {
				labelType = label;
				break;
			}
		}

		int iDisplayStart = (pageNo - 1) * pageSize;
		int iDisplayLength = pageSize;

		return labelService.queryHitWithTypeByPage(labelType, String.valueOf(iDisplayStart),
				String.valueOf(iDisplayLength));
	}

	@RequestMapping(value = "/labelApi/queryLabelsWithMarkByPage")
	@ResponseBody
	public PageView<Label> queryLabelsWithMarkByPage(@RequestParam(required = true) int labelTypeId,
			@RequestParam(required = true) String mark, @RequestParam(required = true) int pageNo,
			@RequestParam(required = true) int pageSize) {
		LabelType labelType = null;
		LabelType[] labels = LabelType.values();
		for (LabelType label : labels) {
			if (label.getValue() == labelTypeId) {
				labelType = label;
				break;
			}
		}

		int iDisplayStart = (pageNo - 1) * pageSize;
		int iDisplayLength = pageSize;

		return labelService.queryLabelsWithMarkByPage(labelType, mark, String.valueOf(iDisplayStart),
				String.valueOf(iDisplayLength));

	}

	@RequestMapping(value = "/labelApi/queryLabelsByMemberId")
	@ResponseBody
	public CommonResult<String> getLabelsByMemberId(@RequestParam(required = true) int labelTypeId,
			@RequestParam(required = true) int memberId) {
		LabelType labelType = null;
		LabelType[] labels = LabelType.values();
		for (LabelType label : labels) {
			if (label.getValue() == labelTypeId) {
				labelType = label;
				break;
			}
		}

		return labelService.queryLabelsByMemberId(labelType, memberId);

	}
}
