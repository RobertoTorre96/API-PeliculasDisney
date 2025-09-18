package com.Disney.DisneyApp.service;

import com.Disney.DisneyApp.models.Erole;
import com.Disney.DisneyApp.models.RoleEntity;
import com.Disney.DisneyApp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public RoleEntity create (String role){
        RoleEntity  roleEntity=RoleEntity.builder()
                .name(Erole.USER)
                .build();

        if(role.equals(Erole.ADMIN.name())){
             roleEntity=RoleEntity.builder()
                    .name(Erole.ADMIN)
                    .build();
        }

        return  roleRepository.save(roleEntity);
    }
}
