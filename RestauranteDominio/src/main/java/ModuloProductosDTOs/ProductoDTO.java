/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloProductosDTOs;

import ModuloIngredientesEntidades.Ingrediente;
import java.util.List;

/**
 *
 * @author sonic
 */
public class ProductoDTO {
    private String nombre;
    private Double precio;
    private String tipo;
    private String descripcion;
    private List<Ingrediente> ingredientes;

    public ProductoDTO() {
    }

    public ProductoDTO(String nombre, Double precio, String tipo, String descripcion, List<Ingrediente> ingredientes) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.ingredientes = ingredientes;
    }

    public ProductoDTO(String nombre, Double precio, String tipo, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.descripcion = descripcion;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }


    
    
    
}
