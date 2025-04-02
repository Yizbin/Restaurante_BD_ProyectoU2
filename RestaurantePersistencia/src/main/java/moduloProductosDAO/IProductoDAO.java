/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package moduloProductosDAO;

import Exception.PersistenciaException;
import ModuloProductosEntidades.Producto;

/**
 *
 * @author sonic
 */
public interface IProductoDAO {
    public Producto registrarProducto(Producto producto) throws PersistenciaException;
}
