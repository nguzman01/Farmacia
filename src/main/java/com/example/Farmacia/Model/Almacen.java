package com.example.Farmacia.Model;

import jakarta.persistence.*;

@Entity
public class Almacen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAlmacen;

    @Column(length = 50)
    private String nombreAlmac;

    // Puedes agregar nombre o ubicación si lo necesitas
}
