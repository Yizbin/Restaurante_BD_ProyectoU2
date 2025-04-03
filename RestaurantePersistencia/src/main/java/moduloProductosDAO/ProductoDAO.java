/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moduloProductosDAO;

import Conexion.Conexion;
import Enums.EstadoProducto;
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

            if (producto.getId() == null) {
                throw new PersistenciaException("Error: No se generó un ID para el producto.");
            }

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
            em.merge(producto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("El producto no se pudo deshabilitar");
        } finally {
            em.close();
            Conexion.cerrarConexion();
        }

    }

    @Override
    public List<Producto> obtenerProductos() throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT p FROM Producto p WHERE p.estadoProducto = :estado", Producto.class)
                    .setParameter("estado", EstadoProducto.HABILITADO)
                    .getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar productos" + e.getMessage());
        } finally {
            Conexion.cerrarConexion();
        }

    }

    @Override
    public List<Producto> obtenerProductosPorNombre(String nombre) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT p FROM Producto p WHERE p.nombre LIKE :nombre AND p.estadoProducto = :estado", Producto.class)
                    .setParameter("nombre", "%" + nombre + "%")
                    .setParameter("estado", EstadoProducto.HABILITADO)
                    .getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar productos por nombre: " + e.getMessage());
        } finally {
            Conexion.cerrarConexion();
        }

    }

    @Override
    public Producto obtenerProductoPorNombre(String nombre) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT p FROM Producto p WHERE p.nombre LIKE :nombre AND p.estadoProducto = :estado", Producto.class)
                    .setParameter("nombre", "%" + nombre + "%")
                    .setParameter("estado", EstadoProducto.HABILITADO)
                    .getSingleResult();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar productos por nombre: " + e.getMessage());
        } finally {
            Conexion.cerrarConexion();
        }

    }

    @Override
    public List<Producto> obtenerProductoPorNombreYTipo(String nombre, String tipo) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT p FROM Producto p WHERE p.nombre LIKE :nombre AND p.tipo LIKE :tipo p.estadoProducto = :estado", Producto.class)
                    .setParameter("nombre", "%" + nombre + "%")
                    .setParameter("tipo", "%" + tipo + "%")
                    .setParameter("estado", EstadoProducto.HABILITADO)
                    .getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar productos por nombre: " + e.getMessage());
        } finally {
            Conexion.cerrarConexion();
        }

    }

    @Override
    public List<Producto> obtenerProductosPorTipo(String tipo) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT p FROM Producto p WHERE p.tipo LIKE :tipo AND p.estadoProducto = :estado", Producto.class)
                    .setParameter("tipo", "%" + tipo + "%")
                    .setParameter("estado", EstadoProducto.HABILITADO)
                    .getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar productos por nombre: " + e.getMessage());
        } finally {
            Conexion.cerrarConexion();
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
            em.getTransaction().rollback();
            throw new PersistenciaException("No se pudo actualizar el producto: " + e.getMessage());
        } finally {
            Conexion.cerrarConexion();
        }
    }

    @Override
    public List<ProductoOcupaIngrediente> obtenerIngredientesDeUnProducto(String nombreProducto) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();

        try {
            return em.createQuery("SELECT i.nombre, poi.cantidadRequerida, i.unidadMedida "
                    + "FROM ProductoOcupaIngrediente poi "
                    + "JOIN poi.ingrediente i "
                    + "JOIN poi.producto p "
                    + "WHERE p.nombre = :nombreProducto", ProductoOcupaIngrediente.class)
                    .setParameter("nombreProducto", nombreProducto)
                    .getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar los ingredientes de el producto " + e.getMessage());
        } finally {
            Conexion.cerrarConexion();
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
                        (String) row[1], // nombre del ingrediente
                        (int) row[2], // cantidad requerida
                        (String) row[3] // unidad de medida
                );
                ingredientes.add(ingredienteProductoDTO);
            }

        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar los ingredientes de el producto " + e.getMessage());
        } finally {
            Conexion.cerrarConexion();
        }
        return ingredientes;
    }

    @Override
    public void agregarIngredienteAProducto(Long idProducto, Long idIngrediente,
            int cantidad) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();

            Producto producto = em.find(Producto.class, idProducto);
            Ingrediente ingrediente = em.find(Ingrediente.class, idIngrediente);

            if (producto == null || ingrediente == null) {
                throw new PersistenciaException("Producto o Ingrediente no encontrado");
            }

            if (cantidad <= 0) {
                throw new PersistenciaException("La cantidad debe ser mayor a cero");
            }

            boolean existeIngrediente = em.createQuery(
                    "SELECT COUNT(poi) FROM ProductoOcupaIngrediente poi "
                    + "WHERE poi.producto.id = :idProducto "
                    + "AND poi.ingrediente.id = :idIngrediente", Long.class)
                    .setParameter("idProducto", idProducto)
                    .setParameter("idIngrediente", idIngrediente)
                    .getSingleResult() > 0;

            if (existeIngrediente) {
                throw new PersistenciaException("El ingrediente ya esta asignado al producto");
            }

            ProductoOcupaIngrediente ingredienteProducto = new ProductoOcupaIngrediente();
            ingredienteProducto.setIngrediente(ingrediente);
            ingredienteProducto.setProducto(producto);
            ingredienteProducto.setCantidadRequerida(cantidad);

            // 3. Actualizar relaciones bidireccionales
            producto.getProductos().add(ingredienteProducto);
            em.persist(ingredienteProducto);

            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al asignar ingrediente " + e.getMessage());
        } finally {
            Conexion.cerrarConexion();
        }
    }

    @Override
    public void actualizarCantidadRequeridaDeUnIngrediente(Long idProducto, Long idIngrediente,
            int nuevaCantidad) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();

            Producto producto = em.find(Producto.class, idProducto);
            Ingrediente ingrediente = em.find(Ingrediente.class, idIngrediente);

            if (producto == null || ingrediente == null) {
                throw new PersistenciaException("Producto o Ingrediente no encontrado");
            }

            if (nuevaCantidad <= 0) {
                throw new PersistenciaException("La cantidad debe ser mayor a cero");
            }

            ProductoOcupaIngrediente ingredienteSeleccionado = em.createQuery(
                    "SELECT poi FROM ProductoOcupaIngrediente poi "
                    + "WHERE poi.producto.id = :idProducto "
                    + "AND poi.ingrediente.id = :idIngrediente", ProductoOcupaIngrediente.class)
                    .setParameter("idProducto", idProducto)
                    .setParameter("idIngrediente", idIngrediente)
                    .getSingleResult();

            ingredienteSeleccionado.setCantidadRequerida(nuevaCantidad);

            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al actualizar cantidad requerida " + e.getMessage());
        } finally {
            Conexion.cerrarConexion();
        }

    }

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
            Conexion.cerrarConexion();
        }
    }

}
