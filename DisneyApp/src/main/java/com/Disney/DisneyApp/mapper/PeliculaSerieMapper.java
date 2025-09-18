package com.Disney.DisneyApp.mapper;

import com.Disney.DisneyApp.models.DTO.PeliculaSerieDtoResponse;
import com.Disney.DisneyApp.models.GeneroEntity;
import com.Disney.DisneyApp.models.PeliculaEntity;
import com.Disney.DisneyApp.models.PersonajeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PeliculaSerieMapper  {

    @Mapping(source = "generos",target = "nombreGenero",qualifiedByName = "generosToNames" )
    @Mapping(source="personajes",target="codSPersonajes",qualifiedByName ="personajesToCod" )
    PeliculaSerieDtoResponse entityToResponse(PeliculaEntity p);

@Named("generosToNames")
    default Set<String> generosToNames (Set<GeneroEntity> generos){
        if(generos==null)return null;

        return generos.stream()
                .map(GeneroEntity::getNombre)
                .collect(Collectors.toSet());
    }

    @Named("personajesToCod")
    default  Set<String>personajesToCod(Set<PersonajeEntity>p){
    if (p==null)return null;
    return p.stream()
            .map(PersonajeEntity::getNombre)
            .collect(Collectors.toSet());
    }
}
