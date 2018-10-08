package com.matthieurb.demosanteclair.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.matthieurb.demosanteclair.domain.Doctor;
import com.matthieurb.demosanteclair.domain.Patient;
import com.matthieurb.demosanteclair.repositories.DoctorRepository;
import com.matthieurb.demosanteclair.repositories.PatientRepository;

@Component
public class Bootstrap implements CommandLineRunner{
	
	private final DoctorRepository doctorRepository;
	private final PatientRepository patientRepository;
	
	public Bootstrap(DoctorRepository doctorRepository, PatientRepository patientRepository) {
		this.doctorRepository = doctorRepository;
		this.patientRepository = patientRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		loadDoctors();
		loadPatients();
	}
	
	private void loadDoctors() {
		Doctor docteur1 = new Doctor();
		docteur1.setFirstName("Paul");
		docteur1.setLastName("Dupond");
		
		Doctor docteur2 = new Doctor();
		docteur2.setFirstName("Henry");
		docteur2.setLastName("Lefort");
		
		Doctor docteur3 = new Doctor();
		docteur3.setFirstName("Jack");
		docteur3.setLastName("Lami");
		
		Doctor docteur4 = new Doctor();
		docteur4.setFirstName("Yann");
		docteur4.setLastName("Laville");
		
		doctorRepository.save(docteur1);
		doctorRepository.save(docteur2);
		doctorRepository.save(docteur3);
		doctorRepository.save(docteur4);
	}
	
	private void loadPatients() {
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
	}

}
