package com.mcoding.emis.goods.mallGame.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class MallgameStakeRuleUtil {

	/**
	 * 获取下注积分列表
	 * 
	 * @param map
	 * @return
	 */
	private static List<Integer> readAllValues(
			LinkedHashMap<String, Integer> map) {
		List<Integer> values = new ArrayList<>();

		Set<Entry<String, Integer>> entrySet = map.entrySet();
		Iterator<Entry<String, Integer>> iter = entrySet.iterator();
		while (iter.hasNext()) {
			Entry<String, Integer> entity = iter.next();
			int value = entity.getValue();
			values.add(value);
		}
		return values;
	}

	/**
	 * 计算每个下注积分的百分比
	 * 
	 * @param values
	 * @return
	 */
	private static List<Integer> changeNumByPercentage(List<Integer> values) {
		List<Integer> perceList = new ArrayList<>();
		float sum = 0;
		for (int value : values) {
			sum += value;
		}
		int cacul = 0;
		for (int i = 0; i < values.size(); i++) {
			if (i + 1 != values.size()) {
				int result = (int) (values.get(i) / sum * 100);
				cacul += result;
				perceList.add(result);
			} else {
				perceList.add(100 - cacul);
			}
		}
		return perceList;
	}

	/**
	 * 计算中奖者的列表序号
	 * 
	 * @param values
	 * @return
	 */
	private static int theWinnerOrder(List<Integer> values) {
		System.out.println(values);
		int randNum = (int) (Math.random() * 100);
		System.out.println(randNum);
		int flag = -1;
		int sum = 0;
		for (int i = 0; i < values.size(); i++) {
			sum += values.get(i);
			if (randNum <= sum) {
				flag = i;
				break;
			}
		}
		System.out.println(values.get(flag) + " and order is" + flag);
		return flag;
	}

	/**
	 * 获取中奖者的openId
	 * 
	 * @param map
	 * @param winnerOrder
	 * @return
	 */
	private static String findTheWinnerOpenId(Map<String, Integer> map,
			int winnerOrder) {
		Set<Entry<String, Integer>> entrySet = map.entrySet();
		Iterator<Entry<String, Integer>> iter = entrySet.iterator();

		String openId = "";
		int count = 0;
		while (iter.hasNext()) {
			Entry<String, Integer> entity = iter.next();
			if (count == winnerOrder) {
				openId = entity.getKey();
				break;
			}
			count++;
		}

		return openId;
	}

	public static String getTheWinnerOpenId(LinkedHashMap<String, Integer> map) {
		String openId = "";
		if (map.size() != 0) {
			List<Integer> values = readAllValues(map);
			List<Integer> perceList = changeNumByPercentage(values);
			int order = theWinnerOrder(perceList);
			openId = findTheWinnerOpenId(map, order);
		}
		return openId;
	}

	public static void main(String[] args) {
		LinkedHashMap<String, Integer> idMapValue = new LinkedHashMap<>();
		idMapValue.put("1sdfse", 21);
		idMapValue.put("2asdf3", 29);
		idMapValue.put("90sdfe", 49);
		idMapValue.put("pop992", 77);
		idMapValue.put("sdfs23", 108);
		idMapValue.put("dsfeds", 999);
		idMapValue.put("sdfer2", 100);
		idMapValue.put("34sdfd", 99);
		idMapValue.put("dsdfga", 39);
		idMapValue.put("pdosps", 9);

		String openId = getTheWinnerOpenId(idMapValue);

		if (StringUtils.isEmpty(openId)) {
			System.out.println("openId为空");
		} else {
			System.out.println("openId is " + openId);
		}
	}
}
