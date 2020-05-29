package com.mcoding.emis.goods.orderReturn.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.mcoding.emis.goods.order.bean.Order;
import com.mcoding.emis.goods.order.bean.OrderProducts;

public class OrderReturn implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public static String RETURN_STATUS_FINISH = "finish";

	public static String RETURN_STATUS_APPLY = "apply";
	
    private Integer id;

    private String openid;

    private Integer memberid;

    private String ordertype;

    private String returnstatus;

    private Integer orderid;

    private String returnreason;

    private Date createtime;

    private Integer fee;

    private String ext1;

    private String ext2;
    
    private List<OrderProducts> orderProductsInfo;
    
    private Order order;

    private Date audittime;

    private String ext3;

    private String ext4;

    private String ext5;

    private String ext6;
    
    // 条件条件
    private String status;
    private String telPhone;
    private String outNo;
    
    
    
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public String getOutNo() {
		return outNo;
	}

	public void setOutNo(String outNo) {
		this.outNo = outNo;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

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

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype == null ? null : ordertype.trim();
    }

    public String getReturnstatus() {
        return returnstatus;
    }

    public void setReturnstatus(String returnstatus) {
        this.returnstatus = returnstatus == null ? null : returnstatus.trim();
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getReturnreason() {
        return returnreason;
    }

    public void setReturnreason(String returnreason) {
        this.returnreason = returnreason == null ? null : returnreason.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
    	//����в�Ʒ�б?���Բ�Ʒ�б��Ϊ׼
    	if(fee==0 && this.orderProductsInfo != null){
			this.fee = countFeeForOrderReturn(this.orderProductsInfo);
		}else{
			this.fee = fee;
		}
    	
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

	public List<OrderProducts> getOrderProductsInfo() {
		return orderProductsInfo;
	}

	public void setOrderProductsInfo(List<OrderProducts> orderProductsInfo) {
		this.orderProductsInfo = orderProductsInfo;
		
		if(this.orderProductsInfo != null){
			this.fee = countFeeForOrderReturn(this.orderProductsInfo); 
		}
		
	}
	
	private static int countFeeForOrderReturn(List<OrderProducts> list){
		int fee = 0;
		for(int i=0; i<list.size(); i++){
			if(list.get(i) == null || list.get(i).getPrice()==null){
				continue;
			}
			fee = fee + list.get(i).getPrice();
		}
		return fee;
	}
    
    public Date getAudittime() {
        return audittime;
    }

    public void setAudittime(Date audittime) {
        this.audittime = audittime;
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

    public String getExt6() {
        return ext6;
    }

    public void setExt6(String ext6) {
        this.ext6 = ext6 == null ? null : ext6.trim();
    }
}