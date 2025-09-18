package com.Disney.DisneyApp.controller;

import com.Disney.DisneyApp.models.DTO.GeneroDtoRequest;
import com.Disney.DisneyApp.models.DTO.GeneroDtoResponse;

import com.Disney.DisneyApp.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping ("/genero")
@PreAuthorize("permitAll()")
public class GeneroController {
    @Autowired
    GeneroService generoService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<GeneroDtoResponse> create(@RequestBody GeneroDtoRequest g){
        GeneroDtoResponse genero=generoService.create(g);
        return ResponseEntity.created(URI.create("/genero/"+g.getNombre())).body(genero);
    }

    @GetMapping ("/{nombre}")
    public ResponseEntity<GeneroDtoResponse> findByNombre(@PathVariable  String nombre){
        GeneroDtoResponse response=generoService.findByNombre(nombre);
        return  ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<List<GeneroDtoResponse>> findAll(){
        List<GeneroDtoResponse> respone=generoService.findAll();
        return ResponseEntity.ok().body(respone);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping ("/{nombre}")
    public ResponseEntity<Void> deleteByNombre (@PathVariable String nombre){
        generoService.deleteByNombre(nombre);
        return ResponseEntity.noContent().build();
    }


}
