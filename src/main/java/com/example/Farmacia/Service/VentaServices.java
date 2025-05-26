package com.example.Farmacia.Service;

import com.example.Farmacia.Model.*;
import com.example.Farmacia.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
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

    @Autowired
    private MedicamentosRepository medicamentosRepository;

    @Autowired
    private Detalle_ventaRepository detalleVentaRepository;

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
        Optional<Venta> venta = ventaRepository.findByIdWithMedicamentos(id);

        // Si la venta existe, agregamos el nombre del empleado
        venta.ifPresent(v -> {
            if (v.getEmpleado() != null) {
                v.setNombreEmpl(v.getEmpleado().getNombreEmpl());
            }
        });

        return venta;
    }


    public Venta crearVenta(Venta venta) {
        Cliente cliente = clienteRepository.findById(venta.getCliente().getId_Cliente())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        Empleado empleado = empleadoRepository.findById(venta.getEmpleado().getId_Empleado())
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));

        venta.setCliente(cliente);
        venta.setEmpleado(empleado);
        venta.setNombreEmpl(empleado.getNombreEmpl());

        // Guardar primero la venta para tener su ID
      //  Venta ventaGuardada = ventaRepository.save(venta);

        // Si se pasan medicamentos, cargarlos desde la BD y asociarlos a la venta
        if (venta.getMedicamentos() != null && !venta.getMedicamentos().isEmpty()) {
            List<Medicamentos> medicamentosActualizados = new ArrayList<>();

            for (Medicamentos med : venta.getMedicamentos()) {
                Medicamentos medExistente = medicamentosRepository.findById(med.getIdMedicamento())
                        .orElseThrow(() -> new IllegalArgumentException("Medicamento con ID " + med.getIdMedicamento() + " no encontrado"));

                medExistente.setVenta(venta); // Asignar la venta al medicamento
                medicamentosActualizados.add(medExistente);
            }

            venta.setMedicamentos(medicamentosActualizados); // Reemplazar lista con objetos persistentes
        }
        //colocado

        if (venta.getTotal() != null) {
            BigDecimal totalRedondeado = venta.getTotal().setScale(2, RoundingMode.HALF_UP);
            venta.setTotal(totalRedondeado);
        }
        //colocado

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
       /* if (ventaDetails.getTotal() == null || ventaDetails.getTotal() < 0) {
            throw new IllegalArgumentException("El total debe ser mayor o igual a cero.");
        }*/
        if (ventaDetails.getTotal() == null || ventaDetails.getTotal().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El total debe ser mayor o igual a cero.");
        }

        venta.setFechaVenta(ventaDetails.getFechaVenta());
        venta.setHoraVenta(ventaDetails.getHoraVenta());
        //colocado
        // Redondear el total a 2 decimales antes de asignarlo
        BigDecimal totalRedondeado = ventaDetails.getTotal().setScale(2, RoundingMode.HALF_UP);
        venta.setTotal(totalRedondeado);
        //colocado

        //venta.setTotal(ventaDetails.getTotal());
        venta.setCliente(ventaDetails.getCliente());
        venta.setEmpleado(ventaDetails.getEmpleado());
        venta.setNombreEmpl(ventaDetails.getEmpleado().getNombreEmpl()); // Actualizar nombreEmpleado

        //  asignar la venta a los medicamentos al actualizar
        if (ventaDetails.getMedicamentos() != null) {
// Eliminar medicamentos antiguos si es necesario
            //venta.getMedicamentos().clear();
            for (Medicamentos med : ventaDetails.getMedicamentos()) {
                med.setVenta(venta);
            }
            venta.setMedicamentos(ventaDetails.getMedicamentos());
        }

        return ventaRepository.save(venta);
    }

    public Optional<Venta> obtenerVentaConMedicamentos(Long id) {
        return ventaRepository.findByIdWithMedicamentos(id);
    }


    // Eliminar una venta por ID
    public void eliminarVentaPorId(Long id) {
        if (!ventaRepository.existsById(id)) {
            throw new IllegalArgumentException("La venta con id " + id + " no existe.");
        }
        ventaRepository.deleteById(id);
    }

    // Metodo de conversión
    public VentaDTO convertirAVentaDTO(Venta venta, List<Detalle_venta> detallesVenta) {
        List<DetalleVentaDTO> detallesDTO = detallesVenta.stream().map(detalle ->
                new DetalleVentaDTO(
                        detalle.getId_DetalleVenta(),
                        detalle.getMedicamento().getNombreMed(),
                        detalle.getCantidad(),
                        detalle.getSubtotal()
                )
        ).toList();

        return new VentaDTO(
                venta.getId_Venta(),
                venta.getFechaVenta().toString(),  // Convertir LocalDate a String
                venta.getTotal().doubleValue(),    // Convertir Double a double primitivo
                detallesDTO,
                venta.getCliente().getNombreCli(),  // Nombre cliente
                venta.getCliente().getEmailCli(),    // Email cliente
                venta.getCliente().getTelefonoCli(), // Teléfono cliente
                venta.getEmpleado().getNombreEmpl()   // Nombre empleado
        );
    }
}