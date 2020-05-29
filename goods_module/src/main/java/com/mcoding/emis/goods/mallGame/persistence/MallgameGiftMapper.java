package com.mcoding.emis.goods.mallGame.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mcoding.emis.goods.mallGame.bean.MallgameGift;
import com.mcoding.emis.goods.mallGame.bean.MallgameGiftExample;
import com.mcoding.emis.goods.mallGame.bean.MallgameOrderRecord;
import com.mcoding.emis.goods.mallGame.bean.MallgameStakeGiftInfor;

public interface MallgameGiftMapper {
	int countByExample(MallgameGiftExample example);

	int deleteByExample(MallgameGiftExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(MallgameGift record);

	int insertSelective(MallgameGift record);

	List<MallgameGift> selectByExample(MallgameGiftExample example);

	MallgameGift selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") MallgameGift record,
			@Param("example") MallgameGiftExample example);

	int updateByExample(@Param("record") MallgameGift record,
			@Param("example") MallgameGiftExample example);

	int updateByPrimaryKeySelective(MallgameGift record);

	int updateByPrimaryKey(MallgameGift record);

	List<MallgameStakeGiftInfor> queryStakeGiftInfor(int giftid);

	List<MallgameOrderRecord> selectGamblingStakeRecordByPage(
			Map<String, Object> param);
}