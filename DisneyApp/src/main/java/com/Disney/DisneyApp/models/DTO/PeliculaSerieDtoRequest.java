package com.Disney.DisneyApp.models.DTO;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PeliculaSerieDtoRequest {


    @NotNull (message = "El titulo no puede ser null")
    private String titulo;
    @NotNull (message = "El cod no puede ser null")
    private String cod;
    @NotNull (message = "la fecha no debe ser null")
    private LocalDate fechaDeCreacion;
    @NotNull (message = "la calificacion de debe ser null")
    @Min(value = 1 ,message = "la Calificacion debe estar entre  1-5")
    @Max(value = 5,message = "la Calificacion debe estar entre  1-5")
    private Integer calificacion  ;

    private Set<String> nombreGeneros;

    private Set<String>codsPersonajes;



}
