package com.mcoding.emis.goods.game.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.utils.StringHelper;
import com.mcoding.emis.goods.game.bean.GameHelp;
import com.mcoding.emis.goods.game.bean.GameHelpExample;
import com.mcoding.emis.goods.game.persistence.GameHelpMapper;
import com.mcoding.emis.goods.game.service.GameHelpService;
import com.mcoding.emis.member.common.CommonResult;

@Service
public class GameHelpServiceImpl implements GameHelpService {
	private static final Logger log = Logger
			.getLogger(GameHelpServiceImpl.class);
	@Autowired
	private GameHelpMapper gameHelpMapper;
	
	CommonResult<GameHelp> commonResult = new CommonResult<GameHelp>();
	CommonResult<String> commonResultString = new CommonResult<String>();

	@Override
	public PageView<GameHelp> queryGameHelpData(HttpServletRequest request,
			String iDisplayStart, String iDisplayLength) {
		PageView<GameHelp> pageView = new PageView<GameHelp>(iDisplayStart, iDisplayLength);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pageView", pageView);
        List<GameHelp> gameGameHelpResults = gameHelpMapper.queryGameHelpByPage(param);
        pageView.setQueryResult(gameGameHelpResults);
        return pageView;
	}

	@Override
	public CommonResult<String> addObj(GameHelp t) {
		CommonResult<String> result = new CommonResult<String>();
        try {
            if(t.getId()==null){
            	//新增
                gameHelpMapper.insert(t);
            }else {
            	//修改
            	gameHelpMapper.updateByPrimaryKey(t);
            	
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
	public CommonResult<GameHelp> queryObjById(String id) {
		if(StringHelper.isNotBlank(id)){
			commonResult.setData(gameHelpMapper.selectByPrimaryKey(Integer.parseInt(id)));
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
	public CommonResult<String> modifyObj(GameHelp t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<ArrayList<GameHelp>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<GameHelp>> queryObjByPage(int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<GameHelp> queryObjByPage(String iDisplayStart,
			String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<String> addGameHelp(GameHelp gamehelp) {
		CommonResult<String> result = new CommonResult<String>();
        try {
            if(gamehelp.getId()==null){
            	//新增
            	gameHelpMapper.insert(gamehelp);
            }else {
            	//修改
            	gameHelpMapper.updateByPrimaryKey(gamehelp);
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
	public CommonResult<GameHelp> queryGameHelpByGameid(Integer gameid,String type) {
		try {
			GameHelpExample example = new GameHelpExample();
			GameHelpExample.Criteria criteria = example.createCriteria();
			if(gameid!=null){
				criteria.andGameidEqualTo(gameid);
			}
			if(type!=null){
				criteria.andExtEqualTo(type);
			}
			List<GameHelp> list = gameHelpMapper.selectByExample(example);
			if(list.size()>0){
				commonResult.setCode(0);
				commonResult.setData(list.get(0));
				commonResult.setMsg("成功");
			}else {
				commonResult.setCode(2);
				commonResult.setData(null);
				commonResult.setMsg("没有游戏说明");
			}
		} catch (Exception e) {
			log.error("失败：", e);
			commonResult.setCode(1);
			commonResult.setData(null);
			commonResult.setMsg(e.getMessage());
        }
        return commonResult;
	}


}
