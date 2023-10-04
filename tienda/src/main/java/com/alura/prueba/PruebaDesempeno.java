package com.alura.prueba;

import javax.persistence.EntityManager;

import com.alura.DAO.*;
import com.alura.modelo.*;
import com.alura.utils.JPAUtils;
import com.alura.vo.RelatorioDeVenta;

import java.time.LocalDate;
import java.util.List;

public class PruebaDesempeno {
    
    public static void main(String[] args) {

        Cliente andres = new Cliente("Andres", "123456789");
        
        guardarProducto("Xiaomi Redmi Note 8", "Celular de gama media");

        EntityManager em = JPAUtils.getEntityManager();

        ProductoDAO productoDAO = new ProductoDAO(em);
        Producto celular = productoDAO.consultarPorId(1l);

        ClienteDAO clienteDAO = new ClienteDAO(em);
        PedidoDAO pedidoDAO = new PedidoDAO(em);


        Pedido  pedido = new Pedido(andres);
        pedido.agregarItems(new ItemsPedido(5, celular, pedido));

        em.getTransaction().begin();
        clienteDAO.guardar(andres);
        pedidoDAO.guardar(pedido);
        em.getTransaction().commit();
        
        Pedido pedido2 = em.find(Pedido.class, 1l);
        
        //em.close();
        // em.find no trae el pedio y el cliente, solo el pedido, por esto el error en sout

        System.out.println(pedido2.getCliente().getNombre());


        // consultar por parametro de pedido dao
        LocalDate   fechaInicio = LocalDate.of(2020, 1, 1);
        List<Producto> productos = pedidoDAO.consultarPorParametros("Andres", 0.0, null);

        productos.forEach(System.out::println);
    }

    private static void guardarProducto(String nombre, String descripcion){
        Producto celular = new Producto();
        Categoria celulares = new Categoria("Celulares");
        celular.setNombre(nombre);
        celular.setDescripcion(descripcion);
        celular.setPrecio(20);
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
