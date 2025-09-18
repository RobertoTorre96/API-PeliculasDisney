package com.Disney.DisneyApp.service;

import com.Disney.DisneyApp.exceptions.customException.EntityExistException;
import com.Disney.DisneyApp.exceptions.customException.EntityNotFoundException;

import com.Disney.DisneyApp.mapper.PersonajeMapper;
import com.Disney.DisneyApp.models.DTO.PersonajeDtoRequest;
import com.Disney.DisneyApp.models.DTO.PersonajeDtoResponse;
import com.Disney.DisneyApp.models.PeliculaEntity;
import com.Disney.DisneyApp.models.PersonajeEntity;
import com.Disney.DisneyApp.repository.PeliculaSerieRepository;
import com.Disney.DisneyApp.repository.PersonajeRepository;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonajeService {

    private final PersonajeRepository personajeRepository;
    private final PeliculaSerieRepository peliculaSerieRepository;
    private final PersonajeMapper personajeMapper;

    public PersonajeService(PersonajeRepository personajeRepository, PeliculaSerieRepository peliculaSerieRepository, PersonajeMapper personajeMapper) {
        this.personajeRepository = personajeRepository;
        this.peliculaSerieRepository = peliculaSerieRepository;
        this.personajeMapper = personajeMapper;
    }


    @Transactional
    public PersonajeDtoResponse create (@Valid PersonajeDtoRequest p){
         if (personajeRepository.findByCod(p.getCod()).isPresent()){
             throw  new EntityExistException("El personaje"+p.getCod()+" ya existe");
         };

        PersonajeEntity entity=PersonajeEntity.builder()
                .cod(p.getCod())
                .peso(p.getPeso())
                .edad(p.getEdad())
                .nombre(p.getNombre())
                .historia(p.getHistoria())
                .peliculas(getPeliculasByCods(p.getCodPeliculas()))
                .build();
        PersonajeEntity guardado=personajeRepository.save(entity);
        return personajeMapper.entityToResponse(guardado);
    }

    public PersonajeDtoResponse findByCod(String cod){
        PersonajeEntity entity=personajeRepository.findByCod(cod)
                .orElseThrow(()->new EntityNotFoundException("Personaje:"+cod+" no encontrado"));
        return personajeMapper.entityToResponse(entity);

    }

    public List<PersonajeDtoResponse> findAll(){
        return  personajeRepository.findAll()
                .stream()
                .map(personajeMapper::entityToResponse)
                .collect(Collectors.toList());
    }



    public List<PersonajeDtoResponse> findByFilters(Integer edad, String nombre, String peso,String codPelicula) {
       List<PersonajeEntity> personajes= personajeRepository.findAll();
       return personajes.stream()
               .filter((p)->edad==null || edad.equals(p.getEdad()))
               .filter((p)->nombre==null || nombre.equals(p.getNombre()))
               .filter((p)->peso==null || peso.equals(p.getPeso()))
               .filter(p -> codPelicula == null || p.getPeliculas().stream()
                                                    .anyMatch(pelicula -> codPelicula.equals(pelicula.getCod()))
               )
               .map(personajeMapper::entityToResponse)
               .collect(Collectors.toList());
  }
    @Transactional
    public void  deleteByCod(String cod){
        personajeRepository.deleteByCod(cod);
    }




    //--------------------------------------------------------------------------------------------------------------------------------
    private Set<PeliculaEntity> getPeliculasByCods(Set<String> codPeliculas) {
        if(codPeliculas==null)return  null;
        return codPeliculas.stream()
                .map((p)-> peliculaSerieRepository.findByCod(p)
                        .orElseThrow(()->new EntityNotFoundException("Pelicula:"+p+"no encontrada.")))
                .collect(Collectors.toSet());
    }

}
