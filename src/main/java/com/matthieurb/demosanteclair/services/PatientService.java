package com.matthieurb.demosanteclair.services;

import java.util.List;

import com.matthieurb.demosanteclair.model.PatientDTO;

public interface PatientService {
	
	List<PatientDTO> getAllPatients();
	
	PatientDTO getPatientById(Long id);
	
	PatientDTO createNewPatient(PatientDTO patientDTO);

    PatientDTO savePatientByDTO(Long id, PatientDTO patientDTO);

    void deletePatientById(Long id);

}
