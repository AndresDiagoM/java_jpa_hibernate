package com.alura.modelo;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="items_pedido")
public class ItemsPedido {

    //----------- Atributos -----------
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private int cantidad;
    private Double precioUnitario;

    @ManyToOne
    private Producto producto;
    // un item de pedido tiene un producto y un producto puede estar en muchos items de pedido

    @ManyToOne
    private Pedido pedido;
    // un item de pedido tiene un pedido y un pedido tiene muchos items de pedido

    //----------- Constructores -----------
    public ItemsPedido() {}

    public ItemsPedido(int cantidad, Producto producto, Pedido pedido) {
        this.cantidad = cantidad;
        this.producto = producto;
        this.pedido = pedido;
        this.precioUnitario=producto.getPrecio();
    }

    //----------- Métodos -----------
    public Double getValor() {
        return precioUnitario*cantidad;
    }

    //getters / setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
}
