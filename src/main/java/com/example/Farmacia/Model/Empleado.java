package com.example.Farmacia.Model;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long idEmpleado;

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

    // constructores

    public Empleado() {
    }

    public Empleado(long idEmpleado, String nombreEmpl, String direccionEmpl, String emailEmpl, String telefonoEmpl, LocalTime horaEntrada, LocalTime horaSalida, String contraseña) {
        this.idEmpleado = idEmpleado;
        this.nombreEmpl = nombreEmpl;
        this.direccionEmpl = direccionEmpl;
        this.emailEmpl = emailEmpl;
        this.telefonoEmpl = telefonoEmpl;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.contraseña = contraseña;
    }

    // Getters y setters


    public long getId_Empleado() {
        return idEmpleado;
    }

    public void setId_Empleado(long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmpl() {
        return nombreEmpl;
    }

    public void setNombreEmpl(String nombreEmpl) {
        this.nombreEmpl = nombreEmpl;
    }

    public String getDireccionEmpl() {
        return direccionEmpl;
    }

    public void setDireccionEmpl(String direccionEmpl) {
        this.direccionEmpl = direccionEmpl;
    }

    public String getEmailEmpl() {
        return emailEmpl;
    }

    public void setEmailEmpl(String emailEmpl) {
        this.emailEmpl = emailEmpl;
    }

    public String getTelefonoEmpl() {
        return telefonoEmpl;
    }

    public void setTelefonoEmpl(String telefonoEmpl) {
        this.telefonoEmpl = telefonoEmpl;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
