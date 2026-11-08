package com.example.practica14_262788.service;

import com.example.practica14_262788.model.Pokemon;
import com.example.practica14_262788.repository.IPokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    @Autowired
    private IPokemonRepository pokemonRepository;

    public List<Pokemon> obtenerTodos() {
        return pokemonRepository.findAll();
    }

    public Optional<Pokemon> obtenerPorId(Long id) {
        return pokemonRepository.findById(id);
    }

    // Reemplaza todo (es el put)
    public Optional<Pokemon> actualizarCompleto(Long id, Pokemon detalles) {

        // Básicamente si lo actualiza, lo regresa, sino pues no xd
        return pokemonRepository.findById(id).map(reemplazar -> {

            // REEMPLAZAR .... barco de theseo????
            reemplazar.setNombre(detalles.getNombre());

            reemplazar.setTipo(detalles.getTipo());

            reemplazar.setNivel(detalles.getNivel());

            reemplazar.setAtaque(detalles.getAtaque());

            reemplazar.setDefensa(detalles.getDefensa());

            reemplazar.setDescripcion(detalles.getDescripcion());

            reemplazar.setImagenUrl(detalles.getImagenUrl());

            return pokemonRepository.save(reemplazar);
        });
    }

    // Se acrtualizan partes especificas (es el patch)
    public Optional<Pokemon> actualizarParcial(Long id, Pokemon detalles) {
        return pokemonRepository.findById(id).map(registrado -> {

            if (detalles.getNombre() != null) registrado.setNombre(detalles.getNombre());

            if (detalles.getTipo() != null) registrado.setTipo(detalles.getTipo());

            if (detalles.getNivel() != null) registrado.setNivel(detalles.getNivel());

            if (detalles.getAtaque() != null) registrado.setAtaque(detalles.getAtaque());

            if(detalles.getDefensa() != null) registrado.setDefensa(detalles.getDefensa());

            if (detalles.getDescripcion() != null) registrado.setDescripcion(detalles.getDescripcion());

            if(detalles.getImagenUrl() != null) registrado.setImagenUrl(detalles.getImagenUrl());

            return pokemonRepository.save(registrado);
        });
    }

    public Pokemon guardar(Pokemon recurso) {
        return pokemonRepository.save(recurso);
    }

    public boolean eliminar(Long id) {
        if (pokemonRepository.existsById(id)) {
            pokemonRepository.deleteById(id);
            return  true;
        }
        return false;
    }
}
