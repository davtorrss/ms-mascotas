package com.vetnova.ms_mascotas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
@Table(name = "mascotas")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 30, message = "El nombre debe tener entre 2 y 30 caracteres")
    private String nombre;

    @NotBlank(message = "La especie es obligatoria")
    @Size(min = 3, max = 20, message = "La especie debe tener entre 3 y 20 caracteres (ej: Perro, Gato)")
    private String especie;

    @NotBlank(message = "La raza es obligatoria")
    @Size(min = 3, max = 30, message = "La raza debe tener entre 3 y 30 caracteres")
    private String raza;

    @NotNull(message = "La edad es obligatoria")
    @DecimalMin(value = "0.0", message = "La edad no puede ser negativa")
    private Double edad; 

    @NotNull(message = "El ID del dueño es obligatorio")
    private Long duenoId;
}