package com.example.Farmacia.Service;

import com.example.Farmacia.Model.Venta;
import com.example.Farmacia.Repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class VentaServices {



    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }

    public Optional<Venta> findById(Long id) {
        return ventaRepository.findById(id);
    }

    public Venta save(Venta venta) {
        if (venta.getFechaVenta() == null) {
            throw new IllegalArgumentException("La fecha de venta es requerida.");
        }
        if (venta.getCliente() == null) {
            throw new IllegalArgumentException("El cliente es requerido.");
        }
        if (venta.getEmpleado() == null) {
            throw new IllegalArgumentException("El empleado es requerido.");
        }
        if (venta.getTotal() == null || venta.getTotal() < 0) {
            throw new IllegalArgumentException("El total debe ser mayor o igual a cero.");
        }
        return ventaRepository.save(venta);
    }

    public Venta update(Long id, Venta ventaDetails) {
        Optional<Venta> optionalVenta = ventaRepository.findById(id);
        if (optionalVenta.isEmpty()) {
            return null;
        }
        Venta venta = optionalVenta.get();

        if (ventaDetails.getFechaVenta() == null) {
            throw new IllegalArgumentException("La fecha de venta es requerida.");
        }
        if (ventaDetails.getCliente() == null) {
            throw new IllegalArgumentException("El cliente es requerido.");
        }
        if (ventaDetails.getEmpleado() == null) {
            throw new IllegalArgumentException("El empleado es requerido.");
        }
        if (ventaDetails.getTotal() == null || ventaDetails.getTotal() < 0) {
            throw new IllegalArgumentException("El total debe ser mayor o igual a cero.");
        }

        venta.setFechaVenta(ventaDetails.getFechaVenta());
        venta.setHoraVenta(ventaDetails.getHoraVenta());
        venta.setTotal(ventaDetails.getTotal());
        venta.setCliente(ventaDetails.getCliente());
        venta.setEmpleado(ventaDetails.getEmpleado());

        return ventaRepository.save(venta);
    }

    public void deleteById(Long id) {
        if (!ventaRepository.existsById(id)) {
            throw new IllegalArgumentException("La venta con id " + id + " no existe.");
        }
        ventaRepository.deleteById(id);
    }
}
