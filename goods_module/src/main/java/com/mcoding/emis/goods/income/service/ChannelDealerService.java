package com.mcoding.emis.goods.income.service;

import javax.servlet.http.HttpServletRequest;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.income.bean.ChannelDealer;
import com.mcoding.emis.member.common.CommonResult;
public interface ChannelDealerService {

	/**
	 * 查询渠道商数据
	 * @param request
	 * @return
	 */
    public PageView<ChannelDealer> queryChanneldealerListByPage(HttpServletRequest request,String iDisplayStart,String iDisplayLength);
    /**
     * 添加渠道商
     * @param req
     * @return
     */
    public CommonResult<String> addchannel(ChannelDealer req);
    /**
     * 根据Id 查询
     * @param tagsId
     * @return
     */
	public  CommonResult<ChannelDealer> selectChannelById(int tagsId);
	/**
	 * 修改渠道商 
	 * @param req
	 * @return
	 */
	public CommonResult<String> updateChannel(ChannelDealer req);
	
	/**
	 * 删除标记为x
	 * @param tagsId
	 * @return
	 */
	public CommonResult<String> deleteChannel(int tagsId);
	
	/**
	 * 查询会员
	 * @param request
	 * @return
	 */
    public PageView<Member> queryMemberListByPage(HttpServletRequest request,String iDisplayStart,String iDisplayLength);
	
}
