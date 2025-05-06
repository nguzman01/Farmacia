package com.example.Farmacia.Controller;

import com.example.Farmacia.Model.Movimiento_inventario;
import com.example.Farmacia.Service.Movimiento_inventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movimiento-inventario")
public class Movimiento_inventarioController {

    @Autowired
    private Movimiento_inventarioService movimientoInventarioService;

    @GetMapping
    public List<Movimiento_inventario> getAll() {
        return movimientoInventarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento_inventario> getById(@PathVariable Long id) {
        Optional<Movimiento_inventario> movimiento = movimientoInventarioService.findById(id);
        return movimiento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Movimiento_inventario create(@RequestBody Movimiento_inventario movimiento) {
        return movimientoInventarioService.save(movimiento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movimiento_inventario> update(@PathVariable Long id, @RequestBody Movimiento_inventario movimientoDetails) {
        Movimiento_inventario updatedMovimiento = movimientoInventarioService.update(id, movimientoDetails);
        if (updatedMovimiento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMovimiento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movimientoInventarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
