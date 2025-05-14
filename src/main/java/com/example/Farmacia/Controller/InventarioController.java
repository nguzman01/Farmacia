package com.example.Farmacia.Controller;

import com.example.Farmacia.Model.Inventario;
import com.example.Farmacia.Service.InventarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    @Autowired
    private InventarioServices inventarioService;

    @GetMapping
    public List<Inventario> getAll() {
        return inventarioService.listarInventarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> getById(@PathVariable Long id) {
        Optional<Inventario> inventario = inventarioService.listarInventarioPorId(id);
        return inventario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Inventario create(@RequestBody Inventario inventario) {
        return inventarioService.crearInventario(inventario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventario> update(@PathVariable Long id, @RequestBody Inventario inventarioDetails) {
        Inventario updatedInventario = inventarioService.actualizarInventario(id, inventarioDetails);
        if (updatedInventario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedInventario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inventarioService.eliminarInventarioPorId(id);
        return ResponseEntity.noContent().build();
    }
}
