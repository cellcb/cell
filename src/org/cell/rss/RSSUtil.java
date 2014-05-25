package org.cell.rss;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * 功能:
 *
 * @author chengb
 * @date 2014-05-24
 */

@Service
public class RSSUtil {
    //
//    static {
//        //for localhost testing only
//        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
//                new javax.net.ssl.HostnameVerifier(){
//
//                    public boolean verify(String hostname,
//                                          javax.net.ssl.SSLSession sslSession) {
//                        if (hostname.equals("asktom.oracle.com")) {
//                            return true;
//                        }
//                        return false;
//                    }
//                });
//    }
    public List<BlogEntry> parseXml(URL url) throws IllegalArgumentException, FeedException {


        List<BlogEntry> blogEntries = new ArrayList<BlogEntry>();

        try {
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = null;
            URLConnection conn;
            conn = url.openConnection();
            String content_encoding = conn.getHeaderField("Content-Encoding");

            if (content_encoding != null && content_encoding.contains("gzip")) {
                System.out.println("conent encoding is gzip");
                GZIPInputStream gzin = new GZIPInputStream(conn
                        .getInputStream());
                feed = input.build(new XmlReader(gzin));
            } else {
                feed = input.build(new XmlReader(conn.getInputStream()));
            }

            List entries = feed.getEntries();//得到所有的标题<title></title>
            for (int i = 0; i < entries.size(); i++) {

                SyndEntry entry = (SyndEntry) entries.get(i);
                BlogEntry blogEntry = new BlogEntry();
                blogEntry.setUrl(entry.getLink());
                blogEntry.setDateTime(entry.getPublishedDate());
                blogEntry.setDescription(entry.getDescription().getValue());
                blogEntry.setTitle(entry.getTitle());
                blogEntry.setAuthor(entry.getAuthor());
                blogEntries.add(blogEntry);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return blogEntries;
    }

    public static void main(String[] args) {
        RSSUtil rss = new RSSUtil();
        try {
            URL url = null;
            try {
                url = new URL("https://asktom.oracle.com/pls/apex/asktom.hot.rss?p_count=10");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            rss.parseXml(url);
        } catch (FeedException e) {
            e.printStackTrace();
        }

    }
}
