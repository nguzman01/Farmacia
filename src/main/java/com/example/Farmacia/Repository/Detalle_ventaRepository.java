package com.example.Farmacia.Repository;

import com.example.Farmacia.Model.Detalle_venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Detalle_ventaRepository extends JpaRepository<Detalle_venta, Long> {

    // Usamos una consulta personalizada con la anotación @Query
    @Query("SELECT d FROM Detalle_venta d WHERE d.venta.id_Venta = :idVenta")
    List<Detalle_venta> findByVentaId(@Param("idVenta") Long idVenta);
}

