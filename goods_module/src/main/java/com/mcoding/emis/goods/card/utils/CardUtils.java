package com.mcoding.emis.goods.card.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.mcoding.emis.goods.card.bean.CardType;

public class CardUtils {
	
    public static String encodeForCard(CardType cardType){
		String cardTypeCode = null;
		Integer cardId = null;
    	if(StringUtils.isNotBlank(cardType.getCode())){
			cardTypeCode = cardType.getCode();
		}
    	
    	if(cardType.getId() != null){
    		cardId = cardType.getId();
    	}
    	StringBuffer b = new StringBuffer();
    	b.append(cardId);
    	b.append(cardTypeCode);
    	b.append(getFixLenthString(4));
    	return b.toString();
	}
	
    
    public static Set<String> getCodeSetForLength(int strLength, int count){
    	Set<String> set = new HashSet<String>();
    	while(set.size() < count){
    		set.add(getFixLenthString(strLength));
    	}
    	
    	return set;
    }
	
	/**
	 * 获取固定长度的随机数
	 * @param strLength
	 * @return
	 */
	public static String getFixLenthString(int strLength) {  
	    Random rm = new Random();  
	    // 获得随机数  
	    double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);  
	    // 将获得的获得随机数转化为字符串  
	    String fixLenthString = String.valueOf(pross);  
	    // 返回固定的长度的随机数  
	    return fixLenthString.substring(1, strLength + 1);  
	} 

}
