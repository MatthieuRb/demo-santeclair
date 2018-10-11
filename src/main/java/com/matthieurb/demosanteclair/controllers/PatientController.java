package com.matthieurb.demosanteclair.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.matthieurb.demosanteclair.model.PatientDTO;
import com.matthieurb.demosanteclair.model.PatientListDTO;
import com.matthieurb.demosanteclair.services.PatientService;

import io.swagger.annotations.Api;

@Api(description="Controlleur pour la gestion des patients")
@RestController
@RequestMapping("/api/patients")
public class PatientController {
	
	private final PatientService patientService;

	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}
	
	@GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PatientListDTO getListOfPatients(){
        return new PatientListDTO(patientService.getAllPatients());
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public PatientDTO getPatientById(@PathVariable Long id){
        return patientService.getPatientById(id);
    }
	
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientDTO createNewPatient(@RequestBody PatientDTO patientDTO){
        return patientService.createNewPatient(patientDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public PatientDTO updatePatient(@PathVariable Long id, @RequestBody PatientDTO patientDTO){
        return patientService.savePatientByDTO(id, patientDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deletePatient(@PathVariable Long id){
        patientService.deletePatientById(id);
    }

}
