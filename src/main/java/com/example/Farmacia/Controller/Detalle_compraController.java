package com.example.Farmacia.Controller;

import com.example.Farmacia.Model.Detalle_compra;
import com.example.Farmacia.Service.Detalle_compraServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/detalle-compra")
public class Detalle_compraController {

    @Autowired
    private Detalle_compraServices detalleCompraService;

    @GetMapping
    public List<Detalle_compra> getAll() {
        return detalleCompraService.listarDetalleCompras();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Detalle_compra> getById(@PathVariable Long id) {
        Optional<Detalle_compra> detalleCompra = detalleCompraService.listarDetalleCompraPorId(id);
        return detalleCompra.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Detalle_compra create(@RequestBody Detalle_compra detalleCompra) {
        return detalleCompraService.crearDetalleCompra(detalleCompra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Detalle_compra> update(@PathVariable Long id, @RequestBody Detalle_compra detalleCompraDetails) {
        Detalle_compra updatedDetalleCompra = detalleCompraService.actualizarDetalleCompra(id, detalleCompraDetails);
        if (updatedDetalleCompra == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedDetalleCompra);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        detalleCompraService.eliminarDetalleCompraPorId(id);
        return ResponseEntity.noContent().build();
    }
}
