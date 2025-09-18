package com.Disney.DisneyApp.models.DTO;

import com.Disney.DisneyApp.models.RoleEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class UsuarioDtoResponse {

    @Size(min = 4)
    @NotNull
    private String nombre;
    @Email
    private String email;
    private Set<String> roles;
}
