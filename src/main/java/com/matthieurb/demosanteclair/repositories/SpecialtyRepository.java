package com.matthieurb.demosanteclair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matthieurb.demosanteclair.domain.Specialty;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {

}
