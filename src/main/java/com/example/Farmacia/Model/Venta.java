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

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    // Getters y setters
}
