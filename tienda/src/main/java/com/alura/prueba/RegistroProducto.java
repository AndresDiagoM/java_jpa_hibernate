package com.alura.prueba;

import javax.persistence.EntityManager;

import com.alura.DAO.CategoriaDAO;
import com.alura.DAO.ProductoDAO;
import com.alura.modelo.Categoria;
import com.alura.modelo.Producto;
import com.alura.utils.JPAUtils;


/**
 *
 * @author felip
 */
public class RegistroProducto {
    
    public static void main(String[] args) {
        Producto celular = new Producto();
        celular.setNombre("Xiaomi Redmi Note 8");
        celular.setDescripcion("Celular de gama media");
        celular.setPrecio(2000000);
        celular.setCategoria(new Categoria("Celulares"));

        EntityManager em = JPAUtils.getEntityManager();

        ProductoDAO productoDAO = new ProductoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);

        em.getTransaction().begin();

        categoriaDAO.guardar(celular.getCategoria());
        productoDAO.guardar(celular);

        em.getTransaction().commit();
        em.close();
    }
}
