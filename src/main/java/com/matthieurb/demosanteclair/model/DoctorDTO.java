package com.matthieurb.demosanteclair.model;

public class DoctorDTO extends PersonDTO {
	
	private SpecialtyDTO specialty;

	public SpecialtyDTO getSpecialty() {
		return specialty;
	}

	public void setSpecialty(SpecialtyDTO specialty) {
		this.specialty = specialty;
	}

}
