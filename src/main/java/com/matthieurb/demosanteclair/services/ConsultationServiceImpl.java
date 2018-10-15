package com.matthieurb.demosanteclair.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.matthieurb.demosanteclair.domain.Consultation;
import com.matthieurb.demosanteclair.mapper.ConsultationMapper;
import com.matthieurb.demosanteclair.model.ConsultationDTO;
import com.matthieurb.demosanteclair.repositories.ConsultationRepository;

@Service
public class ConsultationServiceImpl implements ConsultationService {
	
	private final ConsultationMapper consultationMapper;
	private final ConsultationRepository consultationRepository;

	public ConsultationServiceImpl(ConsultationMapper consultationMapper, ConsultationRepository consultationRepository) {
		
		this.consultationMapper = consultationMapper;
		this.consultationRepository = consultationRepository;
	}

	@Override
	public List<ConsultationDTO> getAllConsultations() {
		
		return consultationRepository.findAll()
                .stream()
                .map(consultationMapper::consultationToConsultationDTO)
                .collect(Collectors.toList());
	}

	@Override
	public ConsultationDTO getConsultationById(Long id) {
		
		Optional<Consultation> optional =  consultationRepository.findById(id);
		if(optional.isPresent()) {
			return consultationMapper.consultationToConsultationDTO(optional.get());
		}else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
    public ConsultationDTO createNewConsultation(ConsultationDTO consultationDTO) {
		
			return saveAndReturnDTO(consultationMapper.consultationDTOtoConsultation(consultationDTO));
    }

    private ConsultationDTO saveAndReturnDTO(Consultation consultation) {
    	if(canCreateConsultation(consultation)) {
	        Consultation savedConsultation = consultationRepository.save(consultation);
	
	        ConsultationDTO returnDto = consultationMapper.consultationToConsultationDTO(savedConsultation);
	
	        return returnDto;
    	}else {
			throw new DateTooCloseException("L'horaire n'est pas disponible");
		}
    }
    
    //Méthode permettant de respecter un écart de 15 minutes entre 2 RDV pour un même patient ou un même docteur
    private boolean canCreateConsultation(Consultation consultation) {
    	
    	if(consultationRepository.findAll()
                .stream()
                .filter(consultationStream -> consultationStream.getDoctor().getId().equals(consultation.getDoctor().getId()) 
                		|| consultationStream.getPatient().getId().equals(consultation.getPatient().getId()))
                .filter(consultationStream -> checkDates(consultationStream.getDate(), consultation.getDate()))
                .collect(Collectors.toList())
                .size() != 0) {
    		return false;
    	}
    	return true;         
    }
    
    //Retourne true si les deux dates ne respectent pas un écart de 15 minutes
    private boolean checkDates(LocalDateTime date1, LocalDateTime date2) {
    	if(ChronoUnit.MINUTES.between(date1, date2) < 15 && ChronoUnit.MINUTES.between(date1, date2) > -15 ) {
    		return true;
    	}
    	return false;
    }

    @Override
    public ConsultationDTO saveConsultationByDTO(Long id, ConsultationDTO consultationDTO) {
    	
        Consultation consultation = consultationMapper.consultationDTOtoConsultation(consultationDTO);
        consultation.setId(id);

        return saveAndReturnDTO(consultation);
    }

    @Override
    public void deleteConsultationById(Long id) {
    	
        consultationRepository.deleteById(id);
    }

}
