package com.work.controller;

import com.work.common.utils.BaseUtil;
import com.work.common.utils.JsonUtil;
import com.work.pojo.Chat;
import com.work.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ChatController 类负责处理与聊天相关的请求。
 * 它提供聊天列表的视图、获取聊天记录、更新聊天记录和删除聊天记录的功能。
 */
@Controller
@RequestMapping(value="/server/chat") // 定义请求路径的前缀，所有方法的请求路径都将以此开始
public class ChatController extends BaseUtil {

	@Autowired // 自动注入 ChatService 实例
	private ChatService chatService;

	/**
	 * 跳转到聊天列表页面
	 * @param mv ModelAndView 对象，用于封装视图和模型数据
	 * @return ModelAndView 对象，指向聊天列表视图
	 */
	@RequestMapping("/goChatList")
	public ModelAndView goChatList(ModelAndView mv) {
		mv.setViewName("server/chatManage/chatList"); // 设置视图名称
		return mv; // 返回 ModelAndView 对象
	}

	/**
	 * 获取聊天列表
	 * @param response HttpServletResponse 对象，用于返回响应
	 * @param chat Chat 对象，用于过滤聊天记录
	 * @param page 当前页码，默认为1
	 * @param limit 每页显示的记录数，默认为10
	 */
	@RequestMapping("/getChatList")
	public void getChatList(HttpServletResponse response, Chat chat, Integer page, Integer limit) {
		if (page == null) {
			page = 1; // 设置默认页码
		}
		if (limit == null) {
			limit = 10; // 设置默认每页记录数
		}
		int totalCount = chatService.getChatListCount(chat); // 获取聊天记录总数
		List<Chat> list = chatService.getChatList(chat, (page - 1) * limit, limit); // 获取分页聊天记录
		output(response, JsonUtil.buildJsonByTotalCount(list, totalCount)); // 构建 JSON 响应并输出
	}

	/**
	 * 跳转到编辑聊天页面
	 * @param mv ModelAndView 对象，用于封装视图和模型数据
	 * @param id 聊天记录的 ID
	 * @return ModelAndView 对象，指向编辑聊天的视图
	 */
	@RequestMapping("/goUpdateChat")
	public ModelAndView goUpdateChat(ModelAndView mv, Integer id) {
		Chat chat = chatService.getChatById(id); // 根据 ID 获取聊天记录
		mv.addObject("chat", chat); // 将聊天记录添加到模型中
		mv.setViewName("server/chatManage/updateChat"); // 设置视图名称
		return mv; // 返回 ModelAndView 对象
	}

	/**
	 * 编辑聊天记录
	 * @param response HttpServletResponse 对象，用于返回响应
	 * @param chat Chat 对象，包含要更新的数据
	 */
	@RequestMapping("/updateChat")
	public void updateChat(HttpServletResponse response, Chat chat) {
		chatService.updateChat(chat); // 调用服务层方法更新聊天记录
		output(response, JsonUtil.buildFalseJson(0, "编辑成功！")); // 返回成功的 JSON 响应
	}

	/**
	 * 删除聊天记录
	 * @param response HttpServletResponse 对象，用于返回响应
	 * @param id 聊天记录的 ID
	 */
	@RequestMapping("/deleteChat")
	public void deleteChat(HttpServletResponse response, Integer id) {
		chatService.deleteChatByChatId(id); // 根据 ID 删除聊天记录
		output(response, JsonUtil.buildFalseJson(0, "删除成功！")); // 返回成功的 JSON 响应
	}
}
