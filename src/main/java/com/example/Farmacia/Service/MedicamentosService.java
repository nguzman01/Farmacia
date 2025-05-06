package com.example.Farmacia.Service;

import com.example.Farmacia.Model.Medicamentos;
import com.example.Farmacia.Repository.MedicamentosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicamentosService {

    @Autowired
    private MedicamentosRepository medicamentosRepository;

    public List<Medicamentos> findAll() {
        return medicamentosRepository.findAll();
    }

    public Optional<Medicamentos> findById(Integer id) {
        return medicamentosRepository.findById(id);
    }

    public Medicamentos save(Medicamentos medicamento) {
        if (medicamento.getNombreMed() == null || medicamento.getNombreMed().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del medicamento es requerido.");
        }
        if (medicamento.getMarca() == null) {
            throw new IllegalArgumentException("La marca es requerida.");
        }
        if (medicamento.getCategoria() == null) {
            throw new IllegalArgumentException("La categoría es requerida.");
        }
        // Puedes agregar más validaciones si lo deseas

        return medicamentosRepository.save(medicamento);
    }

    public Medicamentos update(Integer id, Medicamentos medicamentoDetails) {
        Optional<Medicamentos> optionalMedicamento = medicamentosRepository.findById(id);
        if (optionalMedicamento.isEmpty()) {
            return null;
        }
        Medicamentos medicamento = optionalMedicamento.get();

        if (medicamentoDetails.getNombreMed() == null || medicamentoDetails.getNombreMed().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del medicamento es requerido.");
        }
        if (medicamentoDetails.getMarca() == null) {
            throw new IllegalArgumentException("La marca es requerida.");
        }
        if (medicamentoDetails.getCategoria() == null) {
            throw new IllegalArgumentException("La categoría es requerida.");
        }

        medicamento.setNombreMed(medicamentoDetails.getNombreMed());
        medicamento.setFechaRegistro(medicamentoDetails.getFechaRegistro());
        medicamento.setPresentacion(medicamentoDetails.getPresentacion());
        medicamento.setDescripcion(medicamentoDetails.getDescripcion());
        medicamento.setPrecio(medicamentoDetails.getPrecio());
        medicamento.setCodigo(medicamentoDetails.getCodigo());
        medicamento.setMarca(medicamentoDetails.getMarca());
        medicamento.setCategoria(medicamentoDetails.getCategoria());

        return medicamentosRepository.save(medicamento);
    }

    // busqueda todos los medicamentos por categoria

    public List<Medicamentos> findByCategoriaId(Long idCategoria) {
        return medicamentosRepository.findByCategoria_IdCategoria(idCategoria);
    }

    //búsqueda de medicamentos por id  proveedor

    public List<Medicamentos> findByProveedorId(Long idProveedor) {
        return medicamentosRepository.findByMarca_Proveedor_IdProveedor(idProveedor);
    }

    // método que usa ese repositorio para
    //búsqueda de medicamentos por nombre de  proveedor
    public List<Medicamentos> findByProveedorNombre(String nombreProv) {
        return medicamentosRepository.findByMarca_Proveedor_NombreProv(nombreProv);
    }



    public void deleteById(Integer id) {
        if (!medicamentosRepository.existsById(id)) {
            throw new IllegalArgumentException("El medicamento con id " + id + " no existe.");
        }
        medicamentosRepository.deleteById(id);
    }

}
