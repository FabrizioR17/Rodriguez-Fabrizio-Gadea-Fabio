package com.backend.clinicaOdontologica.entity;

public class Odontologo {
    private Long id;
    private int numeroMatricula;
    private String nombre;
    private String apellido;

    public Odontologo() {
    }

    public Odontologo(Long id, int numero_matricula, String nombre, String apellido) {
        this.id = id;
        this.numeroMatricula = numero_matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Odontologo(int numero_matricula, String nombre, String apellido) {
        this.numeroMatricula = numero_matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumero_matricula() {
        return numeroMatricula;
    }

    public void setNumero_matricula(int numero_matricula) {
        this.numeroMatricula = numero_matricula;
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
        return "\nId: " + id + " - Numero_matricula: " + numeroMatricula + " - Nombre: " + nombre + " - Apellido: " + apellido;
    }

}
