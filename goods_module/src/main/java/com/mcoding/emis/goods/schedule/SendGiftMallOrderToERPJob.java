package com.mcoding.emis.goods.schedule;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcoding.emis.goods.common.api.ERPApi;
import com.mcoding.emis.goods.order.bean.E3AddOrderRequestJson;
import com.mcoding.emis.goods.order.bean.E3Item;
import com.mcoding.emis.goods.order.bean.E3Order;
import com.mcoding.emis.goods.order.bean.E3RequestJson;
import com.mcoding.emis.goods.order.bean.Order;
import com.mcoding.emis.goods.order.bean.OrderExample;
import com.mcoding.emis.goods.order.bean.OrderProducts;
import com.mcoding.emis.goods.order.bean.OrderProductsExample;
import com.mcoding.emis.goods.order.persistence.OrderMapper;
import com.mcoding.emis.goods.order.persistence.OrderProductsMapper;
import com.mcoding.emis.goods.product.bean.Product;
import com.mcoding.emis.goods.product.persistence.ProductMapper;
import com.mcoding.emis.goods.product.persistence.SetProductsMapper;

/**
 * 定时搜索积分商城待发货订单，自动发送待发货订单到ERP系统
 * @author hzy
 *
 */
@Service("com.mcoding.emis.goods.schedule.SendGiftMallOrderToERPJob")
@Transactional
public class SendGiftMallOrderToERPJob {
	private static final Logger log = Logger.getLogger(SendGiftMallOrderToERPJob.class);
	
	@Autowired
	protected OrderMapper orderMapper;
	@Autowired
	protected OrderProductsMapper orderProductsMapper;
	@Autowired
	protected ProductMapper productMapper;
	@Autowired
	protected SetProductsMapper setProductsMapper;
	
	public void execute() throws Exception{
		abc();
		return;
	}
	
	public void abc() throws Exception{
		try {
			//搜索待发货，且未post的订单
			OrderExample example = new OrderExample();
			OrderExample.Criteria cri = example.createCriteria();
			cri.andPaystatusEqualTo("待发货");
			cri.andExt1EqualTo("N");
			cri.andAddressIsNotNull();
			cri.andMalltypeEqualTo("giftMall");

			OrderExample.Criteria cri2 = example.createCriteria();
			cri2.andPaystatusEqualTo("待发货");
			cri2.andExt1EqualTo("N");
			cri2.andAddressIsNotNull();
			cri2.andMalltypeEqualTo("giftMall_gmx");
			example.or(cri2);
			List<Order> list = orderMapper.selectByExample(example);
			if(CollectionUtils.isNotEmpty(list)){
				postByOrderList(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("============发送待发货订单到E3系统失败===========");
			throw new Exception("下发订单到E3系统异常");
		}
		
	}

	public String postByOrderList(List<Order> list) {
		String msg = "";
		if(list.size()>0){
			for (Order order2 : list) {
				//1.批量查询并封装需要JSON数据
				List<E3Order> resulList = new ArrayList<E3Order>();
				E3Order order = new E3Order();
				order.setLine(1);
				order.setOrder_sn(order2.getOutno());
				order.setUser_name(order2.getReceiver());
				order.setConsignee(order2.getReceiver());
				if(order2.getRegson()!=null){
					String[] region = order2.getRegson().split(" ");
					if(region.length<3){
						order.setProvince_name(region[0]);
						order.setCity_name(region[1]);
						order.setDistrict_name(" ");
						order.setAddress(order2.getAddress());
					}
					else {
						order.setProvince_name(region[0]);
						order.setCity_name(region[1]);
						order.setDistrict_name(region[2]);
						order.setAddress(order2.getAddress());
					}
				}
				order.setCountry_name("中国");
				order.setMobile(order2.getReceiverphone());
				order.setAdd_time(order2.getAddtime());
				order.setPay_time(order2.getPaytime());

				if(order2.getOrderbrand().equals("MRMJ") || order2.getOrderbrand().equals("极智构")){
					order.setSd_code("102");//生产店铺代码
				}else {
					order.setSd_code("111");//BIG生活E3店铺代码
				}
				order.setOrder_status(1);
				order.setShipping_status(0);
				order.setShipping_code("zto");
				order.setPay_status(2);
				order.setPay_code("weixin");
				order.setTrans_type("1");
				order.setOrder_out_sn(order2.getThirdno());
				order.setGoods_count(order2.getNums().toString());

				OrderProductsExample orderProductsExample = new OrderProductsExample();
				OrderProductsExample.Criteria criteria = orderProductsExample.createCriteria();
				criteria.andOrderidEqualTo(order2.getId());
				List<OrderProducts> orderAndProducts = orderProductsMapper.selectByExample(orderProductsExample);

				List<E3Item> items = new ArrayList<E3Item>();
				Integer integral_money = 0;//消费积分
				Integer total_fee = 0;//订单总金额
				for (OrderProducts orderProducts : orderAndProducts) {
					Product product = productMapper.queryById(orderProducts.getProductid());
					Integer oringinalPrice = 0;

					List<Product> proList = new ArrayList<Product>();
					proList.add(product);
					for (int i = 0; i < proList.size(); i++) {
						E3Item item = new E3Item();
						//生产的SKU
						item.setSku_sn(proList.get(i).getProductNo());
						item.setOrder_sn(order2.getOutno());
						item.setGoods_sn(proList.get(i).getProductNo());
						item.setGoods_name(orderProducts.getProductname());

						oringinalPrice = proList.get(i).getOriginalPrice() / 100;

						item.setMarket_price(oringinalPrice.toString());
						item.setDiscount("0");

						item.setGoods_number(orderProducts.getNums());
						if(order2.getMalltype().equals("giftMall")||order2.getMalltype().equals("giftMall_gmx")){
							integral_money = proList.get(i).getOriginalPrice() / 100 + integral_money;
							item.setTransaction_price("0");
							if (order2.getOrdertype()!=null && "plusgo".equals(order2.getOrdertype()) && order2.getPlusmoney() != null) { // 是加钱购
								item.setTransaction_price(orderProducts.getPlusMoney() / 100 + "");
								if(orderProducts.getPlusMoney()==0){//设为礼品
									item.setIs_gift(1);
								}
							}
							item.setGoods_price(oringinalPrice.toString());
							item.setIs_gift(1);
						}
						if("赠品".equals(proList.get(i).getProductType())){
							item.setIs_gift(1);
						}
						items.add(item);

						//计算每个商品*数量的总额，得出总金额
						total_fee = total_fee + (orderProducts.getNums() * oringinalPrice);
					}
				}

				Integer fee = 0;//订单总额
				log.info("===============积分giftMall===============");
				if(order2.getMalltype().equals("giftMall")||order2.getMalltype().equals("giftMall_gmx")){
					fee = 0;//订单总额
					order.setIntegral(order2.getFee());
					order.setIntegral_money(order2.getFee());
					integral_money =0;
					total_fee = 0;
					order.setDiscount("0");

					if ("plusgo".equals(order2.getOrdertype()) && order2.getPlusmoney() != null) { // 是加钱购
						log.info("=========send plus go to e3========");
						fee = order2.getPlusmoney() / 100;
					}
				}
				order.setMoney_paid(fee.toString());//已付金额
				order.setOrder_amount(fee.toString());//应付金额
				order.setGoods_amount(total_fee.toString());//商品总金额
				order.setTotal_fee(total_fee.toString());//订单总金额

				order.setIntegral_money(integral_money);
				order.setItems(items);
				resulList.add(order);

				//2.post订单数据到ERP系统
				E3AddOrderRequestJson addResultJson = new E3AddOrderRequestJson();
				addResultJson.setOrderLists(resulList);
				addResultJson.setTotal(1);

				E3RequestJson resultJson = new E3RequestJson();
				resultJson.setData(addResultJson);
				System.out.println("--------------resultJson is " + resultJson);
				msg = ERPApi.addERPOrder(resultJson);

				//3.更新订单物流信息
				order2.setExt1("Y");
				orderMapper.updateByPrimaryKeySelective(order2);
			}
		} else {
			msg = "===暂无订单可发送===";
		}
		return msg;
	}
	
}
