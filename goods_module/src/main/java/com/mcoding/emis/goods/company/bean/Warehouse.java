package com.mcoding.emis.goods.company.bean;

import java.io.Serializable;
import java.util.Date;

public class Warehouse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String warehouseNumber;

	private String warehouseName;

	private String deliveryArea;
	
	private String warehouseAddress;
	
	private String status;
	
	private Date createTiem;
	
	private Date lastUpdateTime;

	
	
	public String getWarehouseAddress() {
		return warehouseAddress;
	}

	public void setWarehouseAddress(String warehouseAddress) {
		this.warehouseAddress = warehouseAddress;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWarehouseNumber() {
		return warehouseNumber;
	}

	public void setWarehouseNumber(String warehouseNumber) {
		this.warehouseNumber = warehouseNumber;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getDeliveryArea() {
		return deliveryArea;
	}

	public void setDeliveryArea(String deliveryArea) {
		this.deliveryArea = deliveryArea;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTiem() {
		return createTiem;
	}

	public void setCreateTiem(Date createTiem) {
		this.createTiem = createTiem;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
}