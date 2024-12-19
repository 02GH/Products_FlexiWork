package com.work.controller;

import com.work.common.utils.BaseUtil;
import com.work.common.utils.JsonUtil;
import com.work.pojo.Notice;
import com.work.pojo.User;
import com.work.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

// 控制器注解，标识这是一个Spring MVC控制器
@Controller
@RequestMapping("/server/notice") // 设置请求路径前缀
public class NoticeController extends BaseUtil {

	@Autowired
	private NoticeService noticeService; // 注入公告服务

	// 跳转到公告列表页面
	@RequestMapping("/goNoticeList")
	public ModelAndView goNoticeList(ModelAndView mv) {
		mv.setViewName("server/noticeManage/noticeList"); // 设置视图名称
		return mv; // 返回ModelAndView对象
	}

	// 获取公告列表
	@RequestMapping("/getNoticeList")
	public void getNoticeList(HttpServletResponse response, Notice notice, Integer page, Integer limit) {
		// 设置默认分页参数
		if (page == null) {
			page = 1; // 默认第一页
		}
		if (limit == null) {
			limit = 10; // 默认每页10条记录
		}

		// 获取公告总数
		int totalCount = noticeService.getNoticeListCount(notice);
		// 获取当前页的公告列表
		List<Notice> list = noticeService.getNoticeList(notice, (page - 1) * limit, limit);

		// 返回JSON格式的响应，包括列表和总数
		output(response, JsonUtil.buildJsonByTotalCount(list, totalCount));
	}

	// 跳转到添加公告页面
	@RequestMapping("/goAddNotice")
	public ModelAndView goAddNotice(ModelAndView mv) {
		mv.setViewName("server/noticeManage/addNotice"); // 设置视图名称
		return mv; // 返回ModelAndView对象
	}

	// 添加公告
	@RequestMapping("/addNotice")
	public void addNotice(HttpServletRequest request, HttpServletResponse response, Notice notice) {
		User serverUser = (User) request.getSession().getAttribute("serverUser"); // 获取当前登录用户
		notice.setCreateTime(new Date()); // 设置公告创建时间
		notice.setUserId(serverUser.getUserId()); // 设置公告发布者ID
		noticeService.addNotice(notice); // 调用服务层添加公告
		output(response, JsonUtil.buildFalseJson(0, "添加成功！")); // 返回添加成功的JSON响应
	}

	// 跳转到编辑公告页面
	@RequestMapping("/goUpdateNotice")
	public ModelAndView goUpdateNotice(ModelAndView mv, Integer id) {
		Notice notice = noticeService.getNoticeById(id); // 根据ID获取公告信息
		mv.addObject("notice", notice); // 将公告对象添加到模型中
		mv.setViewName("server/noticeManage/updateNotice"); // 设置视图名称
		return mv; // 返回ModelAndView对象
	}

	// 编辑公告
	@RequestMapping("/updateNotice")
	public void updateNotice(HttpServletResponse response, Notice notice) {
		noticeService.updateNotice(notice); // 调用服务层更新公告
		output(response, JsonUtil.buildFalseJson(0, "编辑成功！")); // 返回编辑成功的JSON响应
	}

	// 删除公告
	@RequestMapping("/deleteNotice")
	public void deleteNotice(HttpServletResponse response, Integer id) {
		noticeService.deleteNoticeById(id); // 调用服务层删除公告
		output(response, JsonUtil.buildFalseJson(0, "删除成功！")); // 返回删除成功的JSON响应
	}
}
