/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ModuloProductosEntidades;

import ModuloIngredientesEntidades.Ingrediente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author sonic
 */
class ProductoOcupaIngredienteTest {

    @Test
    void testCrearProductoOcupaIngredienteVacio() {
        ProductoOcupaIngrediente productoIngrediente = new ProductoOcupaIngrediente();
        
        assertNotNull(productoIngrediente);
        assertNull(productoIngrediente.getId());
        assertNull(productoIngrediente.getProducto());
        assertNull(productoIngrediente.getIngrediente());
        assertEquals(0, productoIngrediente.getCantidadRequerida());
    }

    @Test
    void testCrearProductoOcupaIngredienteConId() {
        Producto producto = new Producto();
        Ingrediente ingrediente = new Ingrediente();
        ProductoOcupaIngrediente productoIngrediente = new ProductoOcupaIngrediente(
            1L, 
            producto, 
            ingrediente, 
            5
        );
        
        assertEquals(1L, productoIngrediente.getId());
        assertSame(producto, productoIngrediente.getProducto());
        assertSame(ingrediente, productoIngrediente.getIngrediente());
        assertEquals(5, productoIngrediente.getCantidadRequerida());
    }
    
    @Test
    void testCrearProductoOcupaIngredienteSinId() {
        Producto producto = new Producto();
        Ingrediente ingrediente = new Ingrediente();
        ProductoOcupaIngrediente productoIngrediente = new ProductoOcupaIngrediente(
            producto, 
            ingrediente, 
            5
        );
        
        assertSame(producto, productoIngrediente.getProducto());
        assertSame(ingrediente, productoIngrediente.getIngrediente());
        assertEquals(5, productoIngrediente.getCantidadRequerida());
    }

    @Test
    void testGetterYSetter() {
        ProductoOcupaIngrediente productoIngrediente = new ProductoOcupaIngrediente();
        
        // Test ID
        productoIngrediente.setId(2L);
        assertEquals(2L, productoIngrediente.getId());
        
        // Test Producto
        Producto producto = new Producto();
        producto.setNombre("Pizza");
        productoIngrediente.setProducto(producto);
        assertSame(producto, productoIngrediente.getProducto());
        assertEquals("Pizza", productoIngrediente.getProducto().getNombre());
        
        // Test Ingrediente
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre("Queso");
        productoIngrediente.setIngrediente(ingrediente);
        assertSame(ingrediente, productoIngrediente.getIngrediente());
        assertEquals("Queso", productoIngrediente.getIngrediente().getNombre());
        
        // Test Cantidad
        productoIngrediente.setCantidadRequerida(10);
        assertEquals(10, productoIngrediente.getCantidadRequerida());
    }

}

