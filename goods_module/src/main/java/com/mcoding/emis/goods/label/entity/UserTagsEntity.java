package com.mcoding.emis.goods.label.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;


@ApiModel(value="贴标实体")
public class UserTagsEntity implements Serializable{

	/**
	 *    
	 */
	private static final long serialVersionUID = 1L;

	
	  public Integer id; //主键
	  
	  public String openid; //会员id
	  
	  public Integer tagid; //标签id
	  
	  public Date createtime; //创建时间
	  
	  public Integer objId; //关联Id
	  
	  public String memberPhoneNotNull;// 手机号码不能为空

	  public String memberOpenIdNotNull;// openid不能为空
	  
	  public List<String> tagids = new ArrayList<String>();
	  
	  
	  
	  
	public List<String> getTagids() {
		return tagids;
	}

	public void setTagids(List<String> tagids) {
		this.tagids = tagids;
	}

		public String getMemberPhoneNotNull() {
		return memberPhoneNotNull;
	}

	public void setMemberPhoneNotNull(String memberPhoneNotNull) {
		this.memberPhoneNotNull = memberPhoneNotNull;
	}

	public String getMemberOpenIdNotNull() {
		return memberOpenIdNotNull;
	}

	public void setMemberOpenIdNotNull(String memberOpenIdNotNull) {
		this.memberOpenIdNotNull = memberOpenIdNotNull;
	}

		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
	
		public String getOpenid() {
			return openid;
		}
	
		public void setOpenid(String openid) {
			this.openid = openid;
		}
	
		public Integer getTagid() {
			return tagid;
		}
	
		public void setTagid(Integer tagid) {
			this.tagid = tagid;
		}
	
		public Date getCreatetime() {
			return createtime;
		}

		public void setCreatetime(Date createtime) {
			this.createtime = createtime;
		}

		public Integer getObjId() {
			return objId;
		}

		public void setObjId(Integer objId) {
			this.objId = objId;
		}
		  
		  
		
	
	
}
