package com.Disney.DisneyApp.models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonajeDtoRequest {
    @NotBlank
    private String nombre;
    @NotBlank
    @NotNull
    private  String cod;

    private Integer edad;

    private String peso;

    private String historia;

    private Set<String> codPeliculas;


}
