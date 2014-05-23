package org.anne.news;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * News entity.
 */
//@Entity
@Table(name = "news")
public class News implements java.io.Serializable {

	private static final long serialVersionUID = -2877804574899725228L;
	private String id;
	private String title;
	private String content;
	private Date createdate;
	private String crearetuser;

	public News() {
	}

	public News(String title, String content, Timestamp createdate,
			String crearetuser) {
		this.title = title;
		this.content = content;
		this.createdate = createdate;
		this.crearetuser = crearetuser;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "title", length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", length = 10000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "createdate")
	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@Column(name = "crearetuser", length = 40)
	public String getCrearetuser() {
		return this.crearetuser;
	}

	public void setCrearetuser(String crearetuser) {
		this.crearetuser = crearetuser;
	}

}