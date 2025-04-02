/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloComandaEntidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author isaac
 */
@Entity
public class Mesa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "numero_mesa", unique = true, nullable = false)
    private Integer numeroMesa;
    
    @OneToMany(mappedBy = "mesa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comanda> comandas = new ArrayList<>();

    public Mesa() {
    }

    public Mesa(Long id, Integer numeroMesa) {
        this.id = id;
        this.numeroMesa = numeroMesa;
    }

    public Mesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public List<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
    }

    
    

    

    
    
}
