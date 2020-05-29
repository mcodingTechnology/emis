package com.mcoding.emis.goods.common.utils;

public class Constant {
	/**
	 * 标题过长后缀："..."
	 */
	public static final String SUFFIX = "...";
	public static final String SECRET_KEY = "tissonco";
	public static final String PHONE_PATTERN = "^(\\(\\d{3,4}\\)|\\d{3,4}-)?\\d{7,8}$";
	public static final String USERNAME_PATTERN = "^[a-z]([a-z0-9_]){3,31}";// 以a~z打头，后跟3~31位小写字母或数字或下划线

	/**
	 * 返回的系统编码
	 */
	public static final String SAVE_SUCCESS_CODE = "saveSuccess"; // 保存成功
	public static final String SAVE_SUCCESS_MSG = "保存成功";
	public static final String SAVE_FAILURE_CODE = "saveFailure"; // 保存失败
	public static final String SAVE_FAILURE_MSG = "保存失败";
	public static final String UPDATE_SUCCESS_CODE = "updateSuccess"; // 更新成功
	public static final String UPDATE_SUCCESS_MSG = "更新成功";
	public static final String UPDATE_FAILURE_CODE = "updateFailure"; // 更新失败
	public static final String UPDATE_FAILURE_MSG = "更新失败";
	public static final String DEL_SUCCESS_CODE = "delSuccess"; // 删除成功
	public static final String DEL_SUCCESS_MSG = "删除成功";
	public static final String DEL_FAILURE_CODE = "delFailure"; // 删除失败
	public static final String DEL_FAILURE_MSG = "删除失败";


	// 分隔符
	public static final String SEPARATOR = ",";

	// 项目根路径
	public static String BASE_PATH = null;


}
