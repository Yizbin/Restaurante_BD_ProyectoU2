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

    public ReabastecerIngredienteDTO() {
    }

    public ReabastecerIngredienteDTO(Integer stock) {
        this.stock = stock;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

}
