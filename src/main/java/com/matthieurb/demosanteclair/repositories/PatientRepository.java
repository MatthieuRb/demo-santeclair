package com.matthieurb.demosanteclair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matthieurb.demosanteclair.domain.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
