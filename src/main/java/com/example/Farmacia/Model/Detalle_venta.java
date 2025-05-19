package com.example.Farmacia.Model;

import jakarta.persistence.*;

@Entity
public class Detalle_venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_DetalleVenta;
    private Double subtotal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_medicamento")
    private Medicamentos medicamento;

    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;

    // constructores

    public Detalle_venta() {
    }

    public Detalle_venta(long id_DetalleVenta, Double subtotal, Medicamentos medicamento, Venta venta) {
        this.id_DetalleVenta = id_DetalleVenta;
        this.subtotal = subtotal;
        this.medicamento = medicamento;
        this.venta = venta;
    }
// Getters y setters


    public long getId_DetalleVenta() {
        return id_DetalleVenta;
    }

    public void setId_DetalleVenta(long id_DetalleVenta) {
        this.id_DetalleVenta = id_DetalleVenta;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Medicamentos getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamentos medicamento) {
        this.medicamento = medicamento;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
}
