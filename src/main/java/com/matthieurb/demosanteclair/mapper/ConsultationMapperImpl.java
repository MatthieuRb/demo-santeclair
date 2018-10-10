package com.matthieurb.demosanteclair.mapper;

import com.matthieurb.demosanteclair.domain.Consultation;
import com.matthieurb.demosanteclair.domain.Doctor;
import com.matthieurb.demosanteclair.domain.Patient;
import com.matthieurb.demosanteclair.domain.Specialty;
import com.matthieurb.demosanteclair.model.ConsultationDTO;
import com.matthieurb.demosanteclair.model.DoctorDTO;
import com.matthieurb.demosanteclair.model.PatientDTO;
import com.matthieurb.demosanteclair.model.SpecialtyDTO;
import javax.annotation.Generated;

import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-10-10T15:25:46+0200",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_171 (Oracle Corporation)"
)
@Component
public class ConsultationMapperImpl implements ConsultationMapper {

    @Override
    public ConsultationDTO consultationToConsultationDTO(Consultation consultation) {
        if ( consultation == null ) {
            return null;
        }

        ConsultationDTO consultationDTO = new ConsultationDTO();

        consultationDTO.setId( consultation.getId() );
        consultationDTO.setDate( consultation.getDate() );
        consultationDTO.setDescription( consultation.getDescription() );
        consultationDTO.setDoctor( doctorToDoctorDTO( consultation.getDoctor() ) );
        consultationDTO.setPatient( patientToPatientDTO( consultation.getPatient() ) );

        return consultationDTO;
    }

    @Override
    public Consultation consultationDTOtoConsultation(ConsultationDTO consultationDTO) {
        if ( consultationDTO == null ) {
            return null;
        }

        Consultation consultation = new Consultation();

        consultation.setId( consultationDTO.getId() );
        consultation.setDate( consultationDTO.getDate() );
        consultation.setDescription( consultationDTO.getDescription() );
        consultation.setDoctor( doctorDTOToDoctor( consultationDTO.getDoctor() ) );
        consultation.setPatient( patientDTOToPatient( consultationDTO.getPatient() ) );

        return consultation;
    }

    protected SpecialtyDTO specialtyToSpecialtyDTO(Specialty specialty) {
        if ( specialty == null ) {
            return null;
        }

        SpecialtyDTO specialtyDTO = new SpecialtyDTO();

        specialtyDTO.setId( specialty.getId() );
        specialtyDTO.setTitle( specialty.getTitle() );

        return specialtyDTO;
    }

    protected DoctorDTO doctorToDoctorDTO(Doctor doctor) {
        if ( doctor == null ) {
            return null;
        }

        DoctorDTO doctorDTO = new DoctorDTO();

        doctorDTO.setId( doctor.getId() );
        doctorDTO.setFirstName( doctor.getFirstName() );
        doctorDTO.setLastName( doctor.getLastName() );
        doctorDTO.setSpecialty( specialtyToSpecialtyDTO( doctor.getSpecialty() ) );

        return doctorDTO;
    }

    protected PatientDTO patientToPatientDTO(Patient patient) {
        if ( patient == null ) {
            return null;
        }

        PatientDTO patientDTO = new PatientDTO();

        patientDTO.setId( patient.getId() );
        patientDTO.setFirstName( patient.getFirstName() );
        patientDTO.setLastName( patient.getLastName() );

        return patientDTO;
    }

    protected Specialty specialtyDTOToSpecialty(SpecialtyDTO specialtyDTO) {
        if ( specialtyDTO == null ) {
            return null;
        }

        Specialty specialty = new Specialty();

        specialty.setId( specialtyDTO.getId() );
        specialty.setTitle( specialtyDTO.getTitle() );

        return specialty;
    }

    protected Doctor doctorDTOToDoctor(DoctorDTO doctorDTO) {
        if ( doctorDTO == null ) {
            return null;
        }

        Doctor doctor = new Doctor();

        doctor.setId( doctorDTO.getId() );
        doctor.setFirstName( doctorDTO.getFirstName() );
        doctor.setLastName( doctorDTO.getLastName() );
        doctor.setSpecialty( specialtyDTOToSpecialty( doctorDTO.getSpecialty() ) );

        return doctor;
    }

    protected Patient patientDTOToPatient(PatientDTO patientDTO) {
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
