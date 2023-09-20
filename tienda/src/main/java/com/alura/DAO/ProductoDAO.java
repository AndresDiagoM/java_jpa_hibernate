package com.alura.DAO;

import javax.persistence.EntityManager;

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

    //-------------------- Getters y Setters --------------------
}
