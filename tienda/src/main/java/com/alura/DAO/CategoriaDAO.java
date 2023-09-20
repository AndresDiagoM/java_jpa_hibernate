package com.alura.DAO;

import javax.persistence.EntityManager;

public class CategoriaDAO {
    
    //-------------------- Atributos --------------------
    private EntityManager em;

    //-------------------- Constructores --------------------
    public CategoriaDAO(EntityManager em) {
        this.em = em;
    }

    //-------------------- Métodos --------------------
    public void guardar(Object categoria) {
        em.persist(categoria); // Guarda el objeto en la base de datos
    }

    public void actualizar(Object categoria) {
        em.merge(categoria); // Actualiza el objeto en la base de datos
    }

    public void eliminar(Object categoria) {
        categoria = this.em.merge(categoria); // Actualiza el objeto en la base de datos
        em.remove(categoria); // Elimina el objeto de la base de datos
    }

    //-------------------- Getters y Setters --------------------
}
