package com.matthieurb.demosanteclair.model;

import java.util.List;

public class PatientListDTO {
	
	List<PatientDTO> patients;

	public PatientListDTO(List<PatientDTO> patients) {
		this.patients = patients;
	}

	public List<PatientDTO> getPatients() {
		return patients;
	}

	public void setPatients(List<PatientDTO> patients) {
		this.patients = patients;
	}

}
