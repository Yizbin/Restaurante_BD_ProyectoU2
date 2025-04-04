/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
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
import java.lang.reflect.Field;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author sonic
 */
public class ProductoDAOTest {

    private ProductoDAO productoDAO;
    private Ingrediente ingredienteHarina;
    private Ingrediente ingredienteCebolla;

    @BeforeEach
    void setUp() throws Exception {
        resetSingleton();
        limpiarBaseDeDatos();
        inicializarIngredientes();
    }

    private void resetSingleton() throws Exception {
        Field instance = ProductoDAO.class.getDeclaredField("instanceProductoDAO");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    private void limpiarBaseDeDatos() {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM ProductoOcupaIngrediente").executeUpdate();
            em.createQuery("DELETE FROM Producto").executeUpdate();
            em.createQuery("DELETE FROM Ingrediente").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private void inicializarIngredientes() {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();

            // Ingrediente 1: Harina
            ingredienteHarina = new Ingrediente();
            ingredienteHarina.setNombre("Harina");
            ingredienteHarina.setStock(100);
            ingredienteHarina.setUnidadMedida(UnidadMedida.GRAMOS);
            em.persist(ingredienteHarina);

            // Ingrediente 2: Cebolla
            ingredienteCebolla = new Ingrediente();
            ingredienteCebolla.setNombre("Cebolla");
            ingredienteCebolla.setStock(50);
            ingredienteCebolla.setUnidadMedida(UnidadMedida.GRAMOS);
            em.persist(ingredienteCebolla);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private Producto crearProductoHabilitado() {
        Producto producto = new Producto();
        producto.setNombre("Pan");
        producto.setPrecio(25.0);
        producto.setTipo(TipoPlatillo.PLATO_FUERTE);
        producto.setEstadoProducto(EstadoProducto.HABILITADO);
        producto.setDescripcion("Pan fresco");

        ProductoOcupaIngrediente poi = new ProductoOcupaIngrediente();
        poi.setIngrediente(ingredienteHarina);
        poi.setCantidadRequerida(200.0);
        poi.setProducto(producto);
        producto.getProductos().add(poi);

        return producto;
    }

    private Producto crearProductoBase() {
        Producto producto = new Producto();
        producto.setNombre("Tostada de carne");
        producto.setPrecio(50.0);
        producto.setTipo(TipoPlatillo.PLATO_FUERTE);
        producto.setEstadoProducto(EstadoProducto.HABILITADO);
        producto.setDescripcion("Tostada con carne molida");
        return producto;
    }

    private Producto crearProductoConUnIngrediente() {
        Producto producto = crearProductoBase();

        ProductoOcupaIngrediente poiHarina = new ProductoOcupaIngrediente();
        poiHarina.setProducto(producto);
        poiHarina.setIngrediente(ingredienteHarina);
        poiHarina.setCantidadRequerida(200.0);
        producto.getProductos().add(poiHarina);

        return producto;
    }

    @Test
    void testRegistrarProducto_Exitoso() throws PersistenciaException {
        Producto producto = crearProductoHabilitado();
        productoDAO = ProductoDAO.getInstanceDAO();

        Producto resultado = productoDAO.registrarProducto(producto);

        assertNotNull(resultado.getId());
        assertFalse(resultado.getProductos().isEmpty());
    }

    @Test
    void testRegistrarProducto_SinIngredientes_LanzaExcepcion() {
        Producto producto = crearProductoHabilitado();
        producto.getProductos().clear();
        productoDAO = ProductoDAO.getInstanceDAO();

        assertThrows(PersistenciaException.class, () -> {
            productoDAO.registrarProducto(producto);
        });
    }

    @Test
    void testDeshabilitarProducto_Exitoso() throws PersistenciaException {
        productoDAO = ProductoDAO.getInstanceDAO();
        Producto producto = productoDAO.registrarProducto(crearProductoHabilitado());

        productoDAO.deshabilitarProducto(producto);
        Producto obtenido = productoDAO.buscarProductoPorId(producto.getId());

        assertEquals(EstadoProducto.DESHABILITADO, obtenido.getEstadoProducto());
    }

    @Test
    void testHabilitarProducto_Exitoso() throws PersistenciaException {
        productoDAO = ProductoDAO.getInstanceDAO();
        Producto producto = crearProductoHabilitado();
        producto.setEstadoProducto(EstadoProducto.DESHABILITADO);
        producto = productoDAO.registrarProducto(producto);

        productoDAO.habilitarProducto(producto);
        Producto obtenido = productoDAO.buscarProductoPorId(producto.getId());

        assertEquals(EstadoProducto.HABILITADO, obtenido.getEstadoProducto());
    }

    @Test
    void testObtenerProductoPorNombre_Exitoso() throws PersistenciaException {
        productoDAO = ProductoDAO.getInstanceDAO();
        Producto producto = productoDAO.registrarProducto(crearProductoHabilitado());

        Producto obtenido = productoDAO.obtenerProductoPorNombre("Pan");

        assertEquals(producto.getId(), obtenido.getId());
    }

    @Test
    void testActualizarProducto_Exitoso() throws PersistenciaException {
        productoDAO = ProductoDAO.getInstanceDAO();
        Producto producto = productoDAO.registrarProducto(crearProductoHabilitado());
        producto.setPrecio(30.0);

        Producto actualizado = productoDAO.actualizarProducto(producto);

        assertEquals(30.0, actualizado.getPrecio());
    }

    @Test
    void testObtenerIngredientesDeUnProductoDTO_Exitoso() throws PersistenciaException {
        productoDAO = ProductoDAO.getInstanceDAO();
        productoDAO.registrarProducto(crearProductoHabilitado());

        List<IngredienteProductoDTO> ingredientes = productoDAO.obtenerIngredientesDeUnProductoDTO("Pan");

        assertFalse(ingredientes.isEmpty());
        assertEquals("Harina", ingredientes.get(0).getNombreIngrediente());
    }

    @Test
    void testObtenerProductosEnComandas_SoloHabilitados() throws PersistenciaException {
        productoDAO = ProductoDAO.getInstanceDAO();
        productoDAO.registrarProducto(crearProductoHabilitado());

        List<Producto> productos = productoDAO.obtenerProductosEnComandas();

        assertTrue(productos.stream().allMatch(p -> p.getEstadoProducto() == EstadoProducto.HABILITADO));
    }

    @Test
    void testActualizarProductoAgregarIngrediente_Exitoso() throws PersistenciaException {
        productoDAO = ProductoDAO.getInstanceDAO();

        // 1. Registrar producto inicial con 1 ingrediente
        Producto productoInicial = crearProductoConUnIngrediente();
        productoDAO.registrarProducto(productoInicial);

        // 2. Agregar nuevo ingrediente al producto
        Producto productoActualizado = productoDAO.obtenerProductoPorNombre("Tostada de carne");

        ProductoOcupaIngrediente poiCebolla = new ProductoOcupaIngrediente();
        poiCebolla.setProducto(productoActualizado);
        poiCebolla.setIngrediente(ingredienteCebolla);
        poiCebolla.setCantidadRequerida(50.0);
        productoActualizado.getProductos().add(poiCebolla);

        // 3. Actualizar el producto
        productoDAO.actualizarProducto(productoActualizado);

        // 4. Verificar resultados
        Producto productoVerificado = productoDAO.buscarProductoPorId(productoActualizado.getId());
        List<ProductoOcupaIngrediente> ingredientes = productoDAO.obtenerIngredientesDeUnProducto("Tostada de carne");

        assertEquals(2, productoVerificado.getProductos().size());
        assertTrue(ingredientes.stream()
                .anyMatch(poi -> poi.getIngrediente().getNombre().equals("Cebolla")));
    }

    @Test
    void testActualizarProductoEliminarIngrediente_Exitoso() throws PersistenciaException {
        productoDAO = ProductoDAO.getInstanceDAO();

        // 1. Crear producto con 2 ingredientes
        Producto producto = crearProductoConUnIngrediente();
        ProductoOcupaIngrediente poiCebolla = new ProductoOcupaIngrediente();
        poiCebolla.setProducto(producto);
        poiCebolla.setIngrediente(ingredienteCebolla);
        poiCebolla.setCantidadRequerida(50.0);
        producto.getProductos().add(poiCebolla);
        productoDAO.registrarProducto(producto);

        // 2. Eliminar un ingrediente
        Producto productoParaActualizar = productoDAO.obtenerProductoPorNombre("Tostada de carne");
        List<ProductoOcupaIngrediente> ingredientes = productoParaActualizar.getProductos();
        ingredientes.removeIf(poi -> poi.getIngrediente().getNombre().equals("Cebolla"));

        // 3. Actualizar producto
        productoDAO.actualizarProducto(productoParaActualizar);

        // 4. Verificar resultados
        Producto productoVerificado = productoDAO.buscarProductoPorId(productoParaActualizar.getId());
        List<ProductoOcupaIngrediente> ingredientesActualizados
                = productoDAO.obtenerIngredientesDeUnProducto("Tostada de carne");

        assertEquals(1, productoVerificado.getProductos().size());
        assertTrue(ingredientesActualizados.stream()
                .noneMatch(poi -> poi.getIngrediente().getNombre().equals("Cebolla")));
    }
}
