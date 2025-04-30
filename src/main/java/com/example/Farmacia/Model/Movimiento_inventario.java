package com.example.Farmacia.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Movimiento_inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Movimiento;

    @Column(columnDefinition = "TEXT")
    private String tipoMovi; // ENTRADA o SALIDA

    private Double cantidad;
    private LocalDate fechaMovi;

    @Column(length = 20)
    private String referencia; // ID de compra o venta

    @ManyToOne
    @JoinColumn(name = "id_inventario")
    private Inventario inventario;

    // Getters y setters
}
