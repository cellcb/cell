package org.cell.rss;

import org.cell.base.JsonDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * 功能:
 *
 * @author chengb
 * @date 2014-05-25
 */
public class BlogEntry {

    private String title;
    private String url;
    private Date dateTime;
    private String description;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
