package com.Disney.DisneyApp.repository;

import com.Disney.DisneyApp.models.PeliculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PeliculaSerieRepository extends JpaRepository<PeliculaEntity,Long> {
    Optional<PeliculaEntity> findByCod(String cod);

    void deleteByCod(String cod);
}
