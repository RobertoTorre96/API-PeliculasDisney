package com.Disney.DisneyApp.models.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDtorequest {
    @NotBlank
    private String nombre;

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotEmpty
    String role;
}
