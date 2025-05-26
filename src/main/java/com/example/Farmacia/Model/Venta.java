package com.example.Farmacia.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Venta {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Venta;

    private LocalDate fechaVenta;
    private LocalTime horaVenta;
    @Column(precision = 10, scale = 2)
    private BigDecimal total;

    //private Double total;
    private String nombreEmpl; // Solo el nombre del empleado


    @ManyToOne(cascade = CascadeType.PERSIST)  // Permite que se persistan automáticamente las entidades Cliente y Empleado
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne(cascade = CascadeType.PERSIST)  // Permite que se persistan automáticamente las entidades Cliente y Empleado
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

   // Relación uno a muchos con Medicamento (o Producto si lo prefieres)
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Medicamentos> medicamentos;

    public Venta() {
    }

    public Venta(long id_Venta, LocalDate fechaVenta, LocalTime horaVenta, BigDecimal total, String nombreEmpl, Cliente cliente, Empleado empleado, List<Medicamentos> medicamentos) {
        this.id_Venta = id_Venta;
        this.fechaVenta = fechaVenta;
        this.horaVenta = horaVenta;
        this.total = total;
        this.nombreEmpl = nombreEmpl;
        this.cliente = cliente;
        this.empleado = empleado;
        this.medicamentos = medicamentos;
    }

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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
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

    public List<Medicamentos> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamentos> medicamentos) {
        this.medicamentos = medicamentos;
    }



}
