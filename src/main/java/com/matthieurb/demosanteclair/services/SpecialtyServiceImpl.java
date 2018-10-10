package com.matthieurb.demosanteclair.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.matthieurb.demosanteclair.domain.Specialty;
import com.matthieurb.demosanteclair.mapper.SpecialtyMapper;
import com.matthieurb.demosanteclair.model.SpecialtyDTO;
import com.matthieurb.demosanteclair.repositories.SpecialtyRepository;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {
	
	private final SpecialtyMapper specialtyMapper;
	private final SpecialtyRepository specialtyRepository;

	public SpecialtyServiceImpl(SpecialtyMapper specialtyMapper, SpecialtyRepository specialtyRepository) {
		
		this.specialtyMapper = specialtyMapper;
		this.specialtyRepository = specialtyRepository;
	}

	@Override
	public List<SpecialtyDTO> getAllSpecialties() {
		
		return specialtyRepository.findAll()
                .stream()
                .map(specialtyMapper::specialtyToSpecialtyDTO)
                .collect(Collectors.toList());
	}

	@Override
	public SpecialtyDTO getSpecialtyById(Long id) {
		
		Optional<Specialty> optional =  specialtyRepository.findById(id);
		if(optional.isPresent()) {
			return specialtyMapper.specialtyToSpecialtyDTO(optional.get());
		}else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
    public SpecialtyDTO createNewSpecialty(SpecialtyDTO specialtyDTO) {

        return saveAndReturnDTO(specialtyMapper.specialtyDTOtoSpecialty(specialtyDTO));
    }

    private SpecialtyDTO saveAndReturnDTO(Specialty specialty) {
    	
        Specialty savedSpecialty = specialtyRepository.save(specialty);

        SpecialtyDTO returnDto = specialtyMapper.specialtyToSpecialtyDTO(savedSpecialty);

        return returnDto;
    }

    @Override
    public SpecialtyDTO saveSpecialtyByDTO(Long id, SpecialtyDTO specialtyDTO) {
    	
        Specialty specialty = specialtyMapper.specialtyDTOtoSpecialty(specialtyDTO);
        specialty.setId(id);

        return saveAndReturnDTO(specialty);
    }

    @Override
    public void deleteSpecialtyById(Long id) {
    	
        specialtyRepository.deleteById(id);
    }

}
