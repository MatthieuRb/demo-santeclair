package com.matthieurb.demosanteclair.mapper;

import static org.junit.Assert.*;

import org.junit.Test;

import com.matthieurb.demosanteclair.domain.Doctor;
import com.matthieurb.demosanteclair.model.DoctorDTO;

public class DoctorMapperTest {
	
	DoctorMapper doctorMapper = DoctorMapper.INSTANCE;

	@Test
	public void testDoctorToDoctorDTO() throws Exception {
		//given
        Doctor doctor = new Doctor();
        doctor.setFirstName("Henry");
        doctor.setLastName("Dupont");

        //when
        DoctorDTO doctorDTO = doctorMapper.doctorToDoctorDTO(doctor);

        //then
        assertEquals(doctor.getFirstName(), doctorDTO.getFirstName());
        assertEquals(doctor.getLastName(), doctorDTO.getLastName());
	}

	@Test
	public void testDoctorDTOtoDoctor() {
		//given
		DoctorDTO doctorDTO = new DoctorDTO();
		doctorDTO.setFirstName("Jack");
		doctorDTO.setLastName("Collins");

        //when
        Doctor doctor = doctorMapper.doctorDTOtoDoctor(doctorDTO);

        //then
        assertEquals(doctorDTO.getFirstName(), doctor.getFirstName());
        assertEquals(doctorDTO.getLastName(), doctor.getLastName());
	}

}
