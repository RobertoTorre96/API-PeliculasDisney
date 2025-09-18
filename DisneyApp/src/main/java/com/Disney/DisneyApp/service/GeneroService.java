package com.Disney.DisneyApp.service;

import com.Disney.DisneyApp.exceptions.customException.EntityExistException;
import com.Disney.DisneyApp.exceptions.customException.EntityNotFoundException;
import com.Disney.DisneyApp.mapper.GeneroMapper;
import com.Disney.DisneyApp.models.DTO.GeneroDtoRequest;
import com.Disney.DisneyApp.models.DTO.GeneroDtoResponse;
import com.Disney.DisneyApp.models.GeneroEntity;
import com.Disney.DisneyApp.models.PeliculaEntity;
import com.Disney.DisneyApp.repository.GeneroRepository;
import com.Disney.DisneyApp.repository.PeliculaSerieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GeneroService {

    private final GeneroRepository generoRepository;
    private final GeneroMapper generoMapper;
    private final PeliculaSerieRepository peliculaSerieRepository;

    public GeneroService(GeneroRepository generoRepository, GeneroMapper generoMapper, PeliculaSerieRepository peliculaSerieRepository) {
        this.generoRepository = generoRepository;
        this.generoMapper = generoMapper;
        this.peliculaSerieRepository = peliculaSerieRepository;
    }

    @Transactional
    public GeneroDtoResponse create(GeneroDtoRequest g){
        if(generoRepository.findByNombre(g.getNombre()).isPresent()){
            throw new EntityExistException("El genero:"+g.getNombre()+" ya existe.");
        }
        GeneroEntity genero=GeneroEntity.builder()
                .nombre(g.getNombre())
                .peliculasSeries(peliculasByCods(g.getCodPeliculaSerie()))
                .build();
        GeneroEntity guardado =generoRepository.save(genero);
        return  generoMapper.entityToResponse(guardado);

    }
    public List<GeneroDtoResponse> findAll(){
        return generoRepository.findAll().stream()
                .map(generoMapper::entityToResponse).collect(Collectors.toList());
    }

    public GeneroDtoResponse findByNombre ( String nombre){
        GeneroEntity entity= generoRepository.findByNombre(nombre)
                .orElseThrow(()->new EntityNotFoundException("El genero:"+nombre+" no encontrado"));
        return  generoMapper.entityToResponse(entity);
    }

    @Transactional
    public void deleteByNombre (String nombre){
        GeneroEntity entity=(generoRepository.findByNombre(nombre)
                .orElseThrow(() ->new EntityNotFoundException("El genero:"+nombre+" no se ah encontrado")));
        for(PeliculaEntity p:entity.getPeliculasSeries()){
            p.getGeneros().remove(entity);
        }
        generoRepository.deleteByNombre(nombre);
    }
    //-----------------------------------------------------------------------------------

    private Set<PeliculaEntity> peliculasByCods(Set<String> codigos) {
        if (codigos == null || codigos.isEmpty()) {
            return new HashSet<>();
        }
        Set<PeliculaEntity> peliculas = new HashSet<>();
        return codigos.stream().map((p -> peliculaSerieRepository.findByCod(p)
                        .orElseThrow(() -> new EntityNotFoundException("el cod de pelicula:" + p + " no existe"))))
                .collect(Collectors.toSet());
    }
}
