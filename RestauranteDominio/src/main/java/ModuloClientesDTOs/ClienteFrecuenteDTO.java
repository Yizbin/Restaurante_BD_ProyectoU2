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

}
