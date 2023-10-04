package com.alura.modelo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "libros")
public class Libros extends Producto{
    
    //-------------------- Atributos --------------------
    private String autor;
    private String editorial;

    //-------------------- Constructores --------------------

    public Libros(String nombre, Double precio, String autor, String editorial) {
        this.autor = autor;
        this.editorial = editorial;
    }

    public Libros() {
        this(null, null, null, null);
    }

    //-------------------- Métodos --------------------


    //-------------------- Getters y Setters --------------------

    public String getAutor() {return autor;}

    public void setAutor(String autor) {this.autor = autor;}

    public String getEditorial() {return editorial;}

    public void setEditorial(String editorial) {this.editorial = editorial;}
}
