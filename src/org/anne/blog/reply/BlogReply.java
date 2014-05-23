package org.anne.blog.reply;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BlogReply entity.
 */
// @Entity
@Table(name = "blog_reply")
public class BlogReply implements java.io.Serializable {

	private static final long serialVersionUID = -3322712342258813619L;

	private Integer id;
	private String content;
	private String blogid;
	private Date createdate;
	private String createuser;

	public BlogReply() {
	}

	public BlogReply(String content, String blogid, Date createdate,
			String createuser) {
		this.content = content;
		this.blogid = blogid;
		this.createdate = createdate;
		this.createuser = createuser;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "content", length = 1000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "blogid")
	public String getBlogid() {
		return this.blogid;
	}

	public void setBlogid(String blogid) {
		this.blogid = blogid;
	}

	@Column(name = "createdate")
	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@Column(name = "createuser", length = 40)
	public String getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

}