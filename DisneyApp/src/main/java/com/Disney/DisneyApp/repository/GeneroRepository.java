package com.Disney.DisneyApp.repository;

import com.Disney.DisneyApp.models.GeneroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeneroRepository extends JpaRepository<GeneroEntity,Long> {

    Optional<GeneroEntity> findByNombre(String nombre);


    void deleteByNombre(String nombre);
}
