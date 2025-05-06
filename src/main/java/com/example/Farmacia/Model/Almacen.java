package com.example.Farmacia.Model;

import jakarta.persistence.*;

@Entity
public class Almacen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAlmacen;

    @Column(length = 50)
    private String nombreAlmac;

    // constructor

    public Almacen() {
    }

    public Almacen(Integer idAlmacen, String nombreAlmac) {
        this.idAlmacen = idAlmacen;
        this.nombreAlmac = nombreAlmac;
    }

    // getter y Setter


    public Integer getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getNombreAlmac() {
        return nombreAlmac;
    }

    public void setNombreAlmac(String nombreAlmac) {
        this.nombreAlmac = nombreAlmac;
    }
}
