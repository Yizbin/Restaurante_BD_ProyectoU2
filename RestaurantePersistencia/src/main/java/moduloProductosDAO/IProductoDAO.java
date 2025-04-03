/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package moduloProductosDAO;

import Exception.PersistenciaException;
import ModuloProductosDTOs.IngredienteProductoDTO;
import ModuloProductosEntidades.Producto;
import ModuloProductosEntidades.ProductoOcupaIngrediente;
import java.util.List;

/**
 *
 * @author sonic
 */
public interface IProductoDAO {
    public Producto registrarProducto(Producto producto) throws PersistenciaException; //DUDAS EN ESTE TIPO DE METODOS, PIENSO QUE PODRIAN SER BOOLEAN PARA INDICAR SI
    public void deshabilitarProducto(Producto producto) throws PersistenciaException;  //POR EJEMPLO EL REGISTRO DEL PRODUCTO FUE EXITOSO O NO
    public List<Producto> obtenerProductos() throws PersistenciaException;
    public List<Producto> obtenerProductosPorNombre(String nombre) throws PersistenciaException;
    public Producto obtenerProductoPorNombre(String nombre) throws PersistenciaException;
    public List<Producto> obtenerProductosPorTipo(String tipo) throws PersistenciaException;
    public Producto actualizarProducto(Producto producto) throws PersistenciaException;
    public List<IngredienteProductoDTO> obtenerIngredientesDeUnProductoDTO(String nombreProducto) throws PersistenciaException;
    public void agregarIngredienteAProducto(Long idProducto, Long idIngrediente, int cantidad) throws PersistenciaException;
    public void actualizarCantidadRequeridaDeUnIngrediente(Long idProducto, Long idIngrediente, int nuevaCantidad) throws PersistenciaException;
    public List<Producto> obtenerProductoPorNombreYTipo(String nombre, String tipo) throws PersistenciaException;
    public List<ProductoOcupaIngrediente> obtenerIngredientesDeUnProducto(String nombreProducto) throws PersistenciaException;
    
}
