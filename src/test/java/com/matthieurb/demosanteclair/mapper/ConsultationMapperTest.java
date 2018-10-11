package com.matthieurb.demosanteclair.mapper;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

import com.matthieurb.demosanteclair.domain.Consultation;
import com.matthieurb.demosanteclair.domain.Doctor;
import com.matthieurb.demosanteclair.domain.Patient;
import com.matthieurb.demosanteclair.model.ConsultationDTO;
import com.matthieurb.demosanteclair.model.DoctorDTO;
import com.matthieurb.demosanteclair.model.PatientDTO;

public class ConsultationMapperTest {
	
	ConsultationMapper consultationMapper = ConsultationMapper.INSTANCE;

	@Test
	public void testConsultationToConsultationDTO() throws Exception {
		
		//given
		Doctor doctor = new Doctor();
		doctor.setFirstName("Paul");
		doctor.setLastName("Mike");
		
		Patient patient = new Patient();
		patient.setFirstName("George");
		patient.setLastName("Larose");
		
        Consultation consultation = new Consultation();
        consultation.setDate(LocalDateTime.now());
        consultation.setDescription("Description");
        consultation.setDoctor(doctor);
        consultation.setPatient(patient);

        //when
        ConsultationDTO consultationDTO = consultationMapper.consultationToConsultationDTO(consultation);

        //then
        assertEquals(consultation.getDate(), consultationDTO.getDate());
        assertEquals(consultation.getDescription(), consultationDTO.getDescription());
        assertEquals(consultation.getDoctor().getFirstName(), consultationDTO.getDoctor().getFirstName());
        assertEquals(consultation.getDoctor().getLastName(), consultationDTO.getDoctor().getLastName());
	}

	@Test
	public void testConsultationDTOtoConsultation() {
		
		//given
		DoctorDTO doctor = new DoctorDTO();
		doctor.setFirstName("Paul");
		doctor.setLastName("Mike");
		
		PatientDTO patient = new PatientDTO();
		patient.setFirstName("George");
		patient.setLastName("Larose");
		
		ConsultationDTO consultationDTO = new ConsultationDTO();
		consultationDTO.setDate(LocalDateTime.now());
		consultationDTO.setDescription("Description");
		consultationDTO.setDoctor(doctor);
		consultationDTO.setPatient(patient);
        //when
        Consultation consultation = consultationMapper.consultationDTOtoConsultation(consultationDTO);

        //then
        assertEquals(consultationDTO.getDate(), consultation.getDate());
        assertEquals(consultationDTO.getDescription(), consultation.getDescription());
        assertEquals(consultationDTO.getDoctor().getFirstName(), consultation.getDoctor().getFirstName());
        assertEquals(consultationDTO.getDoctor().getLastName(), consultation.getDoctor().getLastName());
	}

}
