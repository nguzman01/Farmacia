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

    // constructor

    public Lote() {
    }

    public Lote(Integer idLote, LocalDate fechaIngreso, LocalDate fechaCaducidad, Double cantidad, Medicamentos medicamento) {
        this.idLote = idLote;
        this.fechaIngreso = fechaIngreso;
        this.fechaCaducidad = fechaCaducidad;
        this.cantidad = cantidad;
        this.medicamento = medicamento;
    }

    // Getters y setters


    public Integer getIdLote() {
        return idLote;
    }

    public void setIdLote(Integer idLote) {
        this.idLote = idLote;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Medicamentos getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamentos medicamento) {
        this.medicamento = medicamento;
    }
}
