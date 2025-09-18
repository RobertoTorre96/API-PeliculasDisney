package com.Disney.DisneyApp.models.DTO;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonajeDtoResponse {
    private Integer id;
    private String cod;
    private String nombre;
    private Integer edad;
    private String peso;
    private String historia;
    private Set<String> peliculas=new HashSet<>();

}
