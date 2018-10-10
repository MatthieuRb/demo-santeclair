package com.matthieurb.demosanteclair.services;

import java.util.List;

import com.matthieurb.demosanteclair.model.ConsultationDTO;

public interface ConsultationService {
	
	List<ConsultationDTO> getAllConsultations();
	
	ConsultationDTO getConsultationById(Long id);
	
	ConsultationDTO createNewConsultation(ConsultationDTO consultationDTO);

    ConsultationDTO saveConsultationByDTO(Long id, ConsultationDTO consultationDTO);

    void deleteConsultationById(Long id);

}
