package com.Disney.DisneyApp.mapper;

import com.Disney.DisneyApp.models.DTO.GeneroDtoResponse;
import com.Disney.DisneyApp.models.GeneroEntity;
import com.Disney.DisneyApp.models.PeliculaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",uses = {PeliculaSerieMapper.class})
public interface GeneroMapper {

    @Mapping(target = "codPeliculasSeries", source = "peliculasSeries", qualifiedByName = "peliculasToCod")
    GeneroDtoResponse entityToResponse(GeneroEntity g);

    @Named("peliculasToCod")
    default Set<String> peliculasToCod(Set<PeliculaEntity> peliculas) {
        if (peliculas == null) {
            return null;
        }
        return peliculas.stream()
                .map(PeliculaEntity::getCod)
                .collect(Collectors.toSet());
    }
}
