package com.example.Farmacia.Repository;

import com.example.Farmacia.Model.Medicamentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicamentosRepository extends JpaRepository<Medicamentos, Integer> {
    //listar los medicamentos por id de categoria
    List<Medicamentos> findByCategoria_IdCategoria(Long idCategoria);

    //listar los medicamentos por id de proveedor
    List<Medicamentos> findByMarca_Proveedor_IdProveedor(Long idProveedor);

    //método para buscar por nombre del proveedor
    //listar los medicamentos por nombre de proveedor
    List<Medicamentos> findByMarca_Proveedor_NombreProv(String nombreProv);

}
