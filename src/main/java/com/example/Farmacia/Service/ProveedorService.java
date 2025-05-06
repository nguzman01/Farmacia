package com.example.Farmacia.Service;

import com.example.Farmacia.Model.Proveedor;
import com.example.Farmacia.Repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Proveedor> findAll() {
        return proveedorRepository.findAll();
    }

    public Optional<Proveedor> findById(Long id) {
        return proveedorRepository.findById(id);
    }

    public Proveedor save(Proveedor proveedor) {
        if (proveedor.getNombreProv() == null || proveedor.getNombreProv().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del proveedor es requerido.");
        }
        return proveedorRepository.save(proveedor);
    }

    public Proveedor update(Long id, Proveedor proveedorDetails) {
        Optional<Proveedor> optionalProveedor = proveedorRepository.findById(id);
        if (optionalProveedor.isEmpty()) {
            return null;
        }
        Proveedor proveedor = optionalProveedor.get();

        if (proveedorDetails.getNombreProv() == null || proveedorDetails.getNombreProv().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del proveedor es requerido.");
        }

        proveedor.setNombreProv(proveedorDetails.getNombreProv());
        proveedor.setDireccionProv(proveedorDetails.getDireccionProv());
        proveedor.setTelefonoProv(proveedorDetails.getTelefonoProv());

        return proveedorRepository.save(proveedor);
    }

    public void deleteById(Long id) {
        if (!proveedorRepository.existsById(id)) {
            throw new IllegalArgumentException("El proveedor con id " + id + " no existe.");
        }
        proveedorRepository.deleteById(id);
    }
}
