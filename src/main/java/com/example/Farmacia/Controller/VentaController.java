package com.example.Farmacia.Controller;

import com.example.Farmacia.Model.Venta;
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

    // Obtener todas las ventas
    @GetMapping
    public List<Venta> getAll() {
        return ventaService.listarVentas();
    }

    // Obtener venta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Venta> getById(@PathVariable Long id) {
        Optional<Venta> venta = ventaService.listarVentaPorId(id);
        return venta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva venta
    @PostMapping
    public ResponseEntity<Venta> create(@RequestBody Venta venta) {
        try {
            // Verifica si la venta fue creada correctamente
            Venta createdVenta = ventaService.crearVenta(venta);
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
