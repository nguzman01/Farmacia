package com.example.Farmacia.Model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Venta {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Venta;

    private LocalDate fechaVenta;
    private LocalTime horaVenta;
    private Double total;
    private String nombreEmpl; // Solo el nombre del empleado


    @ManyToOne(cascade = CascadeType.PERSIST)  // Permite que se persistan automáticamente las entidades Cliente y Empleado
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne(cascade = CascadeType.PERSIST)  // Permite que se persistan automáticamente las entidades Cliente y Empleado
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    //contructores

    public Venta() {
    }

    public Venta(long id_Venta, LocalDate fechaVenta, LocalTime horaVenta, Double total, String nombreEmpl, Cliente cliente, Empleado empleado) {
        this.id_Venta = id_Venta;
        this.fechaVenta = fechaVenta;
        this.horaVenta = horaVenta;
        this.total = total;
        this.nombreEmpl = nombreEmpl;
        this.cliente = cliente;
        this.empleado = empleado;
    }
// Getters y setters


    public long getId_Venta() {
        return id_Venta;
    }

    public void setId_Venta(long id_Venta) {
        this.id_Venta = id_Venta;
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public LocalTime getHoraVenta() {
        return horaVenta;
    }

    public void setHoraVenta(LocalTime horaVenta) {
        this.horaVenta = horaVenta;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getNombreEmpl() {
        return nombreEmpl;
    }

    public void setNombreEmpl(String nombreEmpl) {
        this.nombreEmpl = nombreEmpl;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
