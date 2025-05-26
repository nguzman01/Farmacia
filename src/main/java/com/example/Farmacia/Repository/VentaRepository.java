package com.example.Farmacia.Repository;

import com.example.Farmacia.Model.Venta;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long>{

    //ventas que ha hecho un empleado
    List<Venta> findByEmpleado_IdEmpleado(Long idEmpleado);

    // Traer venta junto con medicamentos para evitar que medicamentos estén vacíos o no cargados
    @Query("SELECT v FROM Venta v LEFT JOIN FETCH v.medicamentos WHERE v.id_Venta = :id")
    Optional<Venta> findByIdWithMedicamentos(@Param("id") Long id);
}
