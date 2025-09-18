package com.Disney.DisneyApp.models.DTO;

import com.Disney.DisneyApp.models.PersonajeEntity;
import lombok.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PeliculaSerieDtoResponse {


    private String cod;
    private String titulo;
    private LocalDate fechaDeCreacion;
    private Integer calificacion  ;
    private Set<String> codSPersonajes;
    private Set<String> nombreGenero;
}
