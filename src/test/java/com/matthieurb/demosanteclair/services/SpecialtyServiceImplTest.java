package com.matthieurb.demosanteclair.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.matthieurb.demosanteclair.domain.Specialty;
import com.matthieurb.demosanteclair.mapper.SpecialtyMapper;
import com.matthieurb.demosanteclair.model.SpecialtyDTO;
import com.matthieurb.demosanteclair.repositories.SpecialtyRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

public class SpecialtyServiceImplTest {

	@Mock
    SpecialtyRepository specialtyRepository;

    SpecialtyMapper specialtyMapper = SpecialtyMapper.INSTANCE;

    SpecialtyService specialtyService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        specialtyService = new SpecialtyServiceImpl(specialtyMapper, specialtyRepository);
    }

    @Test
    public void getAllSpecialtys() throws Exception {

        Specialty specialty1 = new Specialty();
        specialty1.setId(1l);
        specialty1.setTitle("Dentiste");

        Specialty specialty2 = new Specialty();
        specialty2.setId(2l);
        specialty2.setTitle("Kiné");

        when(specialtyRepository.findAll()).thenReturn(Arrays.asList(specialty1, specialty2));

        List<SpecialtyDTO> specialtyDTOS = specialtyService.getAllSpecialties();

        assertEquals(2, specialtyDTOS.size());
    }
    
    @Test
    public void getSpecialtyById() {
    	
    	Specialty specialty1 = new Specialty();
        specialty1.setId(1l);
        specialty1.setTitle("Dentiste");
        
        when(specialtyRepository.findById(anyLong())).thenReturn(Optional.ofNullable(specialty1));
    	
        SpecialtyDTO specialtyDTO = specialtyService.getSpecialtyById(1L);
        
        assertEquals("Dentiste", specialtyDTO.getTitle());
	}
    
    @Test
    public void createNewSpecialty() {

    	SpecialtyDTO specialtyDTO = new SpecialtyDTO();
    	specialtyDTO.setTitle("Dentiste");

        Specialty savedSpecialty = new Specialty();
        savedSpecialty.setTitle(specialtyDTO.getTitle());
        savedSpecialty.setId(1l);

        when(specialtyRepository.save(any(Specialty.class))).thenReturn(savedSpecialty);

        SpecialtyDTO savedDto = specialtyService.createNewSpecialty(specialtyDTO);

        assertEquals(specialtyDTO.getTitle(), savedDto.getTitle());
    }

    @Test
    public void saveSpecialtyByDTO() {
    	
    	SpecialtyDTO specialtyDTO = new SpecialtyDTO();
    	specialtyDTO.setTitle("Dentiste");

        Specialty savedSpecialty = new Specialty();
        savedSpecialty.setTitle(specialtyDTO.getTitle());
        savedSpecialty.setId(1l);

        when(specialtyRepository.save(any(Specialty.class))).thenReturn(savedSpecialty);

        SpecialtyDTO savedDto = specialtyService.saveSpecialtyByDTO(1L, specialtyDTO);

        assertEquals(specialtyDTO.getTitle(), savedDto.getTitle());
    }

    @Test
    public void deleteSpecialtyById() {
    	
    	Long id = 1L;

        specialtyRepository.deleteById(id);

        verify(specialtyRepository, times(1)).deleteById(anyLong());
    }

}
