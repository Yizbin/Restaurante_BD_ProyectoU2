/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloClientesEntidades;

import ModuloComandaEntidades.Comanda;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


/**
 *
 * @author isaac
 */
@Entity
@Table(name = "clientes_frecuentes")
@PrimaryKeyJoinColumn( name = "id_cliente_frecuente")
@DiscriminatorValue("1")

public class ClienteFrecuente extends Cliente  {
    
    private static final long serialVersionUID = 1L;

    @Column(name = "puntos", nullable = false)
    private Integer puntos;

    @Column(name = "gasto_acumulado", nullable = false)
    private Double gastoAcumulado;

    @Column(name = "visitas", nullable = false)
    private Integer visitas;
    
    

    public ClienteFrecuente() {
        super();
    }
    
    //constructor con lo de ClienteFrecuente
    public ClienteFrecuente(Integer puntos, Double gastoAcumulado, Integer visitas) {
        this.puntos = puntos;
        this.gastoAcumulado = gastoAcumulado;
        this.visitas = visitas;
      
    }
    //constructor con cleinte y  clienteFrecuente ( SIN ID )

    public ClienteFrecuente(Integer puntos, Double gastoAcumulado, Integer visitas, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correo, Date fechaRegistro) {
        super(nombre, apellidoPaterno, apellidoMaterno, telefono, correo, fechaRegistro);
        this.puntos = puntos;
        this.gastoAcumulado = gastoAcumulado;
        this.visitas = visitas;
        
    }
    // constructor con TODO de los dos clientes

    public ClienteFrecuente(Integer puntos, Double gastoAcumulado, Integer visitas, Long id, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correo, Date fechaRegistro) {
        super(id, nombre, apellidoPaterno, apellidoMaterno, telefono, correo, fechaRegistro);
        this.puntos = puntos;
        this.gastoAcumulado = gastoAcumulado;
        this.visitas = visitas;
       
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public double getGastoAcumulado() {
        return gastoAcumulado;
    }

    public void setGastoAcumulado(Double gastoAcumulado) {
        this.gastoAcumulado = gastoAcumulado;
    }

    public Integer getVisitas() {
        return visitas;
    }

    public void setVisitas(Integer visitas) {
        this.visitas = visitas;
    }

   

    
    
    
    
    

   

  
    

    

}
