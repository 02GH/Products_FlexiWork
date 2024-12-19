package com.work.controller.pc;

import com.work.common.utils.BaseUtil;
import com.work.common.utils.JsonUtil;
import com.work.common.utils.MD5Util;
import com.work.common.utils.Page;
import com.work.pojo.*;
import com.work.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

// 控制器注解，标识这是一个Spring MVC控制器
@Controller
@RequestMapping("/pc/personal") // 设置请求路径的前缀为 "/pc/personal"
public class PcPersonalController extends BaseUtil {

	@Autowired
	private UserService userService; // 用户服务
	@Autowired
	private WorkService workService; // 兼职服务
	@Autowired
	private ForumService forumService; // 论坛服务
	@Autowired
	private SystemUpsetService systemUpsetService; // 系统配置服务
	@Autowired
	private ForumReplyService forumReplyService; // 论坛回复服务
	@Autowired
	private ChatService chatService; // 聊天服务
	@Autowired
	private CollectService collectService; // 收藏服务
	@Autowired
	private JianzhiService jianzhiService; // 简历服务

	// 个人中心公共部分
	@RequestMapping(value="/goPersonalCommon.action")
	public ModelAndView goPersonalCommon(HttpServletRequest request, ModelAndView mv, SystemUpset systemUpset) {
		User user = (User) request.getSession().getAttribute("pcUser"); // 获取当前Session中的用户信息
		mv.addObject("user", user); // 将用户信息添加到模型中

		// 消息通知（聊天）
		String isNotice = "f";
		Chat chat = new Chat();
		chat.setUserIdJie(user.getUserId());
		chat.setIsLook(0); // 获取未读消息
		List<Chat> chats = chatService.getChatList(chat, null, null);
		if (chats.size() > 0) {
			isNotice = "t"; // 有新消息
		}

		// 消息通知（帖子）
		Forum forum = new Forum();
		forum.setUserId(user.getUserId());
		forum.setIsEffect(1); // 只获取有效帖子
		List<Forum> forums = forumService.getForumList(forum, null, null);
		if (forums.size() > 0) {
			ForumReply forumReply = new ForumReply();
			forumReply.setStatus(0); // 获取未读回复
			for (Forum f : forums) {
				forumReply.setForumId(f.getForumId());
				List<ForumReply> forumReplys = forumReplyService.getForumReplyList(forumReply, null, null);
				if (forumReplys.size() > 0) {
					isNotice = "t"; // 有新回复
				}
			}
		}
		mv.addObject("isNotice", isNotice); // 将消息通知状态添加到模型中
		mv.setViewName("pc/personal_common"); // 设置视图名称为个人中心公共部分
		return mv;
	}

	// 个人中心
	@RequestMapping(value="/goPersonal.action")
	public ModelAndView goPersonal(HttpServletRequest request, ModelAndView mv, SystemUpset systemUpset) {
		List<SystemUpset> systemUpsets = systemUpsetService.getSystemUpsetList(systemUpset, null, null);
		if (systemUpsets.size() > 0) {
			mv.addObject("systemUpset", systemUpsets.get(0)); // 将系统配置添加到模型中
		}
		User user = (User) request.getSession().getAttribute("pcUser");
		mv.addObject("user", user); // 将用户信息添加到模型中
		if (user == null) {
			mv.setViewName("pc/login"); // 如果未登录，跳转到登录页面
		} else {
			mv.setViewName("pc/personal_centre"); // 否则跳转到个人中心页面
		}
		return mv;
	}

	// 编辑资料
	@RequestMapping("/updateUser")
	public void updateUser(HttpServletRequest request, HttpServletResponse response, User user) {
		userService.updateUser(user); // 更新用户信息
		User pcUser = userService.getUserById(user.getUserId()); // 获取更新后的用户信息
		request.getSession().setAttribute("pcUser", pcUser); // 更新Session中的用户信息
		output(response, JsonUtil.buildFalseJson(0, "编辑成功")); // 返回成功信息
	}

	// 修改密码
	@RequestMapping("/updatePassword")
	public void updatePassword(HttpServletRequest request, HttpServletResponse response, String passwordYes, String password, Integer userId) {
		User user = new User();
		if (passwordYes.equals(password)) { // 校验新密码与确认密码是否一致
			user.setUserId(userId);
			user.setPassword(MD5Util.MD5Encode(password, "utf-8")); // 对新密码进行加密
			userService.updateUser(user); // 更新用户信息
			request.getSession().removeAttribute("pcUser"); // 移除Session中的用户信息
			output(response, JsonUtil.buildFalseJson(0, "修改密码成功")); // 返回成功信息
		} else {
			output(response, JsonUtil.buildFalseJson(1, "前后密码不一致")); // 返回错误信息
		}
	}

	// 我的通知
	@RequestMapping(value="/goMyNotice.action")
	public ModelAndView goMyNotice(HttpServletRequest request, ModelAndView mv, SystemUpset systemUpset) {
		User user = (User) request.getSession().getAttribute("pcUser");
		// 聊天消息
		Chat chat = new Chat();
		chat.setUserIdFa(user.getUserId());
		List<Chat> chats = chatService.myChatList(chat); // 获取用户的聊天记录

		// 消息通知（聊天）
		String isChat = "f";
		chat.setUserIdJie(user.getUserId());
		chat.setUserIdFa(null);
		chat.setIsLook(0); // 获取未读聊天消息
		List<Chat> isChats = chatService.getChatList(chat, null, null);
		if (isChats.size() > 0) {
			isChat = "t"; // 有未读聊天消息
		}
		mv.addObject("isChat", isChat); // 将聊天消息通知状态添加到模型中
		mv.addObject("chats", chats); // 将聊天记录添加到模型中

		// 评论消息
		String isReply = "f";
		Forum forum = new Forum();
		forum.setIsEffect(1);
		forum.setUserId(user.getUserId());
		List<Forum> isForums = forumService.getForumList(forum, null, null); // 获取用户的有效帖子
		List<Forum> forums = new ArrayList<>();
		if (isForums.size() > 0) {
			ForumReply forumReply = new ForumReply();
			forumReply.setStatus(0); // 获取未读评论
			for (Forum f : isForums) {
				forumReply.setForumId(f.getForumId());
				List<ForumReply> forumReplys = forumReplyService.getForumReplyList(forumReply, null, null);
				if (forumReplys.size() > 0) {
					isReply = "t"; // 有新评论
					forums.add(f); // 收集有新评论的帖子
				}
			}
		}
		mv.addObject("isReply", isReply); // 将评论消息通知状态添加到模型中
		mv.addObject("forums", forums); // 将相关的帖子添加到模型中
		mv.addObject("user", user); // 将用户信息添加到模型中
		mv.setViewName("pc/my_notice"); // 设置视图名称为我的通知页面
		return mv;
	}

	// 从聊天列表删除
	@RequestMapping("/deleteMyChatList")
	public void updateMyChatList(HttpServletRequest request, HttpServletResponse response, User user) {
		User pcUser = (User) request.getSession().getAttribute("pcUser");
		Chat chat = new Chat();
		// 删除接收方聊天记录
		chat.setUserIdFa(pcUser.getUserId());
		chat.setUserIdJie(user.getUserId());
		chat.setIsRemoveFa(1); // 标记为删除
		chatService.updateChat(chat);
		// 删除发送方聊天记录
		chat.setUserIdFa(user.getUserId());
		chat.setUserIdJie(pcUser.getUserId());
		chat.setIsRemoveJie(1); // 标记为删除
		chat.setIsRemoveFa(null);
		chatService.updateChat(chat);

		output(response, JsonUtil.buildFalseJson(0, "删除成功！")); // 返回成功信息
	}

	// 查看帖子
	@RequestMapping(value="/goFindForum.action")
	public ModelAndView goFindForum(HttpServletRequest request, ModelAndView mv, SystemUpset systemUpset, Forum forum) {
		List<SystemUpset> systemUpsets = systemUpsetService.getSystemUpsetList(systemUpset, null, null);
		if (systemUpsets.size() > 0) {
			mv.addObject("systemUpset", systemUpsets.get(0)); // 将系统配置添加到模型中
		}
		forum = forumService.getForumById(forum.getForumId()); // 根据ID获取帖子
		ForumReply forumReply = new ForumReply();
		forumReply.setForumId(forum.getForumId());
		forum.setForumReplys(forumReplyService.getForumReplyList(forumReply, null, null)); // 获取该帖子的回复
		mv.addObject("forum", forum); // 将帖子信息添加到模型中
		User pcUser = (User) request.getSession().getAttribute("pcUser");
		mv.addObject("pcUser", pcUser); // 将当前用户信息添加到模型中
		int userId = forum.getUserId();
		if (userId == pcUser.getUserId()) {
			forumReply.setStatus(1); // 设置为已读状态
			forumReplyService.updateForumReply(forumReply); // 更新回复状态
		}
		mv.setViewName("pc/find_forum"); // 设置视图名称为查看帖子页面
		return mv;
	}

	// 我的兼职
	@RequestMapping(value="/goMyWork.action")
	public ModelAndView goMyWork(HttpServletRequest request, ModelAndView mv, SystemUpset systemUpset, QueryVo vo) {
		User pcUser = (User) request.getSession().getAttribute("pcUser");
		mv.addObject("pcUser", pcUser); // 将当前用户信息添加到模型中
		// 获取兼职信息
		Work work = new Work();
		Page<Work> page = new Page<>();
		work.setIsEffect(1); // 只获取有效兼职
		work.setUserId(pcUser.getUserId()); // 设置为当前用户ID
		if (vo.getSize() != 7) {
			vo.setSize(7); // 每页显示7条
		}
		if (vo.getPage() == null) {
			vo.setPage(1); // 默认第一页
		}
		// 设置分页信息
		page.setSize(vo.getSize());
		if (vo != null) {
			// 判断当前页
			if (vo.getPage() != null) {
				page.setPage(vo.getPage());
				vo.setStartRow((vo.getPage() - 1) * vo.getSize());
			}
			page.setTotal(workService.getWorkListCount(work)); // 获取总数
			page.setRows(workService.getWorkList(work, (vo.getPage() - 1) * vo.getSize(), vo.getSize())); // 获取兼职数据
		}
		mv.addObject("page", page); // 将分页数据添加到模型中
		mv.setViewName("pc/my_work"); // 设置视图名称为我的兼职页面
		return mv;
	}

	// 我的简历
	@RequestMapping(value="/goMyjianzhi.action")
	public ModelAndView goMyjianzhi(HttpServletRequest request, ModelAndView mv, SystemUpset systemUpset, QueryVo vo) {
		User pcUser = (User) request.getSession().getAttribute("pcUser");
		mv.addObject("pcUser", pcUser); // 将当前用户信息添加到模型中
		// 获取简历信息
		Jianzhi jianzhi = new Jianzhi();
		jianzhi.setUserId(pcUser.getUserId());
		List<Jianzhi> jianzhis = jianzhiService.getList(jianzhi); // 查询当前用户的简历列表
		jianzhis.forEach(jianzhi1 -> {
			Work workById = workService.getWorkById(jianzhi1.getId()); // 获取兼职详细信息
			jianzhi1.setWorkCategory(workById.getWorkCategory());
			jianzhi1.setImage(workById.getImage());
			jianzhi1.setTitle(workById.getTitle());
			jianzhi1.setContent(workById.getContent());
			jianzhi1.setPhone(workById.getPhone());
			jianzhi1.setRealName(workById.getRealName());
		});
		mv.addObject("jianzhis", jianzhis); // 将简历列表添加到模型中
		mv.setViewName("pc/my_jianzhi"); // 设置视图名称为我的简历页面
		return mv;
	}

	// 删除兼职
	@RequestMapping("/deleteMyWork")
	public void deleteMyWork(HttpServletRequest request, HttpServletResponse response, Work work) {
		work.setIsEffect(0); // 将兼职标记为无效
		workService.updateWork(work); // 更新兼职状态
		output(response, JsonUtil.buildFalseJson(0, "删除成功！")); // 返回成功信息
	}

	// 我的帖子
	@RequestMapping(value="/goMyForum.action")
	public ModelAndView goMyForum(HttpServletRequest request, ModelAndView mv, QueryVo vo) {
		User pcUser = (User) request.getSession().getAttribute("pcUser");
		mv.addObject("pcUser", pcUser); // 将当前用户信息添加到模型中
		// 获取帖子信息
		Forum forum = new Forum();
		Page<Forum> page = new Page<>();
		forum.setIsEffect(1); // 只获取有效帖子
		forum.setUserId(pcUser.getUserId()); // 设置为当前用户ID
		if (vo.getSize() != 7) {
			vo.setSize(7); // 每页显示7条
		}
		if (vo.getPage() == null) {
			vo.setPage(1); // 默认第一页
		}
		// 设置分页信息
		page.setSize(vo.getSize());
		if (vo != null) {
			// 判断当前页
			if (vo.getPage() != null) {
				page.setPage(vo.getPage());
				vo.setStartRow((vo.getPage() - 1) * vo.getSize());
			}
			page.setTotal(forumService.getForumListCount(forum)); // 获取总数
			page.setRows(forumService.getForumList(forum, (vo.getPage() - 1) * vo.getSize(), vo.getSize())); // 获取帖子数据
		}
		mv.addObject("page", page); // 将分页数据添加到模型中
		mv.setViewName("pc/my_forum"); // 设置视图名称为我的帖子页面
		return mv;
	}

	// 删除帖子
	@RequestMapping("/deleteMyForum")
	public void deleteMyForum(HttpServletRequest request, HttpServletResponse response, Forum forum) {
		forum.setIsEffect(0); // 将帖子标记为无效
		forumService.updateForum(forum); // 更新帖子状态
		output(response, JsonUtil.buildFalseJson(0, "删除成功！")); // 返回成功信息
	}

	// 我的收藏
	@RequestMapping(value="/goMyCollect.action")
	public ModelAndView goMyCollect(HttpServletRequest request, ModelAndView mv, QueryVo vo) {
		User pcUser = (User) request.getSession().getAttribute("pcUser");
		mv.addObject("pcUser", pcUser); // 将当前用户信息添加到模型中
		// 获取收藏信息
		Collect collect = new Collect();
		collect.setUserId(pcUser.getUserId());
		// 保存帖子
		Page<Forum> page = new Page<>();
		List<Forum> forums = new ArrayList();
		if (vo.getSize() != 7) {
			vo.setSize(7); // 每页显示7条
		}
		if (vo.getPage() == null) {
			vo.setPage(1); // 默认第一页
		}
		// 设置分页信息
		page.setSize(vo.getSize());
		if (vo != null) {
			// 判断当前页
			if (vo.getPage() != null) {
				page.setPage(vo.getPage());
				vo.setStartRow((vo.getPage() - 1) * vo.getSize());
			}
			page.setTotal(collectService.getCollectListCount(collect)); // 获取总数
			List<Collect> collects = collectService.getCollectList(collect, (vo.getPage() - 1) * vo.getSize(), vo.getSize()); // 获取收藏数据
			Forum forum = new Forum();
			forum.setIsEffect(1); // 只获取有效帖子
			for (Collect c : collects) {
				forum.setForumId(c.getForumId());
				List<Forum> forumy = forumService.getForumList(forum, null, null); // 获取帖子详细信息
				if (forumy.size() > 0) {
					forums.add(forumy.get(0)); // 将有效帖子添加到列表中
				}
			}
			page.setRows(forums); // 设置分页的帖子数据
		}
		mv.addObject("page", page); // 将分页数据添加到模型中
		mv.setViewName("pc/my_collect"); // 设置视图名称为我的收藏页面
		return mv;
	}

	// 取消收藏
	@RequestMapping("/deleteMyCollect")
	public void deleteMyCollect(HttpServletRequest request, HttpServletResponse response, Forum forum) {
		User pcUser = (User) request.getSession().getAttribute("pcUser");
		Collect collect = new Collect();
		collect.setUserId(pcUser.getUserId());
		collect.setForumId(forum.getForumId()); // 设置要取消收藏的帖子ID
		collectService.deleteMyCollect(collect); // 调用收藏服务取消收藏
		output(response, JsonUtil.buildFalseJson(0, "删除成功！")); // 返回成功信息
	}

	// 去设置密保
	@RequestMapping(value="/goPassword.action")
	public ModelAndView goPassword(HttpServletRequest request, ModelAndView mv) {
		User pcUser = (User) request.getSession().getAttribute("pcUser"); // 获取当前用户信息
		mv.addObject("pcUser", pcUser); // 将用户信息添加到模型中
		mv.setViewName("pc/get_password"); // 设置视图名称为设置密保页面
		return mv; // 返回视图
	}

	// 设置密保
	@RequestMapping("/setQuestion")
	public void setQuestion(HttpServletRequest request, HttpServletResponse response, User user) {
		User pcUser = (User) request.getSession().getAttribute("pcUser"); // 获取当前用户信息
		user.setUserId(pcUser.getUserId()); // 设置用户ID
		userService.updateUser(user); // 更新用户信息，包括密保问题和答案
		pcUser.setQuestion(user.getQuestion()); // 更新Session中的密保问题
		pcUser.setAnswer(user.getAnswer()); // 更新Session中的密保答案
		request.getSession().setAttribute("pcUser", pcUser); // 更新Session中的用户信息
		output(response, JsonUtil.buildFalseJson(0, "设置成功！")); // 返回成功信息
	}
}
