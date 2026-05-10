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

    @NotBlank(message = "El nombre de la mascota es obligatorio")
    private String nombre;

    @NotBlank(message = "Debe indicar la especie (ej: Perro, Gato)")
    private String especie;

    @Min(value = 0, message = "La edad no puede ser negativa")
    private int edad;

    @NotNull(message = "El ID del dueño es obligatorio")
    private Long duenoId; // Relación lógica con el otro microservicio
}