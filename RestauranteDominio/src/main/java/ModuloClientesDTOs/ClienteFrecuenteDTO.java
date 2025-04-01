/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloClientesDTOs;

import java.util.Date;
import javax.persistence.Column;

/**
 *
 * @author isaac
 */
public class ClienteFrecuenteDTO {

    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String correo;
    private Date fechaRegistro;
    private int clienteTipo;
    private Integer puntos;
    private double gastoAcumulado;
    private double visitas;

    public ClienteFrecuenteDTO() {
    }

    public ClienteFrecuenteDTO(Long id, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correo, Date fechaRegistro, int clienteTipo, Integer puntos, double gastoAcumulado, double visitas) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaRegistro = fechaRegistro;
        this.clienteTipo = clienteTipo;
        this.puntos = puntos;
        this.gastoAcumulado = gastoAcumulado;
        this.visitas = visitas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getClienteTipo() {
        return clienteTipo;
    }

    public void setClienteTipo(int clienteTipo) {
        this.clienteTipo = clienteTipo;
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
