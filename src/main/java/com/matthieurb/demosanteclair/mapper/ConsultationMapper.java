package com.matthieurb.demosanteclair.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.matthieurb.demosanteclair.domain.Consultation;
import com.matthieurb.demosanteclair.model.ConsultationDTO;

@Mapper
public interface ConsultationMapper {
	
	ConsultationMapper INSTANCE = Mappers.getMapper(ConsultationMapper.class);

    ConsultationDTO consultationToConsultationDTO(Consultation consultation);

    Consultation consultationDTOtoConsultation(ConsultationDTO consultationDTO);

}
