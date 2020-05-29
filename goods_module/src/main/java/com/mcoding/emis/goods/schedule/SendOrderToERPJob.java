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
import com.mcoding.emis.goods.product.bean.SetProducts;
import com.mcoding.emis.goods.product.bean.SetProductsExample;
import com.mcoding.emis.goods.product.persistence.ProductMapper;
import com.mcoding.emis.goods.product.persistence.SetProductsMapper;

/**
 * 定时搜索微商城待发货订单，自动发送待发货订单到ERP系统
 * @author hzy Benson
 *
 */
@Service("com.mcoding.emis.goods.schedule.SendOrderToERPJob")
@Transactional
public class SendOrderToERPJob {
	private static final Logger log = Logger.getLogger(SendOrderToERPJob.class);

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
			//搜索微商城待发货，且未post的订单
			OrderExample example = new OrderExample();
			OrderExample.Criteria cri = example.createCriteria();
			cri.andPaystatusEqualTo("待发货");
			cri.andExt1EqualTo("N");
			cri.andAddressIsNotNull();
			cri.andMalltypeEqualTo("wMall");

			OrderExample.Criteria cri2 = example.createCriteria();
			cri2.andPaystatusEqualTo("待发货");
			cri2.andExt1EqualTo("N");
			cri2.andAddressIsNotNull();
			cri2.andMalltypeEqualTo("gMall");
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
				//订单地区信息
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
//					order.setSd_code("015");//测试店铺代码
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
				if(order2.getFreight() > 0){
					Integer freight = order2.getFreight()/100;
					order.setShipping_fee(freight.toString());
				}

				OrderProductsExample orderProductsExample = new OrderProductsExample();
				OrderProductsExample.Criteria criteria = orderProductsExample.createCriteria();
				criteria.andOrderidEqualTo(order2.getId());
				List<OrderProducts> orderAndProducts = orderProductsMapper.selectByExample(orderProductsExample);

				List<E3Item> items = new ArrayList<E3Item>();
				Integer integral_money = 0;//消费积分
				Integer total_fee = 0;//订单总金额
				for (OrderProducts orderProducts : orderAndProducts) {
					boolean isSet = false;
					Product product = productMapper.queryById(orderProducts.getProductid());
					Integer oringinalPrice = 0;

					List<Product> proList = new ArrayList<Product>();
					List<Integer> proNumList = new ArrayList<Integer>();
					//若是套餐商品，则查询对应包含的所有产品
					if("yes".equals(product.getIsSet())) {
						items = getSetProductList(orderProducts,product,order2,items,total_fee);
					}else {
						E3Item item = new E3Item();
						//生产的SKU
						item.setSku_sn(product.getProductNo());
						item.setOrder_sn(order2.getOutno());
						item.setGoods_sn(product.getProductNo());
						item.setGoods_name(orderProducts.getProductname());
						oringinalPrice = product.getOriginalPrice() / 100;
						item.setMarket_price(oringinalPrice.toString());
						item.setDiscount("0");
						item.setGoods_number(orderProducts.getNums());
						Integer price = orderProducts.getPrice() / 100;
						item.setTransaction_price(price.toString());// 实际售价
						item.setGoods_price(price.toString());// 平台售价
						if("赠品".equals(product.getProductType())){
							item.setIs_gift(1);
						}
						items.add(item);

						//计算每个商品*数量的总额，得出总金额
						total_fee = total_fee + (orderProducts.getNums() * oringinalPrice);
					}
				}

				Integer fee = 0;//订单总额
				fee = order2.getFee()/100;//订单总额
				//是否使用优惠券
				if(order2.getFeereduce()!=null && order2.getFeereduce()!=0){
					Integer feereduce = order2.getFeereduce()/100;
					integral_money = integral_money + feereduce;
					//order.setDiscount(feereduce.toString());//优惠金额
					order.setMoney_paid(fee.toString());//已付金额
					order.setOrder_amount(fee.toString());//应付金额
				}else {
					order.setDiscount("0");
				}

				//规则引擎，满减优惠金额
				if(order2.getPreferentialprice()!=null && order2.getPreferentialprice()!=0){
					Integer preferentialprice = order2.getPreferentialprice()/100;
					integral_money = integral_money + preferentialprice;
				}

				/*if("ACTIVITY".equals(order2.getOrdertype())){
					integral_money = integral_money + order2.getFeereduce()/100;
				}*/
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
				msg = ERPApi.addERPOrder(resultJson);
				System.out.println("--------------msg is " + msg);

				//3.更新订单物流信息
				if(msg.contains("success")){
					order2.setExt1("Y");
					orderMapper.updateByPrimaryKeySelective(order2);
				}

			}
		} else {
			msg = "===暂无订单可发送===";
		}
		return msg;
	}

	/***
	 *  获取套餐商品的所有包含产品
	 * */
	private List<E3Item> getSetProductList(OrderProducts orderProducts,Product product,Order order2,List<E3Item> items,Integer total_fee){
		if(product.getIsSet().equals("yes")){
			SetProductsExample setExample = new SetProductsExample();
			SetProductsExample.Criteria setCri = setExample.createCriteria();
			setCri.andSetIdEqualTo(product.getProductId());
 			List<SetProducts> setList = setProductsMapper.selectByExample(setExample);
			//套餐中所有商品的总数量
			int setTotalNums = 0;
			for(SetProducts setProducts: setList){
				setTotalNums += setProducts.getNum();
			}
			if (setList != null && setList.size() > 0) {
				boolean isSumBalance = false;
				for(SetProducts setProducts: setList){
					//查询对应的商品
					Product childProduct = productMapper.queryById(setProducts.getProductId());
					int setNum = setTotalNums * orderProducts.getNums();//购买的单个商品的总数量= 套餐所有商品的数量  * 购买数量
					int setOneProductOrderNum = setProducts.getNum() * orderProducts.getNums();
					for(int i=0;i< setOneProductOrderNum;i++){
						E3Item item = new E3Item();
						//生产的SKU
					    item.setSku_sn(childProduct.getProductNo());
						item.setOrder_sn(order2.getOutno());
						item.setGoods_sn(childProduct.getProductNo());
						item.setGoods_name(childProduct.getProductName());

						//计算套餐里的每个商品的价格，套餐价格 除以 购买的商品数量，不足的则补充差额到第一个商品
						Integer setPrice = orderProducts.getPrice()/100 * orderProducts.getNums();//套餐价格*数量
						Integer oneProductPrice = setPrice/setNum;
						System.out.println(oneProductPrice);
						Integer sumPrice = oneProductPrice * setNum ;
						Integer balance = setPrice - sumPrice;//获得差额

						if(balance > 0 && isSumBalance == false){
							oneProductPrice += balance;
							isSumBalance = true;
						}
						System.out.println(balance);
						System.out.println(sumPrice);

						Integer oringinalPrice = childProduct.getOriginalPrice() / 100;
						item.setMarket_price(oringinalPrice.toString());
						item.setDiscount("0");
						item.setGoods_number(1);
						item.setTransaction_price(oneProductPrice.toString());// 实际售价
						item.setGoods_price(oneProductPrice.toString());// 平台售价
						items.add(item);

						//计算每个商品*数量的总额，得出总金额
						total_fee = total_fee + oneProductPrice;
					}
				}
			}
		}
		return items;
	}

	private Integer[] getPerMoney(List<OrderProducts> orderAndProducts, Integer fee) {
		fee = fee / 100;
		List<Product> proList = new ArrayList<Product>();
		List<Integer> numList = new ArrayList<Integer>();
		for (OrderProducts one : orderAndProducts) {
			Product product = productMapper.queryById(one.getProductid());
			if ("yes".equals(product.getIsSet())) {
				SetProductsExample setExample = new SetProductsExample();
				SetProductsExample.Criteria setCri = setExample.createCriteria();
				setCri.andSetIdEqualTo(product.getProductId());
				setExample.setOrderByClause("id");
				List<SetProducts> setList = setProductsMapper.selectByExample(setExample);
				if (setList != null && setList.size() > 0) {
					for (SetProducts oneSet : setList) {
						Product childProduct = productMapper.queryById(oneSet.getProductId());
						proList.add(childProduct);
						numList.add(oneSet.getNum() * one.getNums());
					}
				}
			} else {
				proList.add(product);
				numList.add(one.getNums());
			}
		}
		Integer[] perMoney = new Integer[proList.size()];
		int lastMoney = fee;
		int money = lastMoney / perMoney.length;
		for (int j = 0; j < proList.size(); j++) {
			if ("赠品".equals(proList.get(j).getProductType())) {
				perMoney[j] = 0;
			} else {
				perMoney[j] = money / numList.get(j);
			}
		}
		for(int one : perMoney){
			lastMoney -= one;
		}

		for (int i = 0; i < perMoney.length; i++) {
			if (perMoney[i].compareTo(0) != 0) {
				perMoney[i] = (perMoney[i] + lastMoney) / numList.get(i);
				break;
			}
		}

		return perMoney;
	}

}
