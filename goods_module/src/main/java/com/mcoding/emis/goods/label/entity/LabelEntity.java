package com.mcoding.emis.goods.label.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;

@ApiModel(value="标签实体")
public class LabelEntity implements Serializable{
	
   /**
	 *   
	 */
	private static final long serialVersionUID = 1L;

   
	  
   public Integer tagsId; //标签ID
   
   public String tagsName; //标签名称
   
   public String tagsServicetype; //业务类型 (暂时没用到)
   
   public String tagsState; //状态 
   
   public Date createTime;   //创建时间
 
   public Date lastUpdateTime; //最后修改时间
   
	public Integer getTagsId() {
		return tagsId;
	}
	
	public void setTagsId(Integer tagsId) {
		this.tagsId = tagsId;
	}
	
	public String getTagsName() {
		return tagsName;
	}
	
	public void setTagsName(String tagsName) {
		this.tagsName = tagsName;
	}
	
	public String getTagsServicetype() {
		return tagsServicetype;
	}
	
	public void setTagsServicetype(String tagsServicetype) {
		this.tagsServicetype = tagsServicetype;
	}
	
	public String getTagsState() {
		return tagsState;
	}
	
	public void setTagsState(String tagsState) {
		this.tagsState = tagsState;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	

	   
	   
   
   
   
}
