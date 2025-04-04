/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloProductoMapper;

import Enums.EstadoProducto;
import Enums.TipoPlatillo;
import ModuloIngredientesDTOs.IngredienteDTO;
import ModuloIngredientesEntidades.Ingrediente;
import ModuloProductosDTOs.IngredienteProductoDTO;
import ModuloProductosDTOs.ProductoDTO;
import ModuloProductosEntidades.Producto;
import ModuloProductosEntidades.ProductoOcupaIngrediente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sonic
 */
public class ProductoMapper {

    public static ProductoDTO toProductoDTO(Producto producto) {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setNombre(producto.getNombre());
        productoDTO.setPrecio(producto.getPrecio());
        productoDTO.setTipo(producto.getTipo());
        productoDTO.setDescripcion(producto.getDescripcion());

        List<IngredienteProductoDTO> listaIngredientesDTO = new ArrayList<>();
        for (ProductoOcupaIngrediente poi : producto.getProductos()) {
            IngredienteProductoDTO ingredienteproductoDTO = new IngredienteProductoDTO();

            ingredienteproductoDTO.setNombreIngrediente(poi.getIngrediente().getNombre());
            ingredienteproductoDTO.setCantidadRequerida(poi.getCantidadRequerida());
            ingredienteproductoDTO.setUnidadMedida(poi.getIngrediente().getUnidadMedida());

            listaIngredientesDTO.add(ingredienteproductoDTO);
        }

        productoDTO.setIngredientes(listaIngredientesDTO);

        return productoDTO;

    }

    public static Producto toEntidadProducto(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setTipo(productoDTO.getTipo());
        producto.setEstadoProducto(productoDTO.getEstado());
        producto.setDescripcion(productoDTO.getDescripcion());

        List<ProductoOcupaIngrediente> relacionesIngredientes = new ArrayList<>();
        for (IngredienteProductoDTO dto : productoDTO.getIngredientes()) {
            ProductoOcupaIngrediente poi = new ProductoOcupaIngrediente();

            Ingrediente ingrediente = new Ingrediente();
            ingrediente.setNombre(dto.getNombreIngrediente());
            ingrediente.setUnidadMedida(dto.getUnidadMedida());

            poi.setProducto(producto);
            poi.setIngrediente(ingrediente);
            poi.setCantidadRequerida(dto.getCantidadRequerida());

            relacionesIngredientes.add(poi);
        }
        producto.setProductos(relacionesIngredientes);

        return producto;
    }
    
}

