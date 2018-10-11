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

import com.matthieurb.demosanteclair.model.SpecialtyDTO;
import com.matthieurb.demosanteclair.model.SpecialtyListDTO;
import com.matthieurb.demosanteclair.services.SpecialtyService;

import io.swagger.annotations.Api;

@Api(description="Controlleur pour la gestion des spécialités")
@RestController
@RequestMapping("/api/specialties")
public class SpecialtyController {
	
	private final SpecialtyService specialtyService;

	public SpecialtyController(SpecialtyService specialtyService) {
		this.specialtyService = specialtyService;
	}
	
	@GetMapping
    @ResponseStatus(HttpStatus.OK)
    public SpecialtyListDTO getListOfSpecialtys(){
        return new SpecialtyListDTO(specialtyService.getAllSpecialties());
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public SpecialtyDTO getSpecialtyById(@PathVariable Long id){
        return specialtyService.getSpecialtyById(id);
    }
	
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SpecialtyDTO createNewSpecialty(@RequestBody SpecialtyDTO specialtyDTO){
        return specialtyService.createNewSpecialty(specialtyDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public SpecialtyDTO updateSpecialty(@PathVariable Long id, @RequestBody SpecialtyDTO specialtyDTO){
        return specialtyService.saveSpecialtyByDTO(id, specialtyDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteSpecialty(@PathVariable Long id){
        specialtyService.deleteSpecialtyById(id);
    }

}
