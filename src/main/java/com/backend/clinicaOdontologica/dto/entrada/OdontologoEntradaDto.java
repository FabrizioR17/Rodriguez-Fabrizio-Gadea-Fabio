package com.backend.clinicaOdontologica.dto.entrada;

import javax.validation.constraints.*;

public class OdontologoEntradaDto {

    private Long id;

    @Min(value = 100000, message = "La matricula no puede ser menor a 6 digitos")
    @Max(value = 999999, message = "La matricula no puede ser mayor a 6 digitos")
    private int numeroMatricula;

    @NotBlank(message = "Debe especificarse el nombre del odontologo")
    @Size(max = 50, message = "El nombre debe tener hasta 50 caracteres.")
    private String nombre;

    @NotBlank(message = "Debe especificarse el apellido del odontologo")
    @Size(max = 50, message = "El nombre debe tener hasta 50 caracteres.")
    private String apellido;

    public OdontologoEntradaDto() {
    }

    public OdontologoEntradaDto(int numeroMatricula, String nombre, String apellido) {
        this.numeroMatricula = numeroMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(int numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "\nnumeroMatricula: " + numeroMatricula + " - nombre: " + nombre + " - apellido: " + apellido;
    }
}
