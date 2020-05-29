package com.mcoding.emis.goods.label.service;

import java.util.ArrayList;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.label.bean.Label;
import com.mcoding.emis.goods.label.common.LabelType;
import com.mcoding.emis.member.common.CommonResult;

public interface LabelService {

	// 新增标签
	public CommonResult<String> addLabel(Label label);

	// 更新标签
	public CommonResult<String> updateLabel(Label label);

	// 删除标签
	public CommonResult<String> deleteLabel(int labelId);

	// 获取标签
	public CommonResult<Label> selectByLabelId(int labelId);

	// 根据标签类型和相关表Id查询标签列表
	public CommonResult<ArrayList<Label>> queryLabelByTypeAndCorrelationId(LabelType type, int correlationId);

	// 根据标签类型和标签内容分页查询
	public PageView<Label> queryLabelsWithMarkByPage(LabelType type, String mark, String iDisplayStart,
			String iDisplayLength);

	// 根据标签类型，分页查询热门标签
	public PageView<Label> queryHitWithTypeByPage(LabelType type, String iDisplayStart, String iDisplayLength);

	// 根据标签类型和相关表ID，相关ID的标签热度频率加1
	public CommonResult<String> addOneHitOnLabel(LabelType type, int correlationId);

	// 根据会员Id获取标签
	public CommonResult<String> queryLabelsByMemberId(LabelType type, int memberId);

}
