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

import com.matthieurb.demosanteclair.model.SpecialtyDTO;
import com.matthieurb.demosanteclair.services.SpecialtyService;
import com.matthieurb.demosanteclair.services.ResourceNotFoundException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

public class SpecialtyControllerTest extends AbstractRestControllerTest{

	@Mock
    SpecialtyService specialtyService;

    @InjectMocks
    SpecialtyController specialtyController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
    	
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(specialtyController)
        		.setControllerAdvice(new RestResponseEntityExceptionHandler())
        		.build();

    }

    @Test
    public void testListSpecialties() throws Exception {
    	
        SpecialtyDTO specialty1 = new SpecialtyDTO();
        specialty1.setId(1l);
        specialty1.setTitle("Dentiste");

        SpecialtyDTO specialty2 = new SpecialtyDTO();
        specialty2.setId(2l);
        specialty2.setTitle("Kiné");

        List<SpecialtyDTO> specialties = Arrays.asList(specialty1, specialty2);

        when(specialtyService.getAllSpecialties()).thenReturn(specialties);

        mockMvc.perform(get("/api/specialties")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.specialties", hasSize(2)));
    }
    
    @Test
    public void getSpecialtyById() throws Exception{
    	
    	SpecialtyDTO specialty1 = new SpecialtyDTO();
        specialty1.setId(1l);
        specialty1.setTitle("Dentiste");
        
        when(specialtyService.getSpecialtyById(anyLong())).thenReturn(specialty1);
        
        mockMvc.perform(get("/api/specialties/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", equalTo("Dentiste")));
    }
    
    @Test
    public void createNewSpecialty() throws Exception {
    	
        SpecialtyDTO specialty = new SpecialtyDTO();
        specialty.setTitle("Dentiste");

        SpecialtyDTO specialtyDTO = new SpecialtyDTO();
        specialtyDTO.setTitle(specialty.getTitle());

        when(specialtyService.createNewSpecialty(specialty)).thenReturn(specialtyDTO);

        mockMvc.perform(post("/api/specialties")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(specialty)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateSpecialty() throws Exception {
    	
        SpecialtyDTO specialty = new SpecialtyDTO();
        specialty.setTitle("Dentiste");

        SpecialtyDTO returnDTO = new SpecialtyDTO();
        returnDTO.setTitle(specialty.getTitle());

        when(specialtyService.saveSpecialtyByDTO(anyLong(), any(SpecialtyDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(put("/api/specialties/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(specialty)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", equalTo("Dentiste")));
    }

    @Test
    public void testDeleteSpecialty() throws Exception {

        mockMvc.perform(delete("/api/specialties/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(specialtyService).deleteSpecialtyById(anyLong());
    }

    @Test
    public void testNotFoundException() throws Exception {

        when(specialtyService.getSpecialtyById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get("/api/specialties/222")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
