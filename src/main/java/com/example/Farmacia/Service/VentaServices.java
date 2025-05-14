package com.example.Farmacia.Service;

import com.example.Farmacia.Model.Cliente;
import com.example.Farmacia.Model.Empleado;
import com.example.Farmacia.Model.Venta;
import com.example.Farmacia.Repository.ClienteRepository;
import com.example.Farmacia.Repository.EmpleadoRepository;
import com.example.Farmacia.Repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class VentaServices {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    // Listar todas las ventas
    public List<Venta> listarVentas() {
        List<Venta> ventas = ventaRepository.findAll();

        // Recorrer las ventas y agregar el nombre del empleado en cada venta
        for (Venta venta : ventas) {
            if (venta.getEmpleado() != null) {
                venta.setNombreEmpl(venta.getEmpleado().getNombreEmpl());
            }
        }

        return ventas;
    }

    // Listar una venta por ID
    public Optional<Venta> listarVentaPorId(Long id) {
        Optional<Venta> venta = ventaRepository.findById(id);

        // Si la venta existe, agregamos el nombre del empleado
        venta.ifPresent(v -> {
            if (v.getEmpleado() != null) {
                v.setNombreEmpl(v.getEmpleado().getNombreEmpl());
            }
        });

        return venta;
    }

    // Crear una nueva venta
    public Venta crearVenta(Venta venta) {
        // Verificar si el cliente existe
        Cliente cliente = clienteRepository.findById(venta.getCliente().getId_Cliente())
                .orElseThrow(() -> new IllegalArgumentException("El cliente con ID " + venta.getCliente().getId_Cliente() + " no existe."));

        // Verificar si el empleado existe
        Empleado empleado = empleadoRepository.findById(venta.getEmpleado().getId_Empleado())
                .orElseThrow(() -> new IllegalArgumentException("El empleado con ID " + venta.getEmpleado().getId_Empleado() + " no existe."));

        // Establecer cliente y empleado en la venta
        venta.setCliente(cliente);
        venta.setEmpleado(empleado);

        // Establecer el nombre del empleado (para mostrarlo en el JSON de respuesta)
        venta.setNombreEmpl(empleado.getNombreEmpl());

        // Guardar la venta
        return ventaRepository.save(venta);
    }

    // Actualizar una venta existente
    public Venta actualizarVenta(Long id, Venta ventaDetails) {
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
        venta.setNombreEmpl(ventaDetails.getEmpleado().getNombreEmpl()); // Actualizar nombreEmpleado

        return ventaRepository.save(venta);
    }

    // Eliminar una venta por ID
    public void eliminarVentaPorId(Long id) {
        if (!ventaRepository.existsById(id)) {
            throw new IllegalArgumentException("La venta con id " + id + " no existe.");
        }
        ventaRepository.deleteById(id);
    }
}
