package org.anne.image.imageGroup;

/**
 * Imagegroup entity.
 */

public class Imagegroup implements java.io.Serializable {

	private static final long serialVersionUID = -2676631356901455891L;
	private String id;
	private String name;
	private String userid;
	private String coverimgid;
	private String desc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCoverimgid() {
		return coverimgid;
	}

	public void setCoverimgid(String coverimgid) {
		this.coverimgid = coverimgid;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}