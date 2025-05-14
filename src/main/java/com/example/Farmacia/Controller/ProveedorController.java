package com.example.Farmacia.Controller;

import com.example.Farmacia.Model.Proveedor;
import com.example.Farmacia.Service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/proveedor")
public class ProveedorController {


    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public List<Proveedor> getAll() {
        return proveedorService.listarProveedores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> getById(@PathVariable Long id) {
        Optional<Proveedor> proveedor = proveedorService.listarProveedorPorId(id);
        return proveedor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Proveedor create(@RequestBody Proveedor proveedor) {
        return proveedorService.crearProveedor(proveedor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> update(@PathVariable Long id, @RequestBody Proveedor proveedorDetails) {
        Proveedor updatedProveedor = proveedorService.actualizarProveedor(id, proveedorDetails);
        if (updatedProveedor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProveedor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        proveedorService.eliminarProveedorPorId(id);
        return ResponseEntity.noContent().build();
    }
}
