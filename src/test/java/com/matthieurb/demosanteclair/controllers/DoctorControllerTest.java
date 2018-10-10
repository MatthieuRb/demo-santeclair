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

import com.matthieurb.demosanteclair.model.DoctorDTO;
<<<<<<< HEAD
import com.matthieurb.demosanteclair.model.SpecialtyDTO;
=======
>>>>>>> 156ad710accfa772b9d2d6b775dfd7bcfe0ae1a1
import com.matthieurb.demosanteclair.services.DoctorService;
import com.matthieurb.demosanteclair.services.ResourceNotFoundException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

public class DoctorControllerTest extends AbstractRestControllerTest{

	@Mock
    DoctorService doctorService;

    @InjectMocks
    DoctorController doctorController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
<<<<<<< HEAD
    	
=======
>>>>>>> 156ad710accfa772b9d2d6b775dfd7bcfe0ae1a1
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(doctorController)
        		.setControllerAdvice(new RestResponseEntityExceptionHandler())
        		.build();

    }

    @Test
<<<<<<< HEAD
    public void testListDoctors() throws Exception {
    	
=======
    public void testListCategories() throws Exception {
>>>>>>> 156ad710accfa772b9d2d6b775dfd7bcfe0ae1a1
        DoctorDTO doctor1 = new DoctorDTO();
        doctor1.setId(1l);
        doctor1.setFirstName("Paul");
        doctor1.setLastName("Williams");

        DoctorDTO doctor2 = new DoctorDTO();
        doctor2.setId(2l);
        doctor2.setFirstName("Henry");
        doctor2.setLastName("Dupont");

        List<DoctorDTO> doctors = Arrays.asList(doctor1, doctor2);

        when(doctorService.getAllDoctors()).thenReturn(doctors);

        mockMvc.perform(get("/api/doctors")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.doctors", hasSize(2)));
    }
    
    @Test
    public void getDoctorById() throws Exception{
    	
    	DoctorDTO doctor1 = new DoctorDTO();
        doctor1.setId(1l);
        doctor1.setFirstName("Paul");
        doctor1.setLastName("Williams");
        
        when(doctorService.getDoctorById(anyLong())).thenReturn(doctor1);
        
        mockMvc.perform(get("/api/doctors/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Paul")))
                .andExpect(jsonPath("$.lastName", equalTo("Williams")));
    }
    
    @Test
<<<<<<< HEAD
    public void getDoctorsBySpecialtyTitle() throws Exception{
    	
    	SpecialtyDTO specialty1 = new SpecialtyDTO();
    	specialty1.setId(1L);
    	specialty1.setTitle("Dentiste");
    	
    	DoctorDTO doctor1 = new DoctorDTO();
        doctor1.setId(1l);
        doctor1.setFirstName("Henry");
        doctor1.setLastName("Dupont");
        doctor1.setSpecialty(specialty1);
        
        List<DoctorDTO> doctors = Arrays.asList(doctor1);

        when(doctorService.getDoctorsBySpecialtyTitle(specialty1.getTitle())).thenReturn(doctors);
        
        mockMvc.perform(get("/api/doctors/specialty/Dentiste")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.doctors", hasSize(1)));
    }
    
    @Test
=======
>>>>>>> 156ad710accfa772b9d2d6b775dfd7bcfe0ae1a1
    public void createNewDoctor() throws Exception {
    	
        DoctorDTO doctor = new DoctorDTO();
        doctor.setFirstName("Paul");
        doctor.setLastName("Williams");

        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setFirstName(doctor.getFirstName());
        doctorDTO.setLastName(doctor.getLastName());

        when(doctorService.createNewDoctor(doctor)).thenReturn(doctorDTO);

        mockMvc.perform(post("/api/doctors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(doctor)))
<<<<<<< HEAD
                .andExpect(status().isCreated());
=======
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo("Paul")))
                .andExpect(jsonPath("$.lastName", equalTo("Williams")));
>>>>>>> 156ad710accfa772b9d2d6b775dfd7bcfe0ae1a1
    }

    @Test
    public void testUpdateDoctor() throws Exception {
    	
        DoctorDTO doctor = new DoctorDTO();
        doctor.setFirstName("Paul");
        doctor.setLastName("Williams");

        DoctorDTO returnDTO = new DoctorDTO();
        returnDTO.setFirstName(doctor.getFirstName());
        returnDTO.setLastName(doctor.getLastName());

        when(doctorService.saveDoctorByDTO(anyLong(), any(DoctorDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(put("/api/doctors/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(doctor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Paul")))
                .andExpect(jsonPath("$.lastName", equalTo("Williams")));
    }

    @Test
    public void testDeleteDoctor() throws Exception {

        mockMvc.perform(delete("/api/doctors/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(doctorService).deleteDoctorById(anyLong());
    }

    @Test
    public void testNotFoundException() throws Exception {

        when(doctorService.getDoctorById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get("/api/doctors/222")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
