package com.matthieurb.demosanteclair.model;

import java.util.List;

public class DoctorListDTO {
	
	List<DoctorDTO> doctors;

	public DoctorListDTO(List<DoctorDTO> doctors) {
		this.doctors = doctors;
	}

	public List<DoctorDTO> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<DoctorDTO> doctors) {
		this.doctors = doctors;
	}

}
