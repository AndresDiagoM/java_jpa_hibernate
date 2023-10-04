package com.alura.modelo;

import javax.persistence.Embeddable;

@Embeddable
public class DatosPersonales {
    
    //-------------------- Atributos --------------------
    private String nombre;
    private String dni;


    //-------------------- Constructores --------------------

    public DatosPersonales(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public DatosPersonales() {
        this(null,null);
    }

    //-------------------- Métodos --------------------


    //-------------------- Getters y Setters --------------------
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

}
