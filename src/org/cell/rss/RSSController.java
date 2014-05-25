package org.cell.rss;

import org.cell.base.BaseController;
import org.cell.base.ResponseInfo;
import org.cell.base.UrlHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.cell.rss.RSSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

/**
 * <br>
 * <b>类描述:</b>
 * <p/>
 * <pre>
 * 所有Controller的基类
 * </pre>
 *
 * @see
 */

@Controller
@RequestMapping(value = "/rss")
public class RSSController {

    private static final Logger log = LoggerFactory.getLogger(RSSController.class);
    @Autowired
    RSSService rssService;


    UrlHelper url = new UrlHelper("/view/rss");

    @RequestMapping("/toRss.html")
    public ModelAndView toRss() {
        List<RSS> rss = rssService.getRSS();
        return new ModelAndView(url.join("rss"), "rssList", rss);
    }

    @RequestMapping("/getRss.html")
    public
    @ResponseBody
    ResponseInfo getRSS() {
        ResponseInfo ri = new ResponseInfo();


        ri.setSuccess(true);
        return ri;
    }
}
