package com.example.practica14_262788.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Entity
@Data // GENERA GETTERS Y SETTERS
@NoArgsConstructor // la semántica lo dice xd
@AllArgsConstructor
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "El nombre es obligatorio y no puede estar vacío.")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres.")
    private String nombre;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "El tipo de pokemon es obligatorio")
    private String tipo;

    @Column(nullable = false)
    @NotBlank(message = "EL nivel es obligatorio.")

    @Min(value = 1, message = "El nivel debe ser al menos 1")
    @Max(value = 100, message = "El nivel máximo es de 100.")
    private String nivel;

    @Column(nullable = false)
    @Min(value = 0, message = "El ataque no puede ser negativo")
    @Positive()
    private Integer ataque;

    @Min(value = 0, message = "La defensa no puede ser negativo")
    @Column(nullable = false)
    @Positive()
    private Integer defensa;

    @Column(length = 1000)
    @Size(max = 255, message = "La descripción no puede exceder de 255.")
    private String descripcion;

    @Column
    @URL(message = "La imagen debe de ser URl válida.")
    private String imagenUrl;

    @PastOrPresent(message = "La fecha de captura debe ser en el futuro")
    private LocalDate fechaCaptura;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "formato de  correo incorrecto.")
    private String correo;

}
