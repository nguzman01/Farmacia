package com.example.Farmacia.Model;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class Medicamentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMedicamento;

    @Column(length = 50)
    private String nombreMed;

    private LocalDate fechaRegistro;

    @Column(columnDefinition = "TEXT")
    private String presentacion;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private Double precio;

    @Column(length = 12)
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;


    // constructores
    public Medicamentos() {
    }

    public Medicamentos(Integer idMedicamento, String nombreMed, LocalDate fechaRegistro, String presentacion, String descripcion, Double precio, String codigo, Marca marca, Categoria categoria) {
        this.idMedicamento = idMedicamento;
        this.nombreMed = nombreMed;
        this.fechaRegistro = fechaRegistro;
        this.presentacion = presentacion;
        this.descripcion = descripcion;
        this.precio = precio;
        this.codigo = codigo;
        this.marca = marca;
        this.categoria = categoria;
    }

    // Getters y setters


    public Integer getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(Integer idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getNombreMed() {
        return nombreMed;
    }

    public void setNombreMed(String nombreMed) {
        this.nombreMed = nombreMed;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
