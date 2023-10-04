package com.alura.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
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
    
    @Embedded
    private DatosPersonales datosPersonales;

    @OneToMany(mappedBy="cliente")
    private List<Pedido> pedidos = new ArrayList<>();

    //----------- Constructores -----------
    public Cliente() {}

    public Cliente(String nombre, String dni) {
        this.datosPersonales = new DatosPersonales(nombre, dni);
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
        return datosPersonales.getNombre();
    }

    public void setNombre(String nombre) {
        this.datosPersonales.setNombre(nombre);
    }

    public String getDni() {
        return datosPersonales.getDni();
    }

    public void setDni(String dni) {
        this.datosPersonales.setDni(dni);
    }

}
