package com.example.Farmacia.Service;

import com.example.Farmacia.Model.Detalle_venta;
import com.example.Farmacia.Model.Medicamentos;
import com.example.Farmacia.Model.Venta;
import com.example.Farmacia.Repository.Detalle_ventaRepository;
import com.example.Farmacia.Repository.MedicamentosRepository;
import com.example.Farmacia.Repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Detalle_ventaServices {

    @Autowired
    private Detalle_ventaRepository detalleVentaRepository;

    @Autowired
    private MedicamentosRepository medicamentoRepository;

    @Autowired
    private VentaRepository ventaRepository;

    // Obtener todos los detalles de venta
    public List<Detalle_venta> listarDetalleVentas() {
        return detalleVentaRepository.findAll();
    }

    // Obtener detalle de venta por id
    public Optional<Detalle_venta> listarDetalleVentaPorId(Long id) {
        return detalleVentaRepository.findById(id);
    }

    // Obtener detalles de venta por id_venta (la nueva funcionalidad)
    public List<Detalle_venta> listarDetalleVentasPorVentaId(Long idVenta) {
        return detalleVentaRepository.findByVentaId(idVenta);
    }


    // Este metodo crea los detalles de venta basados en la lista de medicamentos asociados a la venta.
    public void crearDetallesVenta(Venta venta) {
        for (Medicamentos medicamento : venta.getMedicamentos()) {
            Detalle_venta detalle = new Detalle_venta();
            detalle.setVenta(venta);  // Asociamos la venta al detalle
            detalle.setMedicamento(medicamento);  // Asociamos el medicamento al detalle

            // Aquí se supone que la cantidad es algo que debes manejar o recibir (p.ej., como una cantidad en el objeto medicamento o recibida por el cliente)
            detalle.setCantidad(1);  // Este es un valor de ejemplo; ajusta según lo necesites.

            // Calcula el subtotal (cantidad * precio del medicamento)
            detalle.setSubtotal(detalle.getCantidad() * medicamento.getPrecio());

            // Guarda el detalle de venta
            detalleVentaRepository.save(detalle);
        }
    }

    // Actualizar un detalle de venta
    public Detalle_venta actualizarDetalleVenta(Long id, Detalle_venta detalleVentaDetails) {
        Optional<Detalle_venta> optionalDetalleVenta = detalleVentaRepository.findById(id);
        if (optionalDetalleVenta.isEmpty()) {
            return null;
        }

        if (detalleVentaDetails.getSubtotal() == null || detalleVentaDetails.getSubtotal() < 0) {
            throw new IllegalArgumentException("El subtotal debe ser mayor o igual a cero.");
        }

        if (detalleVentaDetails.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        }

        if (detalleVentaDetails.getMedicamento() == null || detalleVentaDetails.getMedicamento().getIdMedicamento() == 0) {
            throw new IllegalArgumentException("El medicamento es requerido y debe tener un ID válido.");
        }

        if (detalleVentaDetails.getVenta() == null || detalleVentaDetails.getVenta().getId_Venta() == 0) {
            throw new IllegalArgumentException("La venta es requerida y debe tener un ID válido.");
        }

        Medicamentos medicamento = medicamentoRepository.findById(detalleVentaDetails.getMedicamento().getIdMedicamento())
                .orElseThrow(() -> new IllegalArgumentException("El medicamento no existe"));

        Venta venta = ventaRepository.findById(detalleVentaDetails.getVenta().getId_Venta())
                .orElseThrow(() -> new IllegalArgumentException("La venta no existe"));

        Detalle_venta detalleVenta = optionalDetalleVenta.get();
        detalleVenta.setSubtotal(detalleVentaDetails.getSubtotal());
        detalleVenta.setCantidad(detalleVentaDetails.getCantidad());
        detalleVenta.setMedicamento(medicamento);
        detalleVenta.setVenta(venta);

        return detalleVentaRepository.save(detalleVenta);
    }

    // Eliminar un detalle de venta
    public void eliminarDetalleVentaPorId(Long id) {
        if (!detalleVentaRepository.existsById(id)) {
            throw new IllegalArgumentException("El detalle de venta con id " + id + " no existe.");
        }
        detalleVentaRepository.deleteById(id);
    }
}



