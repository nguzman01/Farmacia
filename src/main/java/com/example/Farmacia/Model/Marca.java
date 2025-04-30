package com.example.Farmacia.Model;

import jakarta.persistence.*;

@Entity
public class Marca {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMarca;

    @Column(length = 50)
    private String nombreMarc;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;

    // Getters y setters
}
