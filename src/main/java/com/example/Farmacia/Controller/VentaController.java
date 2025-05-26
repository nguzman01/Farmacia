package com.example.Farmacia.Controller;

import com.example.Farmacia.Model.DetalleVentaDTO;
import com.example.Farmacia.Model.Detalle_venta;
import com.example.Farmacia.Model.Venta;
import com.example.Farmacia.Model.VentaDTO;
import com.example.Farmacia.Repository.Detalle_ventaRepository;
import com.example.Farmacia.Repository.VentaRepository;
import com.example.Farmacia.Service.Detalle_ventaServices;
import com.example.Farmacia.Service.VentaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/venta")
public class VentaController {
    @Autowired
    private VentaServices ventaService;

    @Autowired
    private Detalle_ventaServices detalleVentaService;


    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private Detalle_ventaRepository detalleVentaRepository;

    // Obtener todas las ventas (entidades completas)
    @GetMapping
    public List<Venta> getAll() {
        return ventaService.listarVentas();
    }

    // Obtener una venta completa con medicamentos (entidad completa)
    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVenta(@PathVariable Long id) {
        Optional<Venta> ventaOpt = ventaService.listarVentaPorId(id);
        if (ventaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ventaOpt.get());
    }

    // Metodo privado para convertir entidad Venta + detalles a DTO
    private VentaDTO convertirAVentaDTO(Venta venta, List<Detalle_venta> detalles) {
        List<DetalleVentaDTO> detallesDTO = detalles.stream()
                .map(det -> new DetalleVentaDTO(
                        det.getId_DetalleVenta(),
                        det.getMedicamento().getNombreMed(),
                        det.getCantidad(),
                        det.getSubtotal()
                ))
                .toList();

        return new VentaDTO(
                venta.getId_Venta(),
                venta.getFechaVenta().toString(),
                venta.getTotal().doubleValue(),
                detallesDTO,
                // Datos cliente
                venta.getCliente().getNombreCli(),
                venta.getCliente().getEmailCli(),
                venta.getCliente().getTelefonoCli(),
                // Solo nombre del empleado
                venta.getEmpleado().getNombreEmpl()
        );
    }


    // Obtener una venta como DTO personalizado con detalles simplificados
    @GetMapping("/venta/{id}")
    public ResponseEntity<VentaDTO> obtenerVentaPorId(@PathVariable Long id) {
        Optional<Venta> ventaOpt = ventaRepository.findById(id);

        if (ventaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Venta venta = ventaOpt.get();

        // Obtener detalles usando el metodo del repositorio
        List<Detalle_venta> detalles = detalleVentaRepository.findByVentaId(venta.getId_Venta());


        // Convertir la venta a DTO para respuesta personalizada
        VentaDTO ventaDTO = convertirAVentaDTO(venta, detalles);

        return ResponseEntity.ok(ventaDTO);
    }

    /*
    // Obtener venta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Venta> getById(@PathVariable Long id) {
        Optional<Venta> venta = ventaService.listarVentaPorId(id);
        return venta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
*/
    // Crear una nueva venta
    @PostMapping
    public ResponseEntity<Venta> create(@RequestBody Venta venta) {
        try {
            // Verifica si la venta fue creada correctamente
            Venta createdVenta = ventaService.crearVenta(venta);

            // Crear los detalles de la venta automáticamente después de crear la venta

            detalleVentaService.crearDetallesVenta(createdVenta);  // Llamamos al servicio de detalles

            return ResponseEntity.status(201).body(createdVenta);  // Retorna el objeto creado con un código 201
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();  // Si hay un error, responde con un error 400
        }
    }

    // Actualizar venta existente
    @PutMapping("/{id}")
    public ResponseEntity<Venta> update(@PathVariable Long id, @RequestBody Venta ventaDetails) {
        Venta updatedVenta = ventaService.actualizarVenta(id, ventaDetails);
        if (updatedVenta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedVenta);
    }

    // Eliminar una venta por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ventaService.eliminarVentaPorId(id);
        return ResponseEntity.noContent().build();  // Responde con código 204 para indicar que la eliminación fue exitosa
    }
}
