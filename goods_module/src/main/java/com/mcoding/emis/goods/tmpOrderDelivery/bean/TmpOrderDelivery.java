package com.mcoding.emis.goods.tmpOrderDelivery.bean;

import java.io.Serializable;

public class TmpOrderDelivery  implements Serializable{
	private static final long serialVersionUID = 1L;
    private String orderno;

    private String deliveryorderno;

    private String deliveryname;

    private Integer isupdate;

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public String getDeliveryorderno() {
        return deliveryorderno;
    }

    public void setDeliveryorderno(String deliveryorderno) {
        this.deliveryorderno = deliveryorderno == null ? null : deliveryorderno.trim();
    }

    public String getDeliveryname() {
        return deliveryname;
    }

    public void setDeliveryname(String deliveryname) {
        this.deliveryname = deliveryname == null ? null : deliveryname.trim();
    }

    public Integer getIsupdate() {
        return isupdate;
    }

    public void setIsupdate(Integer isupdate) {
        this.isupdate = isupdate;
    }
}