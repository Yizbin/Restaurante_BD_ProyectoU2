/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package moduloIngredientesDAO;

import Exception.PersistenciaException;
import ModuloIngredientesEntidades.Ingrediente;
import java.util.List;

/**
 *
 * @author Abraham Coronel Bringas
 */
public interface IingredienteDAO {

    public Ingrediente registrarIngrediente(Ingrediente ingrediente) throws PersistenciaException;
    
    public boolean existeIngrediente(String nombre, String unidadMedida);
    
    public boolean comandaActivaConIngrediente(Long idIngrediente);

    public Ingrediente actualizarIngrediente(Ingrediente ingrediente) throws PersistenciaException;

    public boolean eliminarIngrediente(Long id) throws PersistenciaException;

    public List<Ingrediente> buscarPorTodos() throws PersistenciaException;
}
