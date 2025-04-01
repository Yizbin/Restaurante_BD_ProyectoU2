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

    public ComandaDTO() {
    }

    public ComandaDTO(Long id, Date fechaHora, String folio, Estado estado, double totalAcumulado) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.folio = folio;
        this.estado = estado;
        this.totalAcumulado = totalAcumulado;
    }

    public ComandaDTO(Date fechaHora, String folio, Estado estado, double totalAcumulado) {
        this.fechaHora = fechaHora;
        this.folio = folio;
        this.estado = estado;
        this.totalAcumulado = totalAcumulado;
    }
    
    
    private Long id;
    private Date fechaHora;
    private String folio;
    private Estado estado;
    private double totalAcumulado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double getTotalAcumulado() {
        return totalAcumulado;
    }

    public void setTotalAcumulado(double totalAcumulado) {
        this.totalAcumulado = totalAcumulado;
    }
    
    
    
    

}
