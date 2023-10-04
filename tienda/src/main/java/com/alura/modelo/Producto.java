package com.alura.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Producto 
 * 
 * @author felip
 */
@Entity
@Table(name = "productos")
@NamedQuery(name = "Producto.consultaDePrecio", query = "SELECT p.precio FROM Producto p WHERE p.nombre = :nombre")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Producto {
    
    //-------------------- Atributos --------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera el id automáticamente
    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private LocalDate fechaDeRegistro = LocalDate.now();   
    @Enumerated(EnumType.STRING) // Guarda el nombre String del enum en la base de datos, y no como un numero
    @ManyToOne(fetch = FetchType.LAZY) // Muchos productos pueden tener una misma categoría
    private Categoria categoria;

    //-------------------- Constructores --------------------
    public Producto(String nombre, String descripcion, double precio, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
    }
    public Producto() {
        this(null, null, 0, null);
    }

    //-------------------- Métodos --------------------
    @Override
    public String toString() {
        return "Producto [categoria=" + categoria + ", descripcion=" + descripcion + ", fechaDeRegistro="
                + fechaDeRegistro + ", id=" + id + ", nombre=" + nombre + ", precio=" + precio + "]";
    }

    //-------------------- Getters y Setters --------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public LocalDate getFechaDeRegistro() {
        return fechaDeRegistro;
    }

    public void setFechaDeRegistro(LocalDate fechaDeRegistro) {
        this.fechaDeRegistro = fechaDeRegistro;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
