package com.example.Farmacia.Repository;

import com.example.Farmacia.Model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    List<Compra> findByProveedor_IdProveedor(Long proveedorId);
    List<Compra> findByMarca_IdMarca(Long marcaId);
}
