/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moduloIngredientesDAO;

import Conexion.Conexion;
import Enums.Estado;
import Enums.UnidadMedida;
import Exception.PersistenciaException;
import ModuloIngredientesEntidades.Ingrediente;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Abraham Coronel Bringas
 */
public class IngredienteDAO implements IingredienteDAO {

    private static IngredienteDAO instanceIngredienteDAO;

    private IngredienteDAO() {
    }

    public static IngredienteDAO getInstanceDAO() {
        if (instanceIngredienteDAO == null) {
            instanceIngredienteDAO = new IngredienteDAO();
        }
        return instanceIngredienteDAO;
    }

    @Override
    public Ingrediente registrarIngrediente(Ingrediente ingrediente) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            if (existeIngrediente(ingrediente.getNombre(), ingrediente.getUnidadMedida())) {
                throw new PersistenciaException("El ingrediente con el mismo nombre y unidad de medida ya existe");
            }

            em.getTransaction().begin();
            em.persist(ingrediente);
            em.getTransaction().commit();

            if (ingrediente.getId() == null) {
                throw new PersistenciaException("No se encontro un id para el ingrediente.");
            }

            return ingrediente;

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("No se pudo registrar el ingrediente");
        } finally {
            em.close();
        }
    }

    @Override
    public Ingrediente actualizarIngrediente(Ingrediente ingrediente) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            Ingrediente actualizado = em.merge(ingrediente);
            em.getTransaction().commit();
            return actualizado;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("No se pudo actualizar el ingrediente: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public boolean eliminarIngrediente(Long id) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            if (comandaActivaConIngrediente(id)) {
                throw new PersistenciaException("No se puede eliminar el ingrediente porque esta siendo usada en una comanda activa");
            }
            Ingrediente ingrediente = em.find(Ingrediente.class, id);
            if (ingrediente == null) {
                throw new PersistenciaException("No se encontro el ingrediente con id: " + id);
            }
            em.getTransaction().begin();
            em.remove(ingrediente);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("No se pudo eliminar el ingrediente: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Ingrediente> buscarPorTodos() throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT i FROM Ingrediente i", Ingrediente.class).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar todos los ingredientes: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public boolean existeIngrediente(String nombre, UnidadMedida unidadMedida) {
        String consulta = "SELECT COUNT(i) FROM Ingrediente i WHERE i.nombre = :nombre AND i.unidadMedida = :unidadMedida";
        Long count = Conexion.crearConexion().createQuery(consulta, Long.class)
                .setParameter("nombre", nombre)
                .setParameter("unidadMedida", unidadMedida)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public boolean comandaActivaConIngrediente(Long idIngrediente) {
        String consulta = "SELECT COUNT(c) FROM Comanda c "
                + "JOIN c.detalles d "
                + "JOIN d.producto prod "
                + "JOIN prod.productos po "
                + "JOIN po.ingrediente i "
                + "WHERE i.id = :idIngrediente AND c.estado = :estado";

        Long count = Conexion.crearConexion().createQuery(consulta, Long.class).setParameter("idIngrediente", idIngrediente).setParameter("estado", Estado.ABIERTA).getSingleResult();
        return count > 0;

    }

    @Override
    public void actualizarStock(Long id, int nuevoStock) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            Ingrediente ingrediente = em.find(Ingrediente.class, id);
            if (ingrediente == null) {
                throw new PersistenciaException("No se encontro el ingrediente con id: " + id);
            }
            ingrediente.setStock(nuevoStock);
            em.merge(ingrediente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("No se pudo actualizar el stock del ingrediente: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Ingrediente buscarPorNombre(String nombre, UnidadMedida unidadMedida) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT i FROM Ingrediente i WHERE i.nombre LIKE :nombre", Ingrediente.class)
                    .setParameter("nombre", "%" + nombre + "%")
                    .getSingleResult();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar Ingrediente por nombre: " + e.getMessage());
        } finally {
            em.close();
        }
    }

}
