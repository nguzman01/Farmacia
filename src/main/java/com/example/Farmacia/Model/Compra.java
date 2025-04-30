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

    // Getters y setters
}
