/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloIngredientesDTOs;

/**
 *
 * @author Abraham Coronel Bringas
 */
public class ReabastecerIngredienteDTO {

    private Integer stock;
    private String nombre;

    public ReabastecerIngredienteDTO() {
    }

    public ReabastecerIngredienteDTO(Integer stock, String nombre) {
        this.stock = stock;
        this.nombre = nombre;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "ReabastecerIngredienteDTO{" + "stock=" + stock + ", nombre=" + nombre + '}';
    }

    

}
