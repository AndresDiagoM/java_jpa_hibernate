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
        Categoria celulares = new Categoria("Celulares");
        celular.setNombre("Xiaomi Redmi Note 8");
        celular.setDescripcion("Celular de gama media");
        celular.setPrecio(2000000);
        celular.setCategoria(celulares);

        EntityManager em = JPAUtils.getEntityManager();

        ProductoDAO productoDAO = new ProductoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);

        // em.getTransaction().begin();

        // categoriaDAO.guardar(celulares); // Guarda la categoría en la base de datos antes que el celular
        // productoDAO.guardar(celular);

        // em.getTransaction().commit();
        // em.close();

        //despues de cerrar el entity manager, el objeto celular queda en estado detached, no se guardan los cambios
        //el metodo flush a diferencia de commit, guarda los cambios en la base de datos pero no cierra la transaccion

        em.getTransaction().begin();
        celulares.setNombre("Celulares Xiaomi");

        em.flush();;
        em.clear();

        //luego de close, se puede modificar5 usando merge
        celulares = em.merge(celulares); // para esto es necesario constructor sin argumentos en Categoria
        celulares.setNombre("Celulares Xiaomi 2");
        em.flush();
        em.clear();

        // para eliminar un registro tiene que estar en estado managed
        celulares = em.merge(celulares); //traer de detached a managed
        em.remove(celulares);
        em.flush();

        //con flush se puede hacer un rollback 
        
    }
}
