package com.matthieurb.demosanteclair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matthieurb.demosanteclair.domain.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
