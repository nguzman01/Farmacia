package com.example.Farmacia.Controller;

import com.example.Farmacia.Model.Detalle_venta;
import com.example.Farmacia.Service.Detalle_ventaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/detalle-venta")
public class Detalle_ventaController {
    @Autowired
    private Detalle_ventaServices detalleVentaService;

    // Obtener todos los detalles de venta
    @GetMapping
    public List<Detalle_venta> getAll() {
        return detalleVentaService.listarDetalleVentas();
    }

    // Obtener un detalle de venta por id
    @GetMapping("/{id}")
    public ResponseEntity<Detalle_venta> getById(@PathVariable Long id) {
        Optional<Detalle_venta> detalleVenta = detalleVentaService.listarDetalleVentaPorId(id);
        return detalleVenta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Obtener los detalles de una venta específica por id_venta
    @GetMapping("/venta/{idVenta}")
    public ResponseEntity<List<Detalle_venta>> getDetalleVentaByVentaId(@PathVariable Long idVenta) {
        List<Detalle_venta> detallesVenta = detalleVentaService.listarDetalleVentasPorVentaId(idVenta);
        if (detallesVenta.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detallesVenta);
    }
/*
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Detalle_venta detalleVenta) {
        try {
            Detalle_venta creado = detalleVentaService.crearDetalleVenta(detalleVenta);
            return ResponseEntity.ok(creado);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
*/
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Detalle_venta detalleVentaDetails) {
        try {
            Detalle_venta actualizado = detalleVentaService.actualizarDetalleVenta(id, detalleVentaDetails);
            if (actualizado == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            detalleVentaService.eliminarDetalleVentaPorId(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
