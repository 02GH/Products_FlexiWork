package com.work.controller;

import com.work.common.utils.BaseUtil;
import com.work.common.utils.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

// 控制器注解，标识这是一个Spring MVC控制器
@Controller
@RequestMapping("/imageUpload") // 请求路径前缀
public class ImageUploadController extends BaseUtil {

	// 上传头像
	@RequestMapping(value = "/headerImage")
	public void headerImage(@RequestParam(value = "file") CommonsMultipartFile file, HttpServletRequest request,
							HttpServletResponse response) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss"); // 时间格式化
		String respJson = null;

		// 检查上传文件是否为空
		if (file == null) {
			respJson = JsonUtil.buildFalseJson(-1, "上传文件为空!");
			output(response, respJson);
			return;
		}

		// 检查文件大小
		if (file.getSize() > 5000000) { // 限制在5M以内
			respJson = JsonUtil.buildFalseJson(-2, "文件大小限制在5M以内!");
			output(response, respJson);
			return;
		}

		// 生成唯一文件名
		Date now = new Date();
		String random = UUID.randomUUID().toString().replace("-", "").substring(0, 5); // 生成随机字符串
		String houzui = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),
				file.getOriginalFilename().length()); // 获取文件后缀
		String filename = format.format(now) + random + houzui; // 文件名

		// 获取文件存储路径
		String path = request.getServletContext().getRealPath("/") + "headerImages" + "/" + filename;
		String contextPath = request.getContextPath(); // 项目名
		String port = request.getServerPort() == 80 ? "" : ":" + request.getServerPort(); // 处理端口
		String url = request.getScheme() + "://" + request.getServerName() + port + contextPath + "/headerImages/" + filename; // 文件访问URL

		// 创建文件并保存
		File oldFile = new File(path);
		file.transferTo(oldFile);
		respJson = JsonUtil.buildFalseJson(0, url); // 返回文件的URL
		output(response, respJson);
	}

	// 上传兼职图片
	@RequestMapping(value = "/workImage")
	public void workImage(@RequestParam(value = "file") CommonsMultipartFile file, HttpServletRequest request,
						  HttpServletResponse response) throws Exception {
		// 与headerImage方法相似，执行文件上传操作
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String respJson = null;

		if (file == null) {
			respJson = JsonUtil.buildFalseJson(-1, "上传文件为空!");
			output(response, respJson);
			return;
		}
		if (file.getSize() > 5000000) {
			respJson = JsonUtil.buildFalseJson(-2, "文件大小限制在5M以内!");
			output(response, respJson);
			return;
		}
		Date now = new Date();
		String random = UUID.randomUUID().toString().replace("-", "").substring(0, 5);
		String houzui = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),
				file.getOriginalFilename().length());
		String filename = format.format(now) + random + houzui;
		String path = request.getServletContext().getRealPath("/") + "workImages" + "/" + filename;

		String contextPath = request.getContextPath();
		String port = request.getServerPort() == 80 ? "" : ":" + request.getServerPort();
		String url = request.getScheme() + "://" + request.getServerName() + port + contextPath + "/workImages/" + filename;

		// 创建文件并保存
		File oldFile = new File(path);
		file.transferTo(oldFile);
		respJson = JsonUtil.buildFalseJson(0, url);
		output(response, respJson);
	}

	// 上传帖子图片
	@RequestMapping(value = "/forumImage")
	public void forumImage(@RequestParam(value = "file") CommonsMultipartFile file, HttpServletRequest request,
						   HttpServletResponse response) throws Exception {
		// 与headerImage方法相似，执行文件上传操作
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String respJson = null;

		if (file == null) {
			respJson = JsonUtil.buildFalseJson(-1, "上传文件为空!");
			output(response, respJson);
			return;
		}
		if (file.getSize() > 5000000) {
			respJson = JsonUtil.buildFalseJson(-2, "文件大小限制在5M以内!");
			output(response, respJson);
			return;
		}
		Date now = new Date();
		String random = UUID.randomUUID().toString().replace("-", "").substring(0, 5);
		String houzui = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),
				file.getOriginalFilename().length());
		String filename = format.format(now) + random + houzui;
		String path = request.getServletContext().getRealPath("/") + "forumImages" + "/" + filename;

		String contextPath = request.getContextPath();
		String port = request.getServerPort() == 80 ? "" : ":" + request.getServerPort();
		String url = request.getScheme() + "://" + request.getServerName() + port + contextPath + "/forumImages/" + filename;

		// 创建文件并保存
		File oldFile = new File(path);
		file.transferTo(oldFile);
		respJson = JsonUtil.buildFalseJson(0, url);
		output(response, respJson);
	}

	// 上传聊天图片
	@RequestMapping(value = "/chatImage")
	public void chatImage(@RequestParam(value = "file") CommonsMultipartFile file, HttpServletRequest request,
						  HttpServletResponse response) throws Exception {
		// 与headerImage方法相似，执行文件上传操作
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String respJson = null;

		if (file == null) {
			respJson = JsonUtil.buildFalseJson(-1, "上传文件为空!");
			output(response, respJson);
			return;
		}
		if (file.getSize() > 5000000) {
			respJson = JsonUtil.buildFalseJson(-2, "文件大小限制在5M以内!");
			output(response, respJson);
			return;
		}
		Date now = new Date();
		String random = UUID.randomUUID().toString().replace("-", "").substring(0, 5);
		String houzui = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),
				file.getOriginalFilename().length());
		String filename = format.format(now) + random + houzui;
		String path = request.getServletContext().getRealPath("/") + "chatImages" + "/" + filename;

		String contextPath = request.getContextPath();
		String port = request.getServerPort() == 80 ? "" : ":" + request.getServerPort();
		String url = request.getScheme() + "://" + request.getServerName() + port + contextPath + "/chatImages/" + filename;

		// 创建文件并保存
		File oldFile = new File(path);
		file.transferTo(oldFile);
		respJson = JsonUtil.buildFalseJson(0, url);
		output(response, respJson);
	}

	// 上传信件图片
	@RequestMapping(value = "/mailboxImage")
	public void mailboxImage(@RequestParam(value = "file") CommonsMultipartFile file, HttpServletRequest request,
							 HttpServletResponse response) throws Exception {
		// 与headerImage方法相似，执行文件上传操作
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String respJson = null;

		if (file == null) {
			respJson = JsonUtil.buildFalseJson(-1, "上传文件为空!");
			output(response, respJson);
			return;
		}
		if (file.getSize() > 5000000) {
			respJson = JsonUtil.buildFalseJson(-2, "文件大小限制在5M以内!");
			output(response, respJson);
			return;
		}
		Date now = new Date();
		String random = UUID.randomUUID().toString().replace("-", "").substring(0, 5);
		String houzui = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),
				file.getOriginalFilename().length());
		String filename = format.format(now) + random + houzui;
		String path = request.getServletContext().getRealPath("/") + "mailboxImages" + "/" + filename;

		String contextPath = request.getContextPath();
		String port = request.getServerPort() == 80 ? "" : ":" + request.getServerPort();
		String url = request.getScheme() + "://" + request.getServerName() + port + contextPath + "/mailboxImages/" + filename;

		// 创建文件并保存
		File oldFile = new File(path);
		file.transferTo(oldFile);
		respJson = JsonUtil.buildFalseJson(0, url);
		output(response, respJson);
	}

	// 上传系统图片、广告、轮播、公告
	@RequestMapping(value = "/systemImage")
	public void systemImage(@RequestParam(value = "file") CommonsMultipartFile file, HttpServletRequest request,
							HttpServletResponse response) throws Exception {
		// 与headerImage方法相似，执行文件上传操作
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String respJson = null;

		if (file == null) {
			respJson = JsonUtil.buildFalseJson(-1, "上传文件为空!");
			output(response, respJson);
			return;
		}
		if (file.getSize() > 5000000) {
			respJson = JsonUtil.buildFalseJson(-2, "文件大小限制在5M以内!");
			output(response, respJson);
			return;
		}
		Date now = new Date();
		String random = UUID.randomUUID().toString().replace("-", "").substring(0, 5);
		String houzui = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),
				file.getOriginalFilename().length());
		String filename = format.format(now) + random + houzui;
		String path = request.getServletContext().getRealPath("/") + "systemImages" + "/" + filename;

		String contextPath = request.getContextPath();
		String port = request.getServerPort() == 80 ? "" : ":" + request.getServerPort();
		String url = request.getScheme() + "://" + request.getServerName() + port + contextPath + "/systemImages/" + filename;

		// 创建文件并保存
		File oldFile = new File(path);
		file.transferTo(oldFile);
		respJson = JsonUtil.buildFalseJson(0, url);
		output(response, respJson);
	}
}
