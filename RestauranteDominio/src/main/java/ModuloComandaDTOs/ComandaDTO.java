/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloComandaDTOs;

import Enums.Estado;
import java.util.Date;

/**
 *
 * @author isaac
 */
public class ComandaDTO {
 
    private Date fechaHora;
    private String folio;
    private Estado estado;
    private Integer numeroMesa;
    private double totalAcumulado;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;

    public ComandaDTO() {
    }

    public ComandaDTO(Date fechaHora, String folio, Estado estado, Integer numeroMesa, double totalAcumulado, String nombre, String apellidoPaterno, String apellidoMaterno) {
        this.fechaHora = fechaHora;
        this.folio = folio;
        this.estado = estado;
        this.numeroMesa = numeroMesa;
        this.totalAcumulado = totalAcumulado;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Integer getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public double getTotalAcumulado() {
        return totalAcumulado;
    }

    public void setTotalAcumulado(double totalAcumulado) {
        this.totalAcumulado = totalAcumulado;
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

    

}
