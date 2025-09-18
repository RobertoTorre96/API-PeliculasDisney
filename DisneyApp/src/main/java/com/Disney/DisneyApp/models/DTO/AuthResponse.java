package com.Disney.DisneyApp.models.DTO;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    private String token;
    private String username;
    private List<String> roles;
    private String message;
    // constructor, getters y setters
}
