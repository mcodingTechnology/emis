package com.mcoding.emis.goods.cart.bean;

import java.io.Serializable;
import java.util.Date;

public class Cart implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final Integer STATUS_VAILD = 0;
	public static final Integer STATUS_UNVAILD = 1;
	
	public static final String WMALL_TYPE = "wMall";
	public static final String POINTMALL_TYPE = "pointMall";

    public static final int GIFTBUYTYPE_POINT = 0;
    public static final int GIFTBUYTYPE_PLUSMONEY = 1;
	
    private Integer cartid;

    private Integer memberid;

    private String openid;

    private Integer productid;

    private String productname;

    private String productintroduce;

    private Integer productnum;

    private Integer productprice;

    private Date createtime;

    private Date updatetime;

    private Integer status; //状态是否删除   0：未删除     1：已删除

    private String productcoverimg;
    
    private String malltype;
    
    //备注 是否参与限购
    private String ext;
    //备注1 限购份数
    private String ext1;

    private Integer plusMoney;//积分加钱购金额

    //积分商城兑换的类型：0积分兑换， 1加钱购兑换
    private Integer giftBuyType;
    
    //起订量
    private Integer minimumQuantity;

    public Integer getCartid() {
        return cartid;
    }

    public void setCartid(Integer cartid) {
        this.cartid = cartid;
    }

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    public String getProductintroduce() {
        return productintroduce;
    }

    public void setProductintroduce(String productintroduce) {
        this.productintroduce = productintroduce == null ? null : productintroduce.trim();
    }

    public Integer getProductnum() {
        return productnum;
    }

    public void setProductnum(Integer productnum) {
        this.productnum = productnum;
    }

    public Integer getProductprice() {
        return productprice;
    }

    public void setProductprice(Integer productprice) {
        this.productprice = productprice;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getProductcoverimg() {
        return productcoverimg;
    }

    public void setProductcoverimg(String productcoverimg) {
        this.productcoverimg = productcoverimg == null ? null : productcoverimg.trim();
    }

	public String getMalltype() {
		return malltype;
	}

	public void setMalltype(String malltype) {
		this.malltype = malltype;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public Integer getPlusMoney() {
		return plusMoney;
	}

	public void setPlusMoney(Integer plusMoney) {
		this.plusMoney = plusMoney;
	}

    public Integer getGiftBuyType() {
        return giftBuyType;
    }

    public void setGiftBuyType(Integer giftBuyType) {
        this.giftBuyType = giftBuyType;
    }

	public Integer getMinimumQuantity() {
		return minimumQuantity;
	}

	public void setMinimumQuantity(Integer minimumQuantity) {
		this.minimumQuantity = minimumQuantity;
	}
    
}