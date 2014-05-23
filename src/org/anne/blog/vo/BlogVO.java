package org.anne.blog.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

public class BlogVO implements java.io.Serializable {

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
	private Integer start;
	private Integer pagesize;
	private String blogid;
	private String sourceid;
	private Integer transmitType; // 0转发 1转发的转发
	private String nickName;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	private String icon;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getTransmitType() {
		return transmitType;
	}

	public void setTransmitType(Integer transmitType) {
		this.transmitType = transmitType;
	}

	public String getBlogid() {
		return blogid;
	}

	public void setBlogid(String blogid) {
		this.blogid = blogid;
	}

	public String getSourceid() {
		return sourceid;
	}

	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getTransmitCount() {
		return transmitCount;
	}

	public void setTransmitCount(Integer transmitCount) {
		this.transmitCount = transmitCount;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public String getSourceBlogId() {
		return sourceBlogId;
	}

	public void setSourceBlogId(String sourceBlogId) {
		this.sourceBlogId = sourceBlogId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
}