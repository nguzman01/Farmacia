package com.example.Farmacia.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Compra;

    private LocalDate fechaCompra;

    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    // constructor

    public Compra() {
    }

    public Compra(long id_Compra, LocalDate fechaCompra, Proveedor proveedor, Marca marca) {
        this.id_Compra = id_Compra;
        this.fechaCompra = fechaCompra;
        this.proveedor = proveedor;
        this.marca = marca;
    }

    // Getters y setters


    public long getId_Compra() {
        return id_Compra;
    }

    public void setId_Compra(long id_Compra) {
        this.id_Compra = id_Compra;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
}
