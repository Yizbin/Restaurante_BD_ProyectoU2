/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moduloProductosDAO;

import Conexion.Conexion;
import Exception.PersistenciaException;
import javax.persistence.EntityManager;
import moduloProductosEntidades.Producto;

/**
 *
 * @author sonic
 */
public class ProductoDAO implements IProductoDAO{
    
    private static ProductoDAO instanceProductoDAO;

    private ProductoDAO() {
    }

    public static ProductoDAO getInstance() {
        if (instanceProductoDAO == null) {
            instanceProductoDAO = new ProductoDAO();
        }
        return instanceProductoDAO;
    }
    
    @Override
    public Producto registrarProducto(Producto producto) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(producto);
            em.getTransaction().commit();
            return producto;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("El producto no se pudo registrar");
        } finally {
            em.close();
            Conexion.cerrarConexion();
        }
    }
    
}
