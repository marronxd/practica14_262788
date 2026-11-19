package com.example.practica14_262788.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {

    private String  mensaje;

    private List<String> detalle;

    private LocalDateTime fecha;
}
