package com.example.Farmacia.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLote;

    private LocalDate fechaIngreso;
    private LocalDate fechaCaducidad;
    private Double cantidad;

    @ManyToOne
    @JoinColumn(name = "id_medicamento")
    private Medicamentos medicamento;

    // Getters y setters
}
