package com.matthieurb.demosanteclair.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.matthieurb.demosanteclair.domain.Patient;
import com.matthieurb.demosanteclair.mapper.PatientMapper;
import com.matthieurb.demosanteclair.model.PatientDTO;
import com.matthieurb.demosanteclair.repositories.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {
	
	private final PatientMapper patientMapper;
	private final PatientRepository patientRepository;

	public PatientServiceImpl(PatientMapper patientMapper, PatientRepository patientRepository) {
		
		this.patientMapper = patientMapper;
		this.patientRepository = patientRepository;
	}

	@Override
	public List<PatientDTO> getAllPatients() {
		
		return patientRepository.findAll()
                .stream()
                .map(patientMapper::patientToPatientDTO)
                .collect(Collectors.toList());
	}

	@Override
	public PatientDTO getPatientById(Long id) {
		
		Optional<Patient> optional =  patientRepository.findById(id);
		if(optional.isPresent()) {
			return patientMapper.patientToPatientDTO(optional.get());
		}else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
    public PatientDTO createNewPatient(PatientDTO patientDTO) {

        return saveAndReturnDTO(patientMapper.patientDTOtoPatient(patientDTO));
    }

    private PatientDTO saveAndReturnDTO(Patient patient) {
    	
        Patient savedPatient = patientRepository.save(patient);

<<<<<<< HEAD
        PatientDTO returnDto = patientMapper.patientToPatientDTO(savedPatient);

        return returnDto;
=======
        PatientDTO patientDto = patientMapper.patientToPatientDTO(savedPatient);
        
        System.out.println(patientDto.getId());
        System.out.println(patientDto.getFirstName());
        System.out.println(patientDto.getLastName());

        return patientDto;
>>>>>>> 156ad710accfa772b9d2d6b775dfd7bcfe0ae1a1
    }

    @Override
    public PatientDTO savePatientByDTO(Long id, PatientDTO patientDTO) {
    	
        Patient patient = patientMapper.patientDTOtoPatient(patientDTO);
        patient.setId(id);

        return saveAndReturnDTO(patient);
    }

    @Override
    public void deletePatientById(Long id) {
    	
        patientRepository.deleteById(id);
    }

}
