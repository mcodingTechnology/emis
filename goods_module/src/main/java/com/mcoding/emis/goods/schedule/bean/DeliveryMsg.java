package com.mcoding.emis.goods.schedule.bean;

public class DeliveryMsg {
	public static String getDeliverNameByDeliverCode(String deliveryCode) {
		switch (deliveryCode) {
		case "sf":
			return "顺丰速运";
		case "ems":
			return "EMS";
		case "sto":
			return "申通E物流";
		case "yto":
			return "圆通速递";
		case "zto":
			return "中通速递";
		case "yunda":
			return "韵达快运";
		case "ktky":
			return "汇通快运";
		case "post":
			return "中国邮政平邮";
		case "eyb":
			return "EMS经济快递";
		case "uc":
			return "优速物流";
		case "best":
			return "百世物流";
		case "dbl":
			return "德邦物流";
		case "gto":
			return "国通快递";
		default:
			return "";
		}
	}

}
