package com.example.Farmacia.Service;

import com.example.Farmacia.Model.Compra;
import com.example.Farmacia.Repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    public List<Compra> listarCompras() {
        return compraRepository.findAll();
    }

    public Optional<Compra> listarCompraPorId(Long id) {
        return compraRepository.findById(id);
    }

    public Compra crearCompra(Compra compra) {
        if (compra.getFechaCompra() == null) {
            throw new IllegalArgumentException("La fecha de compra es requerida");
        }
        if (compra.getProveedor() == null) {
            throw new IllegalArgumentException("El proveedor es requerido");
        }
        // Podrías agregar más validaciones según reglas de negocio

        return compraRepository.save(compra);
    }



    public Compra actualizarCompra(Long id, Compra compraDetails) {
        Optional<Compra> optionalCompra = compraRepository.findById(id);
        if (optionalCompra.isEmpty()) {
            return null;
        }
        Compra compra = optionalCompra.get();

        if (compraDetails.getFechaCompra() == null) {
            throw new IllegalArgumentException("La fecha de compra es requerida");
        }
        if (compraDetails.getProveedor() == null) {
            throw new IllegalArgumentException("El proveedor es requerido");
        }

        compra.setFechaCompra(compraDetails.getFechaCompra());
        compra.setProveedor(compraDetails.getProveedor());
        compra.setMarca(compraDetails.getMarca());

        return compraRepository.save(compra);
    }

    public void eliminarCompraPorId(Long id) {
        if (!compraRepository.existsById(id)) {
            throw new IllegalArgumentException("La compra con id " + id + " no existe.");
        }
        compraRepository.deleteById(id);
    }
}
