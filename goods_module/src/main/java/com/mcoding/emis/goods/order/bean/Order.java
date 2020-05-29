package com.mcoding.emis.goods.order.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	public static final String PAY_STATUS_CREATED = "待支付";
	public static final String PAY_STATUS_PAID = "待发货";
	public static final String PAY_STATUS_SENT = "已发货";
	public static final String PAY_STATUS_FINISHED = "已完成";
	public static final String PAY_STATYS_CANCEL = "已取消";
	public static final String PAY_STATUS_RETURNED = "退换货";
	public static final String PRESENT_STATUS_GIFT_PRESENTED = "待接受";
	public static final String PRESENT_STATUS_GIFT_RECEIVED = "已接受";
	
	public static final String ORDER_STATUS_RETURN = "退换货";
	
	public static final String ORDER_TYPE_PAY = "001"; //购物订单
	public static final String ORDER_TYPE_RETURN = "002"; //退货订单
	
	public static final Integer INCOME_STATUS_AVAILABLE = 0;
	public static final Integer INCOME_STATUS_UNAVAILABLE = 1;
	
	public static final Integer LIMIT_DAYS_FOR_RETURN = 7; //退货的有效期
	
	public static final String ORDER_TYPE_KILL = "killgameorder";
	public static final String ORDER_TYPE_PLUS = "plusgo";//积分加钱购
	
    private Integer id;

    private Integer memberid;

    private String mobilephone;
    private String fullName;

    /**会员Openid**/
    private String openid;
    /**订单总额**/
    private Integer fee;
    /**订单总数量**/
    private Integer nums;
    /**收货地址ID**/
    private Integer addressid;
    /**订单支付类型**/
    private String paytype;
    /**订单状态 支付状态 0.未支付 ,1.已支付,2.已完成,3.已退款, 4.取消支付**/
    private String paystatus;
    /**订单品牌**/
    private String orderbrand;
    /**订单支付平台来源：支付宝支付，微信支付**/
    private String orderpayresource;
    /**订单添加时间**/
    private Date addtime;
    /**订单支付时间**/
    private Date paytime;
    private Date sendtime;
    private Date receivetime;
    private Date returntime;
    /**第三方交易号（用于区分多门店订单支付）**/
    private String thirdno;
    /**交易流水号**/
    private String tradeno;
    /**站内交易号**/
    private String outno;
    /**是否已打印小票 yes or NULL**/
    private String isprint;
    /**赠送状态**/
    private String presentstatus;
    /**订单类型**/
    private String ordertype;

    private String membername;

    private Integer incomestatus;

    private String returnstatus;

    private Integer cardid;

    private String cardcode;

    private String cardtypename;
    /***减免金额**/
    private Integer feereduce;
    /***运费**/
    private Integer freight;

    private String deliveryname;

    private String deliveryorderno;


    private List<String> productImg;

    private String address;

    private String regson;

    private String receiver;

    private String receiverphone;

    //是否已post到ERP系统 ，"N":未post；"Y":已post
    private String ext1;

    private String ext2;

    //是否需要推送购物消息 ，"N":待推送；"Y":无需推送
    private String ext3;

    //是否需要推送商品秘籍消息 ，"N":待推送；"Y":无需推送
    private String issendtip;
    
    private Integer plusmoney;
    //订单优惠总金额
    private Integer preferentialprice;
    
    //商城类型：微商城 wMall，积分商城 giftMall
    private String malltype;
    private Integer resultid;//非字段值，抽奖领取记录ID
    private Integer totalfee;//非字段值，所有订单总额
    private Integer sumFee;//订单原价总额

    
    public Integer getSumFee() {
		return sumFee;
	}

	public void setSumFee(Integer sumFee) {
		this.sumFee = sumFee;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone == null ? null : mobilephone.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Integer getAddressid() {
        return addressid;
    }

    public void setAddressid(Integer addressid) {
        this.addressid = addressid;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype == null ? null : paytype.trim();
    }

    public String getPaystatus() {
        return paystatus;
    }

    public void setPaystatus(String paystatus) {
        this.paystatus = paystatus == null ? null : paystatus.trim();
    }

    public String getOrderbrand() {
        return orderbrand;
    }

    public void setOrderbrand(String orderbrand) {
        this.orderbrand = orderbrand == null ? null : orderbrand.trim();
    }

    public String getOrderpayresource() {
        return orderpayresource;
    }

    public void setOrderpayresource(String orderpayresource) {
        this.orderpayresource = orderpayresource == null ? null : orderpayresource.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public Date getReceivetime() {
        return receivetime;
    }

    public void setReceivetime(Date receivetime) {
        this.receivetime = receivetime;
    }

    public Date getReturntime() {
        return returntime;
    }

    public void setReturntime(Date returntime) {
        this.returntime = returntime;
    }

    public String getThirdno() {
        return thirdno;
    }

    public void setThirdno(String thirdno) {
        this.thirdno = thirdno == null ? null : thirdno.trim();
    }

    public String getTradeno() {
        return tradeno;
    }

    public void setTradeno(String tradeno) {
        this.tradeno = tradeno == null ? null : tradeno.trim();
    }

    public String getOutno() {
        return outno;
    }

    public void setOutno(String outno) {
        this.outno = outno == null ? null : outno.trim();
    }

    public String getIsprint() {
        return isprint;
    }

    public void setIsprint(String isprint) {
        this.isprint = isprint == null ? null : isprint.trim();
    }

    public String getPresentstatus() {
        return presentstatus;
    }

    public void setPresentstatus(String presentstatus) {
        this.presentstatus = presentstatus == null ? null : presentstatus.trim();
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype == null ? null : ordertype.trim();
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername == null ? null : membername.trim();
    }

    public Integer getIncomestatus() {
        return incomestatus;
    }

    public void setIncomestatus(Integer incomestatus) {
        this.incomestatus = incomestatus;
    }

    public String getReturnstatus() {
        return returnstatus;
    }

    public void setReturnstatus(String returnstatus) {
        this.returnstatus = returnstatus == null ? null : returnstatus.trim();
    }

    public Integer getCardid() {
        return cardid;
    }

    public void setCardid(Integer cardid) {
        this.cardid = cardid;
    }

    public String getCardcode() {
        return cardcode;
    }

    public void setCardcode(String cardcode) {
        this.cardcode = cardcode == null ? null : cardcode.trim();
    }

    public String getCardtypename() {
        return cardtypename;
    }

    public void setCardtypename(String cardtypename) {
        this.cardtypename = cardtypename == null ? null : cardtypename.trim();
    }

    public Integer getFeereduce() {
        return feereduce;
    }

    public void setFeereduce(Integer feereduce) {
        this.feereduce = feereduce;
    }

    public Integer getFreight() {
        return freight;
    }

    public void setFreight(Integer freight) {
        this.freight = freight;
    }

    public String getDeliveryname() {
        return deliveryname;
    }

    public void setDeliveryname(String deliveryname) {
        this.deliveryname = deliveryname == null ? null : deliveryname.trim();
    }

    public String getDeliveryorderno() {
        return deliveryorderno;
    }

    public void setDeliveryorderno(String deliveryorderno) {
        this.deliveryorderno = deliveryorderno == null ? null : deliveryorderno.trim();
    }
    
    public List<String> getProductImg() {
		return productImg;
	}

	public void setProductImg(List<String> productImg) {
		this.productImg = productImg;
	}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getRegson() {
        return regson;
    }

    public void setRegson(String regson) {
        this.regson = regson == null ? null : regson.trim();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getReceiverphone() {
        return receiverphone;
    }

    public void setReceiverphone(String receiverphone) {
        this.receiverphone = receiverphone == null ? null : receiverphone.trim();
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

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }

	public String getMalltype() {
		return malltype;
	}

	public void setMalltype(String malltype) {
		this.malltype = malltype;
	}

	public Integer getResultid() {
		return resultid;
	}

	public void setResultid(Integer resultid) {
		this.resultid = resultid;
	}

	public Integer getTotalfee() {
		return totalfee;
	}

	public void setTotalfee(Integer totalfee) {
		this.totalfee = totalfee;
	}

    public Integer getPlusmoney() {
        return plusmoney;
    }

    public void setPlusmoney(Integer plusmoney) {
        this.plusmoney = plusmoney;
    }

    public Integer getPreferentialprice() {
		return preferentialprice;
	}

	public void setPreferentialprice(Integer preferentialprice) {
		this.preferentialprice = preferentialprice;
	}

    public String getIssendtip() {
        return issendtip;
    }

    public void setIssendtip(String issendtip) {
        this.issendtip = issendtip;
    }

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
    
    
}