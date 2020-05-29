package com.mcoding.emis.goods.product.bean;

import java.io.Serializable;
import java.util.Date;

/**  
* 产品积分表
* @author Benson 
* 2015年3月13日 下午1:04:45  
*/   

public class ProductPoint implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	//积分记录ID
	private Integer pointId;

	//产品编号
	private Integer productId;
	
	//产品名称
	private String productName;
	
	//积分值
	private Integer points;

    //创建时间
    private Date createTime;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            

    //更新时间
    private Date updateTime;

	public Integer getPointId() {
		return pointId;
	}

	public void setPointId(Integer pointId) {
		this.pointId = pointId;
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

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
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
    





    
}