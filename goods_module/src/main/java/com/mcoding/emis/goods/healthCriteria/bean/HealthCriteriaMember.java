package com.mcoding.emis.goods.healthCriteria.bean;

import java.io.Serializable;
import java.util.Date;

public class HealthCriteriaMember implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String membername;

	private String mobilephone;

	private Integer level;

	private String score;

	private String determine;

	private String description;

	private String additionaladvice;

	private String healthcriteriaid;

	private String productid;

	private String detailedreport;

	private Date createdate;

	private Date updatedate;

	private String ext1;

	private String ext2;

	private String healthMark1;

	private String healthMark2;

	private String healthMark3;

	private String healthMark4;

	private String healthMark5;

	private String healthMark6;

	private String openid;

	private Integer resultStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMembername() {
		return membername;
	}

	public void setMembername(String membername) {
		this.membername = membername == null ? null : membername.trim();
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone == null ? null : mobilephone.trim();
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score == null ? null : score.trim();
	}

	public String getDetermine() {
		return determine;
	}

	public void setDetermine(String determine) {
		this.determine = determine == null ? null : determine.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public String getAdditionaladvice() {
		return additionaladvice;
	}

	public void setAdditionaladvice(String additionaladvice) {
		this.additionaladvice = additionaladvice == null ? null : additionaladvice.trim();
	}

	public String getHealthcriteriaid() {
		return healthcriteriaid;
	}

	public void setHealthcriteriaid(String healthcriteriaid) {
		this.healthcriteriaid = healthcriteriaid == null ? null : healthcriteriaid.trim();
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid == null ? null : productid.trim();
	}

	public String getDetailedreport() {
		return detailedreport;
	}

	public void setDetailedreport(String detailedreport) {
		this.detailedreport = detailedreport == null ? null : detailedreport.trim();
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1 == null ? null : ext1.trim();
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2 == null ? null : ext2.trim();
	}

	public String getHealthMark1() {
		return healthMark1;
	}

	public void setHealthMark1(String healthMark1) {
		this.healthMark1 = healthMark1 == null ? null : healthMark1.trim();
	}

	public String getHealthMark2() {
		return healthMark2;
	}

	public void setHealthMark2(String healthMark2) {
		this.healthMark2 = healthMark2 == null ? null : healthMark2.trim();
	}

	public String getHealthMark3() {
		return healthMark3;
	}

	public void setHealthMark3(String healthMark3) {
		this.healthMark3 = healthMark3 == null ? null : healthMark3.trim();
	}

	public String getHealthMark4() {
		return healthMark4;
	}

	public void setHealthMark4(String healthMark4) {
		this.healthMark4 = healthMark4 == null ? null : healthMark4.trim();
	}

	public String getHealthMark5() {
		return healthMark5;
	}

	public void setHealthMark5(String healthMark5) {
		this.healthMark5 = healthMark5 == null ? null : healthMark5.trim();
	}

	public String getHealthMark6() {
		return healthMark6;
	}

	public void setHealthMark6(String healthMark6) {
		this.healthMark6 = healthMark6 == null ? null : healthMark6.trim();
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid == null ? null : openid.trim();
	}

	public Integer getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(Integer resultStatus) {
		this.resultStatus = resultStatus;
	}
}