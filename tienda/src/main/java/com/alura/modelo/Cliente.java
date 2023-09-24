package com.alura.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="clientes")
public class Cliente {

    //----------- Atributos -----------
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String dni;

    @OneToMany(mappedBy="cliente")
    private List<Pedido> pedidos = new ArrayList<>();

    //----------- Constructores -----------
    public Cliente() {}

    public Cliente(String nombre, String dni) {
        this.nombre=nombre;
        this.dni=dni;
    }

    //----------- Métodos -----------
    public void agregarPedido(Pedido pedido) {
        pedido.setCliente(this);
        this.pedidos.add(pedido);
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

}
