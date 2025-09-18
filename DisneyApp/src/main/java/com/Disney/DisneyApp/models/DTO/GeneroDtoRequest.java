package com.Disney.DisneyApp.models.DTO;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeneroDtoRequest {

    private String nombre;
    private Set<String> codPeliculaSerie=new HashSet<>();

}
