/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package moduloClientesDAO;

import Conexion.Conexion;
import Exception.PersistenciaException;
import ModuloClientesEntidades.ClienteFrecuente;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author isaac
 */

public class ClienteFrecuenteDAOTest {

    private ClienteFrecuenteDAO clienteDAO;
    private ClienteFrecuente clienteF;
    private EntityManager em;
    private Long clienteId;

    public ClienteFrecuenteDAOTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws PersistenciaException {
        this.clienteDAO = ClienteFrecuenteDAO.getInstance();

        Date fechaActual = new Date();
        ClienteFrecuente clienteF = new ClienteFrecuente(0, 0D, 0, "Jaime", "Beltran", "Panduro", "6441234567", "jaime@gmail.com", fechaActual);
        
        em = Conexion.crearConexion();
        try {
            em.getTransaction();
            em.persist(clienteF);
            em.getTransaction().commit();
            
            // asignar id
            clienteId = clienteF.getId();
            assertNotNull(clienteId, "El cliente debe tener un id asignado");
        } catch (Exception e) {
            if(em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al preparar los datos de prueba" + e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        
        if ( em != null && em.isOpen()) {
            try {
                em.getTransaction().begin();
                ClienteFrecuente cliente = em.find(ClienteFrecuente.class, clienteId);
                if( cliente != null ){
                    em.remove(cliente);
                }
                em.getTransaction().commit();
            } catch (Exception e) {
                if ( em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
            } finally {
                        em.close();
                        }
            }
        }
    

    /**
     * Test of getInstance method, of class ClienteFrecuenteDAO.
     */
    @Test
    public void testGetInstance() {

        ClienteFrecuenteDAO instancia = ClienteFrecuenteDAO.getInstance();

    }

    /**
     * Test of registrarClienteFrecuente method, of class ClienteFrecuenteDAO.
     */
    @Test
    public void testRegistrarClienteFrecuente() throws Exception {

        Date fechaN = new Date();
        ClienteFrecuente clienteNuevo = new ClienteFrecuente(0,0D,0, "Fernando", "Flores", "Florentino", "541233342", "fernan@gmail.com", fechaN);
        
        ClienteFrecuente clienteRegistrado = clienteDAO.registrarClienteFrecuente(clienteNuevo);
        
        assertNotNull(clienteRegistrado, "El id del cliente registrado no debe ser nulo");
        assertNotNull(clienteRegistrado.getId(), "El id del cliente registrado no debe ser nulo");
        
        try {
        em.getTransaction().begin();
        ClienteFrecuente clienteToRemove = em.find(ClienteFrecuente.class, clienteRegistrado.getId());
        if (clienteToRemove != null) {
            em.remove(clienteToRemove);
        }
        em.getTransaction().commit();
    } finally {
            em.close();
        }
    }

    /**
     * Test of buscarClientePorID method, of class ClienteFrecuenteDAO.
     */
    @Test
    public void testBuscarClientePorID() throws Exception {
        System.out.println("buscarClientePorID");

        

        ClienteFrecuente clienteBuscar = clienteDAO.buscarClientePorID(clienteId);
        
        assertNotNull(clienteBuscar,"El cliente no puede ser nulo");
        assertEquals(clienteBuscar.getId(), clienteId, "El ID del cliente debe coincidir");
        assertEquals(clienteBuscar.getNombre(), clienteF.getNombre(), "El nombre debe coincidir");

    }

    /**
     * Test of buscarClientesPorNombre method, of class ClienteFrecuenteDAO.
     */
    @Test
    public void testBuscarClientesPorNombre() throws Exception {

        System.out.println("Buscar cliente por nombre");

       
        List<ClienteFrecuente> clientesEncontrados = clienteDAO.buscarClientesPorNombre(clienteF.getNombre());

        assertFalse(clientesEncontrados.isEmpty(), "La lista de clientes no debe ser vacia ");
        boolean encontrado = false;
        for (ClienteFrecuente cliente : clientesEncontrados) {
            if (cliente.getNombre().equals(clienteF.getNombre())) {

                encontrado = true;
                break;
            }
        }
        assertTrue(encontrado, "El cliente esta en la lista");

    }

    /**
     * Test of buscarClientesPorTelefono method, of class ClienteFrecuenteDAO.
     */
    @Test
    public void testBuscarClientesPorTelefono() throws Exception {
        System.out.println("buscarClientesPorTelefono");

        
        List<ClienteFrecuente> clientesEncontrados = clienteDAO.buscarClientesPorTelefono(clienteF.getTelefono());

        assertFalse(clientesEncontrados.isEmpty(), "La lista de clientes no debe ser vacia ");
        boolean encontrado = false;
        for (ClienteFrecuente cliente : clientesEncontrados) {
            if (cliente.getTelefono().equals(clienteF.getTelefono())) {

                encontrado = true;
                break;
            }
        }
        assertTrue(encontrado, "El cliente esta en la lista");

    }

    /**
     * Test of existeClienteFrecuentePorTelefono method, of class
     * ClienteFrecuenteDAO.
     */
    @Test
    public void testExisteClienteFrecuentePorTelefono() throws Exception {
        System.out.println("existeClienteFrecuentePorTelefono");

        boolean existe = clienteDAO.existeClienteFrecuentePorTelefono(clienteF.getTelefono());

        assertTrue(existe, "Debe existir un cliente con el numero de telefono de prueba");
    }

    /**
     * Test of existeClienteFrecuentePorNombre method, of class
     * ClienteFrecuenteDAO.
     */
    @Test
    public void testExisteClienteFrecuentePorNombre() throws Exception {
        System.out.println("existeClienteFrecuentePorNombre");

        boolean existe = clienteDAO.existeClienteFrecuentePorNombre(clienteF.getNombre());

        assertTrue(existe, "Debe existir un cliente con el numero de telefono de prueba");
    }

    /**
     * Test of obtenerClienteFrecuentePorId method, of class
     * ClienteFrecuenteDAO.
     */
    @Test
    public void testObtenerClienteFrecuentePorId() throws PersistenciaException {
        System.out.println("obtenerClienteFrecuentePorId");

        ClienteFrecuente clienteRegistrado = clienteDAO.registrarClienteFrecuente(clienteF);
        Long idCliente = clienteRegistrado.getId();

        ClienteFrecuente clienteEncontrado = clienteDAO.obtenerClienteFrecuentePorId(idCliente);

        assertNotNull(clienteEncontrado, "el cliente no puede ser nulo");
        assertEquals(idCliente, clienteEncontrado.getId(), "los id deben de coincidir");

    }

    /**
     * Test of actualizarPuntosYGasto method, of class ClienteFrecuenteDAO.
     */
    @Test
    public void testActualizarPuntosYGasto() throws PersistenciaException {
        System.out.println("actualizarPuntosYGasto");

        ClienteFrecuente clienteRegistrado = clienteDAO.registrarClienteFrecuente(clienteF);
        Long idCliente = clienteRegistrado.getId();
        Double gastoNuevo = 100.0;
        Integer puntosNuevos = 40;

        boolean actualiza = clienteDAO.actualizarPuntosYGasto(idCliente, gastoNuevo, puntosNuevos);
        ClienteFrecuente clienteActualizado = clienteDAO.obtenerClienteFrecuentePorId(idCliente);

        assertTrue(actualiza, "La actualizacion debe ser exitosa");
        assertEquals(clienteActualizado.getGastoAcumulado(), gastoNuevo, "La actualizacion debe ser exitosa");
        assertEquals(clienteActualizado.getPuntos(), puntosNuevos, "Los puntos deben ser actualizados");
    }

    /**
     * Test of incrementarVisitas method, of class ClienteFrecuenteDAO.
     */
    @Test
    public void testIncrementarVisitas() throws PersistenciaException {
        
        ClienteFrecuente clienteRegistrado = clienteDAO.registrarClienteFrecuente(clienteF);
        Long idCliente = clienteRegistrado.getId();
        Integer visitasIniciales = clienteRegistrado.getVisitas();
        
        boolean incremento = clienteDAO.incrementarVisitas(idCliente);
        ClienteFrecuente clienteActualizado = clienteDAO.obtenerClienteFrecuentePorId(idCliente);
        
        assertTrue(incremento, "El incremento debe ser exitoso");
        assertEquals(Integer.valueOf(visitasIniciales + 1), clienteActualizado.getVisitas());
    }

    @Test
    public void testObtenerTodosClientesFrecuentes() throws PersistenciaException {

        clienteDAO.registrarClienteFrecuente(clienteF);

        List<ClienteFrecuente> listaClientes = clienteDAO.obtenerTodosClientesFrecuentes();

        assertNotNull(listaClientes, "Lista de clientes no puede ser nula");
        assertFalse(listaClientes.isEmpty(), "Lista de clientes no puede estar vacia");
    }
}
