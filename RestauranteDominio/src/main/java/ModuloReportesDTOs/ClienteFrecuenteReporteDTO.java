/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloReportesDTOs;

import java.time.LocalDate;

/**
 *
 * @author sonic
 */
public class ClienteFrecuenteReporteDTO {

    private String nombreCompleto;
    private Integer visitas;
    private Integer puntos;
    private Double totalGastado;
    private LocalDate fechaUltimaComanda;

    
    public ClienteFrecuenteReporteDTO() {
    }

    public ClienteFrecuenteReporteDTO(String nombreCompleto, Integer visitas, Integer puntos, Double totalGastado, LocalDate fechaUltimaComanda) {
        this.nombreCompleto = nombreCompleto;
        this.visitas = visitas;
        this.puntos = puntos;
        this.totalGastado = totalGastado;
        this.fechaUltimaComanda = fechaUltimaComanda;
    }
    
    

    
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Integer getVisitas() {
        return visitas;
    }

    public void setVisitas(Integer visitas) {
        this.visitas = visitas;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public Double getTotalGastado() {
        return totalGastado;
    }

    public void setTotalGastado(Double totalGastado) {
        this.totalGastado = totalGastado;
    }

    public LocalDate getFechaUltimaComanda() {
        return fechaUltimaComanda;
    }

    public void setFechaUltimaComanda(LocalDate fechaUltimaComanda) {
        this.fechaUltimaComanda = fechaUltimaComanda;
    }
}
