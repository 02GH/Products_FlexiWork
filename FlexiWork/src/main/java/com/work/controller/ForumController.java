package com.work.controller;

// 导入所需的类和包
import com.work.common.utils.BaseUtil;
import com.work.common.utils.JsonUtil;
import com.work.pojo.Forum;
import com.work.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

// 控制器注解，标识该类为控制器
@Controller
@RequestMapping("/server/forum") // 设置请求的根路径
public class ForumController extends BaseUtil {

	// 自动注入 ForumService 服务
	@Autowired
	private ForumService forumService;

	// 去帖子列表页面
	@RequestMapping("/goForumList") // 映射请求路径
	public ModelAndView goForumList(ModelAndView mv) {
		mv.setViewName("server/forumManage/forumList"); // 设置视图名称
		return mv; // 返回 ModelAndView 对象
	}

	// 获取帖子列表
	@RequestMapping("/getForumList") // 映射请求路径
	public void getForumList(HttpServletResponse response, Forum forum, Integer page, Integer limit) {
		// 设置默认分页参数
		if (page == null) {
			page = 1; // 默认页码为1
		}
		if (limit == null) {
			limit = 10; // 默认每页条目数为10
		}

		// 获取帖子总数
		int totalCount = forumService.getForumListCount(forum);
		// 获取当前页的帖子列表数据
		List<Forum> list = forumService.getForumList(forum, (page - 1) * limit, limit);

		// 输出 JSON 格式的响应
		output(response, JsonUtil.buildJsonByTotalCount(list, totalCount));
	}

	// 去编辑帖子页面
	@RequestMapping("/goUpdateForum") // 映射请求路径
	public ModelAndView goUpdateUser(ModelAndView mv, Integer forumId) {
		// 根据 ID 获取帖子信息
		Forum forum = forumService.getForumById(forumId);
		mv.addObject("forum", forum); // 将帖子信息添加到模型中
		mv.setViewName("server/forumManage/updateForum"); // 设置视图名称
		return mv; // 返回 ModelAndView 对象
	}

	// 编辑帖子
	@RequestMapping("/updateForum") // 映射请求路径
	public void updateForum(HttpServletResponse response, Forum forum) {
		// 更新帖子信息
		forumService.updateForum(forum);
		// 输出编辑成功的 JSON 响应
		output(response, JsonUtil.buildFalseJson(0, "编辑成功！"));
	}

	// 删除或禁用帖子
	@RequestMapping("/deleteForum") // 映射请求路径
	public void deleteForum(HttpServletResponse response, Forum forum) {
		// 更新帖子状态为禁用
		forumService.updateForum(forum);
		// 输出禁用成功的 JSON 响应
		output(response, JsonUtil.buildFalseJson(0, "禁用成功！"));
	}
}
