package com.example.Farmacia.Controller;

import com.example.Farmacia.Model.Medicamentos;
import com.example.Farmacia.Service.MedicamentosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicamentos")

public class MedicamentosController {

    @Autowired
    private MedicamentosService medicamentosService;

    @GetMapping
    public List<Medicamentos> getAll() {
        return medicamentosService.listarMedicamentos();
    }

    @GetMapping("api/medicamentos/{id}")
    public ResponseEntity<Medicamentos> getById(@PathVariable Integer id) {
        Optional<Medicamentos> medicamento = medicamentosService.buscarMedicamentoPorId(id);
        return medicamento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    // busca todos los medicamentos por id de categoria
    @GetMapping("api/medicamentos/categoria/{idCategoria}")
    public List<Medicamentos> getByCategoria(@PathVariable Long idCategoria) {
        System.out.println("Buscando medicamentos con categoría ID: " + idCategoria);
        return medicamentosService.findByCategoriaId(idCategoria);
    }
    // busca  medicamentos por proveedor

    @GetMapping("api/medicamentos/proveedor/{idProveedor}")
    public List<Medicamentos> getByProveedor(@PathVariable Long idProveedor) {
        return medicamentosService.findByProveedorId(idProveedor);
    }

    //creamos un endpoint que permite hacer la consulta por el nombre del proveedor.
    // busca  medicamentos por nombre de proveedor
    @GetMapping("api/medicamentos/proveedor/nombre/{nombreProv}")
    public List<Medicamentos> getByProveedorNombre(@PathVariable String nombreProv) {
        return medicamentosService.findByProveedorNombre(nombreProv);
    }



    @PostMapping
    public Medicamentos create(@RequestBody Medicamentos medicamento) {
        return medicamentosService.crearMedicamento(medicamento);
    }

    @PutMapping("api/medicamentos/{id}")
    public ResponseEntity<Medicamentos> update(@PathVariable Integer id, @RequestBody Medicamentos medicamentoDetails) {
        Medicamentos updatedMedicamento = medicamentosService.actualizarMedicamento(id, medicamentoDetails);
        if (updatedMedicamento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMedicamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        medicamentosService.EliminarMedicamento(id);
        return ResponseEntity.noContent().build();
    }


}
