package com.Disney.DisneyApp.mapper;

import com.Disney.DisneyApp.models.DTO.PersonajeDtoResponse;
import com.Disney.DisneyApp.models.PeliculaEntity;
import com.Disney.DisneyApp.models.PersonajeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PersonajeMapper {
    
    @Mapping(source = "peliculas", target = "peliculas", qualifiedByName = "peliculaToCod")
    PersonajeDtoResponse entityToResponse (PersonajeEntity p);

    @Named("peliculaToCod")
    default   Set<String> peliculaToCod (Set<PeliculaEntity> peliculas){
        if(peliculas==null)return null;

        return peliculas.stream()
                .map(PeliculaEntity::getCod)
                .collect(Collectors.toSet());
    }

}
