/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloReportesDTOs;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author sonic
 */
public class ComandaReporteDTO {

    private LocalDate fecha;
    private LocalTime hora;
    private String nombreCliente;
    private String estado;
    private Double totalVenta;
    private Integer numeroMesa;

    public ComandaReporteDTO() {
    }

    public ComandaReporteDTO(LocalDate fecha, LocalTime hora, String nombreCliente, String estado, Double totalVenta, Integer numeroMesa) {
        this.fecha = fecha;
        this.hora = hora;
        this.nombreCliente = nombreCliente;
        this.estado = estado;
        this.totalVenta = totalVenta;
        this.numeroMesa = numeroMesa;
    }
    

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public Integer getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    @Override
    public String toString() {
        return "ComandaReporteDTO{" + "fecha=" + fecha + ", hora=" + hora + ", nombreCliente=" + nombreCliente + ", estado=" + estado + ", totalVenta=" + totalVenta + ", numeroMesa=" + numeroMesa + '}';
    }
    
    

}
