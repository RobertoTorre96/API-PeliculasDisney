package com.Disney.DisneyApp.service;

import com.Disney.DisneyApp.exceptions.customException.EntityExistException;
import com.Disney.DisneyApp.exceptions.customException.EntityNotFoundException;
import com.Disney.DisneyApp.mapper.UsuarioMapper;
import com.Disney.DisneyApp.models.DTO.UsuarioDtoResponse;
import com.Disney.DisneyApp.models.DTO.UsuarioDtorequest;
import com.Disney.DisneyApp.models.Erole;
import com.Disney.DisneyApp.models.RoleEntity;
import com.Disney.DisneyApp.models.UsuarioEntity;
import com.Disney.DisneyApp.repository.RoleRepository;
import com.Disney.DisneyApp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    UsuarioMapper usuarioMapper;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public UsuarioDtoResponse createUser(UsuarioDtorequest u){
        if (usuarioRepository.existsByEmail(u.getEmail())){
            throw new EntityExistException("El usuario:"+u.getEmail()+" ya existe.");
        }

        UsuarioEntity entity=usuarioMapper.requestToEntity(u);
        entity.setPassword(passwordEncoder.encode(u.getPassword()));

        agregarRol(u.getRole(),entity);
        UsuarioEntity guardado=usuarioRepository.save(entity);
        return usuarioMapper.entitytoresponse(guardado);
    }

    public List<UsuarioDtoResponse> findByFilter(String email){

        return usuarioRepository.findAll().stream()
                .filter(U->email==null || U.getEmail().equals(email))
                .map(usuarioMapper::entitytoresponse)
                .collect(Collectors.toList());
    }

    private void agregarRol(String rol,UsuarioEntity user) {
        RoleEntity role = roleRepository.findByName(Erole.USER)
                .orElseThrow(() -> new EntityNotFoundException("Nombre de rol invalido. "));

        user.getRoles().add(role);

        if (rol.equalsIgnoreCase("ADMIN")){
            role = roleRepository.findByName(Erole.ADMIN)
                    .orElseThrow(()->new EntityNotFoundException("Nombre de rol invalido"));
            user.getRoles().add(role);
        }
    }

    @Transactional
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
}
