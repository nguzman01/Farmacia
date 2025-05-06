package com.example.Farmacia.Controller;

import com.example.Farmacia.Model.Medicamentos;
import com.example.Farmacia.Service.MedicamentosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicamentos")

public class MedicamentosController {

    @Autowired
    private MedicamentosService medicamentosService;

    @GetMapping
    public List<Medicamentos> getAll() {
        return medicamentosService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicamentos> getById(@PathVariable Integer id) {
        Optional<Medicamentos> medicamento = medicamentosService.findById(id);
        return medicamento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    // busca todos los medicamentos por id de categoria
    @GetMapping("/categoria/{idCategoria}")
    public List<Medicamentos> getByCategoria(@PathVariable Long idCategoria) {
        System.out.println("Buscando medicamentos con categoría ID: " + idCategoria);
        return medicamentosService.findByCategoriaId(idCategoria);
    }
    // busca  medicamentos por proveedor

    @GetMapping("/proveedor/{idProveedor}")
    public List<Medicamentos> getByProveedor(@PathVariable Long idProveedor) {
        return medicamentosService.findByProveedorId(idProveedor);
    }

    //creamos un endpoint que permite hacer la consulta por el nombre del proveedor.
    // busca  medicamentos por nombre de proveedor
    @GetMapping("/proveedor/nombre/{nombreProv}")
    public List<Medicamentos> getByProveedorNombre(@PathVariable String nombreProv) {
        return medicamentosService.findByProveedorNombre(nombreProv);
    }



    @PostMapping
    public Medicamentos create(@RequestBody Medicamentos medicamento) {
        return medicamentosService.save(medicamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicamentos> update(@PathVariable Integer id, @RequestBody Medicamentos medicamentoDetails) {
        Medicamentos updatedMedicamento = medicamentosService.update(id, medicamentoDetails);
        if (updatedMedicamento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMedicamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        medicamentosService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
