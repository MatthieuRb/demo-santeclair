package com.matthieurb.demosanteclair.controllers;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.matthieurb.demosanteclair.model.PatientDTO;
import com.matthieurb.demosanteclair.services.PatientService;
import com.matthieurb.demosanteclair.services.ResourceNotFoundException;

public class PatientControllerTest extends AbstractRestControllerTest {

	@Mock
    PatientService patientService;

    @InjectMocks
    PatientController patientController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(patientController)
        		.setControllerAdvice(new RestResponseEntityExceptionHandler())
        		.build();

    }

    @Test
    public void testListPatients() throws Exception {
        PatientDTO patient1 = new PatientDTO();
        patient1.setId(1l);
        patient1.setFirstName("Paul");
        patient1.setLastName("Williams");

        PatientDTO patient2 = new PatientDTO();
        patient2.setId(2l);
        patient2.setFirstName("Henry");
        patient2.setLastName("Dupont");

        List<PatientDTO> patients = Arrays.asList(patient1, patient2);

        when(patientService.getAllPatients()).thenReturn(patients);

        mockMvc.perform(get("/api/patients")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patients", hasSize(2)));
    }
    
    @Test
    public void getPatientById() throws Exception{
    	
    	PatientDTO patient1 = new PatientDTO();
        patient1.setId(1l);
        patient1.setFirstName("Paul");
        patient1.setLastName("Williams");
        
        when(patientService.getPatientById(anyLong())).thenReturn(patient1);
        
        mockMvc.perform(get("/api/patients/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Paul")))
                .andExpect(jsonPath("$.lastName", equalTo("Williams")));
    }
    
    @Test
    public void createNewPatient() throws Exception {
    	
        PatientDTO patient = new PatientDTO();
        patient.setFirstName("Paul");
        patient.setLastName("Williams");

        when(patientService.createNewPatient(patient)).thenReturn(patient);

        mockMvc.perform(post("/api/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(patient)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdatePatient() throws Exception {
    	
        PatientDTO patient = new PatientDTO();
        patient.setFirstName("Paul");
        patient.setLastName("Williams");

        when(patientService.savePatientByDTO(anyLong(), any(PatientDTO.class))).thenReturn(patient);

        mockMvc.perform(put("/api/patients/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(patient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Paul")))
                .andExpect(jsonPath("$.lastName", equalTo("Williams")));
    }

    @Test
    public void testDeletePatient() throws Exception {

        mockMvc.perform(delete("/api/patients/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(patientService).deletePatientById(anyLong());
    }

    @Test
    public void testNotFoundException() throws Exception {

        when(patientService.getPatientById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get("/api/patients/222")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
