package com.alura.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="pedidos")
public class Pedido {

    //----------- Atributos -----------
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha=LocalDate.now();
    @Column(name="valor_total")
    private Double valorTotal;

    @ManyToOne
    private Cliente cliente;
    // un pedido tiene un cliente y un cliente tiene muchos pedidos

    @OneToMany(mappedBy="pedido", cascade=CascadeType.ALL)  // hay que indicar que esta conectado al atributo pedido de la clase ItemsPedido
    // cascade=CascadeType.ALL indica que si se borra un pedido se borran todos los items asociados a ese pedido
    private List<ItemsPedido> items=new ArrayList<>();
    // un pedido tiene muchos items o productos

    //----------- Constructores -----------
    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.valorTotal=0.0;
    }
    public Pedido() {}

    //----------- Métodos -----------
    /**
     * Añade un item a la lista de items del pedido
     * @param item
     */
    public void agregarItems(ItemsPedido item) {
        item.setPedido(this);
        this.items.add(item);
        this.valorTotal += (item.getValor());
    }

    //-------------------- Getters y Setters --------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemsPedido> getItems() {
        return items;
    }

    public void setItems(List<ItemsPedido> items) {
        this.items = items;
    }
    
}
