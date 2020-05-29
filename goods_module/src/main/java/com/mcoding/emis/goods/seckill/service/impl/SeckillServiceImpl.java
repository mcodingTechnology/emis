package com.mcoding.emis.goods.seckill.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.base.member.persistence.member.MemberMapper;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.product.bean.Product;
import com.mcoding.emis.goods.product.persistence.ProductMapper;
import com.mcoding.emis.goods.seckill.bean.Seckill;
import com.mcoding.emis.goods.seckill.bean.SeckillExample;
import com.mcoding.emis.goods.seckill.bean.SeckillResult;
import com.mcoding.emis.goods.seckill.bean.UserAndSeckillListJson;
import com.mcoding.emis.goods.seckill.bean.WinnerListJson;
import com.mcoding.emis.goods.seckill.persistence.SeckillMapper;
import com.mcoding.emis.goods.seckill.persistence.SeckillResultMapper;
import com.mcoding.emis.goods.seckill.service.SeckillService;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberService;

@Service
public class SeckillServiceImpl implements SeckillService {
	private static final Logger log = Logger.getLogger(SeckillServiceImpl.class);
	@Autowired
	private SeckillMapper seckillMapper;
	@Autowired
	private SeckillResultMapper seckillResultMapper;
//	@Autowired
//	private MemberMapper memberMapper; 
//	@Autowired
//	private WeixinUserMapper weixinUserMapper;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	protected MemberService memberService;
	
	CommonResult<Seckill> commonResult = new CommonResult<Seckill>();
	
	public CommonResult<String> addObj(Seckill t) {
		CommonResult<String> result = null;
		try {
			Date now = new Date();
			t.setCreatetime(now);
			t.setUpdatetime(now);
			Date start = null,end = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			try {
				t.setStartTimeStr(t.getStartTimeStr().substring(0, t.getStartTimeStr().length() - 2) + "00");
				start = sdf.parse(t.getStartTimeStr());
				t.setEndTimeStr(t.getEndTimeStr().substring(0, t.getEndTimeStr().length() - 2) + "00");
				end = sdf.parse(t.getEndTimeStr());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Product p = productMapper.queryById(t.getProductid());
			t.setProductname(p.getProductName());
			t.setProductconvert(p.getProductCoverImg());
			t.setStarttime(start);
			t.setEndtime(end);
			
			int id = seckillMapper.insert(t);
			result = new CommonResult<String>();
			if (id > 0) {
				result.setData("OK");
				result.setCode(0);
				result.setMsg("success");
			} else {
				result.setData("FAILT");
				result.setCode(1);
				result.setMsg("failt");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public CommonResult<String> deleteObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	public CommonResult<String> modifyObj(Seckill t) {
		CommonResult<String> result = null;
		try {
			Date start = null,end = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			try {
				t.setStartTimeStr(t.getStartTimeStr().substring(0, t.getStartTimeStr().length() - 2) + "00");
				start = sdf.parse(t.getStartTimeStr());
				t.setEndTimeStr(t.getEndTimeStr().substring(0, t.getEndTimeStr().length() - 2) + "00");
				end = sdf.parse(t.getEndTimeStr());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Product p = productMapper.queryById(t.getProductid());
			t.setProductname(p.getProductName());
			t.setProductconvert(p.getProductCoverImg());
			t.setUpdatetime(new Date());
			t.setStarttime(start);
			t.setEndtime(end);
			int id = seckillMapper.updateByPrimaryKey(t);
			result = new CommonResult<String>();
			if (id > 0) {
				result.setData("OK");
				result.setCode(0);
				result.setMsg("success");
			} else {
				result.setData("FAILT");
				result.setCode(1);
				result.setMsg("failt");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public CommonResult<Seckill> queryObjById(String id) {
		CommonResult<Seckill> result = new CommonResult<Seckill>();
		result.setData(seckillMapper.selectByPrimaryKey(Integer.parseInt(id)));
		return result; 
	}
	public CommonResult<ArrayList<Seckill>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}
	public CommonResult<PageView<Seckill>> queryObjByPage(int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
	public PageView<Seckill> queryObjByPage(String iDisplayStart,
			String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}
	public CommonResult<UserAndSeckillListJson> selectTodaysList(String brandcode) {
		CommonResult<UserAndSeckillListJson> result = null;
		try {
			result = new CommonResult<UserAndSeckillListJson>();
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("brandcode", brandcode);
			List<Seckill> seckillList = seckillMapper.selectByToday(param);
			UserAndSeckillListJson json = new UserAndSeckillListJson();
			json.setSeckillList(seckillList);
			result.setData(json);
			result.setCode(0);
			result.setMsg("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public CommonResult<ArrayList<WinnerListJson>> selectWinnerListByToday(String openId, String isSelf, String brandcode) {
		CommonResult<ArrayList<WinnerListJson>> result = null;
		try {
			ArrayList<WinnerListJson> json = new ArrayList<WinnerListJson>();
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("brandcode", brandcode);
			List<Seckill> seckillList = seckillMapper.selectEndByToday(param);
			if (seckillList != null && seckillList.size() > 0) {
				for (Seckill seckill : seckillList) {
					WinnerListJson one = new WinnerListJson();
					one.setProductConvert(seckill.getProductconvert());
					one.setProductId(seckill.getProductid());
					one.setProductName(seckill.getProductname());
					one.setStartTime(seckill.getStarttime());
					one.setSeckillId(seckill.getId());
					json.add(one);
				}
			}

			if (json != null && json.size() > 0) {
				for (WinnerListJson one : json) {
					param = new HashMap<String, Object>();
					param.put("seckillId", one.getSeckillId());
					param.put("openId", openId);
					param.put("isSelf", isSelf);
					List<SeckillResult> resultList = seckillResultMapper.selectBySeckillId(param);
					one.setWinnerList(resultList);
				}
			}
			result = new CommonResult<ArrayList<WinnerListJson>>();
			result.setData(json);
			result.setCode(00);
			result.setMsg("success");
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(1);
			result.setMsg("failed");
		}
		return result;
	}
	@Override
	public PageView<Seckill> querySeckillData(HttpServletRequest request, String iDisplayStart, String iDisplayLength) {
    	String status = request.getParameter("status");
    	String startTime = request.getParameter("starttime");
    	String endTime = request.getParameter("endtime");
    	String brandcode = request.getParameter("brandcode");
    	
		PageView<Seckill> pageView = null;
		try {
			pageView = new PageView<Seckill>(iDisplayStart, iDisplayLength);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("status", status);
			param.put("startTime", startTime);
			param.put("endTime", endTime);
			param.put("brandcode", brandcode);
			param.put("pageView", pageView);
			List<Seckill> seckillResults = seckillMapper.querySeckillByPage(param);
			pageView.setQueryResult(seckillResults);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return pageView;
	}
	@Override
	public PageView<SeckillResult> querySeckillResultData(HttpServletRequest request,
			String iDisplayStart, String iDisplayLength, Integer id) {
		PageView<SeckillResult> pageView = new PageView<SeckillResult>(iDisplayStart, iDisplayLength);
        try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("seckillId", id);
			param.put("pageView", pageView);
			List<SeckillResult> seckillResults = seckillResultMapper.selectBySeckillIdByPage(param);
			pageView.setQueryResult(seckillResults);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageView;
	}
//	@Override
//	public CommonResult<String> makeSeckillResult(Integer seckillId, String openid, String brandCode, Integer orderId, Integer num, Integer resultId) {
//		log.info("+=+=+=makeSeckillResult+=+=+");
//		CommonResult<String> result = new CommonResult<String>();
//		try {
//			Member m = memberMapper.queryMemberByOpenid(openid);
//			WeixinUser w = weixinUserMapper.selectByOpenid(openid, brandCode);
//			Seckill s =seckillMapper.selectByPrimaryKey(seckillId);
//			SeckillResult record = new SeckillResult();
//			Date now = new Date();
//			record.setId(resultId);
//			record.setUpdatetime(now);
//			record.setCreatetime(now);
//			record.setOrderid(orderId);
//			record.setNum(1);
//			record.setNickname(m.getNickName());
//			record.setHeadimgurl(w.getHeadimgurl());
//			record.setOpenid(openid);
//			record.setSeckillid(s.getId());
//			record.setProductid(s.getProductid());
//			record.setProductname(s.getProductname());
//			int judge = seckillResultMapper.updateByPrimaryKeySelective(record);
//			if (judge > 0) {
//				result.setCode(0);
//				result.setData("插入订单成功");
//				result.setMsg("success");
//			}else{
//				result.setCode(1);
//				result.setData("插入订单失败");
//				result.setMsg("failed");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			result.setCode(1);
//			result.setData("插入订单失败");
//			result.setMsg("failed");
//		}
//		return result;
//	}
	
	@Override
	public CommonResult<SeckillResult> canGet(Integer seckillId, String openid) {
		CommonResult<SeckillResult> result = new CommonResult<SeckillResult>();
		try {
			Seckill secKill = seckillMapper.selectByPrimaryKey(seckillId);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("openid", openid);
			param.put("seckillid", seckillId);
			SeckillResult skResult = seckillResultMapper.getByIdAndOpenid(param); 
			if (skResult != null) {// 判断该用户是否秒过此商品
				if (skResult.getOrderid() <= 0) {
					result.setCode(5);
					result.setData(skResult);
					result.setMsg("已秒过此商品，但未提交订单！");
					return result;
				}else{
					result.setCode(3);
					result.setData(null);
					result.setMsg("已秒过此商品，请在订单列表查看！");
					return result;
				}
			}

			int getCount = seckillResultMapper.getOrderNum(seckillId);
			if (getCount >= secKill.getOrdernum()) {// 判断被秒的商品是否超过能被秒的数量
				if (!secKill.getStatus().equals("end")) {
					seckillResultMapper.setEndById(seckillId);
				}
				result.setCode(4);
				result.setData(null);
				result.setMsg("已被秒完");
				return result;
			}
//			Member m = memberMapper.queryMemberByOpenid(openid);
			Member m = this.memberService.queryMemberByOpenid(openid);
			if (m.getPointSum() > secKill.getNeedpoint()) {
				m.setPointSum(m.getPointSum() - secKill.getNeedpoint());
//				memberMapper.updateByPrimaryKeySelective(m);
				this.memberService.modifyObj(m);
			} else {
				result.setCode(2);
				result.setData(null);
				result.setMsg("积分不足");
				return result;
			}

			SeckillResult record = new SeckillResult();
			Date now = new Date();
			record.setHeadimgurl(m.getHeadimgurl());
			record.setNickname(m.getNickName());
			record.setUpdatetime(now);
			record.setCreatetime(now);
			record.setOpenid(openid);
			record.setOrderid(0);
			record.setSeckillid(seckillId);
			seckillResultMapper.insert(record);
			if (record.getId() > 0) {
				result.setCode(0);
				result.setData(record);
				result.setMsg("success");
			} else {
				result.setCode(1);
				result.setData(record);
				result.setMsg("failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(1);
			result.setMsg("failed");
		}

		return result;
	}
	@Override
	public void updateStatus(Integer seckillId) {
		SeckillExample ex = new SeckillExample();
		SeckillExample.Criteria cr = ex.createCriteria();
		cr.andIdEqualTo(seckillId);
		Seckill seckill = seckillMapper.selectByExample(ex).get(0);
		Date now = new Date();
		int orderNum = seckillResultMapper.getOrderNum(seckillId);
		try {
			if (now.getTime() < seckill.getStarttime().getTime()) {// 开始时间之前
				if (now.getTime() - seckill.getStarttime().getTime() > 43200000) {// 距离开始时间大于12小时
					seckill.setStatus("notReady");
				}
				if (seckill.getStarttime().getTime() - now.getTime() <= 43200000
						&& now.getTime() < seckill.getStarttime().getTime()) {// 距离开始时间小于12小时
					seckill.setStatus("ready");
				}
			} else if (seckill.getStarttime().getTime() < now.getTime()
					&& now.getTime() < seckill.getEndtime().getTime()) {// 开始时间之后，结束时间之前
				if (orderNum >= seckill.getOrdernum()) {// 秒到的数量达到设定值
					seckill.setStatus("end");
				}else{
					seckill.setStatus("playing");
				}
			} else if (seckill.getEndtime().getTime() < now.getTime()) {// 结束时间之后
				seckill.setStatus("end");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		seckillMapper.updateByPrimaryKey(seckill);
	}
	

}
