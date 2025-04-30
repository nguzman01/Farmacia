package com.example.Farmacia.Model;

import jakarta.persistence.*;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoria;

    @Column(length = 50)
    private String nombreCat;

    // Getters y setters
}
