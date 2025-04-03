/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ModuloProductosEntidades;

import Enums.EstadoProducto;
import Enums.TipoPlatillo;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author sonic
 */
class ProductoTest {

    @Test
    void testCrearProductoVacio() { //Prueba de crear un producto con el constructor vacio
        Producto producto = new Producto();
        assertNotNull(producto);
        assertNull(producto.getId());
    }

    @Test
    void testcrearProducto() { //prueba de crear un producto con el constructor sin id
        List<ProductoOcupaIngrediente> productos = new ArrayList<>();
        Producto producto = new Producto("Pizza", 120.0, TipoPlatillo.PLATO_FUERTE, EstadoProducto.HABILITADO, "Pizza con pepperoni", productos);
        
        assertNull(producto.getId());
        assertEquals("Pizza", producto.getNombre());
        assertEquals(120.0, producto.getPrecio());
        assertEquals(TipoPlatillo.PLATO_FUERTE, producto.getTipo());
        assertEquals(EstadoProducto.HABILITADO, producto.getEstadoProducto());
        assertEquals("Pizza con pepperoni", producto.getDescripcion());
        assertEquals(productos, producto.getProductos());
    }

    @Test
    void testcrearProductoAsignandoID() { //prueba de crear un producto con id
        List<ProductoOcupaIngrediente> productos = new ArrayList<>();
        Producto producto = new Producto(30L, "pastel", 80.0, TipoPlatillo.POSTRE, EstadoProducto.DESHABILITADO, "pastel de chocolate", productos);
        
        assertEquals(30L, producto.getId());
        assertEquals("pastel", producto.getNombre());
        assertEquals(80.0, producto.getPrecio());
        assertEquals(TipoPlatillo.POSTRE, producto.getTipo());
        assertEquals(EstadoProducto.DESHABILITADO, producto.getEstadoProducto());
        assertEquals("pastel de chocolate", producto.getDescripcion());
        assertEquals(productos, producto.getProductos());
    }

    @Test
    void testGetterYSetter() { //prueba todos los metodos get y set de la entidad
        Producto producto = new Producto();
        
        producto.setId(2L);
        producto.setNombre("Ensalada");
        producto.setPrecio(20.0);
        producto.setTipo(TipoPlatillo.ENTRADA);
        producto.setEstadoProducto(EstadoProducto.HABILITADO);
        producto.setDescripcion("Ensalada fresca");
        
        List<ProductoOcupaIngrediente> productos = new ArrayList<>();
        producto.setProductos(productos);
        
        assertEquals(2L, producto.getId());
        assertEquals("Ensalada", producto.getNombre());
        assertEquals(20.0, producto.getPrecio());
        assertEquals(TipoPlatillo.ENTRADA, producto.getTipo());
        assertEquals(EstadoProducto.HABILITADO, producto.getEstadoProducto());
        assertEquals("Ensalada fresca", producto.getDescripcion());
        assertEquals(productos, producto.getProductos());
    }

}
