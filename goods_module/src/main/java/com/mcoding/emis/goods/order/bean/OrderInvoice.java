package com.mcoding.emis.goods.order.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="mr_order_invoice")
public class OrderInvoice implements Serializable {
    private Integer id;

    @ApiModelProperty("订单号")
    private Integer orderId;

    private String openId;

    @ApiModelProperty("是否需要快递发票 0不需要 1需要")
    private Integer invoiceIsSend;

    @ApiModelProperty("发票抬头")
    private String invoiceTitle;

    @ApiModelProperty("纳税人识别号（专用发票需填写）")
    private String invoiceIdentifyNo;

    @ApiModelProperty("姓名")
    private String addressName;

    @ApiModelProperty("手机号码")
    private String addressPhone;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Integer getInvoiceIsSend() {
        return invoiceIsSend;
    }

    public void setInvoiceIsSend(Integer invoiceIsSend) {
        this.invoiceIsSend = invoiceIsSend;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle == null ? null : invoiceTitle.trim();
    }

    public String getInvoiceIdentifyNo() {
        return invoiceIdentifyNo;
    }

    public void setInvoiceIdentifyNo(String invoiceIdentifyNo) {
        this.invoiceIdentifyNo = invoiceIdentifyNo == null ? null : invoiceIdentifyNo.trim();
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName == null ? null : addressName.trim();
    }

    public String getAddressPhone() {
        return addressPhone;
    }

    public void setAddressPhone(String addressPhone) {
        this.addressPhone = addressPhone == null ? null : addressPhone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}