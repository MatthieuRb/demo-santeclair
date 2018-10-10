package com.matthieurb.demosanteclair.model;

import java.util.List;

public class ConsultationListDTO {
	
	List<ConsultationDTO> consultations;

	public ConsultationListDTO(List<ConsultationDTO> consultations) {
		this.consultations = consultations;
	}

	public List<ConsultationDTO> getConsultations() {
		return consultations;
	}

	public void setConsultations(List<ConsultationDTO> consultations) {
		this.consultations = consultations;
	}
	

}
