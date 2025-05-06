package com.example.Farmacia.Service;

import com.example.Farmacia.Model.Movimiento_inventario;
import com.example.Farmacia.Repository.Movimiento_inventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Movimiento_inventarioService {

    @Autowired
    private Movimiento_inventarioRepository movimientoInventarioRepository;

    public List<Movimiento_inventario> findAll() {
        return movimientoInventarioRepository.findAll();
    }

    public Optional<Movimiento_inventario> findById(Long id) {
        return movimientoInventarioRepository.findById(id);
    }

    public Movimiento_inventario save(Movimiento_inventario movimientoInventario) {
        if (movimientoInventario.getTipoMovi() == null || movimientoInventario.getTipoMovi().trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de movimiento es requerido.");
        }
        if (!(movimientoInventario.getTipoMovi().equalsIgnoreCase("ENTRADA") || movimientoInventario.getTipoMovi().equalsIgnoreCase("SALIDA"))) {
            throw new IllegalArgumentException("El tipo de movimiento debe ser 'ENTRADA' o 'SALIDA'.");
        }
        if (movimientoInventario.getCantidad() == null || movimientoInventario.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        }
        if (movimientoInventario.getFechaMovi() == null) {
            throw new IllegalArgumentException("La fecha del movimiento es requerida.");
        }
        if (movimientoInventario.getInventario() == null) {
            throw new IllegalArgumentException("El inventario es requerido.");
        }
        return movimientoInventarioRepository.save(movimientoInventario);
    }

    public Movimiento_inventario update(Long id, Movimiento_inventario movimientoInventarioDetails) {
        Optional<Movimiento_inventario> optionalMovimiento = movimientoInventarioRepository.findById(id);
        if (optionalMovimiento.isEmpty()) {
            return null;
        }
        Movimiento_inventario movimientoInventario = optionalMovimiento.get();

        if (movimientoInventarioDetails.getTipoMovi() == null || movimientoInventarioDetails.getTipoMovi().trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de movimiento es requerido.");
        }
        if (!(movimientoInventarioDetails.getTipoMovi().equalsIgnoreCase("ENTRADA") || movimientoInventarioDetails.getTipoMovi().equalsIgnoreCase("SALIDA"))) {
            throw new IllegalArgumentException("El tipo de movimiento debe ser 'ENTRADA' o 'SALIDA'.");
        }
        if (movimientoInventarioDetails.getCantidad() == null || movimientoInventarioDetails.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        }
        if (movimientoInventarioDetails.getFechaMovi() == null) {
            throw new IllegalArgumentException("La fecha del movimiento es requerida.");
        }
        if (movimientoInventarioDetails.getInventario() == null) {
            throw new IllegalArgumentException("El inventario es requerido.");
        }

        movimientoInventario.setTipoMovi(movimientoInventarioDetails.getTipoMovi());
        movimientoInventario.setCantidad(movimientoInventarioDetails.getCantidad());
        movimientoInventario.setFechaMovi(movimientoInventarioDetails.getFechaMovi());
        movimientoInventario.setReferencia(movimientoInventarioDetails.getReferencia());
        movimientoInventario.setInventario(movimientoInventarioDetails.getInventario());

        return movimientoInventarioRepository.save(movimientoInventario);
    }

    public void deleteById(Long id) {
        if (!movimientoInventarioRepository.existsById(id)) {
            throw new IllegalArgumentException("El movimiento de inventario con id " + id + " no existe.");
        }
        movimientoInventarioRepository.deleteById(id);
    }

}
