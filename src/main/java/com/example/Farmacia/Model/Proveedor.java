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

    // constructor

    public Proveedor() {
    }

    public Proveedor(Long idProveedor, String nombreProv, String direccionProv, String telefonoProv) {
        this.idProveedor = idProveedor;
        this.nombreProv = nombreProv;
        this.direccionProv = direccionProv;
        this.telefonoProv = telefonoProv;
    }
    // Getters y setters


    public Long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProv() {
        return nombreProv;
    }

    public void setNombreProv(String nombreProv) {
        this.nombreProv = nombreProv;
    }

    public String getDireccionProv() {
        return direccionProv;
    }

    public void setDireccionProv(String direccionProv) {
        this.direccionProv = direccionProv;
    }

    public String getTelefonoProv() {
        return telefonoProv;
    }

    public void setTelefonoProv(String telefonoProv) {
        this.telefonoProv = telefonoProv;
    }
}
