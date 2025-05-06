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

    // constructor

    public Movimiento_inventario() {
    }

    public Movimiento_inventario(long id_Movimiento, String tipoMovi, Double cantidad, LocalDate fechaMovi, String referencia, Inventario inventario) {
        this.id_Movimiento = id_Movimiento;
        this.tipoMovi = tipoMovi;
        this.cantidad = cantidad;
        this.fechaMovi = fechaMovi;
        this.referencia = referencia;
        this.inventario = inventario;
    }

    // Getters y setters


    public long getId_Movimiento() {
        return id_Movimiento;
    }

    public void setId_Movimiento(long id_Movimiento) {
        this.id_Movimiento = id_Movimiento;
    }

    public String getTipoMovi() {
        return tipoMovi;
    }

    public void setTipoMovi(String tipoMovi) {
        this.tipoMovi = tipoMovi;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFechaMovi() {
        return fechaMovi;
    }

    public void setFechaMovi(LocalDate fechaMovi) {
        this.fechaMovi = fechaMovi;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }
}
