package com.mcoding.emis.goods.game.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.utils.StringHelper;
import com.mcoding.emis.goods.game.bean.Game;
import com.mcoding.emis.goods.game.bean.GameExample;
import com.mcoding.emis.goods.game.bean.GamePrize;
import com.mcoding.emis.goods.game.bean.GamePrizeExample;
import com.mcoding.emis.goods.game.persistence.GameMapper;
import com.mcoding.emis.goods.game.persistence.GamePrizeMapper;
import com.mcoding.emis.goods.game.service.GamePrizeService;
import com.mcoding.emis.member.common.CommonResult;

@Service
@Transactional
public class GamePrizeServiceImpl implements GamePrizeService {
	private static final Logger log = Logger
			.getLogger(GamePrizeServiceImpl.class);
	@Autowired
	private GamePrizeMapper gamePrizeMapper;
	@Autowired
	private GameMapper gameMapper;
	
	CommonResult<GamePrize> commonResult = new CommonResult<GamePrize>();

	@Override
	public PageView<GamePrize> queryGamePrizeData(HttpServletRequest request,
			String iDisplayStart, String iDisplayLength) {
		PageView<GamePrize> pageView = new PageView<GamePrize>(iDisplayStart, iDisplayLength);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pageView", pageView);
        List<GamePrize> gameQuestions = gamePrizeMapper.queryGamePrizeByPage(param);
        pageView.setQueryResult(gameQuestions);
        return pageView;
	}

	@Override
	public CommonResult<String> addObj(GamePrize t) {
		CommonResult<String> result = new CommonResult<String>();
        try {
            Date date = new Date();
            if(t.getId()==null){
            	//新增
                t.setCreatetime(date);
                t.setGainedprizenum(0);
                Game game = gameMapper.selectByPrimaryKey(t.getGameid());
                t.setGamename(game.getGamename());
                gamePrizeMapper.insert(t);
            }else {
            	//修改
                Game game = gameMapper.selectByPrimaryKey(t.getGameid());
                t.setGamename(game.getGamename());
            	gamePrizeMapper.updateByPrimaryKeySelective(t);
			}
            result.setCode(0);
            result.setData("ok");
            result.setMsg("ok");
        } catch (Exception e) {
            log.error("增加失败：", e);
            result.setCode(1);
            result.setData("ok");
            result.setMsg(e.getMessage());
        }
        return result;
	}


	@Override
	public CommonResult<GamePrize> queryObjById(String id) {
		if(StringHelper.isNotBlank(id)){
			commonResult.setData(gamePrizeMapper.selectByPrimaryKey(Integer.parseInt(id)));
			commonResult.setCode(0);
			commonResult.setMsg("success");
		}
		return commonResult;
	}

	@Override
	public CommonResult<String> deleteObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<String> modifyObj(GamePrize t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<ArrayList<GamePrize>> queryListObj(
			String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<GamePrize>> queryObjByPage(int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<GamePrize> queryObjByPage(String iDisplayStart,
			String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView addGamePrizeView(String id) {
		ModelAndView mav = new ModelAndView();
	       if(StringUtils.isNotBlank(id)){
	    	   	GamePrize gamePrize = gamePrizeMapper.selectByPrimaryKey(Integer.parseInt(id));
		       	mav.addObject("gamePrize", gamePrize);
		       	mav.addObject("edit","edit");
	       }
	       GameExample gameExample = new GameExample();
	       List<Game> gameList = gameMapper.selectByExample(gameExample);
	       mav.addObject("gameList",gameList);
	       mav.setViewName("game/addGamePrize");
	       return mav;
	}

	@Override
	public CommonResult<ArrayList<GamePrize>> queryListObj(GamePrize gamePrize) {
		CommonResult<ArrayList<GamePrize>> result = new CommonResult<ArrayList<GamePrize>>();
		try {
			GamePrizeExample gameprizeExample = new GamePrizeExample();
			GamePrizeExample.Criteria cri = gameprizeExample.createCriteria();
			cri.andGameidEqualTo(gamePrize.getGameid());
		    List<GamePrize> gameprizeList = gamePrizeMapper.selectByExample(gameprizeExample);
			result.setCode(0);
            result.setData((ArrayList<GamePrize>) gameprizeList);
            result.setMsg("ok");
        } catch (Exception e) {
            log.error("增加失败：", e);
            result.setCode(1);
            result.setData(null);
            result.setMsg(e.getMessage());
        }
		return result;
	}

	@Override
	public GamePrize queryRandom(int gameid, int type) {
		return gamePrizeMapper.queryRandom(gameid, type);
	}


}
