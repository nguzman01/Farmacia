package com.example.Farmacia.Repository;

import com.example.Farmacia.Model.Detalle_compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Detalle_compraRepository extends JpaRepository<Detalle_compra, Long> {
}
