package com.Disney.DisneyApp.controller;

import com.Disney.DisneyApp.models.DTO.PeliculaSerieDtoRequest;
import com.Disney.DisneyApp.models.DTO.PeliculaSerieDtoResponse;
import com.Disney.DisneyApp.service.PeliculaSerieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping ("/peliculas")
public class PeliculaSerieController {
    @Autowired
    PeliculaSerieService peliculaSerieService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PeliculaSerieDtoResponse> create(@Valid @RequestBody PeliculaSerieDtoRequest p){
        PeliculaSerieDtoResponse response=peliculaSerieService.create(p);
        return ResponseEntity.created(URI.create("/peliculas/"+p.getCod())).body(response);
    }

    @GetMapping("/{cod}")
    public  ResponseEntity<PeliculaSerieDtoResponse> findBYCod(@PathVariable String cod){
        PeliculaSerieDtoResponse respone=peliculaSerieService.findByCod(cod);
        return ResponseEntity.ok().body(respone);
    }

    @GetMapping
    public ResponseEntity<List<PeliculaSerieDtoResponse>> findByFilters(@RequestParam(required = false) String titulo
                                                                        ,@RequestParam(required = false) String nomGenero){
        List<PeliculaSerieDtoResponse> respnse=peliculaSerieService.findByFilters(titulo, nomGenero);
        return  ResponseEntity.ok().body(respnse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{cod}")
    public ResponseEntity<Void>deleteByCod(@PathVariable String cod){
        peliculaSerieService.deleteByCod(cod);

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{codPelicula}/personajes")
    public ResponseEntity<PeliculaSerieDtoResponse> addPersonaje(@PathVariable String codPelicula,@RequestBody List<String> codPersonaje){
        PeliculaSerieDtoResponse response=peliculaSerieService.addPersonaje(codPersonaje,codPelicula);
        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{codPelicula}/personajes")
    public ResponseEntity<PeliculaSerieDtoResponse> removePersonaje(@PathVariable String codPelicula,@RequestBody List<String> codPersonaje){
        PeliculaSerieDtoResponse response=peliculaSerieService.removePersonajes(codPersonaje,codPelicula);

        return ResponseEntity.ok().body(response);
    }

}
