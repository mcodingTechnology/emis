package com.mcoding.emis.goods.game.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.game.bean.Game;
import com.mcoding.emis.goods.game.bean.GameMemberShare;
import com.mcoding.emis.goods.game.bean.GameMemberShareExample;
import com.mcoding.emis.goods.game.persistence.GameMapper;
import com.mcoding.emis.goods.game.persistence.GameMemberShareMapper;
import com.mcoding.emis.goods.game.service.GameMemberShareService;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberService;

@Service
public class GameMemberShareServiceImpl implements GameMemberShareService {
	private static final Logger log = Logger.getLogger(GameMemberShareServiceImpl.class);
	
	/**分享成功**/
	public static final Integer GAME_MEMBER_SHARE_SUCCESS = 0;
	/**已分享**/
	public static final Integer GAME_MEMBER_SHARE_REPEAT = 2;
	
	@Autowired
	private GameMapper gameMapper;
	@Autowired
	private GameMemberShareMapper gameMemberShareMapper;
	
	@Autowired
	private MemberService memberService;
	
	
	CommonResult<Game> commonResult = new CommonResult<Game>();
	CommonResult<String> commonResultString = new CommonResult<String>();
	@Override
	public CommonResult<String> addObj(GameMemberShare t) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CommonResult<String> deleteObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CommonResult<String> modifyObj(GameMemberShare t) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CommonResult<GameMemberShare> queryObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CommonResult<ArrayList<GameMemberShare>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CommonResult<PageView<GameMemberShare>> queryObjByPage(int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PageView<GameMemberShare> queryObjByPage(String iDisplayStart,
			String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CommonResult<GameMemberShare> updateGameMemberShareRecord(
			HttpSession session, Integer gameid, String shareOpenid) {
		CommonResult<GameMemberShare> result = new CommonResult<GameMemberShare>();
		try {
			GameMemberShareExample example = new GameMemberShareExample();
			GameMemberShareExample.Criteria cri = example.createCriteria();
			cri.andGameidEqualTo(gameid);
			cri.andOpenidEqualTo(shareOpenid);
			List<GameMemberShare> gameMemberShares = gameMemberShareMapper.selectByExample(example);
			Date now = new Date();
			GameMemberShare gameMemberShare = new GameMemberShare();
			if(gameMemberShares.size() ==0){
				gameMemberShare.setCreateTime(now);
				gameMemberShare.setUpdateTime(now);
				gameMemberShare.setOpenid(shareOpenid);
				gameMemberShare.setGameid(gameid);
				gameMemberShareMapper.insert(gameMemberShare);
			}else {
				gameMemberShare = gameMemberShares.get(0);
				result.setCode(GAME_MEMBER_SHARE_REPEAT);
				result.setMsg("已分享过");
			}
			result.setData(gameMemberShare);
			result.setCode(GAME_MEMBER_SHARE_SUCCESS);
			result.setMsg("分享成功");
		} catch (Exception e) {
			result.setCode(1);
			result.setMsg("操作失败");
		}
		return result;
	}


	
}
