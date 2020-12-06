package cn.bjtu.edu.common.utils;

import java.util.*;

/**
 * 空对象判断工具
 */
public final class BlankUtil {
	/**
	 * 常量:空字符
	 */
	public static final String EMPTY_STRING = "";

	/**
	 * 判断字符串为空
	 */
	public static boolean isBlank(final String str) {
		return (str == null) || (str.trim().length() <= 0);
	}

	/**
	 * 判断字符串不为空
	 *
	 * @param str
	 * @return true-不为空 false-为空
	 */
	public static boolean isNotBlank(final String str) {
		return !isBlank(str);
	}

	/**
	 * 判断字符为空
	 *
	 * @param cha
	 * @return
	 */
	public static boolean isBlank(final Character cha) {
		return (cha == null) || cha.equals(' ');
	}

	/**
	 * 判断字符不为空
	 *
	 * @param cha
	 * @return true-不为空 false-为空
	 */
	public static boolean isNotBlank(final Character cha) {
		return !isBlank(cha);
	}

	/**
	 * 判断对象为空
	 */
	public static boolean isBlank(final Object obj) {
		return (obj == null);
	}

	/**
	 * 判断对象不为空
	 *
	 * @param obj
	 * @return true-不为空 false-为空
	 */
	public static boolean isNotBlank(final Object obj) {
		return !isBlank(obj);
	}

	/**
	 * 判断数组为空
	 *
	 * @param objs
	 * @return
	 */
	public static boolean isBlank(final Object[] objs) {
		return (objs == null) || (objs.length <= 0);
	}

	/**
	 * 判断数组不为空
	 *
	 * @param objs
	 * @return true-不为空 false-为空
	 */
	public static boolean isNotBlank(final Object[] objs) {
		return !isBlank(objs);
	}

	/**
	 * 判断Collection为空
	 *
	 * @param obj
	 * @return
	 * @author 张宪新 修改日期：2008-07-28
	 */
	public static boolean isBlank(final Collection<?> obj) {
		return (obj == null) || obj.isEmpty();
	}

	/**
	 * 判断Collection不为空
	 *
	 * @param obj
	 * @return true-不为空 false-为空
	 */
	public static boolean isNotBlank(final Collection<?> obj) {
		return !isBlank(obj);
	}

	/**
	 * 判断Properties是否为空
	 *
	 * @param properties
	 * @return true-不为空 false-为空
	 */
	public static boolean isBlank(final Properties properties) {
		return (properties == null || (properties != null && properties.isEmpty()));
	}

	/**
	 * 判断Properties不为空
	 *
	 * @param properties
	 * @return true-不为空 false-为空
	 */
	public static boolean isNotBlank(final Properties properties) {
		return !isBlank(properties);
	}

	/**
	 * 判断Set是否为空
	 *
	 * @param set
	 * @return
	 */
	public static boolean isBlank(final Set<?> set) {
		return (set == null) || set.isEmpty();
	}

	/**
	 * 判断Set不为空
	 *
	 * @param set
	 * @return true-不为空 false-为空
	 */
	public static boolean isNotBlank(final Set<?> set) {
		return !isBlank(set);
	}

	/**
	 * 判断Serializable是否为空
	 *
	 * @param obj
	 * @return
	 */
	public static boolean isBlank(final java.io.Serializable obj) {
		return obj == null;
	}

	/**
	 * 判断Serializable不为空
	 *
	 * @param set
	 * @return true-不为空 false-为空
	 */
	public static boolean isNotBlank(final java.io.Serializable set) {
		return !isBlank(set);
	}

	/**
	 * 判断Map是否为空
	 *
	 * @param map
	 * @return
	 */
	public static boolean isBlank(final Map<?, ?> map) {
		return (map == null) || map.isEmpty();
	}

	/**
	 * 判断Map不为空
	 *
	 * @param map
	 * @return true-不为空 false-为空
	 */
	public static boolean isNotBlank(final Map<?, ?> map) {
		return !isBlank(map);
	}

	/**
	 * 判断字符串是否为空
	 * 支持三种情况：str=null、str="null"、str=""
	 *
	 * @param str
	 * @return true-不为空 false-为空
	 */
	public static boolean isNull(String str) {
		return isBlank(str) || "null".equals(str);
	}

	/**
	 * 判断字符串不为空
	 * 三种情况：str=null、str="null"、str=""
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNotNull(String str) {
		return !isNull(str);
	}

}
