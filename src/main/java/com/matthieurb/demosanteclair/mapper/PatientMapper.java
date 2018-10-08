package com.matthieurb.demosanteclair.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.matthieurb.demosanteclair.domain.Patient;
import com.matthieurb.demosanteclair.model.PatientDTO;

@Mapper
public interface PatientMapper {

	PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

	PatientDTO patientToPatientDTO(Patient patient);

	Patient patientDTOtoPatient(PatientDTO patientDTO);
	
}
