/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloComandaEntidades;

import Enums.Estado;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import ModuloClientesEntidades.ClienteFrecuente;

/**
 *
 * @author isaac
 */
@Entity
@Table(name = "comandas")
public class Comanda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comanda")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = true)
    private ClienteFrecuente clienteFrecuente;
    
    @ManyToOne
    @JoinColumn(name = "numero_mesa", nullable = false)
    private Mesa mesa;


    @OneToMany(mappedBy = "comanda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleComanda> detalles = new ArrayList<>();

    @Column(name = "total_acumulado", nullable = false)
    private double totalAcumulado;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "folio", unique = true, length = 15, nullable = false)
    private String folio;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

    public Comanda(Long id, ClienteFrecuente clienteFrecuente, Mesa mesa, double totalAcumulado, LocalDateTime fechaHora, String folio, Estado estado) {
        this.id = id;
        this.clienteFrecuente = clienteFrecuente;
        this.mesa = mesa;
        this.totalAcumulado = totalAcumulado;
        this.fechaHora = fechaHora;
        this.folio = folio;
        this.estado = estado;
    }

    public Comanda() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteFrecuente getClienteFrecuente() {
        return clienteFrecuente;
    }

    public void setClienteFrecuente(ClienteFrecuente clienteFrecuente) {
        this.clienteFrecuente = clienteFrecuente;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public List<DetalleComanda> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleComanda> detalles) {
        this.detalles = detalles;
    }

    public double getTotalAcumulado() {
        return totalAcumulado;
    }

    public void setTotalAcumulado(double totalAcumulado) {
        this.totalAcumulado = totalAcumulado;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
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

    
    
    

}
