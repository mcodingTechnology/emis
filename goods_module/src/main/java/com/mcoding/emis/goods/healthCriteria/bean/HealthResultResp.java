package com.mcoding.emis.goods.healthCriteria.bean;

import java.io.Serializable;
import java.util.List;

public class HealthResultResp implements Serializable {

	private static final long serialVersionUID = 1L;

	private HealthCriteriaMember result;

	private List<HealthAdviceResult> dietaryAdvs;

	private List<HealthAdviceResult> sportAdvs;

	private List<HealthAdviceResult> healthAdvs;

	private List<HealthRecommendProduct> recommendProducts;

	public HealthCriteriaMember getResult() {
		return result;
	}

	public void setResult(HealthCriteriaMember result) {
		this.result = result;
	}

	public List<HealthAdviceResult> getDietaryAdvs() {
		return dietaryAdvs;
	}

	public void setDietaryAdvs(List<HealthAdviceResult> dietaryAdvs) {
		this.dietaryAdvs = dietaryAdvs;
	}

	public List<HealthAdviceResult> getSportAdvs() {
		return sportAdvs;
	}

	public void setSportAdvs(List<HealthAdviceResult> sportAdvs) {
		this.sportAdvs = sportAdvs;
	}

	public List<HealthAdviceResult> getHealthAdvs() {
		return healthAdvs;
	}

	public void setHealthAdvs(List<HealthAdviceResult> healthAdvs) {
		this.healthAdvs = healthAdvs;
	}

	public List<HealthRecommendProduct> getRecommendProducts() {
		return recommendProducts;
	}

	public void setRecommendProducts(List<HealthRecommendProduct> recommendProducts) {
		this.recommendProducts = recommendProducts;
	}

}
