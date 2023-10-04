package com.alura.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import com.alura.modelo.Pedido;
import com.alura.vo.RelatorioDeVenta;

public class PedidoDAO {
    
    //-------------------- Atributos --------------------
    private EntityManager em;

    
    //-------------------- Constructores --------------------
    public PedidoDAO(EntityManager em) {
        this.em = em;
    }
    
    
    //-------------------- Métodos --------------------
    public void guardar(Pedido pedido) {
        this.em.persist(pedido);
    }

    public Pedido consultarPorId(Long id) {
        return em.find(Pedido.class, id); // Busca un objeto por su id
    }

    public List<Pedido> consultarTodos() {
        String jpql = "SELECT p FROM Pedido p"; // Consulta JPQL
        return  em.createQuery(jpql, Pedido.class).getResultList(); // Ejecuta la consulta
    }

    public List<Pedido> consultarPorNombre(String nombre) {
        String jpql = "SELECT p FROM Pedido p WHERE p.nombre = :nombre"; 
        return em.createQuery(jpql, Pedido.class).setParameter("nombre", nombre).getResultList(); 
    }

    public Double consultarPrecioPorNombrePedido(String nombre){
        String jpql = "SELECT p.precio FROM Pedido p WHERE p.nombre = :nombre"; // Consulta JPQL
        return em.createQuery(jpql, Double.class).setParameter("nombre", nombre).getSingleResult(); 
    }

    public Double valorTotalVendido(){
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p"; // Consulta JPQL
        return em.createQuery(jpql, Double.class).getSingleResult(); 
    }

    public List<RelatorioDeVenta> relatorioVentasVO(){
        // consultar nombre producto, cantidad vendida, fecha ultima venta
        String jpql = "SELECT new com.alura.vo.RelatorioDeVenta( producto.nombre,"+
                "SUM(item.cantidad),"+
                "MAX(pedido.fecha)) FROM Pedido pedido "+
                "JOIN pedido.items item "+
                "JOIN item.producto producto "+
                "GROUP BY producto.nombre "+
                "ORDER BY item.cantidad DESC"; // Consulta JPQL
        return em.createQuery(jpql, RelatorioDeVenta.class).getResultList(); 
    }

    public Pedido consultarPedidoConCliente(Long id){
        String jpql = "SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id"; // Consulta JPQL
        return em.createQuery(jpql, Pedido.class).setParameter("id", id).getSingleResult(); 
    }
    // como cliente es tipo lazy , se debe hacer un join fetch para que se cargue el cliente


    //-------------------- Getters y Setters --------------------
}
