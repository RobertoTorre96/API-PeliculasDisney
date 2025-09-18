package com.Disney.DisneyApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="pelicula")
public class PeliculaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank
    private String titulo;
    @Column(nullable = false)
    private LocalDate fechaDeCreacion;
    @Column(unique = true,nullable = false)
    @NotBlank
    private String cod;
    @NotNull
    @Min(1)
    @Max(5)
    private Integer calificacion  ;
    @ManyToMany(mappedBy = "peliculas",fetch = FetchType.EAGER)
    private Set<PersonajeEntity> personajes=new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="peliculas_genero"
            ,joinColumns = @JoinColumn(name="id_pelicula"),inverseJoinColumns = @JoinColumn(name="id_genero"))
    private Set<GeneroEntity> generos=new HashSet<>();

    public void addPersonaje(PersonajeEntity personaje){
        this.personajes.add(personaje);
        personaje.getPeliculas().add(this);
    }

    public void removePersonaje(PersonajeEntity personaje){
        personaje.getPeliculas().remove(this);
        this.getPersonajes().remove(personaje);
    }

}
