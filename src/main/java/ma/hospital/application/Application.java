package ma.hospital.application;



import ma.hospital.application.entities.Patient;
import ma.hospital.application.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        patientRepository.save(new Patient(null,"Mohamed",new Date(),false,42));
        patientRepository.save(new Patient(null,"Imane",new Date(),true,98));
        patientRepository.save(new Patient(null,"Yassine",new Date(),true,342));
        patientRepository.save(new Patient(null,"Laila",new Date(),false,123));
        patientRepository.save(new Patient(null,"Oussama",new Date(),false,73));
        patientRepository.save(new Patient(null,"Fayssal",new Date(),false,132));
        patientRepository.save(new Patient(null,"Imad",new Date(),false,84));
        patientRepository.save(new Patient(null,"Mohamed",new Date(),false,38));
        patientRepository.save(new Patient(null,"Ahmed",new Date(),false,38));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
