package com.mcoding.emis.goods.wechat.bean.response;

import java.io.Serializable;

/**
 * 地理消息
 */
public  class MapMessage implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final String LOCATION = "location";
    
    //地理位置纬度 Location_X(地理位置专有)
    private String locationX;
    //地理位置经度 Location_Y(地理位置专有)
    private String locationY;
    // 地图缩放大小  (地理位置专有)
    private String scale;  
    // 地理位置信息  (地理位置专有)
    private String label;
    
	public String getLocationX() {
		return locationX;
	}
	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}
	public String getLocationY() {
		return locationY;
	}
	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}
