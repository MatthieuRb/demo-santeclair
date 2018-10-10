package com.matthieurb.demosanteclair.bootstrap;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.matthieurb.demosanteclair.domain.Consultation;
import com.matthieurb.demosanteclair.domain.Doctor;
import com.matthieurb.demosanteclair.domain.Patient;
import com.matthieurb.demosanteclair.domain.Specialty;
import com.matthieurb.demosanteclair.repositories.ConsultationRepository;
import com.matthieurb.demosanteclair.repositories.DoctorRepository;
import com.matthieurb.demosanteclair.repositories.PatientRepository;
import com.matthieurb.demosanteclair.repositories.SpecialtyRepository;

@Component
public class Bootstrap implements CommandLineRunner{
	
	private final DoctorRepository doctorRepository;
	private final PatientRepository patientRepository;
	private final SpecialtyRepository specialtyRepository;
	private final ConsultationRepository consultationRepository;
	
	public Bootstrap(DoctorRepository doctorRepository, PatientRepository patientRepository,
			SpecialtyRepository specialtyRepository, ConsultationRepository consultationRepository) {
		this.doctorRepository = doctorRepository;
		this.patientRepository = patientRepository;
		this.specialtyRepository = specialtyRepository;
		this.consultationRepository = consultationRepository;
	}



	@Override
	public void run(String... args) throws Exception {
		
		Specialty specialty1 = new Specialty();
		specialty1.setTitle("Dentiste");
		
		Specialty specialty2 = new Specialty();
		specialty2.setTitle("Ophtalmologue");
		
		Specialty specialty3 = new Specialty();
		specialty3.setTitle("Kiné");
		
		specialtyRepository.save(specialty1);
		specialtyRepository.save(specialty2);
		specialtyRepository.save(specialty3);
		
		Doctor docteur1 = new Doctor();
		docteur1.setFirstName("Paul");
		docteur1.setLastName("Dupond");
		docteur1.setSpecialty(specialtyRepository.getOne(specialty1.getId()));
		
		Doctor docteur2 = new Doctor();
		docteur2.setFirstName("Henry");
		docteur2.setLastName("Lefort");
		docteur2.setSpecialty(specialtyRepository.getOne(specialty2.getId()));
		
		Doctor docteur3 = new Doctor();
		docteur3.setFirstName("Jack");
		docteur3.setLastName("Lami");
		docteur3.setSpecialty(specialtyRepository.getOne(specialty3.getId()));
		
		Doctor docteur4 = new Doctor();
		docteur4.setFirstName("Yann");
		docteur4.setLastName("Laville");
		docteur4.setSpecialty(specialtyRepository.getOne(specialty2.getId()));
		
		doctorRepository.save(docteur1);
		doctorRepository.save(docteur2);
		doctorRepository.save(docteur3);
		doctorRepository.save(docteur4);
		
		Patient patient1 = new Patient();
		patient1.setFirstName("Maurice");
		patient1.setLastName("Pigeon");
		
		Patient patient2 = new Patient();
		patient2.setFirstName("Charlie");
		patient2.setLastName("Riou");
		
		Patient patient3 = new Patient();
		patient3.setFirstName("Mickael");
		patient3.setLastName("Pearl");
		
		Patient patient4 = new Patient();
		patient4.setFirstName("Patrick");
		patient4.setLastName("Vivant");
		
		patientRepository.save(patient1);
		patientRepository.save(patient2);
		patientRepository.save(patient3);
		patientRepository.save(patient4);
		
		Consultation consultation1 = new Consultation();
		consultation1.setDate(LocalDateTime.of(2018, 10, 9, 10, 10));
		consultation1.setDescription("Rendez-vous pour une ordonnance");
		consultation1.setDoctor(docteur1);
		consultation1.setPatient(patient1);
		
		Consultation consultation2 = new Consultation();
		consultation2.setDate(LocalDateTime.of(2018, 10, 7, 17, 54));
		consultation2.setDescription("Rendez-vous pour un problème aux yeux");
		consultation2.setDoctor(docteur2);
		consultation2.setPatient(patient2);
		
		Consultation consultation3 = new Consultation();
		consultation3.setDate(LocalDateTime.of(2018, 10, 8, 18, 20));
		consultation3.setDescription("Rendez-vous pour un contrôle de routine");
		consultation3.setDoctor(docteur2);
		consultation3.setPatient(patient3);
		
		consultationRepository.save(consultation1);
		consultationRepository.save(consultation2);
		consultationRepository.save(consultation3);
	}

}
