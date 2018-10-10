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
<<<<<<< HEAD
    
    @GetMapping({"/specialty/{specialtyName}"})
    @ResponseStatus(HttpStatus.OK)
    public DoctorListDTO getDoctorsBySpecialtyTitle(@PathVariable String specialtyName){
        return new DoctorListDTO(doctorService.getDoctorsBySpecialtyTitle(specialtyName));
    }
=======
>>>>>>> 156ad710accfa772b9d2d6b775dfd7bcfe0ae1a1
	
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
