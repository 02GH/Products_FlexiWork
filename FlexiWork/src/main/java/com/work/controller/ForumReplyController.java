package com.work.controller;

import com.work.common.utils.BaseUtil;
import com.work.common.utils.JsonUtil;
import com.work.pojo.ForumReply;
import com.work.service.ForumReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

// 控制器注解，标识这是一个Spring MVC控制器
@Controller
@RequestMapping("/server/reply") // 请求路径前缀
public class ForumReplyController extends BaseUtil {

	// 自动注入ForumReplyService，用于处理业务逻辑
	@Autowired
	private ForumReplyService forumReplyService;

	// 跳转到帖子回复列表页面
	@RequestMapping("/goForumReplyList")
	public ModelAndView goForumReplyList(ModelAndView mv, ForumReply forumReply) {
		mv.setViewName("server/forumManage/replyList"); // 设置视图名称
		mv.addObject("forumReply", forumReply); // 将forumReply对象添加到模型中
		return mv; // 返回ModelAndView对象
	}

	// 获取帖子回复列表
	@RequestMapping("/getForumReplyList")
	public void getForumReplyList(HttpServletResponse response, ForumReply forumReply, Integer page, Integer limit) {
		// 设置默认分页参数
		if (page == null) {
			page = 1;
		}
		if (limit == null) {
			limit = 10;
		}
		// 获取总记录数
		int totalCount = forumReplyService.getForumReplyListCount(forumReply);
		// 获取当前页的回复列表
		List<ForumReply> list = forumReplyService.getForumReplyList(forumReply, (page - 1) * limit, limit);
		// 输出Json格式的结果
		output(response, JsonUtil.buildJsonByTotalCount(list, totalCount));
	}

	// 跳转到编辑回复的页面
	@RequestMapping("/goUpdateForumReply")
	public ModelAndView goUpdateForumReply(ModelAndView mv, Integer replyId) {
		// 根据回复ID获取对应的ForumReply对象
		ForumReply forumReply = forumReplyService.getForumReplyById(replyId);
		mv.addObject("forumReply", forumReply); // 将其添加到模型中
		mv.setViewName("server/forumManage/updateReply"); // 设置视图名称
		return mv; // 返回ModelAndView对象
	}

	// 编辑帖子回复
	@RequestMapping("/updateForumReply")
	public void updateForumReply(HttpServletResponse response, ForumReply forumReply) {
		forumReplyService.updateForumReply(forumReply); // 调用服务层更新回复
		// 输出编辑成功的Json响应
		output(response, JsonUtil.buildFalseJson(0, "编辑成功！"));
	}

	// 删除帖子回复
	@RequestMapping("/deleteForumReply")
	public void deleteForumReply(HttpServletResponse response, ForumReply forumReply) {
		forumReplyService.deleteForumReply(forumReply.getReplyId()); // 调用服务层删除回复
		// 输出删除成功的Json响应
		output(response, JsonUtil.buildFalseJson(0, "删除成功！"));
	}
}
