package com.example.Farmacia.Model;

import jakarta.persistence.*;

@Entity
public class Detalle_compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_DetalleCompra;

    private Double precioComp;
    private Double cantidad;

    @ManyToOne
    @JoinColumn(name = "id_compra")
    private Compra compra;

    // constructores

    public Detalle_compra() {
    }

    public Detalle_compra(long id_DetalleCompra, Double precioComp, Double cantidad, Compra compra) {
        this.id_DetalleCompra = id_DetalleCompra;
        this.precioComp = precioComp;
        this.cantidad = cantidad;
        this.compra = compra;
    }

    // Getters y setters

    public long getId_DetalleCompra() {
        return id_DetalleCompra;
    }

    public void setId_DetalleCompra(long id_DetalleCompra) {
        this.id_DetalleCompra = id_DetalleCompra;
    }

    public Double getPrecioComp() {
        return precioComp;
    }

    public void setPrecioComp(Double precioComp) {
        this.precioComp = precioComp;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }
}
