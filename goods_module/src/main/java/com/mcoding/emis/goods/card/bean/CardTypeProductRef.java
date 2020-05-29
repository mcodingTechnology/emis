package com.mcoding.emis.goods.card.bean;

import java.io.Serializable;

public class CardTypeProductRef implements Serializable{
	private static final long serialVersionUID = 1L;
    private Integer refid;

    private Integer cardtypeid;

    private Integer productid;

    private String productName;

    private Integer productcount;
    
    //产品标签
  	private String productLabel;
  	//产品码
  	private String productCode;
  	//产品货号
  	private String productNo;
    //原价
    private Integer originalPrice;
    //折扣价
    private Integer discountPrice;
    //微商价
    private Integer microMallPrice1;
    //产品封面图片
  	private String productCoverImg;

    public Integer getRefid() {
        return refid;
    }

    public void setRefid(Integer refid) {
        this.refid = refid;
    }

    public Integer getCardtypeid() {
        return cardtypeid;
    }

    public void setCardtypeid(Integer cardtypeid) {
        this.cardtypeid = cardtypeid;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productname) {
        this.productName = productname == null ? null : productname.trim();
    }

    public Integer getProductcount() {
        return productcount;
    }

    public void setProductcount(Integer productcount) {
        this.productcount = productcount;
    }

	public String getProductLabel() {
		return productLabel;
	}

	public void setProductLabel(String productLabel) {
		this.productLabel = productLabel;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public Integer getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Integer originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Integer getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Integer discountPrice) {
		this.discountPrice = discountPrice;
	}

	public Integer getMicroMallPrice1() {
		return microMallPrice1;
	}

	public void setMicroMallPrice1(Integer microMallPrice1) {
		this.microMallPrice1 = microMallPrice1;
	}

	public String getProductCoverImg() {
		return productCoverImg;
	}

	public void setProductCoverImg(String productCoverImg) {
		this.productCoverImg = productCoverImg;
	}
}