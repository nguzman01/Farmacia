package com.example.Farmacia.Repository;

import com.example.Farmacia.Model.Movimiento_inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Movimiento_inventarioRepository extends JpaRepository<Movimiento_inventario, Long> {
}
