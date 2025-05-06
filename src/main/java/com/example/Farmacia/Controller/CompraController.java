package com.example.Farmacia.Controller;


import com.example.Farmacia.Model.Compra;
import com.example.Farmacia.Service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/compra")
public class CompraController {


    @Autowired
    private CompraService compraService;

    @GetMapping
    public List<Compra> getAll() {
        return compraService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> getById(@PathVariable Long id) {
        Optional<Compra> compra = compraService.findById(id);
        return compra.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Compra create(@RequestBody Compra compra) {
        return compraService.save(compra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Compra> update(@PathVariable Long id, @RequestBody Compra compraDetails) {
        Compra updatedCompra = compraService.update(id, compraDetails);
        if (updatedCompra == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCompra);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        compraService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
