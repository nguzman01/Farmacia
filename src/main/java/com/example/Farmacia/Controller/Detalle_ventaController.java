package com.example.Farmacia.Controller;

import com.example.Farmacia.Model.Detalle_venta;
import com.example.Farmacia.Service.Detalle_ventaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/detalle-venta")

public class Detalle_ventaController {

    @Autowired
    private Detalle_ventaServices detalleVentaService;

    @GetMapping
    public List<Detalle_venta> getAll() {
        return detalleVentaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Detalle_venta> getById(@PathVariable Long id) {
        Optional<Detalle_venta> detalleVenta = detalleVentaService.findById(id);
        return detalleVenta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Detalle_venta create(@RequestBody Detalle_venta detalleVenta) {
        return detalleVentaService.save(detalleVenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Detalle_venta> update(@PathVariable Long id, @RequestBody Detalle_venta detalleVentaDetails) {
        Detalle_venta updatedDetalleVenta = detalleVentaService.update(id, detalleVentaDetails);
        if (updatedDetalleVenta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedDetalleVenta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        detalleVentaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
