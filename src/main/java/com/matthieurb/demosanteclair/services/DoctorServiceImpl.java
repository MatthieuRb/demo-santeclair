package com.matthieurb.demosanteclair.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.matthieurb.demosanteclair.domain.Doctor;
import com.matthieurb.demosanteclair.mapper.DoctorMapper;
import com.matthieurb.demosanteclair.model.DoctorDTO;
import com.matthieurb.demosanteclair.repositories.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService {
	
	private final DoctorMapper doctorMapper;
	private final DoctorRepository doctorRepository;

	public DoctorServiceImpl(DoctorMapper doctorMapper, DoctorRepository doctorRepository) {
	
		this.doctorMapper = doctorMapper;
		this.doctorRepository = doctorRepository;
	}

	@Override
	public List<DoctorDTO> getAllDoctors() {

		return doctorRepository.findAll()
                .stream()
                .map(doctorMapper::doctorToDoctorDTO)
                .collect(Collectors.toList());
	}
	
	@Override
	public List<DoctorDTO> getDoctorsBySpecialtyTitle(String specialty) {
		
		return doctorRepository.findAll()
                .stream()
                .map(doctorMapper::doctorToDoctorDTO)
                .filter(doctorDTO -> specialty.equals(doctorDTO.getSpecialty().getTitle()))
                .collect(Collectors.toList());
	}
	
	@Override
	public DoctorDTO getDoctorById(Long id) {
		Optional<Doctor> optional =  doctorRepository.findById(id);
		if(optional.isPresent()) {
			return doctorMapper.doctorToDoctorDTO(optional.get());
		}else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
    public DoctorDTO createNewDoctor(DoctorDTO doctorDTO) {

        return saveAndReturnDTO(doctorMapper.doctorDTOtoDoctor(doctorDTO));
    }

    private DoctorDTO saveAndReturnDTO(Doctor doctor) {
    	
        Doctor savedDoctor = doctorRepository.save(doctor);

        DoctorDTO returnDto = doctorMapper.doctorToDoctorDTO(savedDoctor);

        return returnDto;
    }

    @Override
    public DoctorDTO saveDoctorByDTO(Long id, DoctorDTO doctorDTO) {
    	
        Doctor doctor = doctorMapper.doctorDTOtoDoctor(doctorDTO);
        doctor.setId(id);

        return saveAndReturnDTO(doctor);
    }

    @Override
    public void deleteDoctorById(Long id) {
    	
        doctorRepository.deleteById(id);
    }

}
