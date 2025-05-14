package com.example.Farmacia.Controller;

import com.example.Farmacia.Model.Lote;
import com.example.Farmacia.Service.LoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lote")
public class LoteController {

    @Autowired
    private LoteService loteService;

    @GetMapping
    public List<Lote> getAll() {
        return loteService.listarLotes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lote> getById(@PathVariable Integer id) {
        Optional<Lote> lote = loteService.listarLotePorId(id);
        return lote.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Lote create(@RequestBody Lote lote) {
        return loteService.crearLote(lote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lote> update(@PathVariable Integer id, @RequestBody Lote loteDetails) {
        Lote updatedLote = loteService.actualizarLote(id, loteDetails);
        if (updatedLote == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedLote);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        loteService.eliminarLotePorId(id);
        return ResponseEntity.noContent().build();
    }

}
