/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DependencyInjectors;

import ModuloIngredienteBO.IingredienteBO;
import ModuloIngredienteBO.IngredienteBO;
import ModuloProductosBO.IProductoBO;
import ModuloProductosBO.ProductoBO;
import moduloIngredientesDAO.IingredienteDAO;
import moduloIngredientesDAO.IngredienteDAO;
import moduloProductosDAO.IProductoDAO;
import moduloProductosDAO.ProductoDAO;

/**
 *
 * @author sonic
 */
public class DependencyInjector {
    
    public static IProductoBO crearProductoBO() {
        IProductoDAO productoDAO = ProductoDAO.getInstanceDAO();
        IProductoBO productoBO = new ProductoBO(productoDAO);
        return productoBO;
        
    }
    
     public static IingredienteBO crearIngredienteBO() {
        IingredienteDAO ingredienteDAO = IngredienteDAO.getInstanceDAO();
        IngredienteBO ingredienteBO = new IngredienteBO(ingredienteDAO);
        return ingredienteBO;
    }
}
