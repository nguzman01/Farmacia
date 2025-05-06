package com.example.Farmacia.Service;

import com.example.Farmacia.Model.Almacen;
import com.example.Farmacia.Repository.AlmacenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlmacenServices {

    @Autowired
    private AlmacenRepository almacenRepository;

    public List<Almacen> findAll() {
        return almacenRepository.findAll();
    }

    public Optional<Almacen> findById(Integer id) {
        return almacenRepository.findById(id);
    }

    public Almacen save(Almacen almacen) {

        if (almacen.getNombreAlmac() == null || almacen.getNombreAlmac().isEmpty()) {
            throw new IllegalArgumentException("El nombre del almacén es requerido");
        }
        return almacenRepository.save(almacen);
    }

    public Almacen update(Integer id, Almacen almacenDetails) {
        Optional<Almacen> optionalAlmacen = almacenRepository.findById(id);
        if (optionalAlmacen.isEmpty()) {
            return null;
        }
        Almacen almacen = optionalAlmacen.get();

        if (almacenDetails.getNombreAlmac() == null || almacenDetails.getNombreAlmac().isEmpty()) {
            throw new IllegalArgumentException("El nombre del almacén es requerido");
        }

        almacen.setNombreAlmac(almacenDetails.getNombreAlmac());

        return almacenRepository.save(almacen);
    }

    public void deleteById(Integer id) {
        if(!almacenRepository.existsById(id)) {
            throw new IllegalArgumentException("El almacén con id " + id + " no existe.");
        }
        almacenRepository.deleteById(id);
    }
}
