package com.matthieurb.demosanteclair.services;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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

import com.matthieurb.demosanteclair.domain.Patient;
import com.matthieurb.demosanteclair.mapper.PatientMapper;
import com.matthieurb.demosanteclair.model.PatientDTO;
import com.matthieurb.demosanteclair.repositories.PatientRepository;

public class PatientServiceImplTest {

	@Mock
    PatientRepository patientRepository;

    PatientMapper patientMapper = PatientMapper.INSTANCE;

    PatientService patientService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        patientService = new PatientServiceImpl(patientMapper, patientRepository);
    }

    @Test
    public void getAllPatients() throws Exception {
        //given
        Patient patient1 = new Patient();
        patient1.setId(1l);
        patient1.setFirstName("Henry");
        patient1.setLastName("Dupont");

        Patient patient2 = new Patient();
        patient2.setId(2l);
        patient2.setFirstName("Jack");
        patient2.setLastName("Speziale");

        when(patientRepository.findAll()).thenReturn(Arrays.asList(patient1, patient2));

        //when
        List<PatientDTO> patientDTOS = patientService.getAllPatients();

        //then
        assertEquals(2, patientDTOS.size());
    }
    
    @Test
    public void getPatientById() {
    	
    	Patient patient1 = new Patient();
        patient1.setId(1l);
        patient1.setFirstName("Henry");
        patient1.setLastName("Dupont");
        
        when(patientRepository.findById(anyLong())).thenReturn(Optional.ofNullable(patient1));
    	
        PatientDTO patientDTO = patientService.getPatientById(1L);
        
        assertEquals("Henry", patientDTO.getFirstName());
        assertEquals("Dupont", patientDTO.getLastName());
	}
    
    @Test
    public void createNewPatient() {

    	PatientDTO patientDTO = new PatientDTO();
        patientDTO.setFirstName("Jim");
        patientDTO.setLastName("Neutron");

        Patient savedPatient = new Patient();
        savedPatient.setFirstName(patientDTO.getFirstName());
        savedPatient.setLastName(patientDTO.getLastName());
        savedPatient.setId(1l);

        when(patientRepository.save(any(Patient.class))).thenReturn(savedPatient);

        PatientDTO savedDto = patientService.createNewPatient(patientDTO);

        assertEquals(patientDTO.getFirstName(), savedDto.getFirstName());
        assertEquals(patientDTO.getLastName(), savedDto.getLastName());
    }

    @Test
    public void savePatientByDTO() {
    	
    	PatientDTO patientDTO = new PatientDTO();
        patientDTO.setFirstName("Jim");
        patientDTO.setLastName("Neutron");

        Patient savedPatient = new Patient();
        savedPatient.setFirstName(patientDTO.getFirstName());
        savedPatient.setLastName(patientDTO.getLastName());
        savedPatient.setId(1l);

        when(patientRepository.save(any(Patient.class))).thenReturn(savedPatient);

        PatientDTO savedDto = patientService.savePatientByDTO(1L, patientDTO);

        assertEquals(patientDTO.getFirstName(), savedDto.getFirstName());
        assertEquals(patientDTO.getLastName(), savedDto.getLastName());
    }

    @Test
    public void deletePatientById() {
    	
    	Long id = 1L;

        patientRepository.deleteById(id);

        verify(patientRepository, times(1)).deleteById(anyLong());
    }

}
