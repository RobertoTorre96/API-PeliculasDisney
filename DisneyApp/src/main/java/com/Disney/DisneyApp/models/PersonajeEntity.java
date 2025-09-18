package com.Disney.DisneyApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "personaje")
public class PersonajeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotBlank
    private String cod;
    @Column(nullable = false)
    @NotBlank
    private String nombre;
    @NotNull
    @Min(0)
    private Integer edad;
    @NotNull
    @Min(0)
    private String peso;
    private String historia;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinTable (name ="personaje_pelicula",joinColumns = @JoinColumn(name = "id_personaje"),inverseJoinColumns = @JoinColumn(name="id_pelicula"))
    private Set<PeliculaEntity> peliculas=new HashSet<>();


}
