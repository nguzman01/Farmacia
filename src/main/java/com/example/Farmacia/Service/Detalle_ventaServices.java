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

    public List<Detalle_venta> listarDetalleVentas() {
        return detalleVentaRepository.findAll();
    }

    public Optional<Detalle_venta> listarDetalleVentaPorId(Long id) {
        return detalleVentaRepository.findById(id);
    }

    public Detalle_venta crearDetalleVenta(Detalle_venta detalleVenta) {
        if (detalleVenta.getSubtotal() == null || detalleVenta.getSubtotal() < 0) {
            throw new IllegalArgumentException("El subtotal debe ser mayor o igual a cero.");
        }

        if (detalleVenta.getMedicamento() == null || detalleVenta.getMedicamento().getIdMedicamento() == 0) {
            throw new IllegalArgumentException("El medicamento es requerido y debe tener un ID válido.");
        }

        if (detalleVenta.getVenta() == null || detalleVenta.getVenta().getId_Venta() == 0) {
            throw new IllegalArgumentException("La venta es requerida y debe tener un ID válido.");
        }

        // Cargar los objetos desde la base de datos
        Medicamentos medicamento = medicamentoRepository.findById(detalleVenta.getMedicamento().getIdMedicamento())
                .orElseThrow(() -> new IllegalArgumentException("El medicamento no existe"));

        Venta venta = ventaRepository.findById(detalleVenta.getVenta().getId_Venta())
                .orElseThrow(() -> new IllegalArgumentException("La venta no existe"));

        detalleVenta.setMedicamento(medicamento);
        detalleVenta.setVenta(venta);

        return detalleVentaRepository.save(detalleVenta);
    }

    public Detalle_venta actualizarDetalleVenta(Long id, Detalle_venta detalleVentaDetails) {
        Optional<Detalle_venta> optionalDetalleVenta = detalleVentaRepository.findById(id);
        if (optionalDetalleVenta.isEmpty()) {
            return null;
        }

        if (detalleVentaDetails.getSubtotal() == null || detalleVentaDetails.getSubtotal() < 0) {
            throw new IllegalArgumentException("El subtotal debe ser mayor o igual a cero.");
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
        detalleVenta.setMedicamento(medicamento);
        detalleVenta.setVenta(venta);

        return detalleVentaRepository.save(detalleVenta);
    }

    public void eliminarDetalleVentaPorId(Long id) {
        if (!detalleVentaRepository.existsById(id)) {
            throw new IllegalArgumentException("El detalle de venta con id " + id + " no existe.");
        }
        detalleVentaRepository.deleteById(id);
    }
}


/*package com.example.Farmacia.Service;

import com.example.Farmacia.Model.Detalle_venta;
import com.example.Farmacia.Model.Medicamentos;
import com.example.Farmacia.Repository.Detalle_ventaRepository;
import com.example.Farmacia.Repository.MedicamentosRepository;  // Importamos el repositorio de Medicamentos
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Detalle_ventaServices {

    @Autowired
    private Detalle_ventaRepository detalleVentaRepository;

    @Autowired
    private MedicamentosRepository medicamentoRepository;  // Inyectamos el repositorio de Medicamentos

    public List<Detalle_venta> listarDetalleVentas() {
        return detalleVentaRepository.findAll();
    }

    public Optional<Detalle_venta> listarDetalleVentaPorId(Long id) {
        return detalleVentaRepository.findById(id);
    }

    public Detalle_venta crearDetalleVenta(Detalle_venta detalleVenta) {
        // Validar el subtotal
        if (detalleVenta.getSubtotal() == null || detalleVenta.getSubtotal() < 0) {
            throw new IllegalArgumentException("El subtotal debe ser mayor o igual a cero.");
        }

        // Validar medicamento
        if (detalleVenta.getMedicamento() == null) {
            throw new IllegalArgumentException("El medicamento es requerido.");
        }

        // Si el medicamento no tiene id (no está guardado), lo guardamos primero
        if (detalleVenta.getMedicamento().getIdMedicamento() == 0) {
            medicamentoRepository.save(detalleVenta.getMedicamento());  // Guardar el medicamento
        }

        // Validar la venta
        if (detalleVenta.getVenta() == null) {
            throw new IllegalArgumentException("La venta es requerida.");
        }

        // Guardar el detalle de venta
        return detalleVentaRepository.save(detalleVenta);
    }

    public Detalle_venta actualizarDetalleVenta(Long id, Detalle_venta detalleVentaDetails) {
        Optional<Detalle_venta> optionalDetalleVenta = detalleVentaRepository.findById(id);
        if (optionalDetalleVenta.isEmpty()) {
            return null;
        }
        Detalle_venta detalleVenta = optionalDetalleVenta.get();

        // Validar el subtotal
        if (detalleVentaDetails.getSubtotal() == null || detalleVentaDetails.getSubtotal() < 0) {
            throw new IllegalArgumentException("El subtotal debe ser mayor o igual a cero.");
        }

        // Validar medicamento
        if (detalleVentaDetails.getMedicamento() == null) {
            throw new IllegalArgumentException("El medicamento es requerido.");
        }

        // Si el medicamento no tiene id (no está guardado), lo guardamos primero
        if (detalleVentaDetails.getMedicamento().getIdMedicamento() == 0) {
            medicamentoRepository.save(detalleVentaDetails.getMedicamento());
        }


        // Validar la venta
        if (detalleVentaDetails.getVenta() == null) {
            throw new IllegalArgumentException("La venta es requerida.");
        }

        // Actualizar los campos de Detalle_venta
        detalleVenta.setSubtotal(detalleVentaDetails.getSubtotal());
        detalleVenta.setMedicamento(detalleVentaDetails.getMedicamento());
        detalleVenta.setVenta(detalleVentaDetails.getVenta());

        // Guardar el detalle de venta actualizado
        return detalleVentaRepository.save(detalleVenta);
    }

    public void eliminarDetalleVentaPorId(Long id) {
        if (!detalleVentaRepository.existsById(id)) {
            throw new IllegalArgumentException("El detalle de venta con id " + id + " no existe.");
        }
        detalleVentaRepository.deleteById(id);
    }
}
*/




/*package com.example.Farmacia.Service;

import com.example.Farmacia.Model.Detalle_venta;
import com.example.Farmacia.Repository.Detalle_ventaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class Detalle_ventaServices {

    @Autowired
    private Detalle_ventaRepository detalleVentaRepository;

    public List<Detalle_venta> listarDetalleVentas() {
        return detalleVentaRepository.findAll();
    }

    public Optional<Detalle_venta> listarDetalleVentaPorId(Long id) {
        return detalleVentaRepository.findById(id);
    }

    public Detalle_venta crearDetalleVenta(Detalle_venta detalleVenta) {
        if (detalleVenta.getSubtotal() == null || detalleVenta.getSubtotal() < 0) {
            throw new IllegalArgumentException("El subtotal debe ser mayor o igual a cero.");
        }
        if (detalleVenta.getMedicamento() == null) {
            throw new IllegalArgumentException("El medicamento es requerido.");
        }
        if (detalleVenta.getVenta() == null) {
            throw new IllegalArgumentException("La venta es requerida.");
        }
        return detalleVentaRepository.save(detalleVenta);
    }

    public Detalle_venta actualizarDetalleVenta(Long id, Detalle_venta detalleVentaDetails) {
        Optional<Detalle_venta> optionalDetalleVenta = detalleVentaRepository.findById(id);
        if (optionalDetalleVenta.isEmpty()) {
            return null;
        }
        Detalle_venta detalleVenta = optionalDetalleVenta.get();

        if (detalleVentaDetails.getSubtotal() == null || detalleVentaDetails.getSubtotal() < 0) {
            throw new IllegalArgumentException("El subtotal debe ser mayor o igual a cero.");
        }
        if (detalleVentaDetails.getMedicamento() == null) {
            throw new IllegalArgumentException("El medicamento es requerido.");
        }
        if (detalleVentaDetails.getVenta() == null) {
            throw new IllegalArgumentException("La venta es requerida.");
        }

        detalleVenta.setSubtotal(detalleVentaDetails.getSubtotal());
        detalleVenta.setMedicamento(detalleVentaDetails.getMedicamento());
        detalleVenta.setVenta(detalleVentaDetails.getVenta());

        return detalleVentaRepository.save(detalleVenta);
    }

    public void eliminarDetalleVentaPorId(Long id) {
        if (!detalleVentaRepository.existsById(id)) {
            throw new IllegalArgumentException("El detalle de venta con id " + id + " no existe.");
        }
        detalleVentaRepository.deleteById(id);
    }
}
*/