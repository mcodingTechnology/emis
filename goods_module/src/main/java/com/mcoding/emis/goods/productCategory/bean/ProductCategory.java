package com.mcoding.emis.goods.productCategory.bean;

import java.io.Serializable;
import java.util.Date;

public class ProductCategory implements Serializable{
	private static final long serialVersionUID = 1L;
    private Integer id;

    //类目id
    private Integer categoryId;

    //产品id
    private Integer productId;
    
    //产品名称
    private String productName;
    
    //排序
    private Integer sort;

    private Date createTime;

    private Date updateTime;
    
    private String categoryName;//非字段值，类目名称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}