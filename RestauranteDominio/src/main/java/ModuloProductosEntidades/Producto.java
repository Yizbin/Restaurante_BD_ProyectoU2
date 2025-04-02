/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloProductosEntidades;

import Enums.EstadoProducto;
import Enums.TipoPlatillo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author sonic
 */
@Entity
@Table(name = "productos")
public class Producto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Producto", nullable = false)
    private Long id;
    
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    
    @Column(name = "precio", nullable = false)
    private Double precio;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoPlatillo tipo;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoProducto estadoProducto;
    
    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;
    
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<ProductoOcupaIngrediente> productos = new ArrayList<>();

    public Producto() {
    }

    public Producto(Long id, String nombre, Double precio, TipoPlatillo tipo, EstadoProducto estadoProducto, String descripcion, List<ProductoOcupaIngrediente> productos) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.estadoProducto = estadoProducto;
        this.descripcion = descripcion;
        this.productos = productos;
    }

    public Producto(String nombre, Double precio, TipoPlatillo tipo, EstadoProducto estadoProducto, String descripcion, List<ProductoOcupaIngrediente> productos) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.estadoProducto = estadoProducto;
        this.descripcion = descripcion;
        this.productos = productos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public EstadoProducto getEstadoProducto() {
        return estadoProducto;
    }

    public void setEstadoProducto(EstadoProducto estadoProducto) {
        this.estadoProducto = estadoProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<ProductoOcupaIngrediente> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoOcupaIngrediente> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", tipo=" + tipo + ", estadoProducto=" + estadoProducto + ", descripcion=" + descripcion + ", productos=" + productos + '}';
    }

    
    
    
    
    

    
    
}
