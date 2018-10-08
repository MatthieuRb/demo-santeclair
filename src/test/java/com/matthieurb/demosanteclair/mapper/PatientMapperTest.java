package com.matthieurb.demosanteclair.mapper;

import static org.junit.Assert.*;

import org.junit.Test;

import com.matthieurb.demosanteclair.domain.Patient;
import com.matthieurb.demosanteclair.model.PatientDTO;

public class PatientMapperTest {

	PatientMapper patientMapper = PatientMapper.INSTANCE;

	@Test
	public void testPatientToPatientDTO() throws Exception {
		//given
        Patient patient = new Patient();
        patient.setFirstName("Henry");
        patient.setLastName("Dupont");

        //when
        PatientDTO patientDTO = patientMapper.patientToPatientDTO(patient);

        //then
        assertEquals(patient.getFirstName(), patientDTO.getFirstName());
        assertEquals(patient.getLastName(), patientDTO.getLastName());
	}

	@Test
	public void testPatientDTOtoPatient() {
		//given
		PatientDTO patientDTO = new PatientDTO();
		patientDTO.setFirstName("Jack");
		patientDTO.setLastName("Collins");

        //when
        Patient patient = patientMapper.patientDTOtoPatient(patientDTO);

        //then
        assertEquals(patientDTO.getFirstName(), patient.getFirstName());
        assertEquals(patientDTO.getLastName(), patient.getLastName());
	}

}
