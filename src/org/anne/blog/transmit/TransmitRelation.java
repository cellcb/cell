package org.anne.blog.transmit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TransmitRelation entity.
 */
@Entity
@Table(name = "transmit_relation")
public class TransmitRelation implements java.io.Serializable {

	private static final long serialVersionUID = -2446417711541778844L;

	private Integer id;
	private String blogid;
	private String sourceid;

	public TransmitRelation() {
	}

	public TransmitRelation(String blogid, String sourceid) {
		this.blogid = blogid;
		this.sourceid = sourceid;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "blogid")
	public String getBlogid() {
		return this.blogid;
	}

	public void setBlogid(String blogid) {
		this.blogid = blogid;
	}

	@Column(name = "sourceid")
	public String getSourceid() {
		return this.sourceid;
	}

	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}

}