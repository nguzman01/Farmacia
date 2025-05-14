package com.example.Farmacia.Service;

import com.example.Farmacia.Model.Marca;
import com.example.Farmacia.Repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public List<Marca> listarMarcas() {
        return marcaRepository.findAll();
    }

    public Optional<Marca> listarMarcaPorId(Integer id) {
        return marcaRepository.findById(id);
    }

    public Marca crearMarca(Marca marca) {
        if (marca.getNombreMarc() == null || marca.getNombreMarc().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la marca es requerido.");
        }
        if (marca.getProveedor() == null) {
            throw new IllegalArgumentException("El proveedor es requerido.");
        }
        return marcaRepository.save(marca);
    }

    public Marca actualizarMarca(Integer id, Marca marcaDetails) {
        Optional<Marca> optionalMarca = marcaRepository.findById(id);
        if (optionalMarca.isEmpty()) {
            return null;
        }
        Marca marca = optionalMarca.get();

        if (marcaDetails.getNombreMarc() == null || marcaDetails.getNombreMarc().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la marca es requerido.");
        }
        if (marcaDetails.getProveedor() == null) {
            throw new IllegalArgumentException("El proveedor es requerido.");
        }

        marca.setNombreMarc(marcaDetails.getNombreMarc());
        marca.setDescripcion(marcaDetails.getDescripcion());
        marca.setProveedor(marcaDetails.getProveedor());

        return marcaRepository.save(marca);
    }

    public void eliminarMarcaPorId(Integer id) {
        if (!marcaRepository.existsById(id)) {
            throw new IllegalArgumentException("La marca con id " + id + " no existe.");
        }
        marcaRepository.deleteById(id);
    }
}
