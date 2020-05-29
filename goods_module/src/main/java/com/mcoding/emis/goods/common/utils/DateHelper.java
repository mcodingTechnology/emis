package com.mcoding.emis.goods.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class DateHelper {
	// 每月天数(非润年)
	static int daysInMonth[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	// 闰年的特殊月份
	static final int MONTH_FEBRUARY = 2;

	static String weekNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
	"星期六" };

	public static final int PRECISE_YEAR = 1;

	public static final int PRECISE_MONTH = 2;

	public static final int PRECISE_DAY = 3;

	public static final int PRECISE_HOUR = 4;

	public static final int PRECISE_MINUTE = 5;

	public static final int PRECISE_SECOND = 6;

	public static final int PRECISE_MilliSECOND = 7;


	public static final String FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_MINUTE = "yyyy-MM-dd HH:mm";
	public static final String FORMAT_DAY = "yyyy-MM-dd";


	static int weeks = 0;
	/**
	 * 获得当天日期
	 * 
	 * @return yyyy-mm-dd
	 */
	public static String getCurrentDateStr() {
		String curDateStr = "";

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);

		curDateStr = String.valueOf(year) + "-";
		curDateStr += ((month < 10) ? "0" + String.valueOf(month) : String
				.valueOf(month))
				+ "-";
		curDateStr += ((day < 10) ? "0" + String.valueOf(day) : String
				.valueOf(day));

		return curDateStr;
	}

	/**
	 * 获得当前时间，精度到毫秒
	 * 
	 * @return hh:mm:ss.XXX
	 */
	public static String getCurrentTimeStr() {
		String curTimeSr = "";

		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		int milliSecond = cal.get(Calendar.MILLISECOND);
		curTimeSr = ((hour < 10) ? "0" + String.valueOf(hour) : String
				.valueOf(hour))
				+ ":";
		curTimeSr += ((minute < 10) ? "0" + String.valueOf(minute) : String
				.valueOf(minute))
				+ ":";
		curTimeSr += ((second < 10) ? "0" + String.valueOf(second) : String
				.valueOf(second));
		curTimeSr += "." + String.valueOf(milliSecond);

		return curTimeSr;
	}

	/**
	 * 获得当天时间，精度到毫秒
	 * 
	 * @return yyyy-mm-dd hh-mm-ss.XXX
	 */
	public static String getCurrentDateTimeStr() {
		String curDateTimeStr = "";
		curDateTimeStr = getCurrentDateStr() + " " + getCurrentTimeStr();
		return curDateTimeStr;
	}

	/**
	 * 获得当前年份
	 * 
	 * @return yyyy
	 */
	public static int getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * 获取当前月份
	 * 
	 * @return
	 */
	public static int getCurrentMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取当前日
	 * 
	 * @return
	 */
	public static int getCurrentDay() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 判断是否闰月，用于计算当前时间加上分钟后的时间
	 * 
	 * @param year
	 *            年份
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		// 能被100整除, 不能被400整除的年份, 不是闰年.
		// 能被100整除, 也能被400整除的年份, 是闰年.

		if ((year % 100) == 0) {
			return ((year % 400) == 0);
		} else // 不能被100整除, 能被4整除的年份是闰年.
		{
			return ((year % 4) == 0);
		}
	}

	/**
	 * 计算当前时间加上秒钟后的时间,建议方法名换为increaseCurDateTime
	 * 
	 * @param addedSecond
	 *            在当前时间上要加的秒数，注意输入的秒钟数不能大于一个月
	 * @return yyyy-mm-dd hh-mm-ss.XXXX
	 */
	public static String calDateTime(int addedSecond) {
		// 若要限制输入的秒钟数不能大于一个月，则应在此加以判断

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		int millisecond = cal.get(Calendar.MILLISECOND);

		// 获取当前月含有的天数, 如果是闰年的二月, 加一天.
		int daysInCurMonth = daysInMonth[month - 1];
		if (isLeapYear(year) && (month == MONTH_FEBRUARY)) {
			daysInCurMonth += 1;
		}

		addedSecond += second;
		second = addedSecond % 60;

		// 输入的分钟数不能大于一个月 ？？？ pay attention to here
		minute = minute + addedSecond / 60;
		// 总的小时数
		hour = hour + minute / 60;
		// 分钟数
		minute = minute % 60;
		// 总的天数
		day = day + hour / 24;
		// 小时数
		hour = hour % 24;

		if (day > daysInCurMonth) {
			// 总的月份数,限制输入的秒钟数不能大于一个月的原因在此
			month = month + day / daysInCurMonth;
			// 天数
			day = day % daysInCurMonth;
		}

		if (month > 12) {
			// 总的年数
			year = year + month / 12;
			// 月份数
			month = month % 12;
		}

		String dateTimeStr = "1900-01-01";
		dateTimeStr = String.valueOf(year) + "-";
		dateTimeStr += ((month < 10) ? "0" + String.valueOf(month) : String
				.valueOf(month))
				+ "-";
		dateTimeStr += ((day < 10) ? "0" + String.valueOf(day) : String
				.valueOf(day))
				+ " ";
		dateTimeStr += ((hour < 10) ? "0" + String.valueOf(hour) : String
				.valueOf(hour))
				+ ":";
		dateTimeStr += ((minute < 10) ? "0" + String.valueOf(minute) : String
				.valueOf(minute))
				+ ":";
		dateTimeStr += ((second < 10) ? "0" + String.valueOf(second) : String
				.valueOf(second));
		dateTimeStr += "." + String.valueOf(millisecond);

		return dateTimeStr;
	}

	/**
	 * 计算两个时间之间的时间差
	 * 
	 * @param strDateTime1
	 *            减数，格式为yyyy-mm-dd hh-mm-ss
	 * @param strDateTime2
	 *            被减数，格式为yyyy-mm-dd hh-mm-ss
	 * @return strDateTime1 - strDateTime2的时间差，单位为毫秒
	 */
	public static long computeInterval(String strDateTime1, String strDateTime2) {
		long interval = 0;
		Timestamp date1 = convertStrToDate(strDateTime1);
		Timestamp date2 = convertStrToDate(strDateTime2);
		interval = date1.getTime() - date2.getTime();
		return interval;
	}

	/**
	 * 得到指定精度的时间字符串
	 * 
	 * @param dateTimeString
	 *            原始时间字符串，格式为yyyy-mm-dd hh:mm:ss
	 * @param precise
	 *            指定的精度
	 * @return
	 */
	public static String customDateTimeStr(String dateTimeString, int precise) {
		if (dateTimeString == null) {
			dateTimeString = "";
			return dateTimeString;
		}

		if (dateTimeString.trim().length() == 0) {
			return dateTimeString;
		}

		if (dateTimeString.startsWith("1900")) {
			dateTimeString = "";
			return dateTimeString;
		}

		if (precise == PRECISE_YEAR) {
			dateTimeString = dateTimeString.substring(0, 4);
		}
		if (precise == PRECISE_MONTH) {
			dateTimeString = dateTimeString.substring(0, 7);
		}
		if (precise == PRECISE_DAY) {
			dateTimeString = dateTimeString.substring(0, 10);
		}
		if (precise == PRECISE_HOUR) {
			dateTimeString = dateTimeString.substring(0, 13);
		}
		if (precise == PRECISE_MINUTE) {
			dateTimeString = dateTimeString.substring(0, 16);
		}
		if (precise == PRECISE_SECOND) {
			dateTimeString = dateTimeString.substring(0, 19);
		}
		return dateTimeString;
	}

	public static String convertDateToStr(Timestamp date) {
		String result = "1900-01-01 00:00:00.000";
		if (date != null) {
			result = date.toString();
		}
		return result;
	}

	public static Timestamp convertStrToDate(String strDate) {
		if (strDate == null) {
			strDate = "1900-01-01 00:00:00.000";
		} else {
			if (strDate.trim().length() == 0) {
				strDate = "1900-01-01 00:00:00.000";
			} else if (strDate.trim().length() == 10) // 传入的日包含时间
			{
				strDate += " 00:00:00.000";
			} else if (strDate.trim().length() == 16) // 传入的日期包含时间到分钟位,如2000-01-01
				// 10:10
			{
				strDate += ":00.000";
			}
		}
		return Timestamp.valueOf(strDate);
	}

	/**
	 * add by hansomee
	 * 
	 * @param date
	 * @return a string with format YYYY-MM-DD from a Date object
	 */
	@SuppressWarnings("deprecation")
	public static String getDateStr(Date date) {
		String m, d;
		if (date == null)
			return "";
		int year = date.getYear() + 1900;
		int month = date.getMonth() + 1;
		if (month < 10)
			m = "0" + month;
		else
			m = "" + month;
		int day = date.getDate();
		if (day < 10)
			d = "0" + day;
		else
			d = "" + day;
		return year + "-" + m + "-" + d;
	}

	/**
	 * add by ywj 取当前时间 (eg: 23:12:02)
	 * 
	 * @return
	 */
	public static String getCurChsTime() {
		String curtime = "";
		String hourStr = "";
		String minuteStr = "";
		String secondStr = "";
		Calendar cal = Calendar.getInstance(); // 取当前时间
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		if (hour < 10)
			hourStr = "0" + String.valueOf(hour);
		else
			hourStr = String.valueOf(hour);
		if (minute < 10)
			minuteStr = "0" + String.valueOf(minute);
		else
			minuteStr = String.valueOf(minute);
		if (second < 10)
			secondStr = "0" + String.valueOf(second);
		else
			secondStr = String.valueOf(second);

		curtime = hourStr + ":" + minuteStr + ":" + secondStr;
		return curtime;
	}

	/**
	 * add by ywj 取当前时间 (eg: 2001年4月2号 星期五)
	 * 
	 * @return
	 */
	public static String getCurChsDate() {
		String curdate = "";
		Calendar cal = Calendar.getInstance(); // 取当前时间
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DATE);
		curdate = year + "年" + month + "月" + date + "日";
		return curdate;
	}

	/**
	 * 当前时间加上天数(add by ywj)
	 * 
	 * @param days
	 * @return 2001-01-13 00:01:01
	 */
	public static String curCalAddDays(int days) {
		String curDateStr = "1900-01-01 00:01:01";
		Calendar newCal = Calendar.getInstance();
		newCal.set(newCal.get(Calendar.YEAR), newCal.get(Calendar.MONTH),
				newCal.get(Calendar.DATE), 0, 0, 1);
		newCal.add(Calendar.DATE, days);
		curDateStr = converCalendarToString(newCal);
		curDateStr = customDateTimeStr(curDateStr, PRECISE_SECOND);
		return curDateStr;
	}

	/**
	 * 将传入的calendar转换成String(add by ywj)
	 * 
	 * @param cal
	 * @return (like 1900-01-01 00:00:00.000)
	 */
	public static String converCalendarToString(Calendar cal) {
		String time = "1900-01-01 00:00:00.000";
		String m, d, h, mi, s;
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DATE);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		if (month < 10)
			m = "0" + month;
		else
			m = "" + month;
		if (date < 10)
			d = "0" + date;
		else
			d = "" + date;
		if (hour < 10)
			h = "0" + hour;
		else
			h = "" + hour;
		if (minute < 10)
			mi = "0" + minute;
		else
			mi = "" + minute;
		if (second < 10)
			s = "0" + second;
		else
			s = "" + second;

		time = year + "-" + m + "-" + d + " " + h + ":" + mi + ":" + s + ".000";
		return time;
	}


	/**
	 * 获取服务器UTC时间
	 * @return
	 */
	public static Timestamp getCurrentServerUTCTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT")); // 设置时区
		// 获取UTC时间
		String strDate = dateFormat.format(System.currentTimeMillis());
		return Timestamp.valueOf(strDate);
	}

	/**
	 * 将UTC时间(Timestamp)转换成本地时间(Timestamp)
	 * 
	 * @param timestamp
	 * @param offsetHours
	 * @param formatPattern
	 * @return
	 */
	public static Timestamp utcTimeToLocalTime(Timestamp timestamp, int offsetHours) {
		// 判断空参数
		if (null == timestamp) {
			return null;
		}
		int timeZoneOffset = getTimeZoneOffset(offsetHours); // 根据时区获取偏移量
		long time = timestamp.getTime() + timeZoneOffset; // 将时间加上偏移量

		Timestamp times = new Timestamp(time); // 格式化时间
		return times;
	}

	/**
	 * 根据偏移小时数获得时区ID
	 * @param offsetMinutes
	 * @return
	 */
	public static String getTimeZoneId(int offsetHours){
		String[] ids = TimeZone.getAvailableIDs(offsetHours*60*60*1000);
		if(ids != null && ids.length > 0){
			return ids[0];
		}else{
			return "";
		}
	}

	/**
	 * 根据时区ID获取时间偏移量
	 * @param timeZoneId
	 * @return
	 */
	public static int getTimeZoneOffset(String timeZoneId){
		return TimeZone.getTimeZone(timeZoneId).getRawOffset();
	}

	/**
	 * 根据偏移时间分钟获取时区偏移量
	 * @param offsetMinutes
	 * @return
	 */
	public static int getTimeZoneOffset(int offsetHours){
		return TimeZone.getTimeZone(getTimeZoneId(offsetHours)).getRawOffset();
	}

	/**
	 * 将UTC时间(Timestamp)转换为本地时间(Timestamp)
	 * @param time
	 * @return
	 */
	public static Timestamp utcTimeToLocaleTime(Timestamp time){
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		//从session中获得时间差值
		int timeZone = Integer.parseInt(session.getAttribute("timeZone").toString());
		return DateHelper.utcTimeToLocalTime(time, timeZone);
	}

	/**
	 * 将本地时间(Timestamp)转换为UTC时间(Timestamp)
	 * @param time
	 * @return
	 */
	public static Timestamp localeTimeToUTCTime(Timestamp time){
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		//从session中获得时间差值
		int timeZone = Integer.parseInt(session.getAttribute("timeZone").toString());
		return DateHelper.toUTCTime(time, timeZone);
	}

	/**
	 * 将UTC时间(Timestamp)转换成本地时间(String)
	 * 
	 * @param timestamp
	 * @param timeZone
	 * @param formatPattern
	 * @return
	 */
	public static String utcTimeToLocalStr(Timestamp timestamp, int timeZone, String formatPattern) {
		// 判断空参数
		if (null == timestamp) {
			return null;
		}
		String pattern = FORMAT_SECOND;
		if (formatPattern != null && !"".equals(formatPattern)) {
			pattern = formatPattern;
		}

		int timeZoneOffset = getTimeZoneOffset(timeZone); // 根据时区获取偏移量
		long time = timestamp.getTime() + timeZoneOffset; // 将时间加上偏移量

		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		String localStr = dateFormat.format(time); // 格式化时间
		return localStr;
	}

	/**
	 * 将UTC时间(Date)转换成本地时间(Timestamp)
	 * @param time
	 * @param timeZone
	 * @return
	 */
	public static Timestamp utcDateToLocalDate (Date time, String timeZone) {
		if (null == time) {
			return new Timestamp(0);
		}
		long timebefore = 0;
		timebefore = time.getTime();

		int timeZoneOffset = TimeZone.getTimeZone(timeZone == null? "" :timeZone).getRawOffset(); // 根据时区获取偏移量
		long timeafter = timebefore + timeZoneOffset;
		return new Timestamp(timeafter);
	}

	/**
	 * 将UTC时间(String)转换成本地时间(String)
	 * 
	 * @param timeStr
	 * @param timeZone
	 * @param formatPattern
	 * @return
	 */
	public static String utcTimeStrToLocalStr(String timeStr, String timeZone, String formatPattern) {
		// 判断空参数
		if (null == timeStr) {
			return null;
		}
		String pattern = FORMAT_SECOND;
		if (formatPattern != null && !"".equals(formatPattern)) {
			pattern = formatPattern;
		}

		int timeZoneOffset = TimeZone.getTimeZone(timeZone == null? "" :timeZone).getRawOffset(); // 根据时区获取偏移量
		Timestamp timestamp = convertStrToDate(timeStr);
		long time = timestamp.getTime() + timeZoneOffset; // 将时间加上偏移量

		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		String localStr = dateFormat.format(time); // 格式化时间
		return localStr;
	}

	/**
	 * 将本地时间(Timestamp)转换成UTC时间(Timestamp)
	 * 
	 * @param localStr
	 * @param timeZone
	 * @return
	 */
	public static Timestamp toUTCTime(Timestamp localTime, int offsetHours) {
		int timeZoneOffset = getTimeZoneOffset(offsetHours); // 根据时区偏移Hours获取偏移量(毫秒)
		long time = localTime.getTime() - timeZoneOffset; // 将时间加上偏移量
		Timestamp utc = new Timestamp(time);
		return utc;
	}

	/**
	 * 将本地时间(String)转换成UTC时间(Timestamp)
	 * 
	 * @param localStr
	 * @param timeZone
	 * @return
	 */
	public static Timestamp localStrToUTCTime(String localStr, String timeZone) {
		Timestamp localTime = convertStrToDate(localStr);
		int timeZoneOffset = TimeZone.getTimeZone(timeZone == null? "" :timeZone).getRawOffset(); // 根据时区获取偏移量
		long time = localTime.getTime() - timeZoneOffset; // 将时间加上偏移量
		Timestamp utc = new Timestamp(time);
		return utc;
	}

	/**
	 * 将本地时间(String)转换为UTC时间(String)
	 * @param localStr
	 * @param timeZone
	 * @param formatPattern
	 * @return
	 */
	public static String localStrToUTCStr(String localStr, int offSetMinutes, String formatPattern) {
		if (localStr == null || "".equals(localStr) || localStr.equals("undefined")) {
			return "";
		} else {
			Timestamp localTime = convertStrToDate(localStr);
			int timeZoneOffset = getTimeZoneOffset(offSetMinutes); //根据时区获取偏移量
			long time = localTime.getTime() - timeZoneOffset; //将时间加上偏移量

			String pattern = FORMAT_SECOND;
			if (formatPattern != null && !"".equals(formatPattern)) {
				pattern = formatPattern;
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			String utcStr = dateFormat.format(time); // 格式化时间
			return utcStr;
		}
	}

	/**
	 * 本地时间(HH:mm)转换为UTC时间(HH:mm)
	 * @param time		时间字符串(HH:mm)
	 * @param source	源时间类型，可选值：LOCALE  UTC
	 * @param target	目标时间类型，，可选值：LOCALE  UTC
	 * @return
	 */
	public static String convertTime(String time, String source, String target){
		if("".equals(time) || time == null){
			return time;
		}
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		//从session中获得时间差值
		int timeZone = Integer.parseInt(session.getAttribute("timeZone").toString());
		int hour = Integer.parseInt(time.split(":")[0]);
		int min = Integer.parseInt(time.split(":")[1]);
		int resultMin = 0;
		if("LOCALE".equals(source)){
			if(timeZone > 0 && timeZone > hour){
				resultMin = (hour + 24)*60 - timeZone*60 + min;
			}else{
				resultMin = hour*60 - timeZone*60 + min;
			}
		}else{
			if(timeZone < 0 && -timeZone > hour){
				resultMin = (hour + 24)*60 + timeZone*60 + min;
			}else{
				resultMin = hour*60 + timeZone*60 + min;
			}
		}
		String hours = (resultMin/60 + "").length() == 1 ? ("0" + resultMin/60) : (resultMin/60 + "");
		String mins = (resultMin%60 + "").length() == 1 ? ("0" + resultMin%60) : (resultMin%60 + "");
		return hours + ":" + mins;
	}


	/**
	 * 本地时间(HH:mm)转换为UTC时间(HH:mm)
	 * @param time		时间字符串(HH:mm)
	 * @param offsetHours	时区相差小时数(即数字时区)
	 * @param source	源时间类型，可选值：LOCALE  UTC
	 * @param target	目标时间类型，，可选值：LOCALE  UTC
	 * @return
	 */
	public static String convertTime(String time, int offsetHours, String source, String target){
		if("".equals(time) || time == null){
			return time;
		}
		int hour = Integer.parseInt(time.split(":")[0]);
		int min = Integer.parseInt(time.split(":")[1]);
		int resultMin = 0;
		if("LOCALE".equals(source)){
			if(offsetHours > 0 && offsetHours > hour){
				resultMin = (hour + 24)*60 - offsetHours*60 + min;
			}else{
				resultMin = hour*60 - offsetHours*60 + min;
			}
		}else{
			if(offsetHours < 0 && -offsetHours > hour){
				resultMin = (hour + 24)*60 + offsetHours*60 + min;
			}else{
				resultMin = hour*60 + offsetHours*60 + min;
			}
		}
		String hours = (resultMin/60 + "").length() == 1 ? ("0" + resultMin/60) : (resultMin/60 + "");
		String mins = (resultMin%60 + "").length() == 1 ? ("0" + resultMin%60) : (resultMin%60 + "");
		return hours + ":" + mins;
	}

	/**
	 * 获取昨天日期
	 * @author moshow
	 */
	public static String yesterday() {
		Date d=new Date(System.currentTimeMillis()-1000*60*60*24);
		SimpleDateFormat sp=new SimpleDateFormat("yyyy-MM-dd");
		return sp.format(d);//获取昨天日期
	}

	// 获得下一月的日期
	public String getNextMonth() {

		Calendar strDate = Calendar.getInstance();       
		strDate.add(strDate.DATE,28);
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.set(strDate.get(Calendar.YEAR), strDate.get(Calendar.MONTH),strDate.get(Calendar.DATE));
		Date monday = currentDate.getTime();
		SimpleDateFormat df = new SimpleDateFormat("MMdd");
		String preMonday = df.format(monday);

		Calendar cal = Calendar.getInstance();
		cal.set(currentDate.get(GregorianCalendar.YEAR), currentDate.get(GregorianCalendar.MONTH), currentDate.get(GregorianCalendar.DATE));
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+1);
		cal.roll(Calendar.DATE, -1);

		return preMonday+"-"+df.format(cal.getTime());
	}
	// 获得下周星期日的日期
	public String getNextSunday(int count) 
	{
		//weeks++;
		GregorianCalendar currentDate = new GregorianCalendar();
		Calendar strDate = Calendar.getInstance();       
		strDate.add(strDate.DATE,count);
		System.out.println("=="+strDate.getTime());
		currentDate.set(strDate.get(Calendar.YEAR), strDate.get(Calendar.MONTH),strDate.get(Calendar.DATE));
		currentDate.add(GregorianCalendar.DATE, 6);
		Date monday = currentDate.getTime();
		SimpleDateFormat df = new SimpleDateFormat("MMdd");
		String preMonday = df.format(monday);
		return preMonday;
	}
	// 获得下周星期一的日期
	public String getNextMonday(int count) {

		Calendar strDate = Calendar.getInstance();       
		strDate.add(strDate.DATE,count);

		//System.out.println(strDate.getTime());
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.set(strDate.get(Calendar.YEAR), strDate.get(Calendar.MONTH),strDate.get(Calendar.DATE));
		Date monday = currentDate.getTime();
		SimpleDateFormat df = new SimpleDateFormat("MMdd");
		String preMonday = df.format(monday);
		return preMonday;
	}
	//JAVA获取今天、明天、后天的具体月、日，以当前系统日期为主：
	public static String returnDate(int count) {
		Calendar strDate = Calendar.getInstance();
		strDate.add(strDate.DATE, count);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(strDate.getTime());
	}


	public static String getWeek(String sdate)
	{
		Date date = strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}


	public static Date strToDate(String strDate)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}


	public static long getDays(String date1, String date2)
	{
		if ((date1 == null) || (date1.equals("")))
		{
			return 0L;
		}
		if ((date2 == null) || (date2.equals("")))
		{
			return 0L;
		}
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		Date mydate = null;
		try
		{
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		}
		catch (Exception localException)
		{
		}
		long day = (date.getTime() - mydate.getTime()) / 86400000L;
		return day;
	}

	public static String getyd()
	{
		Calendar cal = Calendar.getInstance();
		cal.add(5, -1);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
		return yesterday;
	}

	public static String getDefaultDay()
	{
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(5, 1);
		lastDate.add(2, 1);
		lastDate.add(5, -1);
		str = sdf.format(lastDate.getTime());
		return str;
	}


	public static String getPreviousMonthFirst()
	{
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(5, 1);
		lastDate.add(2, -1);
		str = sdf.format(lastDate.getTime());
		return str;
	}


	public static String getFirstDayOfMonth()
	{
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(5, 1);
		str = sdf.format(lastDate.getTime());
		return str;
	}


	public static String getCurrentWeekday()
	{
		weeks = 0;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(5, mondayPlus + 6);
		Date monday = currentDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = sdf.format(monday);
		return preMonday;
	}


	public static String getNowTime(String dateformat)
	{
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);
		String hehe = dateFormat.format(now);
		return hehe;
	}


	private static int getMondayPlus()
	{
		Calendar cd = Calendar.getInstance();
		int dayOfWeek = cd.get(7) - 1;
		if (dayOfWeek == 1)
		{
			return 0;
		}
		return (1 - dayOfWeek);
	}


	public static String getMondayOFWeek()
	{
		weeks = 0;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(5, mondayPlus);
		Date monday = currentDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = sdf.format(monday);
		return preMonday;
	}

	public static String getPreviousWeekSunday()
	{
		weeks = 0;
		weeks -= 1;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(5, mondayPlus + weeks);
		Date monday = currentDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = sdf.format(monday);
		return preMonday;
	}


	public static String getPreviousWeekday()
	{
		weeks -= 1;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(5, mondayPlus + 7 * weeks);
		Date monday = currentDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = sdf.format(monday);
		return preMonday;
	}


	public static String getPreviousMonthEnd()
	{
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(2, -1);
		lastDate.set(5, 1);
		lastDate.roll(5, -1);
		str = sdf.format(lastDate.getTime());
		return str;
	}
	/**
	 * 当前日期时间转为整型
	 * **/
	public static Integer LinuxTimestampInteger(){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String datetime = sdf.format(calendar.getTime());
		Integer date2=0;
		try {
			date2 = (int) (sdf.parse(datetime).getTime()/1000);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date2;
	}
	
	/**
	 * 获取当前时间
	 * */
	public static String nowDateTime() {
		Long timeMillis=Calendar.getInstance().getTimeInMillis();
		String datetime=new java.text.SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date (timeMillis));
		return datetime;
	}
	
	/**
	 * 获取当前时间
	 * */
	public static String nowDateTime2() {
		Long timeMillis=Calendar.getInstance().getTimeInMillis();
		String datetime=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date (timeMillis));
		return datetime;
	}
	
	public static String todayString() {
		Date d=new Date(System.currentTimeMillis());
		SimpleDateFormat sp=new SimpleDateFormat("yyMMddHHmmss");
		return sp.format(d);//获取昨天日期
	}
	
	/**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    } 
    
    /**  
     * 获取前N天的日期时间
     * @param day 前N天天数
     * @return 日期时间格式的字符串
     * @throws ParseException  
     */    
    public static String getFrontDay(int day) throws ParseException    
    {    
    	Date dNow = new Date();   //当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance();  //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, -day);  //设置为前N天
		dBefore = calendar.getTime();   //得到前一天的时间
        String result = DateUtil.dateTimeFormatStr(dBefore);
       return result;           
    }

	/**
	 * 根据开始时间和结束时间返回时间段内的时间集合
	 *
	 * @param beginDate
	 * @param endDate
	 * @return List
	 */
	public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {
		List<Date> lDate = new ArrayList<Date>();
		lDate.add(beginDate);// 把开始时间加入集合
		Calendar cal = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		cal.setTime(beginDate);
		boolean bContinue = true;
		while (bContinue) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			cal.add(Calendar.DAY_OF_MONTH, 1);
			// 测试此日期是否在指定日期之后
			if (endDate.after(cal.getTime())) {
				lDate.add(cal.getTime());
			} else {
				break;
			}
		}
		lDate.add(endDate);// 把结束时间加入集合
		return lDate;
	}
    
	
	public static void main(String[] args) throws ParseException {
		  String start = "2017-11-15";
		  String end = "2017-11-17";
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  Date dBegin = sdf.parse(start);
		  Date dEnd = sdf.parse(end);
		  List<Date> listDate = getDatesBetweenTwoDate(dBegin, dEnd);
		  for(int i=0;i<listDate.size();i++){
			  System.out.println(sdf.format(listDate.get(i)));
		  }
		/*	Date s = DateUtil.StrFormatDate("2015-09-09");
			Date a = DateUtil.StrFormatDate(DateHelper.getCurrentDateStr());
			int d = daysBetween(s,a);
			System.out.println("获取当天日期:" + d);*/
			
			
		/*	String nowdate= DateUtil.dateTimeFormatStr(new Date());//获取当前日期时间的字符串
			System.out.println(nowdate);
			try {
				System.out.println(getFrontDay(100));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 */
	  }
/*	    System.out.println("获取昨天日期:" + DateHelper.getyd());
	    System.out.println("获取当天日期:" + DateHelper.getNowTime("yyyy-MM-dd"));
	    System.out.println("获取本周一日期:" + DateHelper.getMondayOFWeek());
	    System.out.println("获取本周日的日期~:" + DateHelper.getCurrentWeekday());
	    System.out.println("获取上周一日期:" + DateHelper.getPreviousWeekday());
	    System.out.println("获取上周日日期:" + DateHelper.getPreviousWeekSunday());
	    System.out.println("获得相应周的周六的日期:" + DateHelper.getNowTime("yyyy-MM-dd"));
	    System.out.println("获取本月第一天日期:" + DateHelper.getFirstDayOfMonth());
	    System.out.println("获取本月最后一天日期:" + DateHelper.getDefaultDay());
	    System.out.println("获取上月第一天日期:" + DateHelper.getPreviousMonthFirst());
	    System.out.println("获取上月最后一天的日期:" + DateHelper.getPreviousMonthEnd());
	    System.out.println("获取上月最后一天的日期:" + DateHelper.getPreviousMonthEnd());
	    System.out.println("获取上月最后一天的日期:" + DateHelper.returnDate(1));*/
		
	    //System.out.println("获取上月最后一天的日期:" + DateHelper.todayString());
	    /*
	    Timestamp ts = new Timestamp(System.currentTimeMillis());  
	    String format2 = "yyyy-MM-dd HH:mm:ss";
        Date date = new Date();  
        try {  
            date = ts;  
            SimpleDateFormat dateFormat = new SimpleDateFormat(format2);
            System.out.println(dateFormat.format(date));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	  }*/
}
