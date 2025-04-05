/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControlNavegacion;

import DependencyInjectors.DependencyInjector;
import Enums.TipoPlatillo;
import Enums.UnidadMedida;
import Exception.NegocioException;
import Exception.PresentacionException;
import ModuloIngredienteBO.IingredienteBO;
import ModuloIngredientes.AgregarIngrediente;
import ModuloIngredientes.GestionIngredientes;
import ModuloIngredientes.ReabastecerIngrediente;
import ModuloIngredientesDTOs.IngredienteDTO;
import ModuloProductos.EditarProducto;
import ModuloProductos.GestionProductos;
import ModuloProductos.IngredientesProducto;
import ModuloProductos.ListaProductos;
import ModuloProductos.RegistrarProducto;
import ModuloProductosBO.IProductoBO;
import ModuloProductosDTOs.ProductoDTO;
import PantallaPrincipal.IniciarSesion;
import PantallaPrincipal.MenuPrincipal;
import java.util.List;

/**
 *
 * @author Sebastian Borquez, Abraham Coronel y Isaac Guerrero
 */
public class Control {
    
    private static Control instancia;
    
    private IProductoBO productoBO = DependencyInjector.crearProductoBO();
    private IingredienteBO ingredienteBO = DependencyInjector.crearIngredienteBO();
//  private IClienteBO clienteBO;

    //Pantalla menu
    private MenuPrincipal menuprincipal;
    private IniciarSesion iniciarSesion;

    // Pantallas del modulo de productos
    private GestionProductos gestionProductos; //menu de productos
    private ListaProductos listaProductos;
    private EditarProducto editarProducto;
    private RegistrarProducto registrarProducto;
    private IngredientesProducto ingredientesProducto;

    //Pantalla del modulo de ingrediente
    private GestionIngredientes gestionIngredientes;
    private AgregarIngrediente agregarIngrediente;
    private ReabastecerIngrediente reabastecerIngrediente;
    
    public Control() {
        
    }
    
    public static Control getInstancia() {
        if (instancia == null) {
            instancia = new Control();
        }
        return instancia;
    }

    //Mostar inicio de sesion y menu
    public void mostrarIniciarSesion() {
        if (this.iniciarSesion == null) {
            this.iniciarSesion = new IniciarSesion();
        }
        
        iniciarSesion.setVisible(true);
    }
    
    public void mostrarMenuPrincipal() {
        if (this.menuprincipal == null) {
            this.menuprincipal = new MenuPrincipal();
        }
        
        registrarProducto.setVisible(true);
    }

    //Metodos para mostrar pantallas del modulo de productos y para utilizarlas
    public void mostrarRegistrarProducto() {
        if (this.registrarProducto == null) {
            this.registrarProducto = new RegistrarProducto();
        }
        
        registrarProducto.setVisible(true);
    }
    
    public void mostrarEditarProducto() {
        if (this.editarProducto == null) {
            this.editarProducto = new EditarProducto();
        }
        
        registrarProducto.setVisible(true);
    }
    
    public void mostrarListaProductos() throws PresentacionException {
        if (this.listaProductos == null) {
            this.listaProductos = new ListaProductos();
        }
        
        registrarProducto.setVisible(true);
    }
    
    public void mostrarGestionProductos () throws PresentacionException { //menu de productos
        if (this.gestionProductos == null) {
            this.gestionProductos = new GestionProductos();
        }
        
        registrarProducto.setVisible(true);
    }
    
    public void mostrarIngredientesProducto() {
        if (this.ingredientesProducto == null) {
            this.ingredientesProducto = new IngredientesProducto();
        }
        
        registrarProducto.setVisible(true);
    }

    // Registrar un producto
    public ProductoDTO registrarProducto(ProductoDTO productoNuevo) throws PresentacionException {
        try {
            
            return productoBO.registrarProducto(productoNuevo);
        } catch (NegocioException e) {
            throw new PresentacionException(e.getMessage(), e);
        }
        
    }

    //Actualizar producto
    public ProductoDTO actualizarProducto(ProductoDTO productoNuevo) throws PresentacionException {
        try {
            
            return productoBO.actualizarProducto(productoNuevo);
        } catch (NegocioException e) {
            throw new PresentacionException(e.getMessage(), e);
        }
        
    }

    //Obtener lista de productos en el sistema para mostrarlos
    public List<ProductoDTO> obtenerProductos() throws PresentacionException {
        try {
            
            return productoBO.obtenerProductos();
        } catch (NegocioException e) {
            throw new PresentacionException(e.getMessage(), e);
        }
        
    }

    //Obtener lista de productos por nombre
    public List<ProductoDTO> obtenerProductosPorNombre(String nombre) throws PresentacionException {
        try {
            
            return productoBO.obtenerProductosPorNombre(nombre);
        } catch (NegocioException e) {
            throw new PresentacionException(e.getMessage(), e);
        }
        
    }

    //Obtener lista de productos por tipo
    public List<ProductoDTO> obtenerProductosPorTipo(TipoPlatillo tipo) throws PresentacionException {
        try {
            return productoBO.obtenerProductosPorTipo(tipo);
        } catch (NegocioException e) {
            throw new PresentacionException(e.getMessage(), e);
        }
        
    }

    //Obtener lista de productos por nombre y tipo
    public List<ProductoDTO> obtenerProductosPorTipoYNombre(String nombre, TipoPlatillo  tipo) throws PresentacionException {
        try {
            return productoBO.obtenerProductoPorNombreYTipo(nombre, tipo);
        } catch (NegocioException e) {
            throw new PresentacionException(e.getMessage(), e);
        }
        
    }

    //----------------------------------------------------------------------------------
    //Metodos de ingrediente
    public void mostrarGestionIngrediente() {
        if (this.gestionIngredientes == null) {
            this.gestionIngredientes = new GestionIngredientes();
        }
        
        gestionIngredientes.setVisible(true);
    }
    
    public void mostrarAgregarIngrediente() {
        if (this.agregarIngrediente == null) {
            this.agregarIngrediente = new AgregarIngrediente();
        }
        
        agregarIngrediente.setVisible(true);
    }
    
    public void mostrarReabastecerIngrediente() {
        if (this.reabastecerIngrediente == null) {
            this.reabastecerIngrediente = new ReabastecerIngrediente();
        }
        
        reabastecerIngrediente.setVisible(true);
    }
    
    public IngredienteDTO registrarIngrediente(IngredienteDTO ingredienteNuevo) throws PresentacionException {
        try {
            
            return ingredienteBO.registrarIngrediente(ingredienteNuevo);
        } catch (NegocioException e) {
            throw new PresentacionException(e.getMessage(), e);
        }
        
    }
    
    public IngredienteDTO actualizarIngrediente(IngredienteDTO ingredienteActualizar) throws PresentacionException {
        try {
            return ingredienteBO.actualizarIngrediente(ingredienteActualizar);
        } catch (NegocioException e) {
            throw new PresentacionException(e.getMessage(), e);
        }
    }
    
    public boolean eliminarIngrediente(Long id) throws PresentacionException {
        try {
            return ingredienteBO.eliminarIngrediente(id);
        } catch (NegocioException e) {
            throw new PresentacionException(e.getMessage(), e);
        }
    }
    
    public List<IngredienteDTO> buscarTodos() throws PresentacionException {
        try {
            return ingredienteBO.buscarTodos();
        } catch (NegocioException e) {
            throw new PresentacionException(e.getMessage(), e);
        }
    }
    
    public IngredienteDTO buscarPorNombre(String nombre, UnidadMedida unidadMedida) throws PresentacionException {
        try {
            return ingredienteBO.buscarPorNombre(nombre, unidadMedida);
        } catch (NegocioException e) {
            throw new PresentacionException(e.getMessage(), e);
        }
    }
    
    public IngredienteDTO actualizarStock(Long id, int Stock) throws PresentacionException {
        try {
            return ingredienteBO.actualizarStock(id, Stock);
        } catch (NegocioException e) {
            throw new PresentacionException(e.getMessage(), e);
        }
    }
    
    
    
}
