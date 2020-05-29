package com.mcoding.emis.goods.common.utils;

import java.io.File;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * StringUtil 字符串辅助类
 * 
 * @author Moshow
 */
public class StringUtil {
	String result[];
	String testStr;

	/**
	 * 截取身份证最后六位
	 * 
	 * @author Moshow
	 */
	public String getLast6Id(String id) {
		Pattern p = Pattern.compile("[0-9]+[X|x]{1}");
		Matcher m = p.matcher(id);
		boolean b = m.matches();
		if (b) {
			id = id.substring(id.length() - 7, id.length() - 1);
		} else {
			id = id.substring(id.length() - 6);
		}
		return id;
	}

	/**
	 * Windows下获取WebRoot物理路径(Linux下无效)
	 * 
	 * @author Moshow
	 */
	public String getPhypath(int type) {
		String pathStr = this.getClass().getClassLoader().getResource("").getPath();
		// 对 Windows下获取 物理路径做特殊处理
		if ("\\".equals(File.separator)) {
			pathStr = pathStr.substring(1).replaceAll("/", "\\\\");
			pathStr = pathStr.replaceAll("%20", " ");
			switch (type) {
			case 1:
				pathStr = pathStr.replaceAll("WEB-INF\\\\classes\\\\", "photos\\\\");
				break;
			case 2:
				pathStr = pathStr.replaceAll("build\\\\classes\\\\", "WebRoot\\\\res\\\\images\\\\print\\\\");
				break;
			case 3:
				pathStr = pathStr.replaceAll("WEB-INF\\\\classes\\\\", "res\\\\download\\\\");
				break;
			case 4:
				pathStr = pathStr.replaceAll("WEB-INF\\\\classes\\\\", "resources\\\\download\\\\");
				break;
			case 5:
				pathStr = pathStr.replaceAll("WEB-INF\\\\classes\\\\", "resources\\\\qrcode\\\\");
				break;
			}

		}
		return pathStr;
	}

	/**
	 * 根据字符切割字符串(支持.,,&、空格)
	 * 
	 * @param strs传入要切割的字符串
	 * @return list到处切割好的List<String>
	 * @author Moshow
	 */
	public static List<String> toSplit(String strs) {
		// strs = "Th is,+is.-a !:;&te、st?";
		List<String> list = new ArrayList<String>();
		if (strs != null && strs != "") {
			String[] result = strs.split("\\.|\\,|\\,|\\&|\\、|\\s");
			for (int i = 0; i < result.length; i++) {
				list.add(result[i]);
			}
		}
		return list;
	}

	/**
	 * 根据字符切割字符串(支持.,,&、空格)
	 * 
	 * @param strs传入要切割的字符串
	 * @return map到处切割好的Map<String,String>
	 * @author Moshow&&Tisshit
	 */
	public static Map<String, String> toSplit2(String strs) {
		Map<String, String> map = new HashMap<String, String>();
		if (strs != null && strs != "") {
			String[] result = strs.split("\\.|\\,|\\,|\\&|\\、|\\s");
			for (int i = 0; i < result.length; i++) {
				map.put(result[i], result[i]);
			}
		}
		return map;
	}

	/**
	 * 判断A是否包含B(只能去标点)
	 * 
	 * @author Moshow&&Tisshit
	 */
	public static Integer isRe(String strs, String reStr) {
		Map<String, String> map = new HashMap<String, String>();
		if (strs != null && strs != "") {
			String[] result = strs.split("\\.|\\,|\\,|\\&|\\、|\\s");
			for (int i = 0; i < result.length; i++) {
				map.put(result[i], result[i]);
			}
		}
		if (map.get(reStr) != null) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 * 
	 * @param strDate传入要转换的字符串
	 * @return strtodate时间 yyyy-MM-dd
	 * @author Moshow
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	@SuppressWarnings("deprecation")
	public static List<String> getStartEndTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		List<String> list = new ArrayList<String>();
		String xxx = "";
		int y = 0, m = 0, d = 0;
		y = date.getYear();
		m = date.getMonth();
		d = date.getDate();
		// if (m<1) {
		// y--;m=11;
		// }else {
		// m--;
		// }
		date.setYear(y);
		date.setMonth(m);
		date.setDate(d);
		xxx = formatter.format(date);
		list.add(xxx);
		// System.out.println(xxx);
		date.setMonth(m + 1);
		xxx = formatter.format(date);
		list.add(xxx);
		// System.out.println(xxx);
		return list;
	}

	/**
	 * 根据字符切割成三段
	 * 
	 * @param strs传入要切割的字符串
	 * @return list到处切割好的List<String>
	 * @author Moshow
	 */
	public static List<String> split2triple(String strs) {
		// strs = "Th is,+is.-a !:;&te、st?";
		List<String> list = new ArrayList<String>();
		if (strs != null && strs != "") {
			String[] result = strs.split("\\.|\\,|\\,|\\&|\\、|\\s");
			for (int i = 0; i < result.length; i++) {
				list.add(result[i]);
			}
		}
		Integer n = list.size() / 3;
		String resultStr = "";
		List<String> resultList = new ArrayList<String>();
		for (int i = 0; i < n; i++) {
			resultStr = list.get(0 + i) + "," + list.get(n + i) + "," + list.get(2 * n + i);
			resultList.add(resultStr);
		}
		return resultList;
	}

	/**
	 * 根据字符切割成二段
	 * 
	 * @param strs传入要切割的字符串
	 * @return list到处切割好的List<String>
	 * @author Moshow
	 */
	public static List<String> split2two(String strs) {
		// strs = "Th is,+is.-a !:;&te、st?";
		List<String> list = new ArrayList<String>();
		if (strs != null && strs != "") {
			String[] result = strs.split("\\.|\\,|\\,|\\&|\\、|\\s");
			for (int i = 0; i < result.length; i++) {
				list.add(result[i]);
			}
		}
		Integer n = list.size() / 2;
		String resultStr = "";
		List<String> resultList = new ArrayList<String>();
		for (int i = 0; i < n; i++) {
			resultStr = list.get(0 + i) + "," + list.get(n + i);
			resultList.add(resultStr);
		}
		return resultList;
	}

	/**
	 * 根据字符切割成一段
	 * 
	 * @param strs传入要切割的字符串
	 * @return list到处切割好的List<String>
	 * @author Moshow
	 */
	public static List<String> split2one(String strs) {
		// strs = "Th is,+is.-a !:;&te、st?";
		List<String> list = new ArrayList<String>();
		if (strs != null && strs != "") {
			String[] result = strs.split("\\.|\\,|\\,|\\&|\\、|\\s");
			for (int i = 0; i < result.length; i++) {
				list.add(result[i]);
			}
		}
		Integer n = list.size() / 1;
		String resultStr = "";
		List<String> resultList = new ArrayList<String>();
		for (int i = 0; i < n; i++) {
			resultStr = list.get(0 + i);
			resultList.add(resultStr);
		}
		return resultList;
	}

	public static String GetRadomCode() {
		String[] beforeShuffle = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		List list = Arrays.asList(beforeShuffle);
		Collections.shuffle(list);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
		}
		String afterShuffle = sb.toString();
		String result = afterShuffle.substring(3, 9);
		return result;
	}

	/**
	 * 去除style
	 * 
	 * @param 原始内容
	 * @return 过滤后内容
	 */
	public static String trimStyle(String content) {
		String regEx = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";

		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(content);
		String result = content;
		if (m.find()) {
			result = m.replaceAll("");
		}
		return result;
	}

	/**
	 * 去掉空格和换行符
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			str = str.trim();
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	/**
	 * 根据换行符切割字段串
	 * 
	 * @param str
	 * @return
	 */
	public static List<String> splitByLine(String str) {
		List<String> list = null;
		String[] arr = str.split("\n");
		if (null != arr && arr.length > 0) {
			list = new ArrayList<String>();
			for (String line : arr) {
				if (StringUtils.isBlank(line)) {
					continue;
				}
				line = replaceBlank(line);
				if (StringUtils.isBlank(line)) {
					continue;
				}
				list.add(line);
			}
		}
		if (null != list && list.size() > 0) {
			return list;
		}

		return null;
	}

	/**
	 * 从字符串中查找浮点数
	 * 
	 * @param str
	 * @return
	 */
	public static List<String> findNumberics(String str) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		String numbericRegex = "\\d+(\\.\\d+)?";
		Pattern numbericPattern = Pattern.compile(numbericRegex);
		Matcher numbericMatcher = numbericPattern.matcher(str);
		List<String> list = new ArrayList<String>();
		while (numbericMatcher.find()) {
			list.add(numbericMatcher.group());
		}
		if (null == list || list.isEmpty()) {
			return null;
		}
		return list;
	}
	
	/**
	 * 去除冒号，包括中文冒号
	 * 
	 * @param str
	 * @return
	 */
	public static String wipeColon(String str) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		str = str.replaceAll("[:：]", "");
		return str;
	}
	
	/**
	 * 去除右括号，包括中文括号
	 * 
	 * @param str
	 * @return
	 */
	public static String wipeRightBracket(String str) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		str = str.replaceAll("[)）]", "");
		return str;
	}
	
	
	/**
	 * 用于存放数组中出现相同的元素
	 * 
	 * @param strs
	 * @return
	 */
	public static boolean compareStrArray(String[] strs){
		Set<String> set= new HashSet<String>();
	    boolean result = false;
	   //从第一个元素开始比较元素是不是有相同的出现
	   for(int i=0;i<strs.length;i++){
	        for(int j=i+1;j<strs.length;j++){
	            //如果元素相同，保存到set中
	            if(strs[i].equals(strs[j])){
	                 set.add(strs[i]);
	                 result = true;
	            }
	       }
	   }
	return result;
	}

	public static void main(String[] args) {
		// new StringUtil().stringSplit3("dasd,&$@,ada","," );
		// StringUtil sUtil=new StringUtil();
		// sUtil.toSplit("");
		// System.out.println(StringUtil.okToMeet("高级班","初级班","高级班"));
		// System.out.println(StringUtil.getStartEndTime());
		// System.out.println(StringUtil.split2triple("47,47,1,1,11,12"));
		// System.out.println(StringUtil.split2one("47,46,11,12"));
		// System.err.println(StringUtil.toSplit2("33,3,333"));
		// System.err.println(StringUtil.toSplit2("33,3,333").get("4"));

		// String BODY_JSON =
		// "{\"phone\":\"15989233965\",\"cpOrderNos\":\"YD_36c42573d91a44e89829faf3230d9ed2\"}";
		// String API_KEY =
		// "b292c17df4f64549ba83117ff248512c53fa4b9f59464444bfaede899850d782";
		// System.out.println(BODY_JSON+API_KEY);
		// String Auth = MD5Util.MD5Encode(BODY_JSON+API_KEY, "UTF-8");
		// System.out.println(Auth);

		// String str = "列腺彩超\r\n刃腺前列腺钙〈匕灶\n\n纽胞i十数 增高 (结果:10.60 范围 : 4.0_10.o
		// 10^9/L)\n胃′患胆固醇增高 (结果:6.10范围:0-5.70mmo丨/L)\n\n \n\n";
		// String result = replaceBlank(str);
		// System.out.println(result);
		// System.out.println(result.indexOf("彩超"));
		// List<String> list = splitByLine(str);
		// System.out.println(list);

		try {
			// 全匹配
			 String str = "结果:6.10 范围 : 0-5.70 mmol/L)";
//			String str = "结果:10.60 范围 : 4.0-10.0 10^9/L)";
			// String regex = "[-+]?[0-9]*\\.?[0-9]+";
			// String regex = "^[-+]?[0-9]*\\.?[0-9]+$";
			String regex = "\\d+(\\.\\d+)?";
//			String regex = "[\\s]+[^\\s]+[)）]";

			// String str = "前列腺：前列腺腺钙化灶";
			// String regex = "[^\\s]+[:：][^\\s]+";

			// String str = "白细胞计数 增高: (结果:10.60 范围 : 4.0-10.0 10^9/L)";
			// String str = "血清总胆固醇 增高: (结果:6.10 范围 : 0-5.70 mmol/L)";
			// String regex =
			// "[^\\s]+[\\s]+[^\\s]+[:：][\\s]*[(（](结果)[:：]([-+]?[0-9]*\\.?[0-9]+)[\\s]+(范围)[\\s]*[:：][\\s]*([-+]?[0-9]*\\.?[0-9]+)[-_]([-+]?[0-9]*\\.?[0-9]+)[\\s]+[^\\s]+[)）]";

			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(str);
			if (m.matches()) {
				System.out.println("match");
			} else {
				System.out.println("no match");
			}
			List<String> list = new ArrayList<String>();
			while (m.find()) {
				list.add(m.group());
			}
			System.out.println(list.size());
			for (String temp : list) {
				System.out.println(wipeRightBracket(temp.trim()));
			}
			System.out.println(Float.valueOf("6.10"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
