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

    // Getters y setters
}
