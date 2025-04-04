/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package moduloProductosDAO;

import Enums.TipoPlatillo;
import Exception.PersistenciaException;
import ModuloIngredientesEntidades.Ingrediente;
import ModuloProductosDTOs.IngredienteProductoDTO;
import ModuloProductosEntidades.Producto;
import ModuloProductosEntidades.ProductoOcupaIngrediente;
import java.util.List;

/**
 *
 * @author sonic
 */
public interface IProductoDAO {
    //OPERACIONES CRUD
    public Producto registrarProducto(Producto producto) throws PersistenciaException;
    public Producto actualizarProducto(Producto producto) throws PersistenciaException;
    public void deshabilitarProducto(Producto producto) throws PersistenciaException;
    public void habilitarProducto(Producto producto) throws PersistenciaException;
    
    //Metodos para llenar listas de ingredientes
    public List<IngredienteProductoDTO> obtenerIngredientesDeUnProductoDTO(String nombreProducto) throws PersistenciaException;
    public List<ProductoOcupaIngrediente> obtenerIngredientesDeUnProducto(String nombreProducto) throws PersistenciaException;
    
    //Metodos para obtener un producto
    public Producto obtenerProductoPorNombre(String nombre) throws PersistenciaException;
    public Producto buscarProductoPorId(Long idProducto) throws PersistenciaException;
    
    
    //Metodos para llenar lista de productos en comandas
    public List<Producto> obtenerProductosEnComandas() throws PersistenciaException;
    public List<Producto> obtenerProductosPorNombreEnComandas(String nombre) throws PersistenciaException;
    public List<Producto> obtenerProductosPorTipoEnComandas(TipoPlatillo tipo) throws PersistenciaException;
    public List<Producto> obtenerProductoPorNombreYTipoEnComandas(String nombre, TipoPlatillo tipo) throws PersistenciaException;
    
    //Metodos para llenar lista de productos en modulo de productos
    public List<Producto> obtenerProductos() throws PersistenciaException;
    public List<Producto> obtenerProductosPorNombre(String nombre) throws PersistenciaException;
    public List<Producto> obtenerProductosPorTipo(TipoPlatillo tipo) throws PersistenciaException;
    public List<Producto> obtenerProductoPorNombreYTipo(String nombre, TipoPlatillo tipo) throws PersistenciaException;
    
    
}
