package org.anne.message;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.anne.base.BaseController;
import org.anne.base.ResponseInfo;
import org.anne.junittest.User;
import org.anne.message.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/message")
public class MessageController extends BaseController{
	@Autowired
	MessageService messageService;

	@RequestMapping(value = "/insertMessage")
	public @ResponseBody ResponseInfo insertMessage(@ModelAttribute("message") Message message) {
		ResponseInfo ri = new ResponseInfo();
		try {
			message.setId(UUID.randomUUID().toString());
			message.setSentdate(new Date());
			message.setStatus(0);
			messageService.insertMessage(message);
			ri.setSuccess(true);
		} catch (Exception e) {
			ri.setSuccess(false);
		}
		return ri;
	}
	@RequestMapping(value = "/getMessageDetail")
	public String getMessageDetail(@ModelAttribute("message") MessageVO message,ModelMap mm) {
			List<Message> msglist = messageService.getPrivateMessageRecoed(message);
			mm.addAttribute("messageList", msglist);
		return "message";
	}
	@RequestMapping(value = "/getMessages")
	public String getMessages(@ModelAttribute("message") MessageVO message,ModelMap mm) {
			//List<Message> msglist = messageService.getPrivateMessageRecoed(message);
			//mm.addAttribute("messageList", msglist);
			Calendar calendar = new GregorianCalendar();
			List<User> userList = new ArrayList<User>();
			User user1 = new User();
			user1.setUserName("tom");
			user1.setRealName("汤姆");
			calendar.set(1980, 1, 1);
			user1.setBirthday(calendar.getTime());
			User user2 = new User();
			user2.setUserName("john");
			user2.setRealName("约翰");
			user2.setBirthday(calendar.getTime());
			userList.add(user1);
			userList.add(user2);
			mm.addAttribute("userList", userList);
		return "message";
	}

	@RequestMapping(value = "/getMessagesLimit")
	public @ResponseBody ResponseInfo getMessagesLimit(@ModelAttribute("message") MessageVO message,HttpServletRequest request) {
			message.setSender(getSessionUser(request).getUsername());
			List<Message> msglist = messageService.getPrivateMessageRecoed(message);
			ResponseInfo ri = new ResponseInfo();
			ri.setSuccess(true);
			ri.setResult(msglist);
		return ri;
	}
	@RequestMapping(value = "/getLeaveMessage")
	public @ResponseBody ResponseInfo getLeaveMessage(@ModelAttribute("message") MessageVO message,HttpServletRequest request) {
			message.setReceiver(getSessionUser(request).getUsername());
			List<Message> msglist = messageService.getLeaveMessage(message);
			ResponseInfo ri = new ResponseInfo();
			ri.setSuccess(true);
			ri.setResult(msglist);
		return ri;
	}
}
