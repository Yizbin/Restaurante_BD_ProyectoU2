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
 * @author sonic
 */
public class ClienteFrecuenteDAO implements IClienteFrecuenteDAO{

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
            Conexion.cerrarConexion();
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
            throw new PersistenciaException("No se pudo obtener el cliente con el id " + idCliente + e.getMessage());
        } finally {
            Conexion.cerrarConexion();
        }
    }
    
    @Override
    public List<ClienteFrecuente> buscarClientesPorNombre(String nombre) throws PersistenciaException {

        EntityManager em = Conexion.crearConexion();

        try {
            if (nombre == null) {
                throw new PersistenciaException("El nombre no puede ser nulo");
            }

            return em.createQuery(
                    "SELECT c FROM ClienteFrecuentes c WHERE"
                    + "LOWER(c.nombre) LIKE LOWER(:termino) OR"
                    + "LOWER(c.apellidoPaterno) LIKE LOWER(:termino) OR"
                    + "LOWER(c.apellidoMaterno) LIKE LOWER(:termino)")
                    .setParameter("nombre", nombre)
                    .getResultList();

        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar los clientes por nombre" + e.getMessage());
        } finally {
            Conexion.cerrarConexion();
        }
    }
    
    @Override
    public List<ClienteFrecuente> buscarClientesPorTelefono(String telefono) throws PersistenciaException {

        EntityManager em = Conexion.crearConexion();

        try {
            if (telefono == null) {
                throw new PersistenciaException("El telefono no puede ser nulo");
            }

            return em.createQuery(
                    "SELECT c FROM ClienteFrecuentes c WHERE"
                    + "c.telefono = :telefono")
                    .setParameter("telefono", telefono)
                    .getResultList();

        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar los clientes por nombre" + e.getMessage());
        } finally {
            Conexion.cerrarConexion();
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
            return false;
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
            return false;
        }

    }
    
    @Override
    public ClienteFrecuente obtenerClienteFrecuentePorId(Long id) {

        EntityManager em = Conexion.crearConexion();

        return em.find(ClienteFrecuente.class, id);
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

            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }

    }
    
    @Override
    public boolean incrementarVisitas(Long id ) {
        
        EntityManager em = Conexion.crearConexion();
        
        try {
            ClienteFrecuente clienteF = obtenerClienteFrecuentePorId(id);
            if (clienteF == null) {
                return false;
            }
            
            Integer visitasActuales = clienteF.getVisitas();
            clienteF.setVisitas(visitasActuales + 1);
            
            em.getTransaction().begin();
            em.merge(clienteF);
            em.getTransaction().commit();
            return true;
        } catch ( Exception e) {
            if ( em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

}
