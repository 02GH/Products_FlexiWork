/*BaseUtil.java 是一个工具类，主要用于处理与 HTTP 响应相关的操作，特别是将 Java 对象转换为 JSON 格式并返回给客户端。它提供了一些常用的方法来简化在 Spring MVC 环境中处理 HTTP 响应的过程。*/
package com.work.common.utils; // 定义该类所在的包

import com.alibaba.fastjson.JSON; // 引入阿里巴巴的 FastJSON 库，用于 JSON 序列化
import org.apache.log4j.Logger; // 引入 Log4j 库，用于日志记录
import org.springframework.web.context.request.RequestContextHolder; // 引入 Spring 的请求上下文持有者
import org.springframework.web.context.request.ServletWebRequest; // 引入 ServletWebRequest，用于获取 HTTP 请求
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter; // 引入 WebMvcConfigurerAdapter

import javax.servlet.http.HttpServletResponse; // 引入 HttpServletResponse，用于处理 HTTP 响应
import java.io.IOException; // 引入 IOException 类
import java.io.PrintWriter; // 引入 PrintWriter 类，用于向响应写入数据
import java.util.Map; // 引入 Map 类

public class BaseUtil extends WebMvcConfigurerAdapter {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BaseUtil.class); // 创建 Logger 实例，用于记录日志

	/**
	 * 将对象转换为 JSON，写入 HTTP 响应
	 * @param response HTTP 响应对象
	 * @param object 要转换为 JSON 的对象
	 */
	public void writeJson(HttpServletResponse response, Object object) {
		try {
			// 将对象转换为 JSON 字符串，格式化日期
			String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
			response.setContentType("text/html;charset=utf-8"); // 设置响应内容类型
			response.getWriter().write(json); // 写入 JSON 数据
			response.getWriter().flush(); // 刷新输出流
		} catch (IOException e) {
			logger.error("返回流失败"); // 记录错误日志
		} finally {
			try {
				response.getWriter().close(); // 关闭输出流
			} catch (IOException e) {
				logger.error("关闭输出流失败"); // 记录错误日志
			}
		}
	}

	/**
	 * 输出 JSON 结果到 HTTP 响应
	 * @param response HTTP 响应对象
	 * @param jsonResult 要输出的 JSON 字符串
	 */
	protected void output(HttpServletResponse response, String jsonResult) {
		try {
			response.setContentType("application/json; charset=UTF-8"); // 设置内容类型
			response.setHeader("Access-Control-Allow-Origin", "*"); // 允许跨域请求
			response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS"); // 允许的请求方法
			response.setHeader("Access-Control-Allow-Credentials", "true"); // 允许发送凭据
			response.setContentLength(jsonResult.getBytes("UTF-8").length); // 设置内容长度
			logger.debug("response json: " + jsonResult); // 记录调试日志
			PrintWriter printWriter = response.getWriter(); // 获取 PrintWriter 对象
			printWriter.write(jsonResult); // 写入 JSON 数据
			printWriter.flush(); // 刷新输出流
		} catch (Exception e) {
			logger.error("Error output json data to the client!!!orginal json=" + jsonResult, e); // 记录错误日志
		}
	}

	/**
	 * 将字符串写入 HTTP 响应
	 * @param str 要写入的字符串
	 */
	protected void writeString(String str) {
		HttpServletResponse response = ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse(); // 获取当前 HTTP 响应对象
		response.setContentType("application/json; charset=UTF-8"); // 设置内容类型
		PrintWriter printWriter;
		try {
			printWriter = response.getWriter(); // 获取 PrintWriter 对象
			printWriter.write(str); // 写入字符串
			printWriter.flush(); // 刷新输出流
		} catch (IOException e) {
			e.printStackTrace(); // 打印异常堆栈
		}
	}

	// 用于存储会话数据的 Map
	private Map<String, Object> session;

	// 获取会话数据
	public Map<String, Object> getSession() {
		return session; // 返回会话数据
	}

	// 设置会话数据
	public void setSession(Map<String, Object> arg0) {
		session = arg0; // 设置会话数据
	}
}