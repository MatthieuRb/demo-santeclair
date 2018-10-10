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

import com.matthieurb.demosanteclair.domain.Doctor;
<<<<<<< HEAD
import com.matthieurb.demosanteclair.domain.Specialty;
=======
>>>>>>> 156ad710accfa772b9d2d6b775dfd7bcfe0ae1a1
import com.matthieurb.demosanteclair.mapper.DoctorMapper;
import com.matthieurb.demosanteclair.model.DoctorDTO;
import com.matthieurb.demosanteclair.repositories.DoctorRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

public class DoctorServiceImplTest {

	@Mock
    DoctorRepository doctorRepository;

    DoctorMapper doctorMapper = DoctorMapper.INSTANCE;

    DoctorService doctorService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        doctorService = new DoctorServiceImpl(doctorMapper, doctorRepository);
    }

    @Test
    public void getAllDoctors() throws Exception {

        Doctor doctor1 = new Doctor();
        doctor1.setId(1l);
        doctor1.setFirstName("Henry");
        doctor1.setLastName("Dupont");

        Doctor doctor2 = new Doctor();
        doctor2.setId(2l);
        doctor2.setFirstName("Jack");
        doctor2.setLastName("Speziale");

        when(doctorRepository.findAll()).thenReturn(Arrays.asList(doctor1, doctor2));

        List<DoctorDTO> doctorDTOS = doctorService.getAllDoctors();

        assertEquals(2, doctorDTOS.size());
    }
    
    @Test
<<<<<<< HEAD
    public void getDoctorsBySpecialtyTitle() {
    	
    	Specialty specialty1 = new Specialty();
    	specialty1.setId(1L);
    	specialty1.setTitle("Dentiste");
    	
    	Specialty specialty2 = new Specialty();
    	specialty2.setId(1L);
    	specialty2.setTitle("Kiné");
    	
    	Doctor doctor1 = new Doctor();
        doctor1.setId(1l);
        doctor1.setFirstName("Henry");
        doctor1.setLastName("Dupont");
        doctor1.setSpecialty(specialty1);

        Doctor doctor2 = new Doctor();
        doctor2.setId(2l);
        doctor2.setFirstName("Jack");
        doctor2.setLastName("Speziale");
        doctor2.setSpecialty(specialty2);

        when(doctorRepository.findAll()).thenReturn(Arrays.asList(doctor1, doctor2));

        List<DoctorDTO> doctorDTOS = doctorService.getDoctorsBySpecialtyTitle("Dentiste");

        assertEquals(1, doctorDTOS.size());
	}
    
    @Test
=======
>>>>>>> 156ad710accfa772b9d2d6b775dfd7bcfe0ae1a1
    public void getDoctorById() {
    	
    	Doctor doctor1 = new Doctor();
        doctor1.setId(1l);
        doctor1.setFirstName("Henry");
        doctor1.setLastName("Dupont");
        
        when(doctorRepository.findById(anyLong())).thenReturn(Optional.ofNullable(doctor1));
    	
        DoctorDTO doctorDTO = doctorService.getDoctorById(1L);
        
        assertEquals("Henry", doctorDTO.getFirstName());
        assertEquals("Dupont", doctorDTO.getLastName());
	}
    
    @Test
    public void createNewDoctor() {

    	DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setFirstName("Jim");
        doctorDTO.setLastName("Neutron");

        Doctor savedDoctor = new Doctor();
        savedDoctor.setFirstName(doctorDTO.getFirstName());
        savedDoctor.setLastName(doctorDTO.getLastName());
        savedDoctor.setId(1l);

        when(doctorRepository.save(any(Doctor.class))).thenReturn(savedDoctor);

        DoctorDTO savedDto = doctorService.createNewDoctor(doctorDTO);

        assertEquals(doctorDTO.getFirstName(), savedDto.getFirstName());
        assertEquals(doctorDTO.getLastName(), savedDto.getLastName());
    }

    @Test
    public void saveDoctorByDTO() {
    	
    	DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setFirstName("Jim");
        doctorDTO.setLastName("Neutron");

        Doctor savedDoctor = new Doctor();
        savedDoctor.setFirstName(doctorDTO.getFirstName());
        savedDoctor.setLastName(doctorDTO.getLastName());
        savedDoctor.setId(1l);

        when(doctorRepository.save(any(Doctor.class))).thenReturn(savedDoctor);

        DoctorDTO savedDto = doctorService.saveDoctorByDTO(1L, doctorDTO);

        assertEquals(doctorDTO.getFirstName(), savedDto.getFirstName());
        assertEquals(doctorDTO.getLastName(), savedDto.getLastName());
    }

    @Test
    public void deleteDoctorById() {
    	
    	Long id = 1L;

        doctorRepository.deleteById(id);

        verify(doctorRepository, times(1)).deleteById(anyLong());
    }

}
