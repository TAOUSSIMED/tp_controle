package com.example.tp_controle.sec.repositories;

import com.example.tp_controle.sec.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRolerRepository extends JpaRepository<AppRole,Long> {
    AppRole findByRolename(String roleName);
}
