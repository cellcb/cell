package org.cell.rss;

import com.sun.syndication.io.FeedException;
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

import java.net.MalformedURLException;
import java.net.URL;
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
    @Autowired
    RSSUtil rssUtil;


    UrlHelper url = new UrlHelper("/view/rss");

    @RequestMapping("/toRss.html")
    public ModelAndView toRss() {
        List<RSS> rss = rssService.getRSS();
        return new ModelAndView(url.join("rss"), "rssList", rss);
    }

    @RequestMapping("/loadBlog.html")
    public
    @ResponseBody
    ResponseInfo loadBlog(String url) {
        ResponseInfo ri = new ResponseInfo();
        try {
            List<BlogEntry> blogEntries = rssUtil.parseXml(new URL(url));
            ri.setResult(blogEntries);
        } catch (FeedException e) {
            ri.setSuccess(false);

            e.printStackTrace();
        } catch (MalformedURLException e) {
            ri.setSuccess(false);

            e.printStackTrace();
        }

        ri.setSuccess(true);
        return ri;
    }
}
