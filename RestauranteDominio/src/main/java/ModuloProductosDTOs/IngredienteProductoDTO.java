/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloProductosDTOs;

/**
 *
 * @author sonic
 */
public class IngredienteProductoDTO {
    private String nombreIngrediente;
    private int cantidadRequerida;
    private String unidadMedida;

    public IngredienteProductoDTO(String nombreIngrediente, int cantidadRequerida, String unidadMedida) {
        this.nombreIngrediente = nombreIngrediente;
        this.cantidadRequerida = cantidadRequerida;
        this.unidadMedida = unidadMedida;
    }

    public IngredienteProductoDTO() {
    }

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }

    public int getCantidadRequerida() {
        return cantidadRequerida;
    }

    public void setCantidadRequerida(int cantidadRequerida) {
        this.cantidadRequerida = cantidadRequerida;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    @Override
    public String toString() {
        return "IngredienteProductoDTO{" + "nombreIngrediente=" + nombreIngrediente + ", cantidadRequerida=" + cantidadRequerida + ", unidadMedida=" + unidadMedida + '}';
    }
    
    

}
