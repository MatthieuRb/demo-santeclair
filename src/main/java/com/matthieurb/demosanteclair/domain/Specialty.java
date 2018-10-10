package com.matthieurb.demosanteclair.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="specialties")
public class Specialty extends BaseEntity {
	
	@Column(name="title")
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
