/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moduloProductosDAO;

import Conexion.Conexion;
import Enums.EstadoProducto;
import Enums.TipoPlatillo;
import Enums.UnidadMedida;
import Exception.PersistenciaException;
import ModuloIngredientesEntidades.Ingrediente;
import ModuloProductosDTOs.IngredienteProductoDTO;
import ModuloProductosEntidades.Producto;
import ModuloProductosEntidades.ProductoOcupaIngrediente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author sonic
 */
public class ProductoDAO implements IProductoDAO {

    private static ProductoDAO instanceProductoDAO;

    private ProductoDAO() {
    }

    public static ProductoDAO getInstanceDAO() {
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
            em.flush();

            if (producto.getId() == null) {
                throw new PersistenciaException("Error: No se generó un ID para el producto.");
            }

            if (producto.getProductos().isEmpty()) {
                throw new PersistenciaException("Error: No se puede registrar un producto sin ingredientes.");
            }

            em.getTransaction().commit();
            return producto;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
            throw new PersistenciaException("El producto no se pudo registrar" + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public void deshabilitarProducto(Producto producto) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            if (producto == null) {
                throw new PersistenciaException("Producto no existente");
            }

            if (producto.getEstadoProducto() == EstadoProducto.DESHABILITADO) {
                throw new PersistenciaException("El producto ya está deshabilitado");
            }

            producto.setEstadoProducto(EstadoProducto.DESHABILITADO);
            em.getTransaction().begin();
            em.merge(producto);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
            throw new PersistenciaException("El producto no se pudo deshabilitar" + e.getMessage());
        } finally {
            em.close();
        }

    }

    @Override
    public void habilitarProducto(Producto producto) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            if (producto == null) {
                throw new PersistenciaException("Producto no existente");
            }

            if (producto.getEstadoProducto() == EstadoProducto.HABILITADO) {
                throw new PersistenciaException("El producto ya está habilitado");
            }

            producto.setEstadoProducto(EstadoProducto.HABILITADO);
            em.getTransaction().begin();
            em.merge(producto);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
            throw new PersistenciaException("El producto no se pudo deshabilitar " + e.getMessage());
        } finally {
            em.close();
        }

    }


    @Override
    public Producto actualizarProducto(Producto producto) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            Producto actualizado = em.merge(producto);
            em.getTransaction().commit();
            return actualizado;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
            throw new PersistenciaException("No se pudo actualizar el producto: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<ProductoOcupaIngrediente> obtenerIngredientesDeUnProducto(String nombreProducto) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery(
                    "SELECT poi "
                    + // Selecciona la entidad completa, no campos sueltos
                    "FROM ProductoOcupaIngrediente poi "
                    + "JOIN poi.ingrediente i "
                    + "JOIN poi.producto p "
                    + "WHERE p.nombre = :nombreProducto",
                    ProductoOcupaIngrediente.class)
                    .setParameter("nombreProducto", nombreProducto)
                    .getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar los ingredientes del producto: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<IngredienteProductoDTO> obtenerIngredientesDeUnProductoDTO(String nombreProducto) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        List<IngredienteProductoDTO> ingredientes = new ArrayList<>();

        try {
            List<Object[]> resultados = em.createQuery(
                    "SELECT i.nombre, poi.cantidadRequerida, i.unidadMedida "
                    + "FROM ProductoOcupaIngrediente poi "
                    + "JOIN poi.ingrediente i "
                    + "JOIN poi.producto p "
                    + "WHERE p.nombre = :nombreProducto", Object[].class)
                    .setParameter("nombreProducto", nombreProducto)
                    .getResultList();

            for (Object[] row : resultados) {
                IngredienteProductoDTO ingredienteProductoDTO = new IngredienteProductoDTO(
                        (String) row[0], // nombre del ingrediente
                        (Double) row[1], // cantidad requerida
                        ((UnidadMedida) row[2]).name() // unidad de medida
                );
                ingredientes.add(ingredienteProductoDTO);
            }

        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar los ingredientes de el producto " + e.getMessage());
        } finally {
            em.close();
        }
        return ingredientes;
    }

    @Override
    public Producto obtenerProductoPorNombre(String nombre) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT p FROM Producto p WHERE p.nombre = :nombre", Producto.class)
                    .setParameter("nombre", nombre)
                    .getSingleResult();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar productos por nombre: " + e.getMessage());
        } finally {
            em.close();
        }

    }

    @Override
    public Producto buscarProductoPorId(Long idProducto) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {

            if (idProducto == null) {
                throw new PersistenciaException("ID no puede ser nulo");
            }
            em.getTransaction().begin();
            Producto producto = em.find(Producto.class, idProducto);

            if (producto == null) {
                throw new PersistenciaException("Producto no encontrado");
            }

            return producto;
        } catch (Exception e) {
            throw new PersistenciaException("No se pudo obtener el producto con el id " + idProducto + e.getMessage());
        } finally {
            em.close();
        }
    }
    

    //METODOS PARA OBTENER ENTIDADES PRODUCTO EN EL MODULO DE COMANDAS------------------------------------------------------------------------------------
    @Override
    public List<Producto> obtenerProductosPorNombreEnComandas(String nombre) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT p FROM Producto p WHERE p.nombre LIKE :nombre AND p.estadoProducto = :estado", Producto.class)
                    .setParameter("nombre", "%" + nombre + "%")
                    .setParameter("estado", EstadoProducto.HABILITADO)
                    .getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar productos por nombre: " + e.getMessage());
        } finally {
            em.close();
        }

    }

    @Override
    public List<Producto> obtenerProductoPorNombreYTipoEnComandas(String nombre, TipoPlatillo tipo) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT p FROM Producto p WHERE p.nombre LIKE :nombre AND p.tipo = :tipo AND p.estadoProducto = :estado", Producto.class)
                    .setParameter("nombre", "%" + nombre + "%")
                    .setParameter("tipo", tipo)
                    .setParameter("estado", EstadoProducto.HABILITADO)
                    .getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar productos por nombre: " + e.getMessage());
        } finally {
            em.close();
        }

    }

    @Override
    public List<Producto> obtenerProductosPorTipoEnComandas(TipoPlatillo tipo) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT p FROM Producto p WHERE p.tipo = :tipo AND p.estadoProducto = :estado", Producto.class)
                    .setParameter("tipo", tipo)
                    .setParameter("estado", EstadoProducto.HABILITADO)
                    .getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar productos por nombre: " + e.getMessage());
        } finally {
            em.close();
        }

    }

    @Override
    public List<Producto> obtenerProductosEnComandas() throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT p FROM Producto p WHERE p.estadoProducto = :estado", Producto.class)
                    .setParameter("estado", EstadoProducto.HABILITADO)
                    .getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar productos" + e.getMessage());
        } finally {
            em.close();
        }

    }

    //METODOS PARA OBTENER ENTIDADES PRODUCTO EN EL MODULO DE CLIENTES------------------------------------------------------------------------------------
    @Override
    public List<Producto> obtenerProductos() throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT p FROM Producto p ", Producto.class)
                    .getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar productos" + e.getMessage());
        } finally {
            em.close();
        }

    }

    @Override
    public List<Producto> obtenerProductosPorNombre(String nombre) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT p FROM Producto p WHERE p.nombre LIKE :nombre ", Producto.class)
                    .setParameter("nombre", "%" + nombre + "%")
                    .getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar productos por nombre: " + e.getMessage());
        } finally {
            em.close();
        }

    }

    @Override
    public List<Producto> obtenerProductoPorNombreYTipo(String nombre, TipoPlatillo tipo) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT p FROM Producto p WHERE p.nombre LIKE :nombre AND p.tipo = :tipo ", Producto.class)
                    .setParameter("nombre", "%" + nombre + "%")
                    .setParameter("tipo", tipo)
                    .getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar productos por nombre: " + e.getMessage());
        } finally {
            em.close();
        }

    }

    @Override
    public List<Producto> obtenerProductosPorTipo(TipoPlatillo tipo) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT p FROM Producto p WHERE p.tipo = :tipo", Producto.class)
                    .setParameter("tipo", tipo)
                    .getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar productos por nombre: " + e.getMessage());
        } finally {
            em.close();
        }

    }
    
}
