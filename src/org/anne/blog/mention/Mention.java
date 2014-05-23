package org.anne.blog.mention;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Mention entity.
 */
// @Entity
@Table(name = "mention")
public class Mention implements java.io.Serializable {

	private static final long serialVersionUID = 758504778555384498L;
	private Integer id;
	private Integer blogId;
	private String userid;
	private Integer status;

	public Mention() {
	}

	public Mention(Integer blogId, String userid, Integer status) {
		this.blogId = blogId;
		this.userid = userid;
		this.status = status;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "blog_id")
	public Integer getBlogId() {
		return this.blogId;
	}

	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}

	@Column(name = "userid", length = 40)
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}