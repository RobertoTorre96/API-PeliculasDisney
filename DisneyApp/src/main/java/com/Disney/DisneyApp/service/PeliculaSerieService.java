package com.Disney.DisneyApp.service;

import com.Disney.DisneyApp.exceptions.customException.EntityExistException;
import com.Disney.DisneyApp.exceptions.customException.EntityNotFoundException;
import com.Disney.DisneyApp.mapper.PeliculaSerieMapper;
import com.Disney.DisneyApp.models.DTO.PeliculaSerieDtoRequest;
import com.Disney.DisneyApp.models.DTO.PeliculaSerieDtoResponse;
import com.Disney.DisneyApp.models.GeneroEntity;
import com.Disney.DisneyApp.models.PeliculaEntity;
import com.Disney.DisneyApp.models.PersonajeEntity;
import com.Disney.DisneyApp.repository.GeneroRepository;
import com.Disney.DisneyApp.repository.PeliculaSerieRepository;
import com.Disney.DisneyApp.repository.PersonajeRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PeliculaSerieService {

    private final PeliculaSerieRepository peliculaSerieRepository;
    private final PersonajeRepository personajeRepository;
    private final GeneroRepository generoRepository;
    private final PeliculaSerieMapper peliculaSerieMapper;

    public PeliculaSerieService(PeliculaSerieRepository peliculaSerieRepository, PersonajeRepository personajeRepository, GeneroRepository generoRepository, PeliculaSerieMapper peliculaSerieMapper) {
        this.peliculaSerieRepository = peliculaSerieRepository;
        this.personajeRepository = personajeRepository;
        this.generoRepository = generoRepository;
        this.peliculaSerieMapper = peliculaSerieMapper;
    }

    @Transactional
    public PeliculaSerieDtoResponse create(PeliculaSerieDtoRequest p){
        if (peliculaSerieRepository.findByCod(p.getCod()).isPresent()){
            throw  new EntityExistException("la pelicula:"+p.getCod()+" ya existe");
        }
        PeliculaEntity entity=PeliculaEntity.builder()
                .cod(p.getCod())
                .titulo(p.getTitulo())
                .fechaDeCreacion(p.getFechaDeCreacion())
                .generos(getGenerosByNames(p.getNombreGeneros()))
                .personajes(new HashSet<>())
                .calificacion(p.getCalificacion())
                .build();
        Set<PersonajeEntity> personajes=getPersonajesByCodes(p.getCodsPersonajes());
        for (PersonajeEntity personaje : personajes) {
            entity.addPersonaje(personaje);
        }
        PeliculaEntity guardado=peliculaSerieRepository.save(entity);
        return peliculaSerieMapper.entityToResponse(guardado);
    }


    public PeliculaSerieDtoResponse findByCod(String cod){
        PeliculaEntity response=peliculaSerieRepository.findByCod(cod)
                .orElseThrow(() -> new EntityNotFoundException("El cod:"+cod+" no se encutra."));

        return peliculaSerieMapper.entityToResponse(response);
    }

    public List<PeliculaSerieDtoResponse> findAll(){
        return peliculaSerieRepository.findAll().stream()
                .map(peliculaSerieMapper::entityToResponse)
                .collect(Collectors.toList());
    }
    public List<PeliculaSerieDtoResponse> findByFilters(String titulo,String genero){
        List<PeliculaEntity> peliculas=peliculaSerieRepository.findAll();
        peliculas= peliculas.stream()
                .filter((p)->titulo==null || p.getTitulo().equals(titulo))
                .filter((p)->genero==null || p.getGeneros().stream()
                        .anyMatch(g->g.getNombre().equals(genero)))
                .collect(Collectors.toList());

        return peliculas.stream()
                .map(peliculaSerieMapper::entityToResponse)
                .collect(Collectors.toList());
    }
    @Transactional
    public void deleteByCod (String cod){
        PeliculaEntity pelicula=peliculaSerieRepository.findByCod(cod)
                        .orElseThrow((()->new EntityNotFoundException("pelicula:"+cod+" no encontrada")));
        pelicula.getPersonajes()
                        .forEach(p->p.getPeliculas().remove(pelicula));
        peliculaSerieRepository.delete(pelicula);
    }


    @Transactional
    public PeliculaSerieDtoResponse addPersonaje(List<String> codPersonaje,String codPelicula){

        PeliculaEntity peliculaEntity=peliculaSerieRepository.findByCod(codPelicula)
                .orElseThrow(()->new EntityNotFoundException("El cod de pelicula:"+codPelicula+" no encontrada"));
        List<PersonajeEntity> personajes=codPersonaje.stream()
                .map(cod->personajeRepository.findByCod(cod)
                        .orElseThrow(()->new EntityNotFoundException( "Personaje:"+codPersonaje+" no encontrado")))
                .toList();

        for(PersonajeEntity p:personajes){
            peliculaEntity.getPersonajes().add(p);
        }

        PeliculaEntity guardado=peliculaSerieRepository.save(peliculaEntity);
        return peliculaSerieMapper.entityToResponse(guardado);
    }


    @Transactional
    public PeliculaSerieDtoResponse removePersonajes(List<String> codPersonaje,String codPelicula){
        PeliculaEntity peliculaentity=peliculaSerieRepository.findByCod(codPelicula)
                .orElseThrow(()->new EntityNotFoundException("El cod de la elicula:"+codPelicula+" no encontrado-"));

        List<PersonajeEntity> personajesEntity=codPersonaje.stream()
                        .map(c->personajeRepository.findByCod(c)
                                .orElseThrow(()->new EntityNotFoundException("Personaje:"+c+" no encontrada")))
        .collect(Collectors.toList());

        personajesEntity.forEach(peliculaentity::removePersonaje);
        PeliculaEntity guardado=peliculaSerieRepository.save(peliculaentity);
        return peliculaSerieMapper.entityToResponse(guardado);
    }
    //-------------------------------------------------------------------------------------------------------------------

    private Set<GeneroEntity> getGenerosByNames(Set<String> nombreGeneros) {

        return nombreGeneros.stream()
                .map((g)->{
                    return  generoRepository.findByNombre(g).orElseThrow(()->new EntityNotFoundException("genero:"+g+"no encontrado"));
                }).collect(Collectors.toSet());
    }
    private Set<PersonajeEntity> getPersonajesByCodes(@Valid Set<String> codsPersonajes) {
        if (codsPersonajes==null) return null;
        return codsPersonajes.stream()
                .map((p)->{
                    return personajeRepository.findByCod(p)
                            .orElseThrow(()->new EntityNotFoundException("Personaje:"+p+ " no encontrado"));
                }).collect(Collectors.toSet());
    }


}
