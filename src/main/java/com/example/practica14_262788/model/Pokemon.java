package com.example.practica14_262788.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Data // GENERA GETTERS Y SETTERS
@NoArgsConstructor // la semántica lo dice xd
@AllArgsConstructor
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;

    @Column(unique = true, nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String nivel;

    @Column(nullable = false)
    private Integer ataque;

    @Column(nullable = false)
    private Integer defensa;

    @Column(length = 1000)
    private String descripcion;

    @Column
    private String imagenUrl;

}
