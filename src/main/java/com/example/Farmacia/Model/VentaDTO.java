package com.example.Farmacia.Model;
import java.time.LocalDate;
import java.util.List;

public class VentaDTO {
    private long idVenta;
    private String fechaVenta;
    private double total;
    private List<DetalleVentaDTO> detalles;

    // Datos de cliente
    private String nombreCliente;
    private String correoCliente;
    private String telefonoCliente;

    // Solo nombre del empleado
    private String nombreEmpleado;

    public VentaDTO(long idVenta, String fechaVenta, double total, List<DetalleVentaDTO> detalles,
                    String nombreCliente, String correoCliente, String telefonoCliente,
                    String nombreEmpleado) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.total = total;
        this.detalles = detalles;
        this.nombreCliente = nombreCliente;
        this.correoCliente = correoCliente;
        this.telefonoCliente = telefonoCliente;
        this.nombreEmpleado = nombreEmpleado;
    }


    // Getters y setters


    public long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(long idVenta) {
        this.idVenta = idVenta;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<DetalleVentaDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVentaDTO> detalles) {
        this.detalles = detalles;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }
}