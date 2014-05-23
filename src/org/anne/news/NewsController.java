package org.anne.news;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.anne.base.AnneUtil;
import org.anne.base.BaseController;
import org.anne.base.ResponseInfo;
import org.anne.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/news")
public class NewsController extends BaseController {

	@Autowired
	NewsService newsService;
    /**
     * 添加新闻
     * @param news
     * @param request
     * @return
     */
	@RequestMapping(value = "/addnews.html")
	public @ResponseBody
	ResponseInfo addNews(@ModelAttribute("news") News news,
			HttpServletRequest request) {
		ResponseInfo ri = new ResponseInfo();
		try {
			news.setId(AnneUtil.genId());
			news.setCrearetuser(getSessionUser(request).getUsername());
			news.setCreatedate(new Date());
			newsService.addNews(news);
			ri.setResult(news);
			ri.setSuccess(true);
		} catch (Exception e) {
			ri.setSuccess(false);
		}
		return ri;
	}
	

}
