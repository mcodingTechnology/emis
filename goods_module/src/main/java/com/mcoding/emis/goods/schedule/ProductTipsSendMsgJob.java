package com.mcoding.emis.goods.schedule;

import com.mcoding.emis.goods.common.utils.DateHelper;
import com.mcoding.emis.goods.common.utils.DateUtil;
import com.mcoding.emis.goods.order.bean.Order;
import com.mcoding.emis.goods.order.bean.OrderExample;
import com.mcoding.emis.goods.order.bean.OrderProducts;
import com.mcoding.emis.goods.order.bean.OrderProductsExample;
import com.mcoding.emis.goods.order.persistence.OrderMapper;
import com.mcoding.emis.goods.order.persistence.OrderProductsMapper;
import com.mcoding.emis.goods.product.bean.Product;
import com.mcoding.emis.goods.product.service.ProductService;
import com.mcoding.emis.goods.schedule.bean.GetStoreDomain;
import com.mcoding.emis.goods.wechat.bean.TemplateMessageRecord;
import com.mcoding.emis.goods.wechat.utils.WxMpTemplateMsgUtil;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.service.member.MemberService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 首次购买BIG生活 某些特定商品推送商品秘籍信息 job
 * 特定商品包括：乳清蛋白，重机能，正肌能，酪蛋白，左旋肉碱
 * @author Benson
 *
 */
@Service("com.mcoding.emis.goods.schedule.ProductTipsSendMsgJob")
public class ProductTipsSendMsgJob {
	
	@Autowired
	protected MemberService memberService;
	@Autowired
	protected ProductService productService;
	@Autowired
	protected OrderMapper orderMapper;
	@Autowired
	protected OrderProductsMapper orderProductMapper;
	@Autowired
	private WxMpTemplateMsgUtil wxMpTemplateMsgUtil;
	
	
	public void execute() throws Exception {
		abc();
		return;
	}
	
	public void abc() throws Exception {
		System.out.println("=======开始运行首次购买BIG生活商品推送秘籍信息 job ProductTipsSendMsgJob==========");

		//1.查询已发货，且待发送特定商品消息的订单
		OrderExample example = new OrderExample();
		OrderExample.Criteria cri = example.createCriteria();
		cri.andPaystatusEqualTo("已发货");
		cri.andIssendtipEqualTo("N");
		cri.andPaytimeGreaterThanOrEqualTo(DateUtil.StrFormatDate("2016-09-27"));
		cri.andMalltypeEqualTo("gMall");

		OrderExample.Criteria cri2 = example.createCriteria();
		cri2.andPaystatusEqualTo("已发货");
		cri2.andIssendtipEqualTo("N");
		cri2.andPaytimeGreaterThanOrEqualTo(DateUtil.StrFormatDate("2016-09-27"));
		cri2.andMalltypeEqualTo("giftMall_gmx");
		example.or(cri2);

		Date date = new Date();
		List<Order> list = orderMapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(list)){
			for (Order order: list) {
				//查询订单中是否有某些特定商品
				List<Product> productList = productService.getProductsByOrderId(order.getId());

				if(CollectionUtils.isNotEmpty(list)){
					//查询订单是否达到发货后第5天的条件
					Date sendtime = order.getSendtime();
					if(sendtime!=null){
						//两个日期相减计算相差天数
						int day = DateHelper.daysBetween(sendtime, date);

						if(day == 5){
							System.out.println("=============发货后第5天 自动推送消息==============");
							order.setIssendtip("Y");
							orderMapper.updateByPrimaryKeySelective(order);

							for (Product product: productList) {
								HashMap<String, String> map = this.getMsgContentByProduct(product);
								String first = map.get("first");
								String remark = map.get("remark");
								//推送消息给用户
								wxMpTemplateMsgUtil.sendWxMpTemplateMessageType(TemplateMessageRecord.TEMPLATE_MESSAGE_TYPE_PRODUCT_TIPS, order.getOpenid(), first,
										order.getOutno() , product.getProductName(), null, null, null,remark,null,null);
							}
						}
					}
				}else{
					System.out.println("=========订单中不包含特定商品==========");
				}

			}
		}

	}

	//特定商品发送模板
	private HashMap<String, String> getMsgContentByProduct(Product product){
		HashMap<String, String> map = new HashMap<String, String>();
		String msg = null;
		String first = null;
		//乳清蛋白
		if(product.getProductId() == 39 || product.getProductId() == 632){
			first = "非常感谢您的支持，特意奉上《乳清蛋白增肌秘籍》";
			msg = "第一招，锻炼前半小时内喝1-2勺，锻炼后3勺，第二招，非训练日加餐或者睡前喝2勺。终极杀招，对增肌效果不满意时，可以增量到3勺。习得秘籍精髓，打通肌肉任督二脉，展现肌肉型男风采。";
		}
		//重肌能
		if(product.getProductId() == 25 || product.getProductId() == 26
				|| product.getProductId() == 633 || product.getProductId() == 634 ){
			first = "非常感谢您的支持，特意奉上《重肌能增重秘籍》";
			msg = "第一招，锻炼后半小时内喝2-3勺（可是当增减），第二招，非训练日加餐或者睡前喝一勺。终极杀招，对增重效果不满意时，可以增量到4勺。习得秘籍精髓，摆脱弱不禁风，成为一代肌肉大侠不是梦。";
		}

		//正肌能
		if(product.getProductId() == 23 || product.getProductId() == 24){
			first = "非常感谢您的支持，特意奉上《正肌能增肌秘籍》";
			msg = "第一招，锻炼后半小时内喝2勺（可是当增减），第二招，非训练日加餐或者睡前喝一勺。终极杀招，对增肌效果不满意时，可以增量到3勺。习得秘籍精髓，打通肌肉任督二脉，展现肌肉型男风采。";
		}

		//酪蛋白
		if(product.getProductId() == 483){
			first = "非常感谢您的支持，特意奉上《酪蛋白增肌秘籍》";
			msg = "第一招，锻炼后半小时内喝2勺，第二招，非训练日加餐或者睡前喝2勺。终极杀招，对增肌效果不满意时，训练日睡前喝1-2勺。习得秘籍精髓，打通肌肉任督二脉，展现肌肉型男风采。";
		}

		//左旋肉碱
		if(product.getProductId() == 40 || product.getProductId() == 461){
			first = "非常感谢您的支持，特意奉上《左旋肉碱减脂秘籍》";
			msg = "第一招，锻炼前1-2小时吃4粒，第二招，非训练日早午餐前半小时各2粒。终极杀招，对减肥减脂效果不满意时，可以增加至每日8粒，左旋肉碱需要配合训练才能起到减脂效果，习得秘籍精髓，展现完美身材。";
		}
		map.put("first" , first);
		map.put("remark", msg);

		return map;
	}
	
}
