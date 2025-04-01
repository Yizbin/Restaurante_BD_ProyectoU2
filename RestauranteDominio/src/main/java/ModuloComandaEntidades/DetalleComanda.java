/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moduloComandaEntidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import moduloProductosEntidades.Producto;

/**
 *
 * @author isaac
 */
@Entity
public class DetalleComanda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_comanda")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_comanda", nullable = false)
    private Comanda comanda;
    
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;
    
    @Column(name = "nota", length = 200)
    private String nota;
    
    @Column(name = "cantidad_unidades", nullable = false)
    private int cantidadUnidades;
    
    @Column(name = "precio_unitario", nullable = false)
    private double precioUnitario;
    
    @Column(name = "importe_producto", nullable = false)
    private double importeProducto;

    public DetalleComanda(Long id, Comanda comanda, Producto producto, String nota, int cantidadUnidades, double precioUnitario, double importeProducto) {
        this.id = id;
        this.comanda = comanda;
        this.producto = producto;
        this.nota = nota;
        this.cantidadUnidades = cantidadUnidades;
        this.precioUnitario = precioUnitario;
        this.importeProducto = importeProducto;
    }

    public DetalleComanda() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public int getCantidadUnidades() {
        return cantidadUnidades;
    }

    public void setCantidadUnidades(int cantidadUnidades) {
        this.cantidadUnidades = cantidadUnidades;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getImporteProducto() {
        return importeProducto;
    }

    public void setImporteProducto(double importeProducto) {
        this.importeProducto = importeProducto;
    }
    
    

    
}