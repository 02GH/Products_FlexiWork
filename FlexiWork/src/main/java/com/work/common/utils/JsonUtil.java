package com.work.common.utils; // 定义该类所在的包

import com.google.gson.Gson; // 引入 Gson 类，用于 JSON 序列化
import com.google.gson.GsonBuilder; // 引入 GsonBuilder 类，用于构建 Gson 实例

import java.util.List; // 引入 List 接口
import java.util.Map; // 引入 Map 接口

public class JsonUtil {
	/**
	 * 将对象转换为 JSON 字符串，并封装成标准响应格式
	 * @param obj 要转换的对象
	 * @return JSON 字符串
	 */
	public static String objectToJson(Object obj) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create(); // 创建 Gson 实例并设置日期格式
		String jsonString = "{";
		jsonString += "\"code\": \"0\", "; // 表示处理成功的代码
		jsonString += "\"msg\": \"处理成功\","; // 成功消息
		jsonString += "\"objects\":";
		jsonString += gson.toJson(obj); // 将对象转换为 JSON
		jsonString += "} ";
		return jsonString; // 返回最终的 JSON 字符串
	}

	/**
	 * 根据列表和总记录数构建 JSON 响应
	 * @param list 要编码的列表
	 * @param totalCount 总记录数
	 * @return JSON 字符串
	 */
	public static String buildJsonByTotalCount(List list, int totalCount) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create(); // 创建 Gson 实例并设置日期格式
		String jsonString = "{";
		String JsonContext = ""; // JSON 内容
		int count = 0; // 记录数量

		if (!CommonUtil.isEmpty(list)) { // 检查列表是否为空
			JsonContext = gson.toJson(list); // 将列表转换为 JSON
			count = totalCount; // 记录总数
			jsonString += "\"code\": \"0\", "; // 处理成功代码
			jsonString += "\"msg\": \"处理成功\","; // 成功消息
		} else {
			JsonContext = "[] "; // 如果列表为空，返回空数组
			count = 0; // 记录数量为 0
			jsonString += "\"code\": \"1\", "; // 处理失败代码
			jsonString += "\"msg\": \"此查询无数据\","; // 失败消息
		}

		jsonString += "\"count\": " + count + ","; // 添加记录数
		jsonString += "\"data\":";
		jsonString += JsonContext; // 添加 JSON 内容
		jsonString += "} ";
		return jsonString; // 返回 JSON 字符串
	}

	/**
	 * 根据列表构建 JSON 响应
	 * @param list 要编码的列表
	 * @return JSON 字符串
	 */
	public static String buildJson(List list) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create(); // 创建 Gson 实例并设置日期格式
		String jsonString = "{";
		String JsonContext = ""; // JSON 内容
		int count = 0; // 记录数量

		if (!CommonUtil.isEmpty(list)) { // 检查列表是否为空
			JsonContext = gson.toJson(list); // 将列表转换为 JSON
			count = list.size(); // 记录数量
			jsonString += "\"code\": \"0\", "; // 处理成功代码
			jsonString += "\"msg\": \"处理成功\","; // 成功消息
		} else {
			JsonContext = "[] "; // 如果列表为空，返回空数组
			count = 0; // 记录数量为 0
			jsonString += "\"code\": \"1\", "; // 处理失败代码
			jsonString += "\"msg\": \"处理失败,没有数据\","; // 失败消息
		}

		jsonString += "\"count\": " + count + ","; // 添加记录数
		jsonString += "\"objects\":";
		jsonString += JsonContext; // 添加 JSON 内容
		jsonString += "} ";
		return jsonString; // 返回 JSON 字符串
	}

	/**
	 * 构建返回失败的 JSON 响应
	 * @param code 错误代码
	 * @param message 错误消息
	 * @return JSON 字符串
	 */
	public static String buildFalseJson(int code, String message) {
		String jsonString = "{";
		jsonString += "\"code\": \"" + code + "\", "; // 添加错误代码
		jsonString += "\"msg\": \"" + message + "\" "; // 添加错误消息
		jsonString += "}";
		return jsonString; // 返回 JSON 字符串
	}

	/**
	 * 构建返回失败的 JSON 响应，包含额外信息
	 * @param code 错误代码
	 * @param message1 消息1
	 * @param message2 消息2
	 * @return JSON 字符串
	 */
	public static String buildFalseJson2(int code, String message1, String message2) {
		String jsonString = "{";
		jsonString += "\"code\": \"" + code + "\", "; // 添加错误代码
		jsonString += "\"url\": \"" + message1 + "\","; // 添加 URL 信息
		jsonString += "\"fileName\": \"" + message2 + "\" "; // 添加文件名信息
		jsonString += "}";
		return jsonString; // 返回 JSON 字符串
	}

	/**
	 * 构建检查 jar 包的 JSON 响应
	 * @param result 操作结果
	 * @param detail 详细信息
	 * @param content 内容
	 * @return JSON 字符串
	 */
	public static String buildCheckJarJson(Boolean result, String detail, String content) {
		String jsonString = "{";
		jsonString += "\"result\": " + result + ", "; // 添加结果
		jsonString += "\"detail\": \"" + detail + "\", "; // 添加详情
		jsonString += "\"content\": \"" + content + "\""; // 添加内容
		jsonString += "}";
		return jsonString; // 返回 JSON 字符串
	}

	/**
	 * 将 Map 转换为 JSON 字符串并封装成标准响应格式
	 * @param m 要转换的 Map
	 * @return JSON 字符串
	 */
	public static String MapToJson(Map<String, Object> m) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create(); // 创建 Gson 实例并设置日期格式
		String jsonString = "{";
		String JsonContext = "";
		int count = 0; // 记录数量

		JsonContext = gson.toJson(m); // 将 Map 转换为 JSON
		count = m.size(); // 获取记录数量
		jsonString += "\"code\": \"0\", "; // 处理成功代码
		jsonString += "\"msg\": \"处理成功\","; // 成功消息
		jsonString += "\"count\": " + count + ","; // 添加记录数
		jsonString += "\"data\":";
		jsonString += JsonContext; // 添加 JSON 内容
		jsonString += "} ";
		return jsonString; // 返回 JSON 字符串
	}

	/**
	 * 构建简单的 JSON 响应
	 * @param code 响应代码
	 * @param message 响应消息
	 * @return JSON 字符串
	 */
	public static String buildJson(String code, String message) {
		String jsonString = "{";

		jsonString += "\"code\": \"" + code + "\", "; // 响应代码
		jsonString += "\"msg\": \"" + message + "\""; // 响应消息

		jsonString += "} ";
		return jsonString; // 返回 JSON 字符串
	}
}
