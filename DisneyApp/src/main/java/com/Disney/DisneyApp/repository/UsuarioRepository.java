package com.Disney.DisneyApp.repository;

import com.Disney.DisneyApp.models.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Long> {

    public Boolean existsByEmail (String email);
    Optional<UsuarioEntity> findByEmail(String username);
}
