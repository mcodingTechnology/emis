package com.mcoding.emis.goods.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static final String format1 = "yyyy-MM-dd";
	private static final String format2 = "yyyy-MM-dd HH:mm:ss";
	
	public static String dateFormatStr(Date date) {
		if (date!=null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format1);
			return dateFormat.format(date);
		}else{
			return "";
		}
	}
	
	public static String dateTimeFormatStr(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format2);
		return dateFormat.format(date);
	}
	
	public static Date StrFormatDateTime(String date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format2);
		return dateFormat.parse(date);
	}
	
	public static Date StrFormatDate(String date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format1);
		return dateFormat.parse(date);
	}
}
