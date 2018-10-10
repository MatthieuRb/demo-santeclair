package com.matthieurb.demosanteclair.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.matthieurb.demosanteclair.domain.Specialty;
import com.matthieurb.demosanteclair.model.SpecialtyDTO;

@Mapper
public interface SpecialtyMapper {
	
	SpecialtyMapper INSTANCE = Mappers.getMapper(SpecialtyMapper.class);

    SpecialtyDTO specialtyToSpecialtyDTO(Specialty specialty);

    Specialty specialtyDTOtoSpecialty(SpecialtyDTO specialtyDTO);

}
