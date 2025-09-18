package com.Disney.DisneyApp.controller;

import com.Disney.DisneyApp.models.DTO.PersonajeDtoRequest;
import com.Disney.DisneyApp.models.DTO.PersonajeDtoResponse;
import com.Disney.DisneyApp.service.PersonajeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping ("/personaje")
public class personajeController {
    @Autowired
    PersonajeService personajeService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PersonajeDtoResponse> create (@Valid @RequestBody PersonajeDtoRequest p){
        PersonajeDtoResponse response=personajeService.create(p);
        return ResponseEntity.created(URI.create("/personaje/"+response.getCod())).body(response);
    }


    @GetMapping("/{cod}")
    public ResponseEntity<PersonajeDtoResponse> findByCod (@PathVariable String cod){
        PersonajeDtoResponse response=personajeService.findByCod(cod);
        return  ResponseEntity.ok().body(response);
    }
    @GetMapping
    public ResponseEntity<List<PersonajeDtoResponse>> findByFilters(@RequestParam(required = false) Integer edad
                                                                ,@RequestParam(required = false)String nombre
                                                                ,@RequestParam(required = false)String peso
                                                                ,@RequestParam(required = false)String codPelicula){
        List<PersonajeDtoResponse> responses=personajeService.findByFilters(edad,nombre,peso,codPelicula);
        return  ResponseEntity.ok().body(responses);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{cod}")
    public  ResponseEntity<Void> deleteByCod (@PathVariable String cod){
        personajeService.deleteByCod(cod);
        return ResponseEntity.noContent().build();
    }



}
