/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloProductosEntidades;

import ModuloIngredientesEntidades.Ingrediente;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author sonic
 */
@Entity
@Table(name = "productoOcupaIngrediente")
public class ProductoOcupaIngrediente implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ProductoOcupaIngrediente")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;
    
    @ManyToOne
    @JoinColumn(name = "id_ingrediente", nullable = false)
    private Ingrediente ingrediente;
    
    @Column(name = "cantidad_requerida", nullable = false)
    private int cantidadRequerida;

    public ProductoOcupaIngrediente(Long id, Producto producto, Ingrediente ingrediente, int cantidadRequerida) {
        this.id = id;
        this.producto = producto;
        this.ingrediente = ingrediente;
        this.cantidadRequerida = cantidadRequerida;
    }

    public ProductoOcupaIngrediente() {
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public int getCantidadRequerida() {
        return cantidadRequerida;
    }

    public void setCantidadRequerida(int cantidadRequerida) {
        this.cantidadRequerida = cantidadRequerida;
    }

    @Override
    public String toString() {
        return "ProductoOcupaIngrediente{" + "id=" + id + ", producto=" + producto + ", ingrediente=" + ingrediente + ", cantidadRequerida=" + cantidadRequerida + '}';
    }
    
    
    
    
    
}
