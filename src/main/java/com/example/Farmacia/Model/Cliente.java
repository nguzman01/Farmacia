package com.example.Farmacia.Model;

import jakarta.persistence.*;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Cliente;

    @Column(length = 50)
    private String nombreCli;

    @Column(length = 100)
    private String direccionCli;

    @Column(length = 100)
    private String emailCli;

    @Column(length = 15)
    private String telefonoCli;

    // Getters y setters
}
