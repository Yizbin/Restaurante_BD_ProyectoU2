/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package PruebasPersistencia;

import Enums.UnidadMedida;
import Exception.PersistenciaException;
import ModuloIngredientesEntidades.Ingrediente;
import moduloIngredientesDAO.IingredienteDAO;
import moduloIngredientesDAO.IngredienteDAO;

/**
 *
 * @author Abraham Coronel Bringas
 */
public class PruebasIngrediente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PersistenciaException {
        IingredienteDAO ingredienteDAO = IngredienteDAO.getInstanceDAO();
        
        //INGREDIENTES PRUEBAS
        Ingrediente ingrediente = new Ingrediente("tortillas harina", 10, UnidadMedida.PIEZAS);
        Ingrediente ingrediente1 = new Ingrediente("carne de burro", 10, UnidadMedida.GRAMOS);
        Ingrediente ingrediente2 = new Ingrediente("agua", 1000, UnidadMedida.MILILITROS);
        Ingrediente ingrediente3 = new Ingrediente("jamaica", 100, UnidadMedida.PIEZAS);
        
        ingredienteDAO.registrarIngrediente(ingrediente);
        ingredienteDAO.registrarIngrediente(ingrediente1);
        ingredienteDAO.registrarIngrediente(ingrediente2);
        ingredienteDAO.registrarIngrediente(ingrediente3);
        System.out.println("Ingredientes registrados, todo bien");
        
        ingrediente.setStock(50);
        ingredienteDAO.actualizarIngrediente(ingrediente);
        System.out.println("Ingrediente actualizado, todo bien");
        
        System.out.println("Lista de ingredientes");
        for (Ingrediente i : ingredienteDAO.buscarPorTodos()) {
            System.out.println(i.getId() + " - " + i.getNombre() + " - " + i.getUnidadMedida() + " - " + i.getStock());
        }
        
        boolean eliminado = ingredienteDAO.eliminarIngrediente(1L);
        System.out.println("Ingrediente eliminado " + eliminado);
        
    }

}
