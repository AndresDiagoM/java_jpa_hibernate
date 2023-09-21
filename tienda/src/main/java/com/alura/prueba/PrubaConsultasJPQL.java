package com.alura.prueba;

import javax.persistence.EntityManager;

import com.alura.DAO.CategoriaDAO;
import com.alura.DAO.ProductoDAO;
import com.alura.modelo.Categoria;
import com.alura.modelo.Producto;
import com.alura.utils.JPAUtils;

public class PrubaConsultasJPQL {
    
    public static void main(String[] args) {
        
        guardarProducto("Xiaomi Redmi Note 8", "Celular de gama media");
        EntityManager em = JPAUtils.getEntityManager();

        ProductoDAO productoDAO = new ProductoDAO(em);
        Producto producto = productoDAO.consultarPorId(1l);

        System.out.println(producto.getNombre());

        //crear otro producto
        guardarProducto("Samsung Note 8", "Celular de gama baja");

        productoDAO.consultarTodos().forEach(p -> System.out.println(p.getNombre()));


        //consultar por nombre
        guardarProducto("Xiaomi Redmi Note 8", "Celular 2023");
        productoDAO.consultarPorNombre("Xiaomi Redmi Note 8").forEach(p -> System.out.println(p.getNombre()+ " " + p.getDescripcion()));

        //consultar por nombre de categoria
        productoDAO.consultarPorNombreDeCategoria("Celulares").forEach(p -> System.out.println(p.getNombre()+ " " + p.getDescripcion()));

        //consultar precio por nombre de producto
        System.out.println(productoDAO.consultarPrecioPorNombreProducto("Samsung Note 8"));
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
