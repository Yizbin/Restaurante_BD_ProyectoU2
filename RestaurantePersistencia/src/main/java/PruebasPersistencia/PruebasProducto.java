/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package PruebasPersistencia;

import Conexion.Conexion;
import Enums.EstadoProducto;
import Enums.TipoPlatillo;
import Enums.UnidadMedida;
import Exception.PersistenciaException;
import ModuloIngredientesEntidades.Ingrediente;
import ModuloProductosEntidades.Producto;
import ModuloProductosEntidades.ProductoOcupaIngrediente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import moduloProductosDAO.IProductoDAO;
import moduloProductosDAO.ProductoDAO;

/**
 *
 * @author sonic
 */
public class PruebasProducto {

    /**
     * @param args the command line arguments
     * @throws Exception.PersistenciaException
     */
    public static void main(String[] args) throws PersistenciaException {
        IProductoDAO productoDAO = ProductoDAO.getInstanceDAO();

        //CREAR UNOS INGREDIENTES
        Ingrediente ingrediente1 = new Ingrediente("carne", 100, UnidadMedida.GRAMOS);
        Ingrediente ingrediente2 = new Ingrediente("cebolla", 100, UnidadMedida.GRAMOS);
        EntityManager em = Conexion.crearConexion();
        em.getTransaction().begin();
        em.persist(ingrediente1);
        em.persist(ingrediente2);
        em.getTransaction().commit();
        em.close();

        //CREAR UN PRODUCTO
        Producto producto1 = new Producto();
        producto1.setNombre("tostada de carne");
        producto1.setPrecio(60.0);
        producto1.setTipo(TipoPlatillo.ENTRADA);
        producto1.setEstadoProducto(EstadoProducto.HABILITADO);
        producto1.setDescripcion("Tostada de carne muy rica");

        ProductoOcupaIngrediente prodCarne = new ProductoOcupaIngrediente();
        prodCarne.setProducto(producto1);
        prodCarne.setIngrediente(ingrediente1);
        prodCarne.setCantidadRequerida(2.0);

        producto1.getProductos().add(prodCarne); //AL PRODUCTO SE LE AGREGA UN INGREDIENTE

        productoDAO.registrarProducto(producto1); //ESE PRODUCTO SE PERSISTE

        //AHORA QUE ESTA PERSISTIDO SE BUSCA POR SU NOMBRE
        Producto productoEncontrado = productoDAO.obtenerProductoPorNombre("tostada de carne");

        ProductoOcupaIngrediente prodCebolla = new ProductoOcupaIngrediente(); //SE LE AGREGA UN NUEVO INGREDIENTE
        prodCebolla.setProducto(productoEncontrado);
        prodCebolla.setIngrediente(ingrediente2);
        prodCebolla.setCantidadRequerida(3.0);

        //A LA LISTA DEL PRODUCTO SE AÑADE EL INGREDIENTE
        productoEncontrado.getProductos().add(prodCebolla);

        productoDAO.actualizarProducto(productoEncontrado); //SE ACTUALIZA EL PRODUCTO CON UN NUEVO INGREDIENTE, ASI SE LE AÑADEN NUEVOS INGREDIENTES

        List<ProductoOcupaIngrediente> listaIngredientes = productoDAO.obtenerIngredientesDeUnProducto("tostada de carne");

        //QUITAMOS UN INGREDIENTE DE LA LISTA DE INGREDIENTES EN EL PRODUCTO
        // Buscar y eliminar el ingrediente "cebolla en medida gramos"
        // Eliminar el ingrediente "cebolla"
        listaIngredientes.removeIf(poi
                -> poi.getIngrediente().getNombre().equals("cebolla")
                && poi.getIngrediente().getUnidadMedida().equals(UnidadMedida.GRAMOS)
        );

        // Actualizar el producto sin el ingrediente eliminado
        productoDAO.actualizarProducto(productoEncontrado);

        for (ProductoOcupaIngrediente poi : listaIngredientes) {
            System.out.println(poi.getIngrediente().getNombre());
        }

        //productoDAO.deshabilitarProducto(producto1);
        //productoDAO.habilitarProducto(producto1);
        for (Producto p : productoDAO.obtenerProductosPorNombreEnComandas("tada")) {
            System.out.println(p.getNombre());
        }

        for (Producto p : productoDAO.obtenerProductosPorTipo(TipoPlatillo.ENTRADA)) {
            System.out.println(p.getNombre());
        }

    }

}
