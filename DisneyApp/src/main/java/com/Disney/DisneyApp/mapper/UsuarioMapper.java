package com.Disney.DisneyApp.mapper;

import com.Disney.DisneyApp.models.DTO.UsuarioDtoResponse;
import com.Disney.DisneyApp.models.DTO.UsuarioDtorequest;
import com.Disney.DisneyApp.models.RoleEntity;
import com.Disney.DisneyApp.models.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioEntity requestToEntity(UsuarioDtorequest u);

    @Mapping(source = "roles",target = "roles",qualifiedByName = "nombreRoles" )
    UsuarioDtoResponse entitytoresponse(UsuarioEntity u);

    @Named("nombreRoles")
    default Set<String> nombreRoles(Set<RoleEntity> roles){
        return roles.stream()
                .map(r->r.getName().name())
                .collect(Collectors.toSet());
    }
}
