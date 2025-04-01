/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moduloClienteEntidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


/**
 *
 * @author isaac
 */
@Entity
@DiscriminatorValue("1")
public class ClienteFrecuente extends Cliente implements Serializable {

    @Column(name = "puntos", nullable = false)
    private Integer puntos;

    @Column(name = "gasto_acumulado", nullable = false)
    private double gastoAcumulado;

    @Column(name = "visitas", nullable = false)
    private double visitas;

    public ClienteFrecuente() {
    }

    public ClienteFrecuente(Integer puntos, double gastoAcumulado, double visitas) {
        this.puntos = puntos;
        this.gastoAcumulado = gastoAcumulado;
        this.visitas = visitas;
    }

    public ClienteFrecuente(Integer puntos, double gastoAcumulado, double visitas, Long id, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correo, Date fechaRegistro, int clienteTipo) {
        super(id, nombre, apellidoPaterno, apellidoMaterno, telefono, correo, fechaRegistro, clienteTipo);
        this.puntos = puntos;
        this.gastoAcumulado = gastoAcumulado;
        this.visitas = visitas;
    }

    public ClienteFrecuente(Integer puntos, double gastoAcumulado, double visitas, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correo, Date fechaRegistro, int clienteTipo) {
        super(nombre, apellidoPaterno, apellidoMaterno, telefono, correo, fechaRegistro, clienteTipo);
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

    public void setGastoAcumulado(double gastoAcumulado) {
        this.gastoAcumulado = gastoAcumulado;
    }

    public double getVisitas() {
        return visitas;
    }

    public void setVisitas(double visitas) {
        this.visitas = visitas;
    }
    
    

}
