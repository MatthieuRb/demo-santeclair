package com.matthieurb.demosanteclair.mapper;

import com.matthieurb.demosanteclair.domain.Doctor;
import com.matthieurb.demosanteclair.domain.Specialty;
import com.matthieurb.demosanteclair.model.DoctorDTO;
import com.matthieurb.demosanteclair.model.SpecialtyDTO;
import javax.annotation.Generated;

import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-10-10T15:25:46+0200",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_171 (Oracle Corporation)"
)
@Component
public class DoctorMapperImpl implements DoctorMapper {

    @Override
    public DoctorDTO doctorToDoctorDTO(Doctor doctor) {
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

    @Override
    public Doctor doctorDTOtoDoctor(DoctorDTO doctorDTO) {
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

    protected SpecialtyDTO specialtyToSpecialtyDTO(Specialty specialty) {
        if ( specialty == null ) {
            return null;
        }

        SpecialtyDTO specialtyDTO = new SpecialtyDTO();

        specialtyDTO.setId( specialty.getId() );
        specialtyDTO.setTitle( specialty.getTitle() );

        return specialtyDTO;
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
}
