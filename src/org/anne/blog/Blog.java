package org.anne.blog;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.anne.base.JsonDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Blog entity.
 */
// @Entity
@Table(name = "blog")
public class Blog implements java.io.Serializable {

	private static final long serialVersionUID = 5398535263053843572L;

	private String id;
	private String content;
	private String imageId;
	private String createuser;
	private Date createDate;
	private Integer type;// 0 新增，1 转发
	private Integer transmitCount;
	private Integer replyCount;
	private String sourceBlogId; // 转发时使用被转发BLOG的ID

	@Transient
	public String getSourceBlogId() {
		return sourceBlogId;
	}

	public void setSourceBlogId(String sourceBlogId) {
		this.sourceBlogId = sourceBlogId;
	}

	public Blog() {
	}

	public Blog(String content, String imageId, String createuser,
			Date createDate, Integer type, Integer transmitCount,
			Integer replyCount) {
		this.content = content;
		this.imageId = imageId;
		this.createuser = createuser;
		this.createDate = createDate;
		this.type = type;
		this.transmitCount = transmitCount;
		this.replyCount = replyCount;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "content", length = 1000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "image_id", length = 200)
	public String getImageId() {
		return this.imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	@Column(name = "createuser")
	public String getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "create_date")
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "transmit_count")
	public Integer getTransmitCount() {
		return this.transmitCount;
	}

	public void setTransmitCount(Integer transmitCount) {
		this.transmitCount = transmitCount;
	}

	@Column(name = "reply_count")
	public Integer getReplyCount() {
		return this.replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

}