package org.anne.junittest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "TEST")
public class Test {
	private String a;
	private int b;

	@Id
	@Column(name = "A")
	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	@Column(name = "B")
	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}
}
