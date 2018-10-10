package com.matthieurb.demosanteclair.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="doctors")
public class Doctor extends Person {
	
	@ManyToOne
	@JoinColumn(name="specialty_id")
	private Specialty specialty;

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}
	
}
