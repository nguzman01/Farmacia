package com.example.Farmacia.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Movimiento_inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMovimiento;

    @Column(columnDefinition = "TEXT")
    private String tipoMovi; // ENTRADA o SALIDA

    private Integer cantidad;
    private LocalDate fechaMovi;

    @Column(length = 20)
    private String referencia; // ID de compra o venta

    @ManyToOne
    @JoinColumn(name = "id_inventario")
    private Inventario inventario;

    // constructo

    public Movimiento_inventario() {
    }

    public Movimiento_inventario(long id_Movimiento, String tipoMovi, Integer cantidad, LocalDate fechaMovi, String referencia, Inventario inventario) {
        this.idMovimiento = id_Movimiento;
        this.tipoMovi = tipoMovi;
        this.cantidad = cantidad;
        this.fechaMovi = fechaMovi;
        this.referencia = referencia;
        this.inventario = inventario;
    }

    // Getters y setters


    public long getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(long idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public String getTipoMovi() {
        return tipoMovi;
    }

    public void setTipoMovi(String tipoMovi) {
        this.tipoMovi = tipoMovi;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
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
