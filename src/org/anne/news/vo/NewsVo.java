package org.anne.news.vo;

import java.util.Date;

public class NewsVo {

	private Integer id;
	private String title;
	private String content;
	private Date createdate;
	private String crearetuser;
	private Integer start;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getCrearetuser() {
		return crearetuser;
	}

	public void setCrearetuser(String crearetuser) {
		this.crearetuser = crearetuser;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	private Integer pagesize;
}