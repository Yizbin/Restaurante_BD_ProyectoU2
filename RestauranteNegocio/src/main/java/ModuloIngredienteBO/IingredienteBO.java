/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ModuloIngredienteBO;

import Enums.UnidadMedida;
import Exception.NegocioException;
import ModuloIngredientesDTOs.IngredienteDTO;
import java.util.List;

/**
 *
 * @author Abraham Coronel Bringas
 */
public interface IingredienteBO {

    public IngredienteDTO registrarIngrediente(IngredienteDTO ingredienteNuevo) throws NegocioException;

    public IngredienteDTO actualizarIngrediente(IngredienteDTO ingredienteDTO) throws NegocioException;

    public boolean eliminarIngrediente(Long id) throws NegocioException;
    
    public IngredienteDTO actualizarStock(Long id, int nuevoStock) throws NegocioException;

    public List<IngredienteDTO> buscarTodos() throws NegocioException;

    public IngredienteDTO buscarPorNombre(String nombre, UnidadMedida unidadMedida) throws NegocioException;

}
