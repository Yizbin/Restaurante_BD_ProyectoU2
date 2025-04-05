/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package moduloClientesDAO;

import Exception.PersistenciaException;
import ModuloClientesEntidades.ClienteFrecuente;
import java.util.List;

/**
 *
 * @author sonic
 */
public interface IClienteFrecuenteDAO {

    public ClienteFrecuente registrarClienteFrecuente(ClienteFrecuente clienteF) throws PersistenciaException;

    public ClienteFrecuente buscarClientePorID(Long idCliente) throws PersistenciaException;

    public List<ClienteFrecuente> buscarClientesPorNombre(String nombre) throws PersistenciaException;

    public List<ClienteFrecuente> buscarClientesPorTelefono(String telefono) throws PersistenciaException;

    public boolean existeClienteFrecuentePorTelefono(String telefono) throws PersistenciaException;

    public boolean existeClienteFrecuentePorNombre(String nombre) throws PersistenciaException;

    public ClienteFrecuente obtenerClienteFrecuentePorId(Long id) throws PersistenciaException;

    public boolean actualizarPuntosYGasto(Long idCliente, Double gastoNuevo, Integer puntosNuevos) throws PersistenciaException;
    
     public boolean incrementarVisitas(Long id )throws PersistenciaException;
     
     public List<ClienteFrecuente> obtenerTodosClientesFrecuentes() throws PersistenciaException;
      
}
