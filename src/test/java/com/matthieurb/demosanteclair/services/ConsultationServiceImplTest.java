package com.matthieurb.demosanteclair.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.matthieurb.demosanteclair.domain.Consultation;
import com.matthieurb.demosanteclair.domain.Doctor;
import com.matthieurb.demosanteclair.domain.Patient;
import com.matthieurb.demosanteclair.mapper.ConsultationMapper;
import com.matthieurb.demosanteclair.model.ConsultationDTO;
import com.matthieurb.demosanteclair.model.DoctorDTO;
import com.matthieurb.demosanteclair.model.PatientDTO;
import com.matthieurb.demosanteclair.repositories.ConsultationRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

public class ConsultationServiceImplTest {

	@Mock
    ConsultationRepository consultationRepository;

    ConsultationMapper consultationMapper = ConsultationMapper.INSTANCE;

    ConsultationService consultationService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        consultationService = new ConsultationServiceImpl(consultationMapper, consultationRepository);
    }

    @Test
    public void getAllConsultations() throws Exception {

    	//given
    	Doctor doctor1 = new Doctor();
    	doctor1.setFirstName("Paul");
    	doctor1.setLastName("Mike");
    	doctor1.setId(1L);
    			
		Patient patient1 = new Patient();
		patient1.setFirstName("George");
		patient1.setLastName("Larose");
		patient1.setId(1L);
		
		Doctor doctor2 = new Doctor();
    	doctor2.setFirstName("Henry");
    	doctor2.setLastName("Lefort");
    	doctor2.setId(2L);
    			
		Patient patient2 = new Patient();
		patient2.setFirstName("Thierry");
		patient2.setLastName("Maur");
		patient2.setId(2L);
		
        Consultation consultation1 = new Consultation();
        consultation1.setDate(LocalDateTime.now());
        consultation1.setDescription("Description");
        consultation1.setDoctor(doctor1);
        consultation1.setPatient(patient1);
        consultation1.setId(1L);
        
        Consultation consultation2 = new Consultation();
        consultation2.setDate(LocalDateTime.now());
        consultation2.setDescription("Description deuxième RDV");
        consultation2.setDoctor(doctor2);
        consultation2.setPatient(patient2);
        consultation2.setId(2L);

        when(consultationRepository.findAll()).thenReturn(Arrays.asList(consultation1, consultation2));

        List<ConsultationDTO> consultationDTOS = consultationService.getAllConsultations();

        assertEquals(2, consultationDTOS.size());
    }
    
    @Test
    public void getConsultationById() {
    	
    	//given
    	Doctor doctor1 = new Doctor();
    	doctor1.setFirstName("Paul");
    	doctor1.setLastName("Mike");
    	doctor1.setId(1L);
    			
		Patient patient1 = new Patient();
		patient1.setFirstName("George");
		patient1.setLastName("Larose");
		patient1.setId(1L);
		
		LocalDateTime date = LocalDateTime.now();
		
		Consultation consultation1 = new Consultation();
        consultation1.setDate(date);
        consultation1.setDescription("Description");
        consultation1.setDoctor(doctor1);
        consultation1.setPatient(patient1);
        consultation1.setId(1L);
        
        when(consultationRepository.findById(anyLong())).thenReturn(Optional.ofNullable(consultation1));
    	
        ConsultationDTO consultationDTO = consultationService.getConsultationById(1L);
        
        assertEquals("Description", consultationDTO.getDescription());
        assertEquals(date, consultationDTO.getDate());
        assertEquals("Paul", consultationDTO.getDoctor().getFirstName());
        assertEquals("Mike", consultationDTO.getDoctor().getLastName());
        assertEquals("George", consultationDTO.getPatient().getFirstName());
        assertEquals("Larose", consultationDTO.getPatient().getLastName());
	}
    
    @Test
    public void createNewConsultation() {

    	DoctorDTO doctor1 = new DoctorDTO();
    	doctor1.setFirstName("Paul");
    	doctor1.setLastName("Mike");
    			
		PatientDTO patient1 = new PatientDTO();
		patient1.setFirstName("George");
		patient1.setLastName("Larose");
		
		LocalDateTime date = LocalDateTime.now();
		
		ConsultationDTO consultationDTO = new ConsultationDTO();
        consultationDTO.setDate(date);
        consultationDTO.setDescription("Description");
        consultationDTO.setDoctor(doctor1);
        consultationDTO.setPatient(patient1);
        
        Doctor savedDoctor = new Doctor();
        savedDoctor.setFirstName(doctor1.getFirstName());
        savedDoctor.setLastName(doctor1.getLastName());
    			
		Patient savedPatient = new Patient();
		savedPatient.setFirstName(patient1.getFirstName());
		savedPatient.setLastName(patient1.getLastName());

        Consultation savedConsultation = new Consultation();
        savedConsultation.setDescription(consultationDTO.getDescription());
        savedConsultation.setDate(consultationDTO.getDate());
        savedConsultation.setDoctor(savedDoctor);
        savedConsultation.setPatient(savedPatient);
        savedConsultation.setId(1l);

        when(consultationRepository.save(any(Consultation.class))).thenReturn(savedConsultation);

        ConsultationDTO savedDto = consultationService.createNewConsultation(consultationDTO);

        assertEquals(savedConsultation.getDescription(), savedDto.getDescription());
        assertEquals(savedConsultation.getDate(), savedDto.getDate());
        assertEquals(savedConsultation.getDoctor().getFirstName(), savedDto.getDoctor().getFirstName());
        assertEquals(savedConsultation.getDoctor().getLastName(), savedDto.getDoctor().getLastName());
        assertEquals(savedConsultation.getPatient().getFirstName(), savedDto.getPatient().getFirstName());
        assertEquals(savedConsultation.getPatient().getLastName(), savedDto.getPatient().getLastName());
    }

    @Test
    public void saveConsultationByDTO() {
    	
    	DoctorDTO doctor1 = new DoctorDTO();
    	doctor1.setFirstName("Paul");
    	doctor1.setLastName("Mike");
    			
		PatientDTO patient1 = new PatientDTO();
		patient1.setFirstName("George");
		patient1.setLastName("Larose");
		
		LocalDateTime date = LocalDateTime.now();
		
		ConsultationDTO consultationDTO = new ConsultationDTO();
        consultationDTO.setDate(date);
        consultationDTO.setDescription("Description");
        consultationDTO.setDoctor(doctor1);
        consultationDTO.setPatient(patient1);
        
        Doctor savedDoctor = new Doctor();
        savedDoctor.setFirstName(doctor1.getFirstName());
        savedDoctor.setLastName(doctor1.getLastName());
    			
		Patient savedPatient = new Patient();
		savedPatient.setFirstName(patient1.getFirstName());
		savedPatient.setLastName(patient1.getLastName());

        Consultation savedConsultation = new Consultation();
        savedConsultation.setDescription(consultationDTO.getDescription());
        savedConsultation.setDate(consultationDTO.getDate());
        savedConsultation.setDoctor(savedDoctor);
        savedConsultation.setPatient(savedPatient);
        savedConsultation.setId(1l);

        when(consultationRepository.save(any(Consultation.class))).thenReturn(savedConsultation);

        ConsultationDTO savedDto = consultationService.saveConsultationByDTO(1L, consultationDTO);

        assertEquals(savedConsultation.getDescription(), savedDto.getDescription());
        assertEquals(savedConsultation.getDate(), savedDto.getDate());
        assertEquals(savedConsultation.getDoctor().getFirstName(), savedDto.getDoctor().getFirstName());
        assertEquals(savedConsultation.getDoctor().getLastName(), savedDto.getDoctor().getLastName());
        assertEquals(savedConsultation.getPatient().getFirstName(), savedDto.getPatient().getFirstName());
        assertEquals(savedConsultation.getPatient().getLastName(), savedDto.getPatient().getLastName());
    }

    @Test
    public void deleteConsultationById() {
    	
    	Long id = 1L;

        consultationRepository.deleteById(id);

        verify(consultationRepository, times(1)).deleteById(anyLong());
    }

}
