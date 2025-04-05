/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package PruebasPersistencia;

import Exception.PersistenciaException;
import ModuloClientesEntidades.ClienteFrecuente;
import java.util.Date;
import moduloClientesDAO.ClienteFrecuenteDAO;
import moduloClientesDAO.IClienteFrecuenteDAO;

/**
 *
 * @author isaac
 */
public class PruebasClientes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PersistenciaException {
        
         IClienteFrecuenteDAO clienteFrecuenteDAO = ClienteFrecuenteDAO.getInstance();
         
         Date fechaLoca = new Date();
         ClienteFrecuente cliente1 = new ClienteFrecuente(0, 0D, 0, "Fabricio", "Choix", "Chong", "6444332234", "choixo@gmail.com", fechaLoca);
         ClienteFrecuente cliente2 = new ClienteFrecuente(0, 0D, 0, "Ramon", "Ramiro", "Ramirez", "6244354234", "ramonsito@gmail.com", fechaLoca);
         
         clienteFrecuenteDAO.registrarClienteFrecuente(cliente1); // SE CREAN LOS CLIENTES Y SE REGISTRAN
         clienteFrecuenteDAO.registrarClienteFrecuente(cliente2);
         
         clienteFrecuenteDAO.actualizarPuntosYGasto(1L, 100.0, 20);
         
         
         
         
        
    }
    
}
