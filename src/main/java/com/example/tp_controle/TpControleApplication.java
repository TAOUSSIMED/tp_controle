package com.example.tp_controle;

import com.example.tp_controle.entities.Patient;
import com.example.tp_controle.repositories.PatientRepository;
import com.example.tp_controle.sec.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class TpControleApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpControleApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder ( ) {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(new Patient(null , "TAOUSSI", new Date() , false , 20));
            patientRepository.save(new Patient(null , "MOHAMED", new Date() , false , 10));
            patientRepository.save(new Patient(null , "TAOUSSI2", new Date() , false , 15));
            patientRepository.save(new Patient(null , "TAOUSSI3", new Date() , false , 14));

            patientRepository.findAll().forEach(p ->{
                System.out.println(p.getNom());
            });

        };
    }
    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService)
    {
        return args -> {
            securityService.saveNewUser("mohamed","1234","1234");
            securityService.saveNewUser("taoussi","1234","1234");
            securityService.saveNewUser("mohamed2","1234","1234");

            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");

            securityService.addRoleToUser("mohamed" , "USER");
            securityService.addRoleToUser("taoussi","ADMIN");
            securityService.addRoleToUser("taoussi","USER");
            securityService.addRoleToUser("mohamed2" , "USER");

        };
    }


}
