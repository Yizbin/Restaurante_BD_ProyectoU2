/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloProductosDTOs;

import Enums.EstadoProducto;
import Enums.TipoPlatillo;
import java.util.List;

/**
 *
 * @author sonic
 */
public class ProductoDTO {
    private String nombre;
    private Double precio;
    private TipoPlatillo tipo;
    private EstadoProducto estado;
    private String descripcion;
    private List<IngredienteProductoDTO> ingredientes;

    public ProductoDTO() {
    }

    public ProductoDTO(String nombre, Double precio, TipoPlatillo tipo, EstadoProducto estado, String descripcion, List<IngredienteProductoDTO> ingredientes) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.estado = estado;
        this.descripcion = descripcion;
        this.ingredientes = ingredientes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public TipoPlatillo getTipo() {
        return tipo;
    }

    public void setTipo(TipoPlatillo tipo) {
        this.tipo = tipo;
    }

    public EstadoProducto getEstado() {
        return estado;
    }

    public void setEstado(EstadoProducto estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<IngredienteProductoDTO> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<IngredienteProductoDTO> ingredientes) {
        this.ingredientes = ingredientes;
    }

    @Override
    public String toString() {
        return "ProductoDTO{" + "nombre=" + nombre + ", precio=" + precio + ", tipo=" + tipo + ", estado=" + estado + ", descripcion=" + descripcion + ", ingredientes=" + ingredientes + '}';
    }
 
    
}
