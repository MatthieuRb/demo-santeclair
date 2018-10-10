package com.matthieurb.demosanteclair.mapper;

import com.matthieurb.demosanteclair.domain.Patient;
import com.matthieurb.demosanteclair.model.PatientDTO;
import javax.annotation.Generated;

import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-10-10T15:25:46+0200",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_171 (Oracle Corporation)"
)
@Component
public class PatientMapperImpl implements PatientMapper {

    @Override
    public PatientDTO patientToPatientDTO(Patient patient) {
        if ( patient == null ) {
            return null;
        }

        PatientDTO patientDTO = new PatientDTO();

        patientDTO.setId( patient.getId() );
        patientDTO.setFirstName( patient.getFirstName() );
        patientDTO.setLastName( patient.getLastName() );

        return patientDTO;
    }

    @Override
    public Patient patientDTOtoPatient(PatientDTO patientDTO) {
        if ( patientDTO == null ) {
            return null;
        }

        Patient patient = new Patient();

        patient.setId( patientDTO.getId() );
        patient.setFirstName( patientDTO.getFirstName() );
        patient.setLastName( patientDTO.getLastName() );

        return patient;
    }
}
