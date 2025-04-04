/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloIngredienteBO;

import Enums.UnidadMedida;
import Exception.NegocioException;
import Exception.PersistenciaException;
import ModuloIngredienteMapper.IngredienteMapper;
import ModuloIngredientesDTOs.IngredienteDTO;
import ModuloIngredientesEntidades.Ingrediente;
import java.util.List;
import java.util.logging.Logger;
import moduloIngredientesDAO.IingredienteDAO;

/**
 *
 * @author Abraham Coronel Bringas
 */
public class IngredienteBO implements IingredienteBO {

    private IingredienteDAO ingredienteDAO;

    private static final Logger LOGGER = Logger.getLogger(IngredienteBO.class.getName());

    public IngredienteBO(IingredienteDAO ingredienteDAO) {
        this.ingredienteDAO = ingredienteDAO;
    }

    @Override
    public IngredienteDTO registrarIngrediente(IngredienteDTO ingredienteNuevo) throws NegocioException {
        if (ingredienteNuevo == null) {
            throw new NegocioException("El ingrediente no puede ser nulo");
        }
        if (ingredienteNuevo.getNombre() == null || ingredienteNuevo.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre del ingrediente es obligatorio");
        }
        if (ingredienteNuevo.getUnidadMedida() == null) {
            throw new NegocioException("La unidad de medida es obligatoria");
        }
        if (ingredienteNuevo.getStock() < 0) {
            throw new NegocioException("El stock no puede ser negativo");
        }

        try {
            List<Ingrediente> existentes = ingredienteDAO.buscarPorTodos();
            for (Ingrediente ing : existentes) {
                if (ing.getNombre().equalsIgnoreCase(ingredienteNuevo.getNombre()) && ing.getUnidadMedida().equals(ingredienteNuevo.getUnidadMedida())) {
                    throw new NegocioException("Ya existe un ingrediente con el mismo nombre y unidad");

                }
            }

            Ingrediente entidad = IngredienteMapper.toEntity(ingredienteNuevo);
            Ingrediente registrado = ingredienteDAO.registrarIngrediente(entidad);
            return IngredienteMapper.toDTO(registrado);

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al registrar ingrediente: " + e.getMessage());
        }
    }

    @Override
    public IngredienteDTO actualizarIngrediente(IngredienteDTO ingredienteDTO) throws NegocioException {
        if (ingredienteDTO == null) {
            throw new NegocioException("El ingrediente no puede ser nulo");
        }

        if (ingredienteDTO.getNombre() == null || ingredienteDTO.getNombre().trim().isEmpty()) {
            throw new NegocioException("El ingrediente no puede ser nulo");
        }

        if (ingredienteDTO.getUnidadMedida() == null) {
            throw new NegocioException("La unidad de medida es obligatoria");
        }

        if (ingredienteDTO.getStock() == null) {
            throw new NegocioException("El stock no puede ser negativo");
        }

        try {
            Ingrediente entidad = IngredienteMapper.toEntity(ingredienteDTO);
            Ingrediente actualizado = ingredienteDAO.actualizarIngrediente(entidad);
            return IngredienteMapper.toDTO(actualizado);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al actualizar el ingrediente: " + e.getMessage());
        }
    }

    @Override
    public boolean eliminarIngrediente(Long id) throws NegocioException {
        if (id == null) {
            throw new NegocioException("El id del ingrediente es obligatorio para eliminar");
        }

        try {
            return ingredienteDAO.eliminarIngrediente(id);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al eliminar ingrediente: " + e.getMessage());
        }
    }

    @Override
    public List<IngredienteDTO> buscarTodos() throws NegocioException {
        try {
            List<Ingrediente> lista = ingredienteDAO.buscarPorTodos();
            return lista.stream().map(IngredienteMapper::toDTO).toList();
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener la lista de ingredientes: " + e.getMessage());
        }
    }

    @Override
    public IngredienteDTO buscarPorNombre(String nombre, UnidadMedida unidadMedida) throws NegocioException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NegocioException("El nombre del ingrediente es obligatorio");
        }

        if (unidadMedida == null) {
            throw new NegocioException("La unidad de medida es obligatoria");
        }

        try {
            Ingrediente ing = ingredienteDAO.buscarPorNombre(nombre, unidadMedida);
            return IngredienteMapper.toDTO(ing);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar el ingrediente por nombre: " + e.getMessage());
        }
    }

    @Override
    public IngredienteDTO actualizarStock(Long id, int nuevoStock) throws NegocioException {
        if (id == null) {
            throw new NegocioException("El id del ingrediente es obligatorio");
        }

        if (nuevoStock < 0) {
            throw new NegocioException("El stock no puede ser negativo");
        }

        try {
            Ingrediente actualizado = ingredienteDAO.actualizarStock(id, nuevoStock);
            return IngredienteMapper.toDTO(actualizado);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al actualizar el stock: " + e.getMessage());
        }
    }

}
