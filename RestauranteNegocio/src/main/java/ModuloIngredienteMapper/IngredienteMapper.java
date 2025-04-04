/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloIngredienteMapper;

import ModuloIngredientesDTOs.IngredienteDTO;
import ModuloIngredientesEntidades.Ingrediente;

/**
 *
 * @author Abraham Coronel Bringas
 */
public class IngredienteMapper {

    public static IngredienteDTO toDTO(Ingrediente ingrediente) {
        return new IngredienteDTO(
                ingrediente.getNombre(),
                ingrediente.getStock(),
                ingrediente.getUnidadMedida()
        );
    }

    public static Ingrediente toEntity(IngredienteDTO ingredienteDTO) {
        return new Ingrediente(
                ingredienteDTO.getNombre(),
                ingredienteDTO.getStock(),
                ingredienteDTO.getUnidadMedida()
        );
    }

}
