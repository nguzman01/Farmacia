package com.example.Farmacia.Controller;

import com.example.Farmacia.Model.Marca;
import com.example.Farmacia.Service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/marca")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public List<Marca> getAll() {
        return marcaService.listarMarcas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> getById(@PathVariable Integer id) {
        Optional<Marca> marca = marcaService.listarMarcaPorId(id);
        return marca.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Marca create(@RequestBody Marca marca) {
        return marcaService.crearMarca(marca);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marca> update(@PathVariable Integer id, @RequestBody Marca marcaDetails) {
        Marca updatedMarca = marcaService.actualizarMarca(id, marcaDetails);
        if (updatedMarca == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMarca);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        marcaService.eliminarMarcaPorId(id);
        return ResponseEntity.noContent().build();
    }
}
