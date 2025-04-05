/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moduloClientesDAO;

import Conexion.Conexion;
import Exception.PersistenciaException;
import ModuloClientesEntidades.ClienteFrecuente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author isaac
 */
public class ClienteFrecuenteDAO implements IClienteFrecuenteDAO {

    private static ClienteFrecuenteDAO instanceClienteFrecuenteDAO;

    private ClienteFrecuenteDAO() {

    }

    public static ClienteFrecuenteDAO getInstance() {
        if (instanceClienteFrecuenteDAO == null) {
            instanceClienteFrecuenteDAO = new ClienteFrecuenteDAO();
        }
        return instanceClienteFrecuenteDAO;
    }

    @Override
    public ClienteFrecuente registrarClienteFrecuente(ClienteFrecuente clienteF) throws PersistenciaException {

        EntityManager em = Conexion.crearConexion();
        try {

            if (clienteF == null) {
                throw new PersistenciaException("El cliente no puede ser nulo");
            }
            if (clienteF.getNombre() == null) {
                throw new PersistenciaException("No se puede registrar un cliente sin nombre");
            }
            if (clienteF.getApellidoMaterno() == null) {
                throw new PersistenciaException("No se puede registrar un cliente sin apellido materno");
            }
            if (clienteF.getApellidoPaterno() == null) {
                throw new PersistenciaException("No se puede registrar un cliente sin apellido paterno");
            }

            if (clienteF.getTelefono() == null) {
                throw new PersistenciaException("No se puede registrar un cliente sin telefono");
            }

            em.getTransaction().begin();
            em.persist(clienteF);
            em.getTransaction().commit();

            if (clienteF.getId() == null) {
                throw new PersistenciaException("No se encontro un id para el clienteF.");
            }

            return clienteF;

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("No se pudo registrar el cliente frecuente");
        } finally {
            em.close();
        }

    }

    @Override
    public ClienteFrecuente buscarClientePorID(Long idCliente) throws PersistenciaException {

        EntityManager em = Conexion.crearConexion();
        try {

            if (idCliente == null) {
                throw new PersistenciaException("ID no puede ser nulo");
            }
            em.getTransaction().begin();
            ClienteFrecuente clienteF = em.find(ClienteFrecuente.class, idCliente);

            if (clienteF == null) {
                throw new PersistenciaException("cliente no encontrado");
            }

            return clienteF;
        } catch (Exception e) {
            throw new PersistenciaException("error al verificar si existe cliente por telelfono" + e.getMessage());
        } finally {
            em.close();

        }
    }

    @Override
    public List<ClienteFrecuente> buscarClientesPorNombre(String nombre) throws PersistenciaException {

        EntityManager em = Conexion.crearConexion();

        try {
            if (nombre == null) {
                throw new PersistenciaException("El nombre no puede ser nulo");
            }
            String jpql = "SELECT c FROM ClienteFrecuente c "
                    + "WHERE LOWER(c.nombre) LIKE LOWER(:termino)"
                    + "OR LOWER(c.apellidoPaterno) LIKE LOWER(:termino)"
                    + "OR LOWER(c.apellidoMaterno) LIKE LOWER(:termino)";

            return em.createQuery(jpql, ClienteFrecuente.class)
                    .setParameter("termino", "%" + nombre.toLowerCase() + "%")
                    .getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar los clientes por nombre" + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<ClienteFrecuente> buscarClientesPorTelefono(String telefono) throws PersistenciaException {

        EntityManager em = Conexion.crearConexion();

        try {
            if (telefono == null) {
                throw new PersistenciaException("El telefono no puede ser nulo");
            }

            String jpql = "SELECT c FROM ClienteFrecuente c WHERE c.telefono = :telefono";

            return em.createQuery(jpql, ClienteFrecuente.class)
                    .setParameter("telefono", telefono)
                    .getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar los clientes por nombre" + e.getMessage());
        } finally {
            em.close();
        }

    }

    @Override
    public boolean existeClienteFrecuentePorTelefono(String telefono) throws PersistenciaException {

        EntityManager em = Conexion.crearConexion();

        if (telefono == null) {
            return false;
        }

        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(c) FROM ClienteFrecuente c WHERE c.telefono = :telefono", Long.class);
            query.setParameter("telefono", telefono);
            return query.getSingleResult() > 0;
        } catch (Exception e) {
            throw new PersistenciaException("error al verificar si existe cliente por telelfono" + e.getMessage());
        } finally {
            em.close();
        }

    }

    @Override
    public boolean existeClienteFrecuentePorNombre(String nombre) throws PersistenciaException {

        EntityManager em = Conexion.crearConexion();

        if (nombre == null) {
            return false;
        }

        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(c) FROM ClienteFrecuente c WHERE c.nombre = :nombre", Long.class);
            query.setParameter("nombre", nombre);
            return query.getSingleResult() > 0;
        } catch (Exception e) {
            throw new PersistenciaException("error al verificar si existe cliente por telelfono" + e.getMessage());
        } finally {
            em.close();
        }

    }

    @Override
    public ClienteFrecuente obtenerClienteFrecuentePorId(Long id) throws PersistenciaException {

        EntityManager em = Conexion.crearConexion();

        try {
            return em.find(ClienteFrecuente.class, id);

        } catch (Exception e) {
            throw new PersistenciaException("error al verificar si existe cliente por telelfono" + e.getMessage());
        } finally {
            em.close();
        }

    }

    @Override
    public boolean actualizarPuntosYGasto(Long idCliente, Double gastoNuevo, Integer puntosNuevos) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();

        try {
            ClienteFrecuente cliente = obtenerClienteFrecuentePorId(idCliente);

            if (cliente == null) {
                return false;
            }

            Double gastoActual = cliente.getGastoAcumulado();
            Integer puntosActuales = cliente.getPuntos();

            cliente.setGastoAcumulado(gastoActual + gastoNuevo);
            cliente.setPuntos(puntosNuevos + puntosActuales);

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();
            return true;

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException(" no se pudo actualizar puntos y gasto del cliente" + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public boolean incrementarVisitas(Long id) throws PersistenciaException {

        EntityManager em = Conexion.crearConexion();

        try {
            em.getTransaction().begin();
            ClienteFrecuente clienteF = em.find(ClienteFrecuente.class, id);
            if (clienteF == null) {
                em.getTransaction().rollback();
                return false;
            }
            clienteF.setVisitas(clienteF.getVisitas()+ 1);
            em.merge(clienteF);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al incrementar visitas" + e.getMessage());
        } finally {
            em.close();

        }
    }

    @Override
    public List<ClienteFrecuente> obtenerTodosClientesFrecuentes() throws PersistenciaException {

        EntityManager em = Conexion.crearConexion();
        
        try {
            em.getTransaction().begin();
            TypedQuery<ClienteFrecuente> query = em.createQuery("SELECT c FROM ClienteFrecuente c", ClienteFrecuente.class);
            
            List<ClienteFrecuente> resultados =  query.getResultList();
            em.getTransaction().commit();
            return resultados;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al obtener clientes: " + e.getMessage());
        } finally {
            em.close();
        }
    }

}
