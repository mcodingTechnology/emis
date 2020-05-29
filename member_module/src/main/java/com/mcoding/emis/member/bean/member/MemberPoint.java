package com.mcoding.emis.member.bean.member;

import java.io.Serializable;
import java.util.Date;

import com.mcoding.base.core.PageView;
import com.mcoding.base.ui.bean.dictionary.DicGroupItem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="会员记录表")
public class MemberPoint implements Serializable {
	
//	########### 以下非自动生成，请不要覆盖######################3
	private String productName;//非字段值，产品名称
	private Integer productId;//非字段值，产品ID
	private String productCoverImg;//非字段值，产品封面图片
	private String productIntroduce;//非字段值，产品功能介绍
	private Integer allPoints;//非字段值，全部积分
	private Integer enabledPoints;//非字段值，可以积分
	private Integer allSignnum;//非字段值，连续签到次数总和
	private PageView<MemberPoint> list;//非字段值，积分列表
    private DicGroupItem dicGroupItem;//非字段值，字典项
    private Integer clearPoint ;//非字段值，即将清除的积分
    private Integer facecodepoint ;//非字段值，防伪总积分
    private Integer giftpoint ;//非字段值，消耗总积分

    public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductCoverImg() {
		return productCoverImg;
	}

	public void setProductCoverImg(String productCoverImg) {
		this.productCoverImg = productCoverImg;
	}

	public String getProductIntroduce() {
		return productIntroduce;
	}

	public void setProductIntroduce(String productIntroduce) {
		this.productIntroduce = productIntroduce;
	}

	public Integer getAllPoints() {
		return allPoints;
	}

	public void setAllPoints(Integer allPoints) {
		this.allPoints = allPoints;
	}

	public Integer getEnabledPoints() {
		return enabledPoints;
	}

	public void setEnabledPoints(Integer enabledPoints) {
		this.enabledPoints = enabledPoints;
	}

	public Integer getAllSignnum() {
		return allSignnum;
	}

	public void setAllSignnum(Integer allSignnum) {
		this.allSignnum = allSignnum;
	}

	public PageView<MemberPoint> getList() {
		return list;
	}

	public void setList(PageView<MemberPoint> list) {
		this.list = list;
	}

    public DicGroupItem getDicGroupItem() {
        return dicGroupItem;
    }

    public void setDicGroupItem(DicGroupItem dicGroupItem) {
        this.dicGroupItem = dicGroupItem;
    }
    //	########### end ######################3
	
    @ApiModelProperty("会员积分ID")
    private Integer memberPointId;

    @ApiModelProperty("会员名称")
    private String fullName;

    @ApiModelProperty("手机号码")
    private String mobilePhone;

    @ApiModelProperty("积分类型 A：防伪，B 注册，C 游戏或活动 D 微商城，E 积分商城 F微社区")
    private String pointsType;

    @ApiModelProperty("防伪码")
    private String fakeCheckCode;

    @ApiModelProperty("积分值")
    private Integer points;

    @ApiModelProperty("产品码")
    private String relatedTransactionNo;

    @ApiModelProperty("积分时间")
    private Date addDate;

    @ApiModelProperty("品牌编号")
    private String brandCode;

    @ApiModelProperty("微信OpenId")
    private String openid;

    @ApiModelProperty("经销商代码")
    private String deal;

    private static final long serialVersionUID = 1L;

    public Integer getMemberPointId() {
        return memberPointId;
    }

    public void setMemberPointId(Integer memberPointId) {
        this.memberPointId = memberPointId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPointsType() {
        return pointsType;
    }

    public void setPointsType(String pointsType) {
        this.pointsType = pointsType;
    }

    public String getFakeCheckCode() {
        return fakeCheckCode;
    }

    public void setFakeCheckCode(String fakeCheckCode) {
        this.fakeCheckCode = fakeCheckCode;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getRelatedTransactionNo() {
        return relatedTransactionNo;
    }

    public void setRelatedTransactionNo(String relatedTransactionNo) {
        this.relatedTransactionNo = relatedTransactionNo;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Integer getClearPoint() {
        return clearPoint;
    }

    public void setClearPoint(Integer clearPoint) {
        this.clearPoint = clearPoint;
    }

    public Integer getFacecodepoint() {
        return facecodepoint;
    }

    public void setFacecodepoint(Integer facecodepoint) {
        this.facecodepoint = facecodepoint;
    }

    public Integer getGiftpoint() {
        return giftpoint;
    }

    public void setGiftpoint(Integer giftpoint) {
        this.giftpoint = giftpoint;
    }

    public String getDeal() {
        return deal;
    }

    public void setDeal(String deal) {
        this.deal = deal;
    }
}