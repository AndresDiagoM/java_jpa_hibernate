package com.alura.DAO;

import javax.persistence.EntityManager;

import com.alura.modelo.Producto;
import java.util.List;

public class ProductoDAO {
    
    //-------------------- Atributos --------------------
    private EntityManager em;

    //-------------------- Constructores --------------------
    public ProductoDAO(EntityManager em) {
        this.em = em;
    }

    //-------------------- Métodos --------------------
    public void guardar(Object producto) {
        em.persist(producto); // Guarda el objeto en la base de datos
    }

    public Producto consultarPorId(Long id) {
        return em.find(Producto.class, id); // Busca un objeto por su id
    }

    public List<Producto> consultarTodos() {
        String jpql = "SELECT p FROM Producto p"; // Consulta JPQL
        return  em.createQuery(jpql, Producto.class).getResultList(); // Ejecuta la consulta
    }

    //-------------------- Getters y Setters --------------------
}
