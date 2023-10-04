package com.alura.modelo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "electronicos")
public class Electronico extends Producto {
    

    //-------------------- Atributos --------------------
    private String marca;
    private String modelo;

    //-------------------- Constructores --------------------
    public Electronico(String nombre, Double precio, String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    public Electronico() {
        this(null, null, null, null);
    }

    //-------------------- Métodos --------------------


    //-------------------- Getters y Setters --------------------

    public String getMarca() {return marca;}

    public void setMarca(String marca) {this.marca = marca;}

    public String getModelo() {return modelo;}

    public void setModelo(String modelo) {this.modelo = modelo;}
}
