package com.example.Farmacia.Service;

import com.example.Farmacia.Model.Lote;
import com.example.Farmacia.Repository.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoteService {

    @Autowired
    private LoteRepository loteRepository;

    public List<Lote> findAll() {
        return loteRepository.findAll();
    }

    public Optional<Lote> findById(Integer id) {
        return loteRepository.findById(id);
    }

    public Lote save(Lote lote) {
        if (lote.getCantidad() == null || lote.getCantidad() < 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor o igual a cero.");
        }
        if (lote.getMedicamento() == null) {
            throw new IllegalArgumentException("El medicamento es requerido.");
        }
        if (lote.getFechaIngreso() == null) {
            throw new IllegalArgumentException("La fecha de ingreso es requerida.");
        }
        if (lote.getFechaCaducidad() == null) {
            throw new IllegalArgumentException("La fecha de caducidad es requerida.");
        }
        return loteRepository.save(lote);
    }

    public Lote update(Integer id, Lote loteDetails) {
        Optional<Lote> optionalLote = loteRepository.findById(id);
        if (optionalLote.isEmpty()) {
            return null;
        }
        Lote lote = optionalLote.get();

        if (loteDetails.getCantidad() == null || loteDetails.getCantidad() < 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor o igual a cero.");
        }
        if (loteDetails.getMedicamento() == null) {
            throw new IllegalArgumentException("El medicamento es requerido.");
        }
        if (loteDetails.getFechaIngreso() == null) {
            throw new IllegalArgumentException("La fecha de ingreso es requerida.");
        }
        if (loteDetails.getFechaCaducidad() == null) {
            throw new IllegalArgumentException("La fecha de caducidad es requerida.");
        }

        lote.setCantidad(loteDetails.getCantidad());
        lote.setMedicamento(loteDetails.getMedicamento());
        lote.setFechaIngreso(loteDetails.getFechaIngreso());
        lote.setFechaCaducidad(loteDetails.getFechaCaducidad());

        return loteRepository.save(lote);
    }

    public void deleteById(Integer id) {
        if (!loteRepository.existsById(id)) {
            throw new IllegalArgumentException("El lote con id " + id + " no existe.");
        }
        loteRepository.deleteById(id);
    }
}
