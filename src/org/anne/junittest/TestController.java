package org.anne.junittest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;

@Controller
public class TestController {

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	@RequestMapping(value = "/testfm.html")
	public String showUserListInFtl(ModelMap mm) {
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
		return "userListFtl";
	}

	@RequestMapping(value = "/upload.html")
	public void register()throws Exception {
		Template tpl = freeMarkerConfigurer.getConfiguration().getTemplate(
				"listUser.ftl");
		Map<String, List<User>> userMap = new HashMap<String, List<User>>();
		List<User> userList = new ArrayList<User>();
		User user1 = new User();
		user1.setUserName("tom");
		user1.setRealName("汤姆");
		User user2 = new User();
		user2.setUserName("john");
		user2.setRealName("约翰");
		userList.add(user1);
		userList.add(user2);
		userMap.put("userList", userList);
		String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(
				tpl, userMap);
		System.out.println(htmlText);
	}
}
