package com.mcoding.emis.goods.label.common;

public enum LabelType {

	EMPTY(-1, "不存在"), Aticle_DT(1, "数据价值网"), Aticle_MRMJ(2, "极智构"), Article_JLD(3, "BIG生活"), Article_NAIL(4,
			"印奈儿"), Investigation(5, "个性化自测调查"), Article_NM(6, "牛么");

	private int value;

	private String desc;

	private LabelType(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}
}
