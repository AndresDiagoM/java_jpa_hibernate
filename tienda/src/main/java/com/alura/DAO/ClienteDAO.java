package com.alura.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import com.alura.modelo.Cliente;

public class ClienteDAO {
    
    //-------------------- Atributos --------------------
    private EntityManager em;

    
    //-------------------- Constructores --------------------
    public ClienteDAO(EntityManager em) {
        this.em = em;
    }
    
    
    //-------------------- Métodos --------------------
    public void guardar(Cliente cliente) {
        this.em.persist(cliente);
    }

    public Cliente consultarPorId(Long id) {
        return em.find(Cliente.class, id); // Busca un objeto por su id
    }

    public List<Cliente> consultarTodos() {
        String jpql = "SELECT p FROM Cliente p"; // Consulta JPQL
        return  em.createQuery(jpql, Cliente.class).getResultList(); // Ejecuta la consulta
    }

    public List<Cliente> consultarPorNombre(String nombre) {
        String jpql = "SELECT p FROM Cliente p WHERE p.nombre = :nombre"; 
        return em.createQuery(jpql, Cliente.class).setParameter("nombre", nombre).getResultList(); 
    }

    public Double consultarPrecioPorNombreCliente(String nombre){
        String jpql = "SELECT p.precio FROM Cliente p WHERE p.nombre = :nombre"; // Consulta JPQL
        return em.createQuery(jpql, Double.class).setParameter("nombre", nombre).getSingleResult(); 
    }


    //-------------------- Getters y Setters --------------------
}
