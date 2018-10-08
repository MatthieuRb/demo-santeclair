package com.matthieurb.demosanteclair.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.matthieurb.demosanteclair.domain.Doctor;
import com.matthieurb.demosanteclair.model.DoctorDTO;

@Mapper
public interface DoctorMapper {

	DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    DoctorDTO doctorToDoctorDTO(Doctor doctor);

    Doctor doctorDTOtoDoctor(DoctorDTO doctorDTO);
	
}
