package com.matthieurb.demosanteclair.services;

import java.util.List;

import com.matthieurb.demosanteclair.model.DoctorDTO;

public interface DoctorService {
	
	List<DoctorDTO> getAllDoctors();
	
	List<DoctorDTO> getDoctorsBySpecialtyTitle(String specialty);

	DoctorDTO getDoctorById(Long id);
	
	DoctorDTO createNewDoctor(DoctorDTO doctorDTO);

    DoctorDTO saveDoctorByDTO(Long id, DoctorDTO doctorDTO);

    void deleteDoctorById(Long id);

}
