package com.Disney.DisneyApp.repository;

import com.Disney.DisneyApp.models.PersonajeEntity;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonajeRepository extends JpaRepository<PersonajeEntity,Long> {
   Optional<PersonajeEntity> findByCod(@NotBlank String cod);

    void deleteByCod(String cod);
}
