package com.mcoding.emis.goods.income.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.utils.DateUtil;
import com.mcoding.emis.goods.income.bean.IncomeMonth;
import com.mcoding.emis.goods.income.bean.IncomeOrder;
import com.mcoding.emis.goods.income.bean.IncomeOrderExample;
import com.mcoding.emis.goods.income.bean.IncomeOrderProduct;
import com.mcoding.emis.goods.income.bean.IncomeOrderProductExample;
import com.mcoding.emis.goods.income.component.IncomeCalculateStrategy;
import com.mcoding.emis.goods.income.persistence.IncomeOrderMapper;
import com.mcoding.emis.goods.income.persistence.IncomeProductMapper;
import com.mcoding.emis.goods.income.service.IncomeDayService;
import com.mcoding.emis.goods.income.service.IncomeOrderProductService;
import com.mcoding.emis.goods.income.service.IncomeOrderService;
import com.mcoding.emis.goods.income.service.IncomeService;
import com.mcoding.emis.goods.order.bean.Order;
import com.mcoding.emis.goods.order.bean.OrderProducts;
import com.mcoding.emis.goods.order.bean.OrderProductsExample;
import com.mcoding.emis.goods.order.persistence.OrderProductsMapper;
import com.mcoding.emis.goods.order.service.OrderService;
import com.mcoding.emis.goods.orderReturn.bean.OrderReturn;
import com.mcoding.emis.goods.schedule.bean.GetStoreDomain;
import com.mcoding.emis.goods.wechat.bean.TemplateMessageRecord;
import com.mcoding.emis.goods.wechat.utils.WxMpTemplateMsgUtil;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberLevelService;
import com.mcoding.emis.member.service.member.MemberPointService;
import com.mcoding.emis.member.service.member.MemberService;

@Service("incomeOrderService")
@Transactional
public class IncomeOrderServiceImpl implements IncomeOrderService {

	private static Logger logger = LoggerFactory.getLogger(IncomeOrderServiceImpl.class);

	private static String[] formateStr = new String[] { "yyyy-MM-dd" };

	@Autowired
	protected MemberService memberService;

	@Autowired
	protected MemberLevelService memberLevelService;

	@Autowired
	protected IncomeOrderMapper incomeOrderMapper;

	@Autowired
	protected IncomeProductMapper incomeProductMapper;

	@Autowired
	protected IncomeService incomeService;

	@Autowired
	protected IncomeDayService incomeDayService;

	@Autowired
	protected OrderService orderService;

	@Autowired
	protected MemberPointService memberPointService;

	@Autowired
	protected OrderProductsMapper orderProductsMapper;

	@Autowired
	protected IncomeOrderProductService incomeOrderProductService;

	@Autowired
	private WxMpTemplateMsgUtil wxMpTemplateMsgUtil;

	// @Resource(name="incomeCalculateCommonStrategy")
	@Resource(name = "incomeCalculateProfitStrategy")
	protected IncomeCalculateStrategy incomeCalculateStrategy;

	/**
	 * 获取今天的日期
	 * 
	 * @return
	 */
	/*
	 * private static Date getDay(Date day){
	 * 
	 * Calendar calendar = Calendar.getInstance(); calendar.setTime(day);
	 * calendar.set(Calendar.HOUR_OF_DAY, 0); calendar.set(Calendar.MINUTE, 0);
	 * calendar.set(Calendar.SECOND, 0); Date today = calendar.getTime(); return
	 * today; }
	 */

	@Override
	public CommonResult<String> addObj(IncomeOrder t) {
		Integer id = this.incomeOrderMapper.insertSelective(t);

		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData(String.valueOf(id));
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<String> deleteObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<String> modifyObj(IncomeOrder t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<IncomeOrder> queryObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<ArrayList<IncomeOrder>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<IncomeOrder>> queryObjByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<IncomeOrder> queryObjByPage(String iDisplayStart, String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public void addIncreaseIncomeOrderByOrder(Order order) {
		List<Integer> distributorIds = new ArrayList<>();

		// 1、找出order的购买者
		// 1.1、如果本人是分销商，就开始计算他的佣金
		Member distributor = this.memberService.queryMemberByOpenid(order.getOpenid());
		if (distributor.getLevelId() == null) {
			// 1.2、如果本人不是是分销商，就找出他的上级，就开始计算上级的佣金
			distributor = this.memberService.queryObjById(distributor.getParentMemberId().toString()).getData();
		}

		// 找出订单的明细
		OrderProductsExample orderProductsExample = new OrderProductsExample();
		orderProductsExample.createCriteria().andOrderidEqualTo(order.getId());
		List<OrderProducts> orderProductList = this.orderProductsMapper.selectByExample(orderProductsExample);

		while (distributor != null) {
			// 2、计算出订单产生的佣金、返利积分
			Integer income = 0;
			Integer point = 0;

			List<IncomeOrderProduct> incomeOrderProductList = new ArrayList<>();
			for (OrderProducts orderProduct : orderProductList) {
				int productIncome = this.incomeCalculateStrategy.calculateProductIncomeForMember(orderProduct,
						distributor, distributor.getMemberId().equals(order.getMemberid()));
				int productPoint = this.incomeCalculateStrategy.calculateProductPointForMember(orderProduct,
						distributor, distributor.getMemberId().equals(order.getMemberid()));

				income = income + productIncome * orderProduct.getNums();
				point = point + productPoint * orderProduct.getNums();

				if (productIncome == 0 && productPoint == 0) {
					continue;
				}

				IncomeOrderProduct incomeOrderProduct = new IncomeOrderProduct();
				incomeOrderProduct.setIncome(productIncome);
				incomeOrderProduct.setPoint(productPoint);
				incomeOrderProduct.setMemberId(distributor.getMemberId());
				incomeOrderProduct.setMemberName(distributor.getFullName());
				incomeOrderProduct.setOpenid(distributor.getOpenid());
				incomeOrderProduct.setProductId(orderProduct.getProductid());
				incomeOrderProduct.setProductName(orderProduct.getProductname());
				incomeOrderProduct.setProductCount(orderProduct.getNums());
				incomeOrderProduct.setOrderId(order.getId());

				incomeOrderProductList.add(incomeOrderProduct);
			}

			// 3、插入佣金明细
			IncomeOrder incomeOrder = new IncomeOrder();
			incomeOrder.setCommission(income);
			incomeOrder.setPoint(point);
			incomeOrder.setOpenid(distributor.getOpenid());
			incomeOrder.setMemberid(distributor.getMemberId());
			incomeOrder.setMembername(distributor.getFullName());
			incomeOrder.setOrderid(order.getId());
			incomeOrder.setCreatetime(new Date());
			incomeOrder.setUpdatetime(new Date());
			incomeOrder.setExt2(IncomeOrder.TYPE_INCREASE_INCOME);

			// 记录订单详情
			incomeOrder.setOrdermemberid(order.getMemberid());
			incomeOrder.setOrdermembername(order.getMembername());
			incomeOrder.setOrderfee(order.getFee());
			incomeOrder.setOrdertime(order.getAddtime());
			incomeOrder.setExt1(order.getOutno());
			this.addObj(incomeOrder);

			// 插入各个产品的佣金情况
			for (IncomeOrderProduct incomeOrderProduct : incomeOrderProductList) {
				incomeOrderProduct.setIncomeOrderId(incomeOrder.getId());
				this.incomeOrderProductService.addObj(incomeOrderProduct);
			}

			// 4、统计佣金
			// 4.1 统计日佣金和日积分返利
			this.incomeDayService.addCommissionToIncomeForDay(distributor.getMemberId(), income, point, order.getFee(),
					DateUtils.truncate(new Date(), Calendar.DATE));

			// 4.2 统计总佣金和总积分返利
			this.incomeService.addCommissionToIncome(distributor.getMemberId(), income, point);

			// 5 累加会员积分记录
			if (point > 0) {
				this.memberPointService.updateAndAddMemberPoint(distributor, point, "D", "incomePoint");
			}

			// 6、有产生佣金时发送模板消息
			sendWxTemplateMsg(order, distributor, income, point);

			// 7、查找该分销商的上级，如果还有上级，就继续计算，如果没有上级，就结束
			distributorIds.add(distributor.getMemberId());
			if (distributor.getParentMemberId() == null) {
				break;
			}
			distributor = this.memberService.queryObjById(distributor.getParentMemberId().toString()).getData();
			if (distributorIds.indexOf(distributor.getMemberId()) != -1) {
				throw new RuntimeException("陷入死循环了......");
			}
		}
	}

	private void sendWxTemplateMsg(Order order, Member member, int income, int point) {
		if (income <= 0) {
			return;
		}

		String incomeOutput = income / 100 + "元";
		String pointOutput = null;
		if (point > 0) {
			pointOutput = "(获得" + point + "积分)";
		} else {
			pointOutput = "";
		}

		String url = null;
		String domain = GetStoreDomain.getDomain(order.getOrderbrand());
		try {
			if (order.getOrderbrand().equals("JLD")) {
				url = domain + "/gMall/distributor_wallet.html";
				wxMpTemplateMsgUtil.sendWxMpTemplateMessageByType(
						TemplateMessageRecord.TEMPLATE_MESSAGE_TYPE_GMX_INCOME, member.getOpenid(),
						incomeOutput + pointOutput, "已收入佣金金额", DateUtil.dateTimeFormatStr(new Date()), null, null, null,
						url);
			} else {
				url = domain + "/wMall/income2.html";
				wxMpTemplateMsgUtil.sendWxMpTemplateMessageByType(TemplateMessageRecord.TEMPLATE_MESSAGE_TYPE_INCOME,
						member.getOpenid(), incomeOutput + pointOutput, "已收入佣金金额",
						DateUtil.dateTimeFormatStr(new Date()), null, null, null, url);
			}
		} catch (Exception e) {
			logger.error("发送佣金的模板消息异常,order[" + order.getId() + "],member[" + member.getMemberId() + "]", e);
		}
	}

	@Override
	public void addPointOrderByOrder(Order order) {

	}

	@Override
	public List<IncomeOrder> queryIncomeOrderByExample(IncomeOrderExample ex) {
		return this.incomeOrderMapper.selectByExample(ex);
	}

	@Transactional
	@Override
	public void addDecreaseIncomeOrderByOrder(OrderReturn orderReturn) {

		List<OrderProducts> orderProductList = orderReturn.getOrderProductsInfo();
		if (CollectionUtils.isEmpty(orderProductList)) {
			throw new RuntimeException("退货单中没有关联 退货商品");
		}

		IncomeOrderExample incomeOrderExample = new IncomeOrderExample();
		incomeOrderExample.createCriteria().andOrderidEqualTo(orderReturn.getOrderid());
		List<IncomeOrder> incomeList = this.queryIncomeOrderByExample(incomeOrderExample);
		if (CollectionUtils.isEmpty(incomeList)) {
			throw new RuntimeException("该订单没有佣金的记录");
		}

		List<Integer> productIds = new ArrayList<>();
		for (OrderProducts orderProduct : orderProductList) {
			productIds.add(orderProduct.getId());
		}

		for (IncomeOrder incomeOrder : incomeList) {
			IncomeOrderProductExample incomeOrderProductExample = new IncomeOrderProductExample();
			incomeOrderProductExample.createCriteria().andIncomeOrderIdEqualTo(incomeOrder.getId())
					.andProductIdIn(productIds);
			List<IncomeOrderProduct> incomeOrderProductList = this.incomeOrderProductService
					.queryAllObjByExample(incomeOrderProductExample);
			if (CollectionUtils.isEmpty(incomeOrderProductList)) {
				throw new RuntimeException("佣金单下，没有产品佣金明细");
			}

			int incomeDecrease = 0;
			int pointDecrease = 0;
			for (IncomeOrderProduct incomeOrderProduct : incomeOrderProductList) {
				incomeDecrease = incomeDecrease + incomeOrderProduct.getIncome();
				pointDecrease = pointDecrease + incomeOrderProduct.getPoint();
			}

			incomeOrder.setId(null);
			incomeOrder.setCommission(-incomeDecrease);
			incomeOrder.setPoint(-pointDecrease);
			incomeOrder.setCreatetime(new Date());
			incomeOrder.setExt2(IncomeOrder.TYPE_DECREASE_INCOME);
			this.addObj(incomeOrder);

			for (IncomeOrderProduct incomeOrderProduct : incomeOrderProductList) {
				incomeOrderProduct.setId(null);
				incomeOrderProduct.setIncomeOrderId(incomeOrder.getId());
				incomeOrderProduct.setIncome(-incomeOrderProduct.getIncome());
				incomeOrderProduct.setPoint(-incomeOrderProduct.getPoint());
				this.incomeOrderProductService.addObj(incomeOrderProduct);
			}

			// 在日收入中，添加减免金额
			Date today = DateUtils.truncate(new Date(), Calendar.DATE);
			this.incomeDayService.addCommissionToIncomeForDay(incomeOrder.getMemberid(), -incomeDecrease,
					-pointDecrease, -orderReturn.getFee(), today);

			// 在总收入中，添加减免金额
			this.incomeService.addCommissionToIncome(incomeOrder.getMemberid(), -incomeDecrease, -pointDecrease);
		}

		// 1、找出需要添加佣金明细的人
		// 1.1、找出order的购买者，如果订单购买者是分销商，就计算该分销商的佣金
		// Member distributor =
		// this.memberService.queryMemberByOpenid(orderReturn.getOpenid());
		// if (distributor.getLevelId() == null) {
		// //1.2如果购买者不是分销商，就查找他的上级分销商，开始计算佣金
		// distributor =
		// this.memberService.queryObjById(distributor.getParentMemberId().toString()).getData();
		// }

		// while (distributor != null) {
		//
		// //2、计算出订单产生的佣金
		// int income = 0;
		// int point = 0;
		// for(OrderProducts orderProduct : products){
		// IncomeOrderProductExample incomeOrderProductExample = new
		// IncomeOrderProductExample();
		// incomeOrderProductExample.createCriteria().andProductIdEqualTo(orderProduct.getId()).andMemberIdEqualTo(distributor.getMemberId());
		//
		// List<IncomeOrderProduct> list =
		// this.incomeOrderProductService.queryAllObjByExample(incomeOrderProductExample);
		// if (CollectionUtils.isNotEmpty(list)) {
		//
		// }
		//
		// int tmpIncome =
		// this.incomeCalculateStrategy.calculateProductIncomeForMember(orderProduct,
		// parent.getData());
		// income = income + tmpIncome * orderProduct.getNums();
		// }
		//
		// //3、插入佣金明细
		// IncomeOrder incomeOrder = new IncomeOrder();
		// incomeOrder.setCommission(-income); //收入为负数，因为是退换货的收入，要划扣的
		// incomeOrder.setOpenid(parent.getData().getOpenid());
		// incomeOrder.setMemberid(parent.getData().getMemberId());
		// incomeOrder.setMembername(parent.getData().getFullName());
		// incomeOrder.setOrderid(orderReturn.getOrderid());
		// incomeOrder.setCreatetime(new Date());
		// incomeOrder.setUpdatetime(new Date());
		// incomeOrder.setExt2(IncomeOrder.TYPE_DECREASE_INCOME);
		//
		// //记录订单详情
		// incomeOrder.setOrdermemberid(distributor.getMemberId());
		// incomeOrder.setOrdermembername(distributor.getFullName());
		// incomeOrder.setOrderfee(orderReturn.getFee());
		// incomeOrder.setOrdertime(orderReturn.getAudittime());
		// incomeOrder.setExt1(orderReturn.getExt1());
		// this.addObj(incomeOrder);
		//
		// //在日收入中，添加减免金额
		// Date today = DateUtils.truncate(new Date(), Calendar.DATE);
		// this.incomeDayService.addCommissionToIncomeForDay(parent.getData().getMemberId(),
		// -income, -point, -orderReturn.getFee(), today);
		//
		// //在总收入中，添加减免金额
		// this.incomeService.addCommissionToIncome(parent.getData().getMemberId(),
		// -income, -point);
		//
		// parentMemberIdTmp = parent.getData().getParentMemberId();
		// CommonResult<Member> parent =
		// this.memberService.queryObjById(parentMemberIdTmp.toString());
		// if (parentMemberIdTmp == null || parentMemberIdTmp == 0) {
		// break;
		// }
		// }

	}

	@Override
	public void countIncomeOrderByOrder(Order order) throws Exception {
		// 1、找出order的购买者
		Member memberTmp = this.memberService.queryMemberByOpenid(order.getOpenid());
		Integer parentMemberIdTmp = memberTmp.getParentMemberId();

		while (parentMemberIdTmp != null && parentMemberIdTmp != 0) {

			// 1、找出需要添加佣金明细的人
			CommonResult<Member> parent = this.memberService.queryObjById(parentMemberIdTmp.toString());

			// 2、计算出订单产生的佣金
			Integer income = this.incomeCalculateStrategy.calculateOrderIncomeForMember(order, parent.getData(),
					parentMemberIdTmp);
			// 有产生佣金时发送模板消息
			if (income != null & income > 0) {
				System.out.println("======================income========");
				// String templateid =
				// "NW2mt5eYpS3t6qhOMoAV6IYkkM2VI1847qHixzykQE8";//生产
				// String templateid =
				// "OEuNpslq6Sifbz7v0A8rOP9swoKpvTveS912i7d8b3o";//测试
				income = income / 100;
				// wxMpTemplateMsgUtil.sendWxMpTemplateMessage(templateid,
				// parent.getData().getOpenid(),"您有一笔待收入佣金", "待收入金额",
				// income+"元", "待定", null, null, "待订单完成后将自动收入佣金【极智构微商城】","",
				// "e18f12");
				wxMpTemplateMsgUtil.sendWxMpTemplateMessageByType(TemplateMessageRecord.TEMPLATE_MESSAGE_TYPE_INCOME,
						parent.getData().getOpenid(), "待收入金额", income + "元", "待定", null, null, null, "");
			}

			parentMemberIdTmp = parent.getData().getParentMemberId();
			if (parentMemberIdTmp == null || parentMemberIdTmp == 0) {
				break;
			}
		}
	}

	@Override
	public PageView<IncomeOrder> queryObjByPage(IncomeOrderExample example) {

		PageView<IncomeOrder> pageView = example.getPageView();
		if (pageView == null) {
			pageView = new PageView<>(1, 10);
			example.setPageView(pageView);
		}
		pageView.setQueryResult(this.incomeOrderMapper.selectByExampleByPage(example));
		return pageView;
	}

	/**
	 * 更改佣金标识
	 */
	@Override
	public int updateByPrimaryext3(int months) {

		return incomeOrderMapper.updateByPrimaryext3(months);
	}

	/**
	 * 查询上月佣金
	 */
	@Override
	public int selectSumCommission() {
		return incomeOrderMapper.selectSumCommission();
	}

	@Override
	public Map<String, Object> getSumSaleAndIncome(String openid) {
		return incomeOrderMapper.getSumSaleAndIncome(openid);
	}

	@Override
	public Map<String, Object> selectSumByExample(String startDate, String endDate, Integer memberId) {
		try {
			IncomeOrderExample example = new IncomeOrderExample();
			IncomeOrderExample.Criteria criteria = example.createCriteria();
			criteria.andMemberidEqualTo(memberId);
			if (StringUtils.isNotBlank(startDate) && startDate.matches("\\d{4}-\\d{2}-\\d{2}"))
				criteria.andCreatetimeGreaterThanOrEqualTo(DateUtils.parseDate(startDate, formateStr));
			if (StringUtils.isNotBlank(endDate) && endDate.matches("\\d{4}-\\d{2}-\\d{2}"))
				criteria.andCreatetimeLessThan(DateUtils.parseDate(endDate, formateStr));
			return incomeOrderMapper.selectSumByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 报表导出
	 */
	@Override
	public List<Map<String, Object>> querycommissionForExportExcel(Map<String, Object> param) {

		List<Map<String, Object>> lists = incomeOrderMapper.querycommissionForExportExcel(param);
		for (Map<String, Object> map : lists) {
			map.put("commission", (Integer) map.get("commission") / 100);
			map.put("orderFee", (Integer) map.get("orderFee") / 100);
		}
		return lists;
	}

}
