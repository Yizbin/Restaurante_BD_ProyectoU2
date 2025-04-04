/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloProductosBO;

import Enums.TipoPlatillo;
import Exception.NegocioException;
import Exception.PersistenciaException;
import ModuloProductoMapper.ProductoMapper;
import ModuloProductosDTOs.IngredienteProductoDTO;
import ModuloProductosDTOs.ProductoDTO;
import ModuloProductosEntidades.Producto;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import moduloProductosDAO.IProductoDAO;

/**
 *
 * @author sonic
 */
public class ProductoBO implements IProductoBO {

    private IProductoDAO productoDAO;
    private static final Logger LOGGER = Logger.getLogger(ProductoBO.class.getName());

    public ProductoBO(IProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    @Override
    public ProductoDTO registrarProducto(ProductoDTO productoNuevo) throws NegocioException {
        // Validacion de ingredientes
        if (productoNuevo.getIngredientes().isEmpty() || productoNuevo.getIngredientes() == null) {
            LOGGER.warning("No se puede registrar un producto sin ingredientes");
            throw new NegocioException("El producto no se puede registrar si no tiene ingredientes");
        }

        // Validación de nombre
        String nombreProducto = productoNuevo.getNombre();
        if (nombreProducto == null || nombreProducto.trim().isEmpty()) {
            LOGGER.warning("Intento de registro con nombre vacío");
            throw new NegocioException("El nombre del producto es requerido");
        }

        // Validación de nombre único
        try {
            if (productoDAO.obtenerProductoPorNombre(nombreProducto) == null) {
                LOGGER.warning("Intento de registro con nombre duplicado: " + nombreProducto);
                throw new NegocioException("Ya existe un producto registrado con el nombre: " + nombreProducto);
            }
        } catch (PersistenciaException e) {
            LOGGER.severe("Error al agregar el producto: " + e.getMessage());
            throw new NegocioException("Error al validar el nombre del producto", e);
        }

        Producto productoARegistrar = ProductoMapper.toEntidadProducto(productoNuevo);

        try {
            Producto productoRegistrado = productoDAO.registrarProducto(productoARegistrar);

            if (productoRegistrado == null || productoRegistrado.getId() == null) {
                LOGGER.severe("El producto no se registro correctamente en la base de datos.");
                throw new NegocioException("No se pudo registrar el producto.");
            }
            LOGGER.info("Producto registrado con éxito en la base de datos. ID asignado: " + productoRegistrado.getId());

            return ProductoMapper.toProductoDTO(productoRegistrado);
        } catch (PersistenciaException e) {
            LOGGER.severe("Error al registrar producto: " + e.getMessage());
            throw new NegocioException("Error al registrar el producto: " + e.getMessage(), e);
        }
    }

    @Override
    public ProductoDTO actualizarProducto(ProductoDTO productoDTO) throws NegocioException {
        try {
            // 1. Validación de ID obligatorio
            if (productoDAO.obtenerProductoPorNombre(productoDTO.getNombre()).getId() == null) {
                LOGGER.severe("Intento de actualización sin ID de producto");
                throw new NegocioException("Se requiere el ID del producto para actualizar");
            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(ProductoBO.class.getName()).log(Level.SEVERE, null, ex);
        }

        // 4. Validación de ingredientes
        if (productoDTO.getIngredientes() == null || productoDTO.getIngredientes().isEmpty()) {
            LOGGER.warning("Intento de actualización sin ingredientes");
            throw new NegocioException("El producto debe contener ingredientes");
        }

        // 5. Validación de datos básicos
        if (productoDTO.getPrecio() == null || productoDTO.getPrecio() <= 0) {
            throw new NegocioException("El precio debe ser mayor a cero");
        }

        // 6. Conversión y actualización
        try {
            Producto productoActualizar = ProductoMapper.toEntidadProducto(productoDTO);

            Producto productoActualizado = productoDAO.actualizarProducto(productoActualizar);
            return ProductoMapper.toProductoDTO(productoActualizado);

        } catch (PersistenciaException e) {
            LOGGER.severe("Error al actualizar producto: " + e.getMessage());
            throw new NegocioException("Error interno al actualizar", e);
        }
    }

    @Override
    public List<IngredienteProductoDTO> obtenerIngredientesProducto(ProductoDTO productoDTO) throws NegocioException, PersistenciaException {
        String nombreProducto = productoDTO.getNombre();
        return productoDAO.obtenerIngredientesDeUnProductoDTO(nombreProducto);

    }

    @Override
    public List<ProductoDTO> obtenerProductos() throws NegocioException {
        try {
            List<Producto> productosEntidad = productoDAO.obtenerProductos();

            return productosEntidad.stream()
                    .map(ProductoMapper::toProductoDTO)
                    .collect(Collectors.toList());

        } catch (PersistenciaException e) {
            String mensajeError = "Error al obtener los productos: " + e.getMessage();
            LOGGER.log(Level.SEVERE, mensajeError, e);
            throw new NegocioException(mensajeError, e);
        }
    }

    @Override
    public List<ProductoDTO> obtenerProductosPorNombre(String nombre) throws NegocioException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NegocioException("El nombre de búsqueda no puede estar vacío");
        }

        try {
            List<Producto> productos = productoDAO.obtenerProductosPorNombre(nombre);
            return productos.stream()
                    .map(ProductoMapper::toProductoDTO)
                    .collect(Collectors.toList());

        } catch (PersistenciaException e) {
            String mensaje = "Error al buscar productos por nombre: " + nombre;
            LOGGER.log(Level.SEVERE, mensaje, e);
            throw new NegocioException(mensaje, e);
        }
    }

    @Override
    public List<ProductoDTO> obtenerProductoPorNombreYTipo(String nombre, TipoPlatillo tipo) throws NegocioException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NegocioException("El nombre de búsqueda no puede estar vacío");
        }
        
        if (tipo == null) {
            throw new NegocioException("El tipo de platillo es requerido");
        }

        try {
            List<Producto> productos = productoDAO.obtenerProductoPorNombreYTipo(nombre, tipo);
            return productos.stream()
                    .map(ProductoMapper::toProductoDTO)
                    .collect(Collectors.toList());
            
        } catch (PersistenciaException e) {
            String mensaje = String.format("Error al buscar productos por nombre: %s y tipo: %s", nombre, tipo);
            LOGGER.log(Level.SEVERE, mensaje, e);
            throw new NegocioException(mensaje, e);
        }
    }
    
    @Override
    public List<ProductoDTO> obtenerProductosPorTipo(TipoPlatillo tipo) throws NegocioException {
        if (tipo == null) {
            throw new NegocioException("El tipo de platillo es requerido");
        }
        
        try {
            List<Producto> productos = productoDAO.obtenerProductosPorTipo(tipo);
            return productos.stream()
                    .map(ProductoMapper::toProductoDTO)
                    .collect(Collectors.toList());
            
        } catch (PersistenciaException e) {
            String mensaje = "Error al buscar productos por tipo: " + tipo;
            LOGGER.log(Level.SEVERE, mensaje, e);
            throw new NegocioException(mensaje, e);
        }
    }
    
    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    
    //METODOS PARA MANEJAR LAS LISTAS DE PRODUCTOS EN EL MODULO DE COMANDAS
    @Override
    public List<ProductoDTO> obtenerProductosEnComandas() throws NegocioException {
        try {
            List<Producto> productosEntidad = productoDAO.obtenerProductosEnComandas();

            return productosEntidad.stream()
                    .map(ProductoMapper::toProductoDTO)
                    .collect(Collectors.toList());

        } catch (PersistenciaException e) {
            String mensajeError = "Error al obtener los productos: " + e.getMessage();
            LOGGER.log(Level.SEVERE, mensajeError, e);
            throw new NegocioException(mensajeError, e);
        }
    }

    @Override
    public List<ProductoDTO> obtenerProductosPorNombreEnComandas(String nombre) throws NegocioException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NegocioException("El nombre de búsqueda no puede estar vacío");
        }

        try {
            List<Producto> productos = productoDAO.obtenerProductosPorNombreEnComandas(nombre);
            return productos.stream()
                    .map(ProductoMapper::toProductoDTO)
                    .collect(Collectors.toList());

        } catch (PersistenciaException e) {
            String mensaje = "Error al buscar productos por nombre: " + nombre;
            LOGGER.log(Level.SEVERE, mensaje, e);
            throw new NegocioException(mensaje, e);
        }
    }

    @Override
    public List<ProductoDTO> obtenerProductoPorNombreYTipoEnComandas(String nombre, TipoPlatillo tipo) throws NegocioException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NegocioException("El nombre de búsqueda no puede estar vacío");
        }
        
        if (tipo == null) {
            throw new NegocioException("El tipo de platillo es requerido");
        }

        try {
            List<Producto> productos = productoDAO.obtenerProductoPorNombreYTipoEnComandas(nombre, tipo);
            return productos.stream()
                    .map(ProductoMapper::toProductoDTO)
                    .collect(Collectors.toList());
            
        } catch (PersistenciaException e) {
            String mensaje = String.format("Error al buscar productos por nombre: %s y tipo: %s", nombre, tipo);
            LOGGER.log(Level.SEVERE, mensaje, e);
            throw new NegocioException(mensaje, e);
        }
    }
    
    @Override
    public List<ProductoDTO> obtenerProductosPorTipoEnComandas(TipoPlatillo tipo) throws NegocioException {
        if (tipo == null) {
            throw new NegocioException("El tipo de platillo es requerido");
        }
        
        try {
            List<Producto> productos = productoDAO.obtenerProductosPorTipoEnComandas(tipo);
            return productos.stream()
                    .map(ProductoMapper::toProductoDTO)
                    .collect(Collectors.toList());
            
        } catch (PersistenciaException e) {
            String mensaje = "Error al buscar productos por tipo: " + tipo;
            LOGGER.log(Level.SEVERE, mensaje, e);
            throw new NegocioException(mensaje, e);
        }
    }

}
