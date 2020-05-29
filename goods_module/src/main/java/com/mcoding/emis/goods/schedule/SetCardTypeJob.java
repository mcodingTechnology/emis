package com.mcoding.emis.goods.schedule;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcoding.emis.goods.card.bean.CardType;
import com.mcoding.emis.goods.card.bean.CardTypeExample;
import com.mcoding.emis.goods.card.persistence.CardTypeMapper;

/**
 * 定时查询card_type表，把过期的数据设置为无效
 * @author hzy
 *
 */
@Service("com.mcoding.emis.goods.schedule.SetCardTypeJob")
@Transactional
public class SetCardTypeJob {
	
	@Autowired
	protected CardTypeMapper cardTypeMapper;
	
	public void execute() throws Exception{
		try {
			System.out.println("==cardType定时器开始==");
			//搜索isvalid==1 && timetype==rime_range
			CardTypeExample example = new CardTypeExample();
			CardTypeExample.Criteria cri = example.createCriteria();
			cri.andTimetypeEqualTo("TIME_RANGE");
			cri.andIsvalidEqualTo(1);
			cri.andEndtimeLessThan(new Date());
			CardType c = new CardType();
			c.setIsvalid(0);
			cardTypeMapper.updateByExampleSelective(c,example);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("============更新card_type失败===========");
		}
		
	}

	public static void main(String args[]) throws Exception{
		SetCardTypeJob a = new SetCardTypeJob();
		a.execute();
	}
}
