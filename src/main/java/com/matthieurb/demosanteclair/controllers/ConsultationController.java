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

import com.matthieurb.demosanteclair.model.ConsultationDTO;
import com.matthieurb.demosanteclair.model.ConsultationListDTO;
import com.matthieurb.demosanteclair.services.ConsultationService;

@RestController
@RequestMapping("/api/consultations")
public class ConsultationController {
	
	private final ConsultationService consultationService;

	public ConsultationController(ConsultationService consultationService) {
		this.consultationService = consultationService;
	}
	
	@GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ConsultationListDTO getListOfConsultations(){
        return new ConsultationListDTO(consultationService.getAllConsultations());
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ConsultationDTO getConsultationById(@PathVariable Long id){
        return consultationService.getConsultationById(id);
    }
	
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsultationDTO createNewConsultation(@RequestBody ConsultationDTO consultationDTO){
        return consultationService.createNewConsultation(consultationDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ConsultationDTO updateConsultation(@PathVariable Long id, @RequestBody ConsultationDTO consultationDTO){
        return consultationService.saveConsultationByDTO(id, consultationDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteConsultation(@PathVariable Long id){
        consultationService.deleteConsultationById(id);
    }

}
