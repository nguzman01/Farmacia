package com.example.Farmacia.Controller;

import com.example.Farmacia.Model.Proveedor;
import com.example.Farmacia.Service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/proveedor")
public class ProveedorController {


    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public List<Proveedor> getAll() {
        return proveedorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> getById(@PathVariable Long id) {
        Optional<Proveedor> proveedor = proveedorService.findById(id);
        return proveedor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Proveedor create(@RequestBody Proveedor proveedor) {
        return proveedorService.save(proveedor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> update(@PathVariable Long id, @RequestBody Proveedor proveedorDetails) {
        Proveedor updatedProveedor = proveedorService.update(id, proveedorDetails);
        if (updatedProveedor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProveedor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        proveedorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
