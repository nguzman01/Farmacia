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

    // constructores

    public Inventario() {
    }

    public Inventario(Long id_inventario, Double existencias, Almacen almacen, Medicamentos medicamento) {
        this.id_inventario = id_inventario;
        this.existencias = existencias;
        this.almacen = almacen;
        this.medicamento = medicamento;
    }

    // Getters y setters


    public Long getId_inventario() {
        return id_inventario;
    }

    public void setId_inventario(Long id_inventario) {
        this.id_inventario = id_inventario;
    }

    public Double getExistencias() {
        return existencias;
    }

    public void setExistencias(Double existencias) {
        this.existencias = existencias;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    public Medicamentos getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamentos medicamento) {
        this.medicamento = medicamento;
    }
}
