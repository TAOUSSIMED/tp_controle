package com.example.tp_controle.repositories;

import com.example.tp_controle.entities.Medecin;
import com.example.tp_controle.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin , Long> {
    Page<Medecin> findByNomContains(String kw , Pageable pageable);


}
