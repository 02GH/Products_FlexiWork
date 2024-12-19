package com.work.controller;

import com.work.common.utils.BaseUtil;
import com.work.common.utils.JsonUtil;
import com.work.pojo.Mailbox;
import com.work.service.MailboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

// 控制器注解，标识这是一个Spring MVC控制器
@Controller
@RequestMapping("/server/mailbox") // 设置请求路径前缀
public class MailboxController extends BaseUtil {

	// 自动注入MailboxService，用于业务逻辑处理
	@Autowired
	private MailboxService mailboxService;

	// 跳转到信箱列表页面
	@RequestMapping("/goMailboxList")
	public ModelAndView goMailboxList(ModelAndView mv) {
		mv.setViewName("server/mailboxManage/mailboxList"); // 设置视图名称
		return mv; // 返回ModelAndView对象
	}

	// 获取信箱列表
	@RequestMapping("/getMailboxList")
	public void getMailboxList(HttpServletResponse response, Mailbox mailbox, Integer page, Integer limit) {
		// 设置默认分页参数
		if (page == null) {
			page = 1; // 默认第一页
		}
		if (limit == null) {
			limit = 10; // 默认每页10条记录
		}

		// 获取信箱总数
		int totalCount = mailboxService.getMailboxListCount(mailbox);
		// 获取当前页的信箱列表
		List<Mailbox> list = mailboxService.getMailboxList(mailbox, (page - 1) * limit, limit);

		// 返回JSON格式的响应，包括列表和总数
		output(response, JsonUtil.buildJsonByTotalCount(list, totalCount));
	}

	// 跳转到查看信件页面
	@RequestMapping("/goUpdateMailbox")
	public ModelAndView goUpdateMailbox(ModelAndView mv, Integer id) {
		// 根据信件ID获取信件信息
		Mailbox mailbox = mailboxService.getMailboxById(id);
		mv.addObject("mailbox", mailbox); // 将信件对象添加到模型中
		mv.setViewName("server/mailboxManage/updateMailbox"); // 设置视图名称
		return mv; // 返回ModelAndView对象
	}

	// 编辑信件
	@RequestMapping("/updateMailbox")
	public void updateMailbox(HttpServletResponse response, Mailbox mailbox) {
		mailboxService.updateMailbox(mailbox); // 调用服务层方法更新信件
		// 返回编辑成功的JSON响应
		output(response, JsonUtil.buildFalseJson(0, "编辑成功！"));
	}

	// 删除信件
	@RequestMapping("/deleteMailbox")
	public void deleteMailbox(HttpServletResponse response, Integer id) {
		mailboxService.deleteMailboxById(id); // 调用服务层方法删除信件
		// 返回删除成功的JSON响应
		output(response, JsonUtil.buildFalseJson(0, "删除成功！"));
	}
}
