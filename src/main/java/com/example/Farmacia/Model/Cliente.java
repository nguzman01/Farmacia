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

    //constructores

    public Cliente() {
    }

    public Cliente(Long id_Cliente, String nombreCli, String direccionCli, String emailCli, String telefonoCli) {
        this.id_Cliente = id_Cliente;
        this.nombreCli = nombreCli;
        this.direccionCli = direccionCli;
        this.emailCli = emailCli;
        this.telefonoCli = telefonoCli;
    }

    // Getters y setters


    public Long getId_Cliente() {
        return id_Cliente;
    }

    public void setId_Cliente(Long id_Cliente) {
        this.id_Cliente = id_Cliente;
    }

    public String getNombreCli() {
        return nombreCli;
    }

    public void setNombreCli(String nombreCli) {
        this.nombreCli = nombreCli;
    }

    public String getDireccionCli() {
        return direccionCli;
    }

    public void setDireccionCli(String direccionCli) {
        this.direccionCli = direccionCli;
    }

    public String getEmailCli() {
        return emailCli;
    }

    public void setEmailCli(String emailCli) {
        this.emailCli = emailCli;
    }

    public String getTelefonoCli() {
        return telefonoCli;
    }

    public void setTelefonoCli(String telefonoCli) {
        this.telefonoCli = telefonoCli;
    }
}
