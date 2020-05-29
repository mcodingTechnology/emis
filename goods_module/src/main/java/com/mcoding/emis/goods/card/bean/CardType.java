package com.mcoding.emis.goods.card.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mcoding.base.utils.json.CustomDateSerializer;
import com.mcoding.emis.goods.common.utils.jackson.CustomDateTimeDeSerializer;

public class CardType implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final Integer IS_VALID_YES = 1;
	public static final Integer IS_VALID_NO = 0;
	
//	优惠卡类型:团购：GROUPON，代金卷：CASH，折扣券：DISCOUNT，礼品券：GIFT，活动券 ACTIVITY，特权卡 PRIVILEGE
	public static final String CARD_TYPE_CASH = "CASH";
	public static final String CARD_TYPE_GROUPON = "GROUPON";
	public static final String CARD_TYPE_DISCOUNT = "DISCOUNT";
	public static final String CARD_TYPE_GIFT = "GIFT";
	public static final String CARD_TYPE_ACTIVITY = "ACTIVITY";
	public static final String CARD_TYPE_PRIVILEGE = "PRIVILEGE";
	
    private Integer id;

    private String cardid;
    //优惠卡类型:团购：GROUPON，代金券：CASH，折扣券：DISCOUNT，礼品券：GIFT，活动券：ACTIVITY
    private String cardtype;

    private Integer cardquantity;

    private Integer cardcount;
    //是否有效，0无效，1有效
    private Integer isvalid;

    private String code;

    private String encryptCode;

    private String name;
    //效期有类型：固定时间区间TIME_RANGE ，固定时长FIX_TERM
    private String timetype;

    private Date createtime;

    private Date begintime;

    private Date endtime;

    private Integer fixedterm;

    private Date fixedbegintime;

    private Integer leastcost;

    private Integer reducecost;

    private Integer discount;

    private Integer productid;

    private String productname;

    private String defaultDetail;

    private String ext1;

    private String ext2;

    private String ext3;

    private String ext4;

    private String ext5;
    
    private String cardFirstLetter;//非字段值，卡券券码首字母
    private int cardNumberLength;//非字段值，卡券券码数字长度
    
    private List<CardTypeProductRef> productList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid == null ? null : cardid.trim();
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype == null ? null : cardtype.trim();
    }

    public Integer getCardquantity() {
        return cardquantity;
    }

    public void setCardquantity(Integer cardquantity) {
        this.cardquantity = cardquantity;
    }

    public Integer getCardcount() {
        return cardcount;
    }

    public void setCardcount(Integer cardcount) {
        this.cardcount = cardcount;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getEncryptCode() {
        return encryptCode;
    }

    public void setEncryptCode(String encryptCode) {
        this.encryptCode = encryptCode == null ? null : encryptCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTimetype() {
        return timetype;
    }

    public void setTimetype(String timetype) {
        this.timetype = timetype == null ? null : timetype.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @JsonSerialize(using=CustomDateSerializer.class)
    public Date getBegintime() {
        return begintime;
    }

    @JsonDeserialize(using=CustomDateTimeDeSerializer.class)
    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    @JsonSerialize(using=CustomDateSerializer.class)
    public Date getEndtime() {
        return endtime;
    }

    @JsonDeserialize(using=CustomDateTimeDeSerializer.class)
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getFixedterm() {
        return fixedterm;
    }

    public void setFixedterm(Integer fixedterm) {
        this.fixedterm = fixedterm;
    }

    @JsonSerialize(using=CustomDateSerializer.class)
    public Date getFixedbegintime() {
        return fixedbegintime;
    }

    @JsonDeserialize(using=CustomDateTimeDeSerializer.class)
    public void setFixedbegintime(Date fixedbegintime) {
        this.fixedbegintime = fixedbegintime;
    }

    public Integer getLeastcost() {
        return leastcost;
    }

    public void setLeastcost(Integer leastcost) {
        this.leastcost = leastcost;
    }

    public Integer getReducecost() {
        return reducecost;
    }

    public void setReducecost(Integer reducecost) {
        this.reducecost = reducecost;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
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

    public String getDefaultDetail() {
        return defaultDetail;
    }

    public void setDefaultDetail(String defaultDetail) {
        this.defaultDetail = defaultDetail == null ? null : defaultDetail.trim();
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

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }

    public String getExt4() {
        return ext4;
    }

    public void setExt4(String ext4) {
        this.ext4 = ext4 == null ? null : ext4.trim();
    }

    public String getExt5() {
        return ext5;
    }

    public void setExt5(String ext5) {
        this.ext5 = ext5 == null ? null : ext5.trim();
    }

	public List<CardTypeProductRef> getProductList() {
		return productList;
	}

	public void setProductList(List<CardTypeProductRef> productList) {
		this.productList = productList;
	}

	public String getCardFirstLetter() {
		return cardFirstLetter;
	}

	public void setCardFirstLetter(String cardFirstLetter) {
		this.cardFirstLetter = cardFirstLetter;
	}

	public int getCardNumberLength() {
		return cardNumberLength;
	}

	public void setCardNumberLength(int cardNumberLength) {
		this.cardNumberLength = cardNumberLength;
	}
}