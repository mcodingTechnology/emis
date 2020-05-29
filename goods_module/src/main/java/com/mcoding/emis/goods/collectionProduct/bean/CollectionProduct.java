package com.mcoding.emis.goods.collectionProduct.bean;

import java.io.Serializable;
import java.util.Date;

import com.mcoding.emis.goods.product.bean.Product;

public class CollectionProduct implements Serializable{
	private static final long serialVersionUID = 1L;
	
    private Integer id;

    private String openid;

    private Integer memberid;

    private String membername;

    private Integer productid;

    private String productname;

    private Date collecttime;
    
    private Product product;

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

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername == null ? null : membername.trim();
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

    public Date getCollecttime() {
        return collecttime;
    }

    public void setCollecttime(Date collecttime) {
        this.collecttime = collecttime;
    }

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}    
}