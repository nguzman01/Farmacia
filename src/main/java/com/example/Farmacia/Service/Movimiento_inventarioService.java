package com.example.Farmacia.Service;

import com.example.Farmacia.Model.Inventario;
import com.example.Farmacia.Model.Movimiento_inventario;
import com.example.Farmacia.Repository.InventarioRepository;
import com.example.Farmacia.Repository.Movimiento_inventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Movimiento_inventarioService {

    @Autowired
    private Movimiento_inventarioRepository movimientoInventarioRepository;

    @Autowired
    private InventarioRepository inventarioRepository;

    public List<Movimiento_inventario> listarMovimientosInventario() {
        return movimientoInventarioRepository.findAll();
    }

    public Optional<Movimiento_inventario> listarMovimientoInventarioPorId(Long id) {
        return movimientoInventarioRepository.findById(id);
    }

    public Movimiento_inventario crearMovimientoInventario(Movimiento_inventario movimientoInventario) {
        // Validaciones previas
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
        if (movimientoInventario.getInventario() == null || movimientoInventario.getInventario().getId_inventario() == null) {
            throw new IllegalArgumentException("El inventario es requerido.");
        }

        // Verificar si el inventario existe
        Inventario inventario = inventarioRepository.findById(movimientoInventario.getInventario().getId_inventario())
                .orElseThrow(() -> new IllegalArgumentException("El inventario con ID " + movimientoInventario.getInventario().getId_inventario() + " no existe."));

        // Asociar el inventario persistido al movimiento
        movimientoInventario.setInventario(inventario);

        // Guardar el movimiento de inventario
        return movimientoInventarioRepository.save(movimientoInventario);
    }

    public Movimiento_inventario actualizarMovimientoInventario(Long id, Movimiento_inventario movimientoInventarioDetails) {
        Optional<Movimiento_inventario> optionalMovimiento = movimientoInventarioRepository.findById(id);
        if (optionalMovimiento.isEmpty()) {
            return null;
        }
        Movimiento_inventario movimientoInventario = optionalMovimiento.get();

        // Validaciones para la actualización
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

        // Verificar si el inventario existe
        Inventario inventario = inventarioRepository.findById(movimientoInventarioDetails.getInventario().getId_inventario())
                .orElseThrow(() -> new IllegalArgumentException("El inventario con ID " + movimientoInventarioDetails.getInventario().getId_inventario() + " no existe."));

        // Actualizar los detalles del movimiento de inventario
        movimientoInventario.setTipoMovi(movimientoInventarioDetails.getTipoMovi());
        movimientoInventario.setCantidad(movimientoInventarioDetails.getCantidad());
        movimientoInventario.setFechaMovi(movimientoInventarioDetails.getFechaMovi());
        movimientoInventario.setReferencia(movimientoInventarioDetails.getReferencia());
        movimientoInventario.setInventario(inventario);

        return movimientoInventarioRepository.save(movimientoInventario);
    }

    public void eliminarMovimientoInventarioPorId(Long id) {
        if (!movimientoInventarioRepository.existsById(id)) {
            throw new IllegalArgumentException("El movimiento de inventario con id " + id + " no existe.");
        }
        movimientoInventarioRepository.deleteById(id);
    }

}
