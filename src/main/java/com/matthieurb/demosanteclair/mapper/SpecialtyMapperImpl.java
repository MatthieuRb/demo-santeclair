package com.matthieurb.demosanteclair.mapper;

import com.matthieurb.demosanteclair.domain.Specialty;
import com.matthieurb.demosanteclair.model.SpecialtyDTO;
import javax.annotation.Generated;

import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-10-10T15:25:46+0200",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_171 (Oracle Corporation)"
)
@Component
public class SpecialtyMapperImpl implements SpecialtyMapper {

    @Override
    public SpecialtyDTO specialtyToSpecialtyDTO(Specialty specialty) {
        if ( specialty == null ) {
            return null;
        }

        SpecialtyDTO specialtyDTO = new SpecialtyDTO();

        specialtyDTO.setId( specialty.getId() );
        specialtyDTO.setTitle( specialty.getTitle() );

        return specialtyDTO;
    }

    @Override
    public Specialty specialtyDTOtoSpecialty(SpecialtyDTO specialtyDTO) {
        if ( specialtyDTO == null ) {
            return null;
        }

        Specialty specialty = new Specialty();

        specialty.setId( specialtyDTO.getId() );
        specialty.setTitle( specialtyDTO.getTitle() );

        return specialty;
    }
}
