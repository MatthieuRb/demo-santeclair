package com.matthieurb.demosanteclair.services;

import java.util.List;

import com.matthieurb.demosanteclair.model.SpecialtyDTO;

public interface SpecialtyService {
	
	List<SpecialtyDTO> getAllSpecialties();
	
	SpecialtyDTO getSpecialtyById(Long id);
	
	SpecialtyDTO createNewSpecialty(SpecialtyDTO specialtyDTO);

    SpecialtyDTO saveSpecialtyByDTO(Long id, SpecialtyDTO specialtyDTO);

    void deleteSpecialtyById(Long id);

}
