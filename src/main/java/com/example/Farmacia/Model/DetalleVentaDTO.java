package com.example.Farmacia.Model;

    public class DetalleVentaDTO {
        private long idDetalleVenta;
        private String medicamento;
        private int cantidad;
        private double subtotal;

        public DetalleVentaDTO(long idDetalleVenta, String medicamento, int cantidad, double subtotal) {
            this.idDetalleVenta = idDetalleVenta;
            this.medicamento = medicamento;
            this.cantidad = cantidad;
            this.subtotal = subtotal;
        }

    // Getters y Setters


    public long getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(long idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}