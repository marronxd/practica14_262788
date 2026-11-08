package com.example.practica14_262788.repository;

import com.example.practica14_262788.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

// HACER LA EXTENSIÓN DA ACCESO DIRECTO A LA BD SIN ESCRIBIR QUERYS
public interface IPokemonRepository extends JpaRepository<Pokemon, Long> {

}
