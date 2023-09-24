package com.alura.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import com.alura.modelo.Pedido;
import com.alura.modelo.Producto;

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


    //-------------------- Getters y Setters --------------------
}
