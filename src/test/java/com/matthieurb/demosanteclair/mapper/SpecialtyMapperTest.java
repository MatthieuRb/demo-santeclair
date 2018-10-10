package com.matthieurb.demosanteclair.mapper;

import static org.junit.Assert.*;

import org.junit.Test;

import com.matthieurb.demosanteclair.domain.Specialty;
import com.matthieurb.demosanteclair.model.SpecialtyDTO;

public class SpecialtyMapperTest {
	
	SpecialtyMapper specialtyMapper = SpecialtyMapper.INSTANCE;

	@Test
	public void testSpecialtyToSpecialtyDTO() throws Exception {

        Specialty specialty = new Specialty();
        specialty.setTitle("Dentiste");

        SpecialtyDTO specialtyDTO = specialtyMapper.specialtyToSpecialtyDTO(specialty);

        assertEquals(specialty.getTitle(), specialtyDTO.getTitle());
	}

	@Test
	public void testSpecialtyDTOtoSpecialty() {
		//given
		SpecialtyDTO specialtyDTO = new SpecialtyDTO();
		specialtyDTO.setTitle("Dentiste");

        //when
        Specialty specialty = specialtyMapper.specialtyDTOtoSpecialty(specialtyDTO);

        //then
        assertEquals(specialtyDTO.getTitle(), specialty.getTitle());
	}

}
