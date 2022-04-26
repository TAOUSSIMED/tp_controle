package com.example.tp_controle.sec.service;

import com.example.tp_controle.sec.entities.AppRole;
import com.example.tp_controle.sec.entities.AppUser;
import com.example.tp_controle.sec.repositories.AppRolerRepository;
import com.example.tp_controle.sec.repositories.AppUserRepository;
import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class SecurityServiceImpl implements SecurityService {
    private AppUserRepository appUserRepository;
    private AppRolerRepository appRolerRepository;
    private PasswordEncoder passwordEncoder;



    @Override
    public AppUser saveNewUser(String username, String password, String rePassword) {
        if(!password.equals(rePassword)) throw new  RuntimeException("Password not match");
        String hashedPWD=passwordEncoder.encode(password);
        AppUser appUser = new AppUser();
        appUser.setUserid(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setPassword(hashedPWD);
        appUser.setActive(true);
        AppUser savedAppUser = appUserRepository.save(appUser);
        return  savedAppUser;

    }

    @Override
    public AppRole saveNewRole(String roleName, String description) {
        AppRole appRole = appRolerRepository.findByRolename(roleName);
        if(appRole!=null) throw new RuntimeException("Role "+roleName+"already exist");
        appRole = new AppRole();
        appRole.setRoleName(roleName);
        appRole.setDescription(description);
       AppRole savedAppRole =  appRolerRepository.save(appRole);
        return savedAppRole;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
       AppUser appUser = appUserRepository.findByUsername(username);
       if(appUser==null) throw new RuntimeException("User Not found");
       AppRole appRole = appRolerRepository.findByRolename(roleName);
       if(appRole==null) throw new RuntimeException("Role Not found");
       appUser.getAppRoles().add(appRole);
    }
    @Override
    public void removeRoleFromUser(String username , String roleName)
    {
        AppUser appUser = appUserRepository.findByUsername(username);
        if(appUser==null) throw new RuntimeException("User Not found");
        AppRole appRole = appRolerRepository.findByRolename(roleName);
        if(appRole==null) throw new RuntimeException("Role Not found");
        appUser.getAppRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUserName(String username) {

        return appUserRepository.findByUsername(username);
    }
}
