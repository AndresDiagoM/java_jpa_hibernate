package com.alura.vo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

public class RelatorioDeVenta {

    //-------------------- Atributos --------------------
    private EntityManager em;
    private String nombreDelProducto;
    private Long CantidadDeProducto;
    private LocalDate FechaDeUltimaVenta;

    //-------------------- Constructores --------------------
    public RelatorioDeVenta(String nombreDelProducto, Long cantidadDeProducto, LocalDate fechaDeUltimaVenta) {
        this.nombreDelProducto = nombreDelProducto;
        CantidadDeProducto = cantidadDeProducto;
        FechaDeUltimaVenta = fechaDeUltimaVenta;
    }
    
    //-------------------- Métodos --------------------

    public List<RelatorioDeVenta> relatorioDeVentasVO() {
        String jpql = "SELECT new com.latam.alura.tienda.vo.RelatorioDeVenta(producto.nombre, "
                + "SUM(item.cantidad), "
                + "MAX(pedido.fecha)) "
                + "FROM Pedido pedido "
                + "JOIN pedido.items item "
                + "JOIN item.producto producto "
                + "GROUP BY producto.nombre "
                + "ORDER BY item.cantidad DESC";
        return em.createQuery(jpql, RelatorioDeVenta.class).getResultList();
    }

    @Override
    public String toString() {
        return "RelatorioDeVenta [CantidadDeProducto=" + CantidadDeProducto + ", FechaDeUltimaVenta="
                + FechaDeUltimaVenta + ", nombreDelProducto=" + nombreDelProducto + "]";
    }

    //-------------------- Getters y Setters --------------------
    public String getNombreDelProducto() {
        return nombreDelProducto;
    }

    public void setNombreDelProducto(String nombreDelProducto) {
        this.nombreDelProducto = nombreDelProducto;
    }

    public Long getCantidadDeProducto() {
        return CantidadDeProducto;
    }

    public void setCantidadDeProducto(Long CantidadDeProducto) {
        this.CantidadDeProducto = CantidadDeProducto;
    }

    public LocalDate getFechaDeUltimaVenta() {
        return FechaDeUltimaVenta;
    }

    public void setFechaDeUltimaVenta(LocalDate FechaDeUltimaVenta) {
        this.FechaDeUltimaVenta = FechaDeUltimaVenta;
    }

}
