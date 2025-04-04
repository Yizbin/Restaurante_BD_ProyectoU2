/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ModuloProductosBO;

import Enums.TipoPlatillo;
import Exception.NegocioException;
import Exception.PersistenciaException;
import ModuloProductosDTOs.IngredienteProductoDTO;
import ModuloProductosDTOs.ProductoDTO;
import java.util.List;

/**
 *
 * @author sonic
 */
public interface IProductoBO {
    public ProductoDTO registrarProducto(ProductoDTO productoNuevo) throws NegocioException;
    public ProductoDTO actualizarProducto(ProductoDTO productoDTO) throws NegocioException;
    public List<IngredienteProductoDTO> obtenerIngredientesProducto(ProductoDTO productoDTO) throws NegocioException, PersistenciaException;
    public List<ProductoDTO> obtenerProductos() throws NegocioException;
    public List<ProductoDTO> obtenerProductosPorNombre(String nombre) throws NegocioException;
    public List<ProductoDTO> obtenerProductoPorNombreYTipo(String nombre, TipoPlatillo tipo) throws NegocioException;
    public List<ProductoDTO> obtenerProductosPorTipo(TipoPlatillo tipo) throws NegocioException;
    
    //mostrar listas de productos en comandas
    public List<ProductoDTO> obtenerProductosEnComandas() throws NegocioException;
    public List<ProductoDTO> obtenerProductosPorNombreEnComandas(String nombre) throws NegocioException;
    public List<ProductoDTO> obtenerProductoPorNombreYTipoEnComandas(String nombre, TipoPlatillo tipo) throws NegocioException;
    public List<ProductoDTO> obtenerProductosPorTipoEnComandas(TipoPlatillo tipo) throws NegocioException;
    
}
