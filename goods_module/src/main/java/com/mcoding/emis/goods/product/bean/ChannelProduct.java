package com.mcoding.emis.goods.product.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 渠道分销产品表
 * @author wzz
 *
 */
@ApiModel(value="渠道分销产品表")
public class ChannelProduct implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1496198029290417744L;
	
	@ApiModelProperty("主键id")
	private Integer id;
	
	@ApiModelProperty("渠道商id")
	private Integer channelId;
	
	@ApiModelProperty("渠道商名称")
	private String dealerName;
	
	@ApiModelProperty("产品id")
	private Integer productId;
	
	@ApiModelProperty("产品名称")
	private String productName;
	
	@ApiModelProperty("产品库存")
	private Integer productInventory;
	
	@ApiModelProperty("创建时间")
	private Date createTime;
	
	@ApiModelProperty("最后修改时间")
	private Date lastUpdateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getProductInventory() {
		return productInventory;
	}

	public void setProductInventory(Integer productInventory) {
		this.productInventory = productInventory;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
}