package com.alura.prueba;

import com.alura.DAO.*;
import com.alura.modelo.*;
import com.alura.utils.JPAUtils;

import java.time.LocalDate;

import javax.persistence.EntityManager;

public class LoadApp {

    public static void main(String[] args) {

        Cliente andres = new Cliente("Andres", "123456789");
        
        Producto celular = new Producto();
        Categoria celulares = new Categoria("Celulares");
        celular.setNombre("XIAOMI X10");
        celular.setDescripcion("GAMA ALTA");
        celular.setPrecio(2000000);
        celular.setCategoria(celulares);
        
        Pedido  pedido = new Pedido();
        
        
        
        guardarProducto("Xiaomi Redmi Note 8", "Celular de gama media");
        EntityManager em = JPAUtils.getEntityManager();

        ProductoDAO productoDAO = new ProductoDAO(em);
        Producto producto = productoDAO.consultarPorId(1l);

        System.out.println(producto.getNombre());

    }

    private static void guardarProducto(String nombre, String descripcion){
        Producto celular = new Producto();
        Categoria celulares = new Categoria("Celulares");
        celular.setNombre(nombre);
        celular.setDescripcion(descripcion);
        celular.setPrecio(2000000);
        celular.setCategoria(celulares);

        EntityManager em = JPAUtils.getEntityManager();

        ProductoDAO productoDAO = new ProductoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);

        
        em.getTransaction().begin();

        categoriaDAO.guardar(celulares); // Guarda la categoría en la base de datos antes que el celular
        productoDAO.guardar(celular);

        em.getTransaction().commit();
        em.close();
    }
    
}
