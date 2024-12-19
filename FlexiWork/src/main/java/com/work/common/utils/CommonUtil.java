/**
 * CommonUtil.java
 * 提供一些通用的工具方法，包括数据类型检查、日期处理、MD5 加密等。
 */
package com.work.common.utils;

import java.io.*; // 导入输入输出相关类
import java.net.URLEncoder; // 导入 URL 编码类
import java.security.MessageDigest; // 导入消息摘要类
import java.security.NoSuchAlgorithmException; // 导入没有该算法异常类
import java.text.SimpleDateFormat; // 导入日期格式化类
import java.util.*; // 导入 Java 集合框架

public final class CommonUtil {
	// 日期时间格式常量
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT = "yyyy-MM-dd";

	// 检查列表是否为空
	public static boolean isEmpty(List list) {
		return (list == null || list.size() == 0);
	}

	// 检查映射是否为空
	public static boolean isEmpty(Map map) {
		return (map == null || map.size() == 0);
	}

	// 检查集合是否为空
	public static boolean isEmpty(Set set) {
		return (set == null) || (set.size() == 0);
	}

	// 检查字符串是否为空
	public static boolean isEmpty(String value) {
		return (value == null || "".equals(value.trim()));
	}

	// 检查对象是否为空
	public static boolean isEmpty(Object value) {
		return (value == null);
	}

	// 检查 Long 类型是否为空
	public static boolean isEmpty(Long value) {
		return (value == null);
	}

	// 检查 Integer 类型是否为空
	public static boolean isEmpty(Integer value) {
		return (value == null);
	}

	// 检查字符串数组是否为空
	public static boolean isEmpty(String[] arrValue) {
		return (arrValue == null || arrValue.length == 0);
	}

	// 检查对象数组是否为空
	public static boolean isEmpty(Object[] arrObject) {
		return (arrObject == null || arrObject.length == 0);
	}

	// 检查值是否不为空
	public static boolean isNotEmpty(List list) {
		return !isEmpty(list);
	}

	public static boolean isNotEmpty(Map map) {
		return !isEmpty(map);
	}

	public static boolean isNotEmpty(Set set) {
		return !isEmpty(set);
	}

	public static boolean isNotEmpty(String value) {
		return !isEmpty(value);
	}

	public static boolean isNotEmpty(Object value) {
		return !isEmpty(value);
	}

	public static boolean isNotEmpty(Long value) {
		return !isEmpty(value);
	}

	public static boolean isNotEmpty(Integer value) {
		return !isEmpty(value);
	}

	public static boolean isNotEmpty(String[] arrValue) {
		return !isEmpty(arrValue);
	}

	public static boolean isNotEmpty(Object[] arrObject) {
		return !isEmpty(arrObject);
	}

	// 检查 Integer 是否不为零
	public static boolean isNotZero(Integer aNum) {
		return (aNum != null && aNum != 0);
	}

	// 获取对象的字符串值，若为空则返回空字符串
	public static String getStringValue(Object value) {
		return isEmpty(value) ? "" : value.toString();
	}

	// 检查两个日期是否相同
	public static boolean isDate(Calendar sysDate, Date dbDate) {
		SimpleDateFormat frm = new SimpleDateFormat("yyyy-MM-dd");
		String dbdateStr = frm.format(dbDate);
		String sysdateStr = frm.format(sysDate.getTime());
		return sysdateStr.equals(dbdateStr);
	}

	// 检查系统日期是否晚于数据库日期
	public static boolean lessSysDateTime(Calendar sysDate, Date dbDate) {
		return sysDate.getTime().getTime() > dbDate.getTime();
	}

	// 格式化日期并返回字符串
	public static String formatDateString(String pattern, int field, int timeLapse) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(field, timeLapse);
		SimpleDateFormat frm = new SimpleDateFormat(pattern);
		return frm.format(calendar.getTime());
	}

	// 将日期格式化为字符串
	public static String dateString(String pattern, Date date) {
		SimpleDateFormat frm = new SimpleDateFormat(pattern);
		return frm.format(date.getTime());
	}

	// MD5 加密
	public static String getMD5Str(String str) {
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			return "";
		}
		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (byte b : byteArray) {
			if (Integer.toHexString(0xFF & b).length() == 1)
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & b));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & b));
		}
		return md5StrBuff.toString();
	}

	// 判断传输的分页参数和页码是否同时为空
	public static boolean pageParmsIsEmpty(String allData, String page) {
		return isEmpty(allData) && isEmpty(page);
	}

	// 判断传入的参数是否为整数
	public static boolean isInt(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isLong(String str) {
		try {
			Long.parseLong(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String datetostring24(Long date) {
		return dateString(DATE_TIME_FORMAT, new Date(date));
	}

	// 将英文双引号转为中文双引号
	public static String enStringToZhString(String paramString) {
		return paramString.replaceAll("\"", "”");
	}

	// 对资源名称进行 URL 编码
	public static String getCurrURLEncoder(String resourceName) {
		try {
			if (resourceName.length() > 6) {
				resourceName = resourceName.substring(6) + "..."; // 最大6个字符
			}
			resourceName = URLEncoder.encode(resourceName, "UTF-8");
			return resourceName.replace("%", "-");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return resourceName; // 返回原始值以防止丢失信息
		}
	}

	// 判断价格是否合法
	public static boolean isDouble(String str) {
		return str != null && str.matches("^[0-9]+[.]?[0-9]*$");
	}

	// 判断是否为整形数字
	public static boolean isInt2(String str) {
		return str != null && str.matches("\\d+");
	}

	// 获取订单返回的 URL
	public static String getOrderBackurl(String url, String orderResult, String userId) {
		userId = userId == null ? "" : userId;
		String parmStr = "orderResult=" + orderResult + "&userId=" + userId;
		if (url != null) {
			if (url.indexOf(".action?") <= 0) {
				url = url + "?" + parmStr;
			} else {
				url = url + "&" + parmStr;
			}
		}
		return url;
	}

	// 计算今天之前的日期
	public static Date getTodayBefore(int intervalDays, String dateFormat) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -intervalDays);
		if ("Date".equals(dateFormat)) {
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
		}
		return calendar.getTime();
	}

	// 计算今天之前的日期（返回 Date 对象）
	public static Date getToday2Before(int intervalDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -intervalDays);
		return calendar.getTime();
	}

	// 计算今天之前的日期（返回字符串）
	public static String getTodayBefore(int intervalDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -intervalDays);
		Date date = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	// 计算今天之后的日期（返回字符串）
	public static String getTodayAfter(int intervalDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, intervalDays);
		Date date = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	// 计算今天之后的日期（返回 Date 对象）
	public static Date getToday2After(int intervalDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, intervalDays);
		return calendar.getTime();
	}

	// 计算指定日期之前的小时数
	public static Date getTodayBeforeHour(int afterHour) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, -afterHour);
		return calendar.getTime();
	}

	// 获取指定时间的偏移日期
	public static Date getDateTime(Date dateTime, int field, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateTime);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	// 获取集合的大小
	public static int getColLen(Collection list) {
		return (list == null) ? 0 : list.size();
	}

	// 判断是否为高清
	public static boolean isHD(String[] type) {
		if (isEmpty(type)) {
			return false;
		}
		for (String str : type) {
			if (str.equals("MPEG-2-HD") || str.equals("H.264-HD")) {
				return true;
			}
		}
		return false;
	}

	public static boolean isHD(String videoType) {
		return !isEmpty(videoType) && videoType.equals("1");
	}

	// 判断高清标志并返回字符串
	public static String isHDStr(String[] type) {
		for (String str : type) {
			if (str.equals("MPEG-2-HD") || str.equals("H.264-HD")) {
				return "1";
			}
		}
		return "0";
	}

	// 根据配置字符串转换为视频类型
	public static String transformConfigToVideoType(String configStr) {
		switch (configStr) {
			case "1": return "MPEG-2-HD";
			case "2": return "MPEG-2-SD";
			case "3": return "H.264-HD";
			case "4": return "H.264-SD";
			default: return "MPEG-2-HD";
		}
	}

	// 去掉字符串中的后续数字
	public static String getNameRemoveDigital(String name) {
		int index = 0;
		for (int i = 0; i < name.length(); i++) {
			char ch = name.charAt(i);
			if (Character.isDigit(ch)) {
				index = i;
				break;
			}
		}
		return index == 0 ? name : name.substring(0, index);
	}

	// 过滤字符串中的字母
	public static String filtrateLetters(String userCode) {
		StringBuilder result = new StringBuilder(userCode);
		for (int i = 0; i < userCode.length(); i++) {
			char ch = userCode.charAt(i);
			if (Character.isLetter(ch)) {
				result.deleteCharAt(i);
				i--; // Adjust index after removal
			}
		}
		return result.toString();
	}

	// 将 "时:分:秒" 格式的时间长度转换为秒数
	public static int time2Second(String runtime) {
		int timeCount = 0;
		try {
			String[] celltime = runtime.split(":");
			int hour = Integer.parseInt(celltime[0]);
			int minute = Integer.parseInt(celltime[1]);
			int second = Integer.parseInt(celltime[2]);
			timeCount = 3600 * hour + 60 * minute + second;
		} catch (Exception e) {
			try {
				timeCount = Integer.parseInt(runtime);
			} catch (Exception e2) {
				return 0;
			}
		}
		return timeCount;
	}

	// 筛选字符并添加后缀
	public static String Filtername(String resourceName, String suffix, int beginIndex, int endIndex) {
		if (isEmpty(resourceName)) {
			resourceName = "";
		}
		if (isEmpty(suffix)) {
			suffix = "";
		}
		if (resourceName.length() > endIndex) {
			return resourceName.substring(beginIndex, endIndex) + suffix;
		}
		return resourceName;
	}

	// 将列表转换为字节数组
	public static byte[] getBytesByList(List list) {
		try {
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			ObjectOutputStream objectOut = new ObjectOutputStream(byteOut);
			objectOut.writeObject(list);
			return byteOut.toByteArray();
		} catch (Exception e) {
			return null;
		}
	}

	// 从字节数组还原为列表
	public static List getListBybytes(byte[] bytes) {
		try {
			ByteArrayInputStream byteIn = new ByteArrayInputStream(bytes);
			ObjectInputStream objectInputStream = new ObjectInputStream(byteIn);
			return (List<Object>) objectInputStream.readObject();
		} catch (Exception e) {
			return null;
		}
	}
}
