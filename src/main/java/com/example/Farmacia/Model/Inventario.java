package com.example.Farmacia.Model;

import jakarta.persistence.*;

@Entity
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_inventario;

    private Double existencias;

    @ManyToOne
    @JoinColumn(name = "id_almacen")
    private Almacen almacen;

    @ManyToOne
    @JoinColumn(name = "id_medicamento")
    private Medicamentos medicamento;

    // Getters y setters
}
