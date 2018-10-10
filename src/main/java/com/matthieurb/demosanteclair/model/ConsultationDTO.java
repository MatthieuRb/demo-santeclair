package com.matthieurb.demosanteclair.model;

import java.time.LocalDateTime;

public class ConsultationDTO extends BaseEntityDTO {
	
	private LocalDateTime date;
	private String description;
	private DoctorDTO doctor;
	private PatientDTO patient;
	
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public DoctorDTO getDoctor() {
		return doctor;
	}
	public void setDoctor(DoctorDTO doctor) {
		this.doctor = doctor;
	}
	public PatientDTO getPatient() {
		return patient;
	}
	public void setPatient(PatientDTO patient) {
		this.patient = patient;
	}
	
}
