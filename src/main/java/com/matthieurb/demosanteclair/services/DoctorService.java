package com.matthieurb.demosanteclair.services;

import java.util.List;

import com.matthieurb.demosanteclair.model.DoctorDTO;

public interface DoctorService {
	
	List<DoctorDTO> getAllDoctors();
	
<<<<<<< HEAD
	List<DoctorDTO> getDoctorsBySpecialtyTitle(String specialty);
	
=======
>>>>>>> 156ad710accfa772b9d2d6b775dfd7bcfe0ae1a1
	DoctorDTO getDoctorById(Long id);
	
	DoctorDTO createNewDoctor(DoctorDTO doctorDTO);

    DoctorDTO saveDoctorByDTO(Long id, DoctorDTO doctorDTO);

    void deleteDoctorById(Long id);

}
