package com.example.Farmacia.Model;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Empleado;

    @Column(length = 50)
    private String nombreEmpl;

    @Column(length = 100)
    private String direccionEmpl;

    @Column(length = 100)
    private String emailEmpl;

    @Column(length = 15)
    private String telefonoEmpl;

    private LocalTime horaEntrada;
    private LocalTime horaSalida;

    @Column(length = 10)
    private String contraseña;

    // Getters y setters
}
