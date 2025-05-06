package com.example.Farmacia.Service;

import com.example.Farmacia.Model.Detalle_compra;
import com.example.Farmacia.Repository.Detalle_compraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Detalle_compraServices {

    @Autowired
    private Detalle_compraRepository detalleCompraRepository;

    public List<Detalle_compra> findAll() {
        return detalleCompraRepository.findAll();
    }

    public Optional<Detalle_compra> findById(Long id) {
        return detalleCompraRepository.findById(id);
    }

    public Detalle_compra save(Detalle_compra detalleCompra) {
        if (detalleCompra.getCantidad() == null || detalleCompra.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        }
        if (detalleCompra.getPrecioComp() == null || detalleCompra.getPrecioComp() < 0) {
            throw new IllegalArgumentException("El precio debe ser mayor o igual a cero.");
        }
        if (detalleCompra.getCompra() == null) {
            throw new IllegalArgumentException("La compra es requerida.");
        }
        return detalleCompraRepository.save(detalleCompra);
    }

    public Detalle_compra update(Long id, Detalle_compra detalleCompraDetails) {
        Optional<Detalle_compra> optionalDetalleCompra = detalleCompraRepository.findById(id);
        if (optionalDetalleCompra.isEmpty()) {
            return null;
        }
        Detalle_compra detalleCompra = optionalDetalleCompra.get();

        if (detalleCompraDetails.getCantidad() == null || detalleCompraDetails.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        }
        if (detalleCompraDetails.getPrecioComp() == null || detalleCompraDetails.getPrecioComp() < 0) {
            throw new IllegalArgumentException("El precio debe ser mayor o igual a cero.");
        }
        if (detalleCompraDetails.getCompra() == null) {
            throw new IllegalArgumentException("La compra es requerida.");
        }

        detalleCompra.setCantidad(detalleCompraDetails.getCantidad());
        detalleCompra.setPrecioComp(detalleCompraDetails.getPrecioComp());
        detalleCompra.setCompra(detalleCompraDetails.getCompra());

        return detalleCompraRepository.save(detalleCompra);
    }

    public void deleteById(Long id) {
        if (!detalleCompraRepository.existsById(id)) {
            throw new IllegalArgumentException("El detalle compra con id " + id + " no existe.");
        }
        detalleCompraRepository.deleteById(id);
    }
}
