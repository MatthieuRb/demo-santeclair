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

import com.matthieurb.demosanteclair.model.DoctorDTO;
import com.matthieurb.demosanteclair.model.DoctorListDTO;
import com.matthieurb.demosanteclair.services.DoctorService;

import io.swagger.annotations.Api;

@Api(description="Controlleur pour la gestion des médecins")
@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
	
	private final DoctorService doctorService;

	public DoctorController(DoctorService doctorService) {
		this.doctorService = doctorService;
	}
	
	@GetMapping
    @ResponseStatus(HttpStatus.OK)
    public DoctorListDTO getListOfDoctors(){
        return new DoctorListDTO(doctorService.getAllDoctors());
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public DoctorDTO getDoctorById(@PathVariable Long id){
        return doctorService.getDoctorById(id);
    }
    @GetMapping({"/specialty/{specialtyName}"})
    @ResponseStatus(HttpStatus.OK)
    public DoctorListDTO getDoctorsBySpecialtyTitle(@PathVariable String specialtyName){
        return new DoctorListDTO(doctorService.getDoctorsBySpecialtyTitle(specialtyName));
    }
	
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorDTO createNewDoctor(@RequestBody DoctorDTO doctorDTO){
        return doctorService.createNewDoctor(doctorDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public DoctorDTO updateDoctor(@PathVariable Long id, @RequestBody DoctorDTO doctorDTO){
        return doctorService.saveDoctorByDTO(id, doctorDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteDoctor(@PathVariable Long id){
        doctorService.deleteDoctorById(id);
    }

}
