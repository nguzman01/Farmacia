package com.example.Farmacia.Repository;

import com.example.Farmacia.Model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long>{
    //ventas que ha hecho un empleado

    List<Venta> findByEmpleado_IdEmpleado(Long idEmpleado);

}
