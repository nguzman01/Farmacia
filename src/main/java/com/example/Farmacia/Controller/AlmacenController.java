package com.example.Farmacia.Controller;

import com.example.Farmacia.Model.Almacen;
import com.example.Farmacia.Service.AlmacenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/almacen")
public class AlmacenController {



    @Autowired
    private AlmacenServices almacenService;

    @GetMapping
    public List<Almacen> getAll() {
        return almacenService.listarAlmacenes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Almacen> getById(@PathVariable Integer id) {
        Optional<Almacen> almacen = almacenService.listarAlmacenPorId(id);
        return almacen.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Almacen create(@RequestBody Almacen almacen) {
        return almacenService.crearAlmacen(almacen);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Almacen> update(@PathVariable Integer id, @RequestBody Almacen almacenDetails) {
        Almacen updatedAlmacen = almacenService.actualizarAlmacen(id, almacenDetails);
        if (updatedAlmacen == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedAlmacen);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        almacenService.eliminarAlmacenPorId(id);
        return ResponseEntity.noContent().build();
    }
}
