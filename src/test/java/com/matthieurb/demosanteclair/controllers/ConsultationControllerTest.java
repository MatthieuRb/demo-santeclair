package com.matthieurb.demosanteclair.controllers;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.matthieurb.demosanteclair.model.ConsultationDTO;
import com.matthieurb.demosanteclair.model.DoctorDTO;
import com.matthieurb.demosanteclair.model.PatientDTO;
import com.matthieurb.demosanteclair.services.ConsultationService;
import com.matthieurb.demosanteclair.services.ResourceNotFoundException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

public class ConsultationControllerTest extends AbstractRestControllerTest{

	@Mock
    ConsultationService consultationService;

    @InjectMocks
    ConsultationController consultationController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(consultationController)
        		.setControllerAdvice(new RestResponseEntityExceptionHandler())
        		.build();

    }

    @Test
    public void testListConsultations() throws Exception {
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
        consultationDTO.setId(1L);

        ConsultationDTO consultationDTO2 = new ConsultationDTO();
        consultationDTO2.setDate(date);
        consultationDTO2.setDescription("Description 2");
        consultationDTO2.setDoctor(doctor1);
        consultationDTO2.setPatient(patient1);
        consultationDTO2.setId(2L);

        List<ConsultationDTO> consultations = Arrays.asList(consultationDTO, consultationDTO2);

        when(consultationService.getAllConsultations()).thenReturn(consultations);

        mockMvc.perform(get("/api/consultations")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.consultations", hasSize(2)));
    }
    
    @Test
    public void getConsultationById() throws Exception{
    	
    	DoctorDTO doctor1 = new DoctorDTO();
    	doctor1.setFirstName("Paul");
    	doctor1.setLastName("Mike");
    	doctor1.setId(1L);
    			
		PatientDTO patient1 = new PatientDTO();
		patient1.setFirstName("George");
		patient1.setLastName("Larose");
		patient1.setId(1L);
		
		ConsultationDTO consultationDTO = new ConsultationDTO();
        consultationDTO.setDate(LocalDateTime.of(2018, 10, 9, 10, 10));
        consultationDTO.setDescription("Description");
        consultationDTO.setDoctor(doctor1);
        consultationDTO.setPatient(patient1);
        consultationDTO.setId(1L);
        
        when(consultationService.getConsultationById(anyLong())).thenReturn(consultationDTO);
        
        mockMvc.perform(get("/api/consultations/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", equalTo("Description")))
                .andExpect(jsonPath("$.doctor.firstName", equalTo("Paul")))
                .andExpect(jsonPath("$.doctor.lastName", equalTo("Mike")))
                .andExpect(jsonPath("$.patient.firstName", equalTo("George")))
                .andExpect(jsonPath("$.patient.lastName", equalTo("Larose")));
    }
    
    @Test
    public void createNewConsultation() throws Exception {
    	
    	DoctorDTO doctor1 = new DoctorDTO();
    	doctor1.setFirstName("Paul");
    	doctor1.setLastName("Mike");
    	doctor1.setId(1L);
    			
		PatientDTO patient1 = new PatientDTO();
		patient1.setFirstName("George");
		patient1.setLastName("Larose");
		patient1.setId(1L);
		
		ConsultationDTO consultationDTO = new ConsultationDTO();
        consultationDTO.setDescription("Description");
        consultationDTO.setDoctor(doctor1);
        consultationDTO.setPatient(patient1);

        when(consultationService.createNewConsultation(consultationDTO)).thenReturn(consultationDTO);
        
        System.out.println(asJsonString(consultationDTO));

        mockMvc.perform(post("/api/consultations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(consultationDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateConsultation() throws Exception {
    	
    	DoctorDTO doctor1 = new DoctorDTO();
    	doctor1.setFirstName("Paul");
    	doctor1.setLastName("Mike");
    	doctor1.setId(1L);
    			
		PatientDTO patient1 = new PatientDTO();
		patient1.setFirstName("George");
		patient1.setLastName("Larose");
		patient1.setId(1L);
		
		ConsultationDTO consultationDTO = new ConsultationDTO();
        consultationDTO.setDescription("Description");
        consultationDTO.setDoctor(doctor1);
        consultationDTO.setPatient(patient1);

        when(consultationService.saveConsultationByDTO(anyLong(), any(ConsultationDTO.class))).thenReturn(consultationDTO);

        mockMvc.perform(put("/api/consultations/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(consultationDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", equalTo("Description")))
                .andExpect(jsonPath("$.doctor.firstName", equalTo("Paul")))
                .andExpect(jsonPath("$.doctor.lastName", equalTo("Mike")))
                .andExpect(jsonPath("$.patient.firstName", equalTo("George")))
                .andExpect(jsonPath("$.patient.lastName", equalTo("Larose")));
    }

    @Test
    public void testDeleteConsultation() throws Exception {

        mockMvc.perform(delete("/api/consultations/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(consultationService).deleteConsultationById(anyLong());
    }

    @Test
    public void testNotFoundException() throws Exception {

        when(consultationService.getConsultationById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get("/api/consultations/222")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
