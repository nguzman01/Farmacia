package com.example.Farmacia.Service;

import com.example.Farmacia.Model.Inventario;
import com.example.Farmacia.Repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioServices {

    @Autowired
    private InventarioRepository inventarioRepository;

    public List<Inventario> listarInventarios() {
        return inventarioRepository.findAll();
    }

    public Optional<Inventario> listarInventarioPorId(Long id) {
        return inventarioRepository.findById(id);
    }

    public Inventario crearInventario(Inventario inventario) {
        if (inventario.getExistencias() == null || inventario.getExistencias() < 0) {
            throw new IllegalArgumentException("Las existencias deben ser mayores o iguales a cero.");
        }
        if (inventario.getAlmacen() == null) {
            throw new IllegalArgumentException("El almacén es requerido.");
        }
        if (inventario.getMedicamento() == null) {
            throw new IllegalArgumentException("El medicamento es requerido.");
        }
        return inventarioRepository.save(inventario);
    }

    public Inventario 	actualizarInventario(Long id, Inventario inventarioDetails) {
        Optional<Inventario> optionalInventario = inventarioRepository.findById(id);
        if (optionalInventario.isEmpty()) {
            return null;
        }
        Inventario inventario = optionalInventario.get();

        if (inventarioDetails.getExistencias() == null || inventarioDetails.getExistencias() < 0) {
            throw new IllegalArgumentException("Las existencias deben ser mayores o iguales a cero.");
        }
        if (inventarioDetails.getAlmacen() == null) {
            throw new IllegalArgumentException("El almacén es requerido.");
        }
        if (inventarioDetails.getMedicamento() == null) {
            throw new IllegalArgumentException("El medicamento es requerido.");
        }

        inventario.setExistencias(inventarioDetails.getExistencias());
        inventario.setAlmacen(inventarioDetails.getAlmacen());
        inventario.setMedicamento(inventarioDetails.getMedicamento());

        return inventarioRepository.save(inventario);
    }

    public void eliminarInventarioPorId(Long id) {
        if (!inventarioRepository.existsById(id)) {
            throw new IllegalArgumentException("El inventario con id " + id + " no existe.");
        }
        inventarioRepository.deleteById(id);
    }
}
