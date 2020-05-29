package com.mcoding.emis.goods.seckill.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.seckill.bean.Seckill;
import com.mcoding.emis.goods.seckill.bean.SeckillResult;
import com.mcoding.emis.goods.seckill.bean.UserAndSeckillListJson;
import com.mcoding.emis.goods.seckill.bean.WinnerListJson;
import com.mcoding.emis.member.common.CommonResult;

/**
 * 游戏答题和抽奖结果Service
 * @author Administrator
 *
 */
@Service
public interface SeckillService extends BaseService<Seckill, String> {
	public CommonResult<UserAndSeckillListJson> selectTodaysList(String brandCode);
	public CommonResult<ArrayList<WinnerListJson>> selectWinnerListByToday(String openId, String isSelf, String brandCode);
	public PageView<Seckill> querySeckillData(HttpServletRequest request, String iDisplayStart, String iDisplayLength);
	public PageView<SeckillResult> querySeckillResultData(HttpServletRequest request, String iDisplayStart, String iDisplayLength, Integer id);
//	public CommonResult<String> makeSeckillResult(Integer seckillId, String openid, String brandCode, Integer orderId, Integer num, Integer resultId);
	public CommonResult<SeckillResult> canGet(Integer seckillId, String openId);
	public void updateStatus(Integer id);
}
