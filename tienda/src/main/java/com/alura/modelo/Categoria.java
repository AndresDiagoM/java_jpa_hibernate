package com.alura.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {
    
    //-------------------- Atributos --------------------
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String nombre;


    //-------------------- Constructores --------------------
    public Categoria(String nombre) {
        this.nombre = nombre;
    }
    public Categoria() {
        this(null);
    }

    //-------------------- Métodos --------------------

    //-------------------- Getters y Setters --------------------
    public Long getId() {return id;    }

    public void setId(Long id) {this.id = id;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}
}
