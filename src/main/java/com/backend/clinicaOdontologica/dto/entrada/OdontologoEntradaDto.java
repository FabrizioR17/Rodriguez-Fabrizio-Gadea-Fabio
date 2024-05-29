package com.backend.clinicaOdontologica.dto.entrada;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class OdontologoEntradaDto {

    @Positive(message = "El numero de matricula no puede ser menor a 0 o nulo")
    private int numero_matricula;

    @NotBlank(message = "Debe especificarse el nombre del odontologo")
    @Size(max = 50, message = "El nombre debe tener hasta 50 caracteres.")
    private String nombre;

    @NotBlank(message = "Debe especificarse el apellido del odontologo")
    @Size(max = 50, message = "El nombre debe tener hasta 50 caracteres.")
    private String apellido;

    public OdontologoEntradaDto() {
    }

    public OdontologoEntradaDto(int numero_matricula, String nombre, String apellido) {
        this.numero_matricula = numero_matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getNumero_matricula() {
        return numero_matricula;
    }

    public void setNumero_matricula(int numero_matricula) {
        this.numero_matricula = numero_matricula;
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
}
