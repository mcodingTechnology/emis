package com.mcoding.emis.goods.sign.bean;

import java.io.Serializable;
import java.util.Date;

public class SigninStatistics  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	  private Integer id;

	    private String openid;
	    //会员名称
	    private String membername;
	    //会员ID
	    private Integer memberid;
	    //签到次数
	    private Integer signnum;
	    //签到分数
	    private Integer signintegralsum;
	    //创建日期
	    private Date createtime;
	    //更新日期
	    private Date updatetime;
	    //品牌
	    private String brandcode;
	    
	    private Integer allSignintegralsum;//非字段值，所有积分值总和
	    private Integer allSignnum;//非字段值，连续签到次数总和
	    private Integer signIntegral;//非字段值，本次签到积分

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
	        this.openid = openid == null ? null : openid.trim();
	    }

	    public String getMembername() {
	        return membername;
	    }

	    public void setMembername(String membername) {
	        this.membername = membername == null ? null : membername.trim();
	    }

	    public Integer getMemberid() {
	        return memberid;
	    }

	    public void setMemberid(Integer memberid) {
	        this.memberid = memberid;
	    }

	    public Integer getSignnum() {
	        return signnum;
	    }

	    public void setSignnum(Integer signnum) {
	        this.signnum = signnum;
	    }

	    public Integer getSignintegralsum() {
	        return signintegralsum;
	    }

	    public void setSignintegralsum(Integer signintegralsum) {
	        this.signintegralsum = signintegralsum;
	    }

	    public Date getCreatetime() {
	        return createtime;
	    }

	    public void setCreatetime(Date createtime) {
	        this.createtime = createtime;
	    }

	    public Date getUpdatetime() {
	        return updatetime;
	    }

	    public void setUpdatetime(Date updatetime) {
	        this.updatetime = updatetime;
	    }

	    public String getBrandcode() {
	        return brandcode;
	    }

	    public void setBrandcode(String brandcode) {
	        this.brandcode = brandcode == null ? null : brandcode.trim();
	    }

		public Integer getAllSignintegralsum() {
			return allSignintegralsum;
		}

		public void setAllSignintegralsum(Integer allSignintegralsum) {
			this.allSignintegralsum = allSignintegralsum;
		}

		public Integer getAllSignnum() {
			return allSignnum;
		}

		public void setAllSignnum(Integer allSignnum) {
			this.allSignnum = allSignnum;
		}

	public Integer getSignIntegral() {
		return signIntegral;
	}

	public void setSignIntegral(Integer signIntegral) {
		this.signIntegral = signIntegral;
	}
}