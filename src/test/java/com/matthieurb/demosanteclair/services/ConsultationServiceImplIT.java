package com.matthieurb.demosanteclair.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.matthieurb.demosanteclair.bootstrap.Bootstrap;
import com.matthieurb.demosanteclair.mapper.ConsultationMapper;
import com.matthieurb.demosanteclair.model.ConsultationDTO;
import com.matthieurb.demosanteclair.model.DoctorDTO;
import com.matthieurb.demosanteclair.model.PatientDTO;
import com.matthieurb.demosanteclair.repositories.ConsultationRepository;
import com.matthieurb.demosanteclair.repositories.DoctorRepository;
import com.matthieurb.demosanteclair.repositories.PatientRepository;
import com.matthieurb.demosanteclair.repositories.SpecialtyRepository;

import java.time.LocalDateTime;

/**
 * Created by jt on 10/3/17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ConsultationServiceImplIT {

    @Autowired
    ConsultationRepository consultationRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;
    
    @Autowired
    SpecialtyRepository specialtyRepository;

    ConsultationService consultationService;

    @Before
    public void setUp() throws Exception {

        //setup data for testing
        Bootstrap bootstrap = new Bootstrap(doctorRepository, patientRepository, specialtyRepository, consultationRepository);
        bootstrap.run(); //load data

        consultationService = new ConsultationServiceImpl(ConsultationMapper.INSTANCE, consultationRepository);
    }

    @Test(expected = DateTooCloseException.class)
    public void createNewConsultationWithDatesTooClose() {
    	
    	DoctorDTO doctor1 = new DoctorDTO();
    	doctor1.setFirstName("Paul");
    	doctor1.setLastName("Mike");
    	doctor1.setId(1L);
    			
		PatientDTO patient1 = new PatientDTO();
		patient1.setFirstName("George");
		patient1.setLastName("Larose");
		patient1.setId(1L);
		
		LocalDateTime date = LocalDateTime.now();
		
		ConsultationDTO consultationDTO = new ConsultationDTO();
        consultationDTO.setDate(date);
        consultationDTO.setDescription("Description");
        consultationDTO.setDoctor(doctor1);
        consultationDTO.setPatient(patient1);

        consultationService.createNewConsultation(consultationDTO);
        
        ConsultationDTO consultationDTO2 = new ConsultationDTO();
        consultationDTO2.setDate(date);
        consultationDTO2.setDescription("Description");
        consultationDTO2.setDoctor(doctor1);
        consultationDTO2.setPatient(patient1);
        
        consultationService.createNewConsultation(consultationDTO2);
    }
}
