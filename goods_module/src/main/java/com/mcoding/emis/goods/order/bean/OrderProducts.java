package com.mcoding.emis.goods.order.bean;

import java.io.Serializable;

public class OrderProducts implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    private Integer id;

    private Integer orderid;

    private Integer productid;

    private String productname;

    private String productno;

    private String producttype;

    private Integer nums;

    private Integer price;
    private Integer originalPrice;

    private String productcoverimg;

    private Integer returnorderid;
    
    private Integer plusMoney;

    //积分商城兑换的类型：0积分兑换， 1加钱购兑换
    private Integer giftBuyType;
    
    // 起订量
    private Integer minimumQuantity = 1;
    
    
    public Integer getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Integer originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getProductno() {
        return productno;
    }

    public void setProductno(String productno) {
        this.productno = productno;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
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

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype == null ? null : producttype.trim();
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getProductcoverimg() {
        return productcoverimg;
    }

    public void setProductcoverimg(String productcoverimg) {
        this.productcoverimg = productcoverimg == null ? null : productcoverimg.trim();
    }

    public Integer getReturnorderid() {
        return returnorderid;
    }

    public void setReturnorderid(Integer returnorderid) {
        this.returnorderid = returnorderid;
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