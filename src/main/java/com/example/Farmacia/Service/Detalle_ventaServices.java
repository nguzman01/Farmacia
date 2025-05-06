package com.example.Farmacia.Service;

import com.example.Farmacia.Model.Detalle_venta;
import com.example.Farmacia.Repository.Detalle_ventaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class Detalle_ventaServices {

    @Autowired
    private Detalle_ventaRepository detalleVentaRepository;

    public List<Detalle_venta> findAll() {
        return detalleVentaRepository.findAll();
    }

    public Optional<Detalle_venta> findById(Long id) {
        return detalleVentaRepository.findById(id);
    }

    public Detalle_venta save(Detalle_venta detalleVenta) {
        if (detalleVenta.getSubtotal() == null || detalleVenta.getSubtotal() < 0) {
            throw new IllegalArgumentException("El subtotal debe ser mayor o igual a cero.");
        }
        if (detalleVenta.getMedicamento() == null) {
            throw new IllegalArgumentException("El medicamento es requerido.");
        }
        if (detalleVenta.getVenta() == null) {
            throw new IllegalArgumentException("La venta es requerida.");
        }
        return detalleVentaRepository.save(detalleVenta);
    }

    public Detalle_venta update(Long id, Detalle_venta detalleVentaDetails) {
        Optional<Detalle_venta> optionalDetalleVenta = detalleVentaRepository.findById(id);
        if (optionalDetalleVenta.isEmpty()) {
            return null;
        }
        Detalle_venta detalleVenta = optionalDetalleVenta.get();

        if (detalleVentaDetails.getSubtotal() == null || detalleVentaDetails.getSubtotal() < 0) {
            throw new IllegalArgumentException("El subtotal debe ser mayor o igual a cero.");
        }
        if (detalleVentaDetails.getMedicamento() == null) {
            throw new IllegalArgumentException("El medicamento es requerido.");
        }
        if (detalleVentaDetails.getVenta() == null) {
            throw new IllegalArgumentException("La venta es requerida.");
        }

        detalleVenta.setSubtotal(detalleVentaDetails.getSubtotal());
        detalleVenta.setMedicamento(detalleVentaDetails.getMedicamento());
        detalleVenta.setVenta(detalleVentaDetails.getVenta());

        return detalleVentaRepository.save(detalleVenta);
    }

    public void deleteById(Long id) {
        if (!detalleVentaRepository.existsById(id)) {
            throw new IllegalArgumentException("El detalle de venta con id " + id + " no existe.");
        }
        detalleVentaRepository.deleteById(id);
    }
}
