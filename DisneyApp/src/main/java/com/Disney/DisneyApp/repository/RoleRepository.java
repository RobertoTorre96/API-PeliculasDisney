package com.Disney.DisneyApp.repository;

import com.Disney.DisneyApp.models.Erole;
import com.Disney.DisneyApp.models.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(Erole name);
}
