package com.Disney.DisneyApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "genero")
public class GeneroEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String nombre;


    @ManyToMany(mappedBy = "generos", fetch = FetchType.EAGER)
    private Set<PeliculaEntity> peliculasSeries=new HashSet<>();

}
