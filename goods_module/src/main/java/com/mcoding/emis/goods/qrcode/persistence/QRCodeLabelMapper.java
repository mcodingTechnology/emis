package com.mcoding.emis.goods.qrcode.persistence;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.qrcode.bean.QRCodeLabel;
import com.mcoding.emis.goods.qrcode.bean.QRCodeLabelExample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface QRCodeLabelMapper {
    int countByExample(QRCodeLabelExample example);

    int deleteByExample(QRCodeLabelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(QRCodeLabel record);

    int insertSelective(QRCodeLabel record);

    List<QRCodeLabel> selectByExample(QRCodeLabelExample example);

    QRCodeLabel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") QRCodeLabel record, @Param("example") QRCodeLabelExample example);

    int updateByExample(@Param("record") QRCodeLabel record, @Param("example") QRCodeLabelExample example);

    int updateByPrimaryKeySelective(QRCodeLabel record);

    int updateByPrimaryKey(QRCodeLabel record);

	/**
	 * @Description:分页查询
	 * @param param
	 * @return
	 * @return: List<QRCodeLabel>
	 * @author Leiming
	 */
	List<QRCodeLabel> selectByExampleByPage(QRCodeLabelExample example);
	
	/**
	 * 邀请的数字上加1
	 * @param id
	 * @return
	 */
	int addInvitationCountNum(int id);

	/**
	 * 根据key，查询二维码邀请人数的排行
	 * @param key
	 * @param pageView
	 * @return
	 */
	List<HashMap<String, Object>> selectRankingByPage(@Param("key") String key, @Param("pageView") PageView<HashMap<String, Object>> pageView);
	
}