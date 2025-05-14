package com.example.Farmacia.Controller;

import com.example.Farmacia.Model.Categoria;
import com.example.Farmacia.Service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // lista
    @GetMapping
    public List<Categoria> getAll() {
        return categoriaService.listarCategoria();
    }
    // lista por id
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable Integer id) {
        Optional<Categoria> categoria = categoriaService.listarCategoriaPorId(id);
        return categoria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    //crea
    @PostMapping
    public Categoria create(@RequestBody Categoria categoria) {
        return categoriaService.crearCategoria(categoria);
    }
    // actualiza
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> update(@PathVariable Integer id, @RequestBody Categoria categoriaDetails) {
        Categoria updatedCategoria = categoriaService.actualizarCategoria(id, categoriaDetails);
        if (updatedCategoria == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCategoria);
    }
    //elimina
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoriaService.eliminarCategoriaPorId(id);
        return ResponseEntity.noContent().build();
    }
}
