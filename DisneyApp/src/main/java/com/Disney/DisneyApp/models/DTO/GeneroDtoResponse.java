package com.Disney.DisneyApp.models.DTO;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class GeneroDtoResponse {

    private String nombre;
    private Set<String> codPeliculasSeries;

}
