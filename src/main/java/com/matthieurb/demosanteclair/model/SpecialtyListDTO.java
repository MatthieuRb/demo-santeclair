package com.matthieurb.demosanteclair.model;

import java.util.List;

public class SpecialtyListDTO {
	
	List<SpecialtyDTO> specialties;

	public SpecialtyListDTO(List<SpecialtyDTO> specialties) {
		this.specialties = specialties;
	}

	public List<SpecialtyDTO> getSpecialties() {
		return specialties;
	}

	public void setSpecialties(List<SpecialtyDTO> specialties) {
		this.specialties = specialties;
	}

}
