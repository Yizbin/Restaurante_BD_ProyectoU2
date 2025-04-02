/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package PruebasDominio;

import Enums.Estado;
import Enums.EstadoProducto;
import Enums.TipoPlatillo;
import ModuloClientesEntidades.ClienteFrecuente;
import ModuloComandaEntidades.Comanda;
import ModuloComandaEntidades.DetalleComanda;
import ModuloComandaEntidades.Mesa;
import ModuloIngredientesEntidades.Ingrediente;
import ModuloProductosEntidades.Producto;
import ModuloProductosEntidades.ProductoOcupaIngrediente;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author sonic
 */
public class PruebasDominio {

    private static LocalDate ultimaFecha = null;
    private static int contador = 0;

    /**
     * @param dateTime
     * @return 
     */
    public static synchronized String generarFolio(LocalDateTime dateTime) {
        // Se extrae la fecha (sin la hora) del objeto LocalDateTime
        LocalDate fechaActual = dateTime.toLocalDate();

        // Si es la primera vez o la fecha es diferente, se reinicia el contador
        if (ultimaFecha == null || !ultimaFecha.equals(fechaActual)) {
            contador = 1;
            ultimaFecha = fechaActual;
        } else {
            contador++;
        }

        // Se formatea la fecha en el patrón YYYYMMDD
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyyMMdd");
        String dateFormatted = fechaActual.format(formater);

        // Se formatea el contador para que tenga siempre tres dígitos (ej. 001, 002, ...)
        String counterFormatted = String.format("%03d", contador);

        // Se construye y retorna el folio en el formato solicitado
        return "OB-" + dateFormatted + "-" + counterFormatted;
    }

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConexionPU");
        EntityManager em = emf.createEntityManager();

        //TABLA INGREDIENTES
        em.getTransaction().begin();
        Ingrediente ingrediente = new Ingrediente("tortillas harina", 10, "piezas");
        Ingrediente ingrediente1 = new Ingrediente("carne de burro", 10, "gr");
        Ingrediente ingrediente2 = new Ingrediente("agua", 1000, "ml");
        Ingrediente ingrediente3 = new Ingrediente("jamaica", 100, "piezas");
        em.persist(ingrediente);
        em.persist(ingrediente1);
        em.persist(ingrediente2);
        em.persist(ingrediente3);

        //TABLA PRODUCTOS
        Producto producto1 = new Producto();
        producto1.setNombre("taco de burro");
        producto1.setPrecio(35.0);
        producto1.setTipo(TipoPlatillo.ENTRADA);
        producto1.setEstadoProducto(EstadoProducto.HABILITADO);
        producto1.setDescripcion("Un taco con doble tortilla y carne de burro especialidad de la casa");

        Producto producto2 = new Producto();
        producto2.setNombre("Agua de jamaica");
        producto2.setPrecio(20.0);
        producto2.setTipo(TipoPlatillo.BEBIDA);
        producto2.setEstadoProducto(EstadoProducto.HABILITADO);
        producto2.setDescripcion("Agua de jamaica refrescante");

        em.persist(producto1);
        em.persist(producto2);
        em.persist(ingrediente);
        em.persist(ingrediente1);

        //TABLA PRODUCTO OCUPA INGREDIENTES
        ProductoOcupaIngrediente prodCarneBurro = new ProductoOcupaIngrediente();
        prodCarneBurro.setProducto(producto1);
        prodCarneBurro.setIngrediente(ingrediente1);
        prodCarneBurro.setCantidadRequerida(100);

        ProductoOcupaIngrediente prodTortilla = new ProductoOcupaIngrediente();
        prodTortilla.setProducto(producto1);
        prodTortilla.setIngrediente(ingrediente);
        prodTortilla.setCantidadRequerida(2);

        ProductoOcupaIngrediente prodAgua = new ProductoOcupaIngrediente();
        prodAgua.setProducto(producto2);
        prodAgua.setIngrediente(ingrediente2);
        prodAgua.setCantidadRequerida(500);

        ProductoOcupaIngrediente prodJamaica = new ProductoOcupaIngrediente();
        prodJamaica.setProducto(producto2);
        prodJamaica.setIngrediente(ingrediente3);
        prodJamaica.setCantidadRequerida(30);

        producto1.getProductos().add(prodCarneBurro);
        producto1.getProductos().add(prodTortilla);

        ingrediente.getIngredientes().add(prodTortilla);
        ingrediente1.getIngredientes().add(prodCarneBurro);

        producto2.getProductos().add(prodAgua);
        producto2.getProductos().add(prodJamaica);

        ingrediente2.getIngredientes().add(prodAgua);
        ingrediente3.getIngredientes().add(prodJamaica);

        em.persist(prodCarneBurro);
        em.persist(prodTortilla);
        em.persist(prodAgua);
        em.persist(prodJamaica);

        //TABLA MESA
        Mesa mesa1 = new Mesa();
        mesa1.setNumeroMesa(1);

        em.persist(mesa1);

        //TABLA CLIENTES FRECUENTES
//        ClienteFrecuente cliente1 = new ClienteFrecuente();
//        cliente1.setNombre("Gilberto");
//        cliente1.setApellidoPaterno("paredes");
//        cliente1.setApellidoMaterno("Flores");
//        cliente1.setClienteTipo(1);
//        cliente1.setCorreo("sonic15622@gmail.com");
//        cliente1.setFechaRegistro(new Date());
//        cliente1.setGastoAcumulado(0);
//        cliente1.setPuntos(0);
//        cliente1.setVisitas(0);
//        cliente1.setTelefono("6442595242");
        
//        em.persist(cliente1);

        //TABLA COMANDAS
        LocalDateTime fechaComanda1 = LocalDateTime.now();
        String folio1 = generarFolio(fechaComanda1);
        
        Comanda comanda1 = new Comanda();
//        comanda1.setClienteFrecuente(cliente1);
        comanda1.setEstado(Estado.ABIERTA);
        comanda1.setFechaHora(fechaComanda1);
        comanda1.setFolio(folio1);
        comanda1.setMesa(mesa1);
        
        
        

        //TABLA DETALLE COMANDA
        DetalleComanda detalle1 = new DetalleComanda();
        detalle1.setProducto(producto1);
        detalle1.setCantidadUnidades(3);
        detalle1.setNota("bien cocidos");
        detalle1.setPrecioUnitario(detalle1.getProducto().getPrecio());
        detalle1.setImporteProducto(detalle1.getCantidadUnidades() * detalle1.getPrecioUnitario());
        detalle1.setComanda(comanda1);
        
        DetalleComanda detalle2 = new DetalleComanda();
        detalle2.setProducto(producto2);
        detalle2.setCantidadUnidades(1);
        detalle2.setNota(null);
        detalle2.setPrecioUnitario(detalle2.getProducto().getPrecio());
        detalle2.setImporteProducto(detalle2.getCantidadUnidades() * detalle2.getPrecioUnitario());
        detalle2.setComanda(comanda1);
        
        comanda1.getDetalles().add(detalle1);
        comanda1.getDetalles().add(detalle2);
        
        em.persist(detalle1);
        em.persist(detalle2);
        em.persist(comanda1);

        
        em.getTransaction().commit();
        //////////////////////////////////

        em.close();
        emf.close();
    }

}
