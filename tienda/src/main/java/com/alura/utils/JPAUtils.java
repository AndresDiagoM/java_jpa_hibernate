package com.alura.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtils {
    
    //-------------------- Atributos --------------------
    private static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("tienda");
    
    //-------------------- Constructores --------------------
    
    //-------------------- Métodos --------------------
    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }

    //-------------------- Getters y Setters --------------------
}
