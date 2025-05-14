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
            //(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;

    // constructor

    public Marca() {
    }

    public Marca(Integer idMarca, String nombreMarc, String descripcion, Proveedor proveedor) {
        this.idMarca = idMarca;
        this.nombreMarc = nombreMarc;
        this.descripcion = descripcion;
        this.proveedor = proveedor;
    }

    // Getters y setters


    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombreMarc() {
        return nombreMarc;
    }

    public void setNombreMarc(String nombreMarc) {
        this.nombreMarc = nombreMarc;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
