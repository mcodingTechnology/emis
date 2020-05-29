package com.mcoding.emis.goods.game.service.impl;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.utils.StringHelper;
import com.mcoding.emis.goods.game.bean.*;
import com.mcoding.emis.goods.game.persistence.GameMapper;
import com.mcoding.emis.goods.game.persistence.GameQuestionMapper;
import com.mcoding.emis.goods.game.service.GameQuestionService;
import com.mcoding.emis.member.bean.member.WeixinUser;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.WeixinUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class GameQuestionServiceImpl implements GameQuestionService {
	private static final Logger log = Logger
			.getLogger(GameQuestionServiceImpl.class);
	@Autowired
	private GameQuestionMapper gameQuestionMapper;
	@Autowired
	private GameMapper gameMapper;
//	@Autowired
//	private WeixinUserMapper weixinUserMapper;
	@Autowired
	private WeixinUserService weixinUserService;
	
	CommonResult<GameQuestion> commonResult = new CommonResult<GameQuestion>();

	@Override
	public PageView<GameQuestion> queryGameQuestionData(HttpServletRequest request,
			String iDisplayStart, String iDisplayLength) {
		PageView<GameQuestion> pageView = new PageView<GameQuestion>(iDisplayStart, iDisplayLength);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pageView", pageView);
        String sSearch = request.getParameter("sSearch");
        param.put("questionTitle", sSearch);
        List<GameQuestion> gameQuestions = gameQuestionMapper.queryGameQuestionByPage(param);
        pageView.setQueryResult(gameQuestions);
        return pageView;
	}

	@Override
	public CommonResult<String> addObj(GameQuestion t) {
		CommonResult<String> result = new CommonResult<String>();
        try {
            Date date = new Date();
            if(t.getId()==null){
            	//新增
                t.setCreatetime(date);
                t.setUpdatetime(date);
                gameQuestionMapper.insert(t);
            }else {
            	//修改
            	t.setUpdatetime(date);
            	gameQuestionMapper.updateByPrimaryKey(t);
            	
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
	@Transactional(rollbackFor=Exception.class)
	public CommonResult<String> deleteObjById(String id) {
		CommonResult<String> result = new CommonResult<String>();
        //TransactionStatus status = transactionManager.getTransaction(def);
        try {
        	if(StringHelper.isNotBlank(id)){
    			//删除
            	gameQuestionMapper.deleteByPrimaryKey(Integer.parseInt(id));
            	result.setCode(0);
                result.setData("ok");
                result.setMsg("ok");
        	}
        } catch (Exception e) {
            result.setCode(1);
            result.setData("ok");
            result.setMsg(e.getMessage());
            throw new RuntimeException("删除失败");
        }

        return result;
	}

	@Override
	public CommonResult<String> modifyObj(GameQuestion t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<GameQuestion> queryObjById(String id) {
		if(StringHelper.isNotBlank(id)){
			commonResult.setData(gameQuestionMapper.selectByPrimaryKey(Integer.parseInt(id)));
			commonResult.setCode(0);
			commonResult.setMsg("success");
		}
		return commonResult;
	}

	@Override
	public CommonResult<ArrayList<GameQuestion>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<GameQuestion>> queryObjByPage(int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<GameQuestion> queryObjByPage(String iDisplayStart,
			String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<GameAnswerJson> getRandomQuestion(HttpSession session) {
		CommonResult<GameAnswerJson> commonResult = new CommonResult<GameAnswerJson>();
		try {
			String brandCode = (String) session.getAttribute("brandCode");
			if(StringUtils.isBlank(brandCode)){
				throw new NullPointerException("brandCode can not be null");
			}
			GameAnswerJson gameAnswerJson = new GameAnswerJson();
			String openid = null;
			//String openid =null;
			if(session.getAttribute("openid")!=null){
				openid = (String) session.getAttribute("openid");
			}
//			WeixinUser weixinUser = weixinUserMapper.selectByOpenid(openid, brandCode);
			WeixinUser weixinUser = this.weixinUserService.selectByOpenid(openid);
			System.out.println("weixinUser======================"+weixinUser);
			if(weixinUser!=null){
				gameAnswerJson.setNickname(weixinUser.getNickname());
				gameAnswerJson.setHeadimgurl(weixinUser.getHeadimgurl());
			}
			
			//随机获取5道题
			ArrayList<GameQuestion> gameQuestions = gameQuestionMapper.getRadomQuestion();
			gameAnswerJson.setGameQuestionList(gameQuestions);
			commonResult.setCode(0);
			commonResult.setData(gameAnswerJson);
			commonResult.setMsg("ok");
		} catch (Exception e) {
			e.printStackTrace();
			commonResult.setCode(1);
			commonResult.setData(null);
			commonResult.setMsg(e.getMessage());
		}
				
		return commonResult;
	}

	@Override
	public ModelAndView addGameQuestionView(String id) {
		ModelAndView mav = new ModelAndView();
	       if(StringUtils.isNotBlank(id)){
	    	   GameQuestion gameQuestion = queryObjById(id).getData();
		       mav.addObject("gameQuestion", gameQuestion);
		       mav.addObject("edit","edit");
	       }
	       GameExample gameExample = new GameExample();
	       List<Game> gameList = gameMapper.selectByExample(gameExample);
	       mav.addObject("gameList",gameList);
	       mav.setViewName("game/addGameQuestion");
	       return mav;
	}

	@Override
	public CommonResult<GameAnswerJson> getQuestionsByGameid(HttpSession session,Integer gameid) {
		CommonResult<GameAnswerJson> commonResult = new CommonResult<GameAnswerJson>();
		String brandCode = (String) session.getAttribute("brandCode");
		if (StringUtils.isBlank(brandCode)) {
			throw new NullPointerException("brandCode can not be null");
		}
		try {
			GameAnswerJson gameAnswerJson = new GameAnswerJson();
			String openid = null;
			if(session.getAttribute("openid")!=null){
				openid = (String) session.getAttribute("openid");
			}
//			WeixinUser weixinUser = weixinUserMapper.selectByOpenid(openid, brandCode);
			WeixinUser weixinUser = this.weixinUserService.selectByOpenid(openid);
			System.out.println("weixinUser======================"+weixinUser);
			if(weixinUser!=null){
				gameAnswerJson.setNickname(weixinUser.getNickname());
				gameAnswerJson.setHeadimgurl(weixinUser.getHeadimgurl());
			}
			
			//随机获取5道题
			GameQuestionExample example = new GameQuestionExample();
			GameQuestionExample.Criteria criteria = example.createCriteria();
			criteria.andGameidEqualTo(gameid);
			List<GameQuestion> gameQuestions = gameQuestionMapper.selectByExample(example);
			gameAnswerJson.setGameQuestionList((ArrayList<GameQuestion>) gameQuestions);
			commonResult.setCode(0);
			commonResult.setData(gameAnswerJson);
			commonResult.setMsg("ok");
		} catch (Exception e) {
			e.printStackTrace();
			commonResult.setCode(1);
			commonResult.setData(null);
			commonResult.setMsg(e.getMessage());
		}
				
		return commonResult;
	}

}
