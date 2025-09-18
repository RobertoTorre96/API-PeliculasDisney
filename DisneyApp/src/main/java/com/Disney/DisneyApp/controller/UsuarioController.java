package com.Disney.DisneyApp.controller;

import com.Disney.DisneyApp.models.DTO.UsuarioDtoResponse;
import com.Disney.DisneyApp.models.DTO.UsuarioDtorequest;

import com.Disney.DisneyApp.service.UsuarioService;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@PreAuthorize("hasRole('ADMIN')")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDtoResponse> createUser (@Valid @RequestBody UsuarioDtorequest u){
        UsuarioDtoResponse response= usuarioService.createUser(u);
        return  ResponseEntity.created(URI.create("/usuarios/"+response.getEmail())).body(response);
    }
    @GetMapping
    public  ResponseEntity<List<UsuarioDtoResponse>> findByFilter(@RequestParam(required = false) String email){
        List<UsuarioDtoResponse> response=usuarioService.findByFilter(email);
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }




}
