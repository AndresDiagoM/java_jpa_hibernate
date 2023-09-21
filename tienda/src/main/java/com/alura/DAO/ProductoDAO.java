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

    public List<Producto> consultarPorNombre(String nombre) {
        String jpql = "SELECT p FROM Producto p WHERE p.nombre = :nombre"; // Consulta JPQL
        return em.createQuery(jpql, Producto.class).setParameter("nombre", nombre).getResultList(); // Ejecuta la consulta
    }

    public List<Producto> consultarPorNombreDeCategoria(String nombre) {
        String jpql = "SELECT p FROM Producto p WHERE p.categoria.nombre = :nombre"; // Consulta JPQL
        return em.createQuery(jpql, Producto.class).setParameter("nombre", nombre).getResultList(); // Ejecuta la consulta
    }

    public Double consultarPrecioPorNombreProducto(String nombre){
        String jpql = "SELECT p.precio FROM Producto p WHERE p.nombre = :nombre"; // Consulta JPQL
        return em.createQuery(jpql, Double.class).setParameter("nombre", nombre).getSingleResult(); // Ejecuta la consulta
    }

    //-------------------- Getters y Setters --------------------
}
