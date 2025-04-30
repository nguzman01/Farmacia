package com.example.Farmacia.Model;

import jakarta.persistence.*;

@Entity
public class Proveedor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProveedor;

    @Column(length = 50)
    private String nombreProv;

    @Column(length = 100)
    private String direccionProv;

    @Column(length = 15)
    private String telefonoProv;

    // Getters y setters
}
