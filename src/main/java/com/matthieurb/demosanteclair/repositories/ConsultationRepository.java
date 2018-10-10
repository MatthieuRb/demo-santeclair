package com.matthieurb.demosanteclair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matthieurb.demosanteclair.domain.Consultation;

public interface ConsultationRepository extends JpaRepository<Consultation, Long>{

}
