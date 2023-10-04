package com.alura.DAO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.alura.modelo.Pedido;
import com.alura.modelo.Producto;
import com.alura.vo.RelatorioDeVenta;

public class PedidoDAO {
    
    //-------------------- Atributos --------------------
    private EntityManager em;

    
    //-------------------- Constructores --------------------
    public PedidoDAO(EntityManager em) {
        this.em = em;
    }
    
    
    //-------------------- Métodos --------------------
    public void guardar(Pedido pedido) {
        this.em.persist(pedido);
    }

    public Pedido consultarPorId(Long id) {
        return em.find(Pedido.class, id); // Busca un objeto por su id
    }

    public List<Pedido> consultarTodos() {
        String jpql = "SELECT p FROM Pedido p"; // Consulta JPQL
        return  em.createQuery(jpql, Pedido.class).getResultList(); // Ejecuta la consulta
    }

    public List<Pedido> consultarPorNombre(String nombre) {
        String jpql = "SELECT p FROM Pedido p WHERE p.nombre = :nombre"; 
        return em.createQuery(jpql, Pedido.class).setParameter("nombre", nombre).getResultList(); 
    }

    public Double consultarPrecioPorNombrePedido(String nombre){
        String jpql = "SELECT p.precio FROM Pedido p WHERE p.nombre = :nombre"; // Consulta JPQL
        return em.createQuery(jpql, Double.class).setParameter("nombre", nombre).getSingleResult(); 
    }

    public Double valorTotalVendido(){
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p"; // Consulta JPQL
        return em.createQuery(jpql, Double.class).getSingleResult(); 
    }

    public List<RelatorioDeVenta> relatorioVentasVO(){
        // consultar nombre producto, cantidad vendida, fecha ultima venta
        String jpql = "SELECT new com.alura.vo.RelatorioDeVenta( producto.nombre,"+
                "SUM(item.cantidad),"+
                "MAX(pedido.fecha)) FROM Pedido pedido "+
                "JOIN pedido.items item "+
                "JOIN item.producto producto "+
                "GROUP BY producto.nombre "+
                "ORDER BY item.cantidad DESC"; // Consulta JPQL
        return em.createQuery(jpql, RelatorioDeVenta.class).getResultList(); 
    }

    public Pedido consultarPedidoConCliente(Long id){
        String jpql = "SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id"; // Consulta JPQL
        return em.createQuery(jpql, Pedido.class).setParameter("id", id).getSingleResult(); 
    }
    // como cliente es tipo lazy , se debe hacer un join fetch para que se cargue el cliente

    public List<Producto> consultarPorParametros(String nombre, Double precio,LocalDate fecha){
        StringBuilder jpql=new StringBuilder("SELECT p FROM Producto p WHERE 1=1 ");

        if(nombre!=null && !nombre.trim().isEmpty()) {
            jpql.append("AND p.nombre=:nombre ");
        }
        if(precio!=null && !precio.equals(0)) {
            jpql.append("AND p.precio=:precio ");
        }
        if(fecha!=null) {
            jpql.append("AND p.fechaDeRegistro=:fecha");
        }

        TypedQuery<Producto> query = em.createQuery(jpql.toString(),Producto.class);

        if(nombre!=null && !nombre.trim().isEmpty()) {
            query.setParameter("nombre", nombre);
        }
        if(precio!=null && !precio.equals(0)) {
            query.setParameter("precio", precio);
        }
        if(fecha!=null) {
            query.setParameter("fechaDeRegistro", fecha);
        }
        
        return query.getResultList();        
    }


    //-------------------- Getters y Setters --------------------

}
