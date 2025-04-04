/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package moduloIngredientesDAO;

import Enums.UnidadMedida;
import Exception.PersistenciaException;
import ModuloIngredientesEntidades.Ingrediente;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Abraham Coronel Bringas
 */
public class IngredienteDAOTest {

    private IngredienteDAO dao;

    public IngredienteDAOTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        dao = IngredienteDAO.getInstanceDAO();
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getInstanceDAO method, of class IngredienteDAO.
     */
    @Test
    public void testGetInstanceDAO() {
        IngredienteDAO instancia = IngredienteDAO.getInstanceDAO();
        assertNotNull(instancia, "La instancia del DAO no debe ser nula");
    }

    /**
     * Test of registrarIngrediente method, of class IngredienteDAO.
     */
    @Test
    public void testRegistrarIngrediente() throws PersistenciaException {
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre("IngredienteTestRegistrar");
        ingrediente.setUnidadMedida(UnidadMedida.PIEZAS);
        ingrediente.setStock(100);

        Ingrediente registrado = dao.registrarIngrediente(ingrediente);
        assertNotNull(registrado.getId(), "El id del ingrediente registrado no debe ser nulo");
    }

    /**
     * Test of actualizarIngrediente method, of class IngredienteDAO.
     */
    @Test
    public void testActualizarIngrediente() throws PersistenciaException {
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre("IngredienteActualizar");
        ingrediente.setUnidadMedida(UnidadMedida.PIEZAS);
        ingrediente.setStock(50);
        Ingrediente registrado = dao.registrarIngrediente(ingrediente);

        registrado.setStock(75);
        Ingrediente actualizado = dao.actualizarIngrediente(registrado);

        assertEquals(75, actualizado.getStock(), "El stock actualizado debe ser 75");
    }

    /**
     * Test of eliminarIngrediente method, of class IngredienteDAO.
     */
    @Test
    public void testEliminarIngrediente() throws PersistenciaException {
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre("IngredienteTestEliminar");
        ingrediente.setUnidadMedida(UnidadMedida.PIEZAS);
        ingrediente.setStock(10);
        Ingrediente registrado = dao.registrarIngrediente(ingrediente);

        boolean eliminado = dao.eliminarIngrediente(registrado.getId());
        assertTrue(eliminado, "El ingrediente debe eliminar correctamente");
    }

    /**
     * Test of buscarPorTodos method, of class IngredienteDAO.
     */
    @Test
    public void testBuscarPorTodos() throws PersistenciaException {
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre("IngredienteTestBuscarTodos");
        ingrediente.setUnidadMedida(UnidadMedida.PIEZAS);
        ingrediente.setStock(20);
        dao.registrarIngrediente(ingrediente);

        List<Ingrediente> lista = dao.buscarPorTodos();
        assertNotNull(lista, "La lista no debe ser nula");
        assertFalse(lista.isEmpty(), "La lista debe contener al menos un ingrediente");
    }

    /**
     * Test of comandaActivaConIngrediente method, of class IngredienteDAO.
     */
    @Test
    public void testComandaActivaConIngrediente() throws PersistenciaException {
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre("IngredienteTestComanda");
        ingrediente.setUnidadMedida(UnidadMedida.PIEZAS);
        ingrediente.setStock(40);
        Ingrediente registrado = dao.registrarIngrediente(ingrediente);

        boolean comandaActiva = dao.comandaActivaConIngrediente(registrado.getId());
        assertFalse(comandaActiva, "No deberia haber comanda activa para el ingrediente");
    }

    /**
     * Test of actualizarStock method, of class IngredienteDAO.
     */
    @Test
    public void testActualizarStock() throws Exception {
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre("IngredienteTestStock");
        ingrediente.setUnidadMedida(UnidadMedida.PIEZAS);
        ingrediente.setStock(20);
        Ingrediente registrado = dao.registrarIngrediente(ingrediente);

        int nuevoStock = 40;
        dao.actualizarStock(registrado.getId(), nuevoStock);

        List<Ingrediente> lista = dao.buscarPorTodos();
        Ingrediente actualizado = lista.stream()
                .filter(i -> i.getId().equals(registrado.getId()))
                .findFirst()
                .orElse(null);
        assertNotNull(actualizado, "El ingrediente debe existir en la base de datos");
        assertEquals(nuevoStock, actualizado.getStock(), "El stock debe hacerse actualizado a 40");
    }

}
