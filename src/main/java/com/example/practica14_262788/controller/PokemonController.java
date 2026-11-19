package com.example.practica14_262788.controller;

import com.example.practica14_262788.model.Pokemon;
import com.example.practica14_262788.service.PokemonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


/**
 * ResponseEntity es una respuesta http con formato json
 *
 * optional maneja los null pointer, obliga a buscar primero en la bd
 */
@RestController
@RequestMapping("/api/pokemones")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping
    public List<Pokemon> listar(){
        return pokemonService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> obtenerPorId(@PathVariable Long id){
        Optional<Pokemon> buscado = pokemonService.obtenerPorId(id);
        if( buscado.isPresent()){
            Pokemon poke = buscado.get();
            return ResponseEntity.ok(poke); // REGRESA ESTATUS 200 DE OK Y EL OBJETO EN FORMA JSON
        }else{
            return ResponseEntity.notFound().build(); // REGRESA NO ENCONTRADO 404 Y CUERPO VACÍO
        }
    }

    /**
     * Regresa una respuesta http con el estatus de creado
     * @param pokemon lo que se agrega
     * @return
     */
    @PostMapping
    public ResponseEntity<Pokemon> crear(@Valid @RequestBody Pokemon pokemon){
        Pokemon nuevoPoke = pokemonService.guardar(pokemon);
        return new ResponseEntity<>(nuevoPoke, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> actualizarTodo(@PathVariable Long id,
                                                  @Valid @RequestBody Pokemon detalles){
        // SE ACTUALIZA, PRIMERO SE COMPRUEBA QUE EXISTA EN LA BD CON OPTIONAL
        Optional<Pokemon> actualizado = pokemonService.actualizarCompleto(id, detalles);

        // SI ESTÁ GUARDADO, ENTONCES...
        if(actualizado.isPresent()){

            Pokemon poke = actualizado.get();

            return ResponseEntity.ok(poke); // actualizado
        }else{
            return ResponseEntity.notFound().build(); // no se encontró y no puede actualizarse
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Pokemon> actualizarAlgunasCosas(@PathVariable Long id,
                                                          @Valid @RequestBody Pokemon detalles){

        // COMPRUEBA EXISTENCIA Y ACTUALIZA
        Optional<Pokemon> actualizado = pokemonService.actualizarParcial(id, detalles);

        // PREGUNTA EN LA CAJA DEL OPTIONAL SI HAY UN OBJETO
        if(actualizado.isPresent()){
            Pokemon poke = actualizado.get();
            return ResponseEntity.ok(poke);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    // el void es porque pues nbo tiene nada el cuerpo porque se eliminó
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRecurso(@PathVariable Long id){
        if (pokemonService.eliminar(id)) {
            return ResponseEntity.noContent().build(); // DICE QUE NO HAY CONTENIDO, O SEA SE ELIMINA
        } else {
            return ResponseEntity.notFound().build(); // NO EXISTE
        }
    }
}
