package com.example.Farmacia.Model;

import jakarta.persistence.*;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCategoria;

    @Column(length = 50)
    private String nombreCat;

    // constructor

    public Categoria() {
    }

    public Categoria(long idCategoria, String nombreCat) {
        this.idCategoria = idCategoria;
        this.nombreCat = nombreCat;
    }

    // Getters y setters


    public long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCat() {
        return nombreCat;
    }

    public void setNombreCat(String nombreCat) {
        this.nombreCat = nombreCat;
    }
}
