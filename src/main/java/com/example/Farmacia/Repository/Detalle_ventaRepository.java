package com.example.Farmacia.Repository;

import com.example.Farmacia.Model.Detalle_venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Detalle_ventaRepository extends JpaRepository<Detalle_venta, Long> {
}
