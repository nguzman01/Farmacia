package com.example.Farmacia.Model;

import jakarta.persistence.*;

@Entity
public class Detalle_venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_DetalleVenta;

    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "id_medicamento")
    private Medicamentos medicamento;

    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;

    // Getters y setters
}
