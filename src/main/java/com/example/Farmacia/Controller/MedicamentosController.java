package com.example.Farmacia.Controller;

import com.example.Farmacia.Model.Medicamentos;
import com.example.Farmacia.Service.MedicamentosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicamentos")

public class MedicamentosController {

    // Agregar el manejador de excepciones global
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleValidationExceptions(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }



    @Autowired
    private MedicamentosService medicamentosService;

    @GetMapping
    public List<Medicamentos> getAll() {
        return medicamentosService.obtenerTodosLosEmpleados();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicamentos> getById(@PathVariable Long id) {
        Optional<Medicamentos> medicamento = medicamentosService.buscarMedicamentoPorId(id);
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

        // Imprime para verificar qué datos llegan
        System.out.println("Medicamento recibido: " + medicamento);

        return medicamentosService.crearMedicamento(medicamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicamentos> update(@PathVariable Long id, @RequestBody Medicamentos medicamentoDetails) {
        Medicamentos updatedMedicamento = medicamentosService.actualizarMedicamento(id, medicamentoDetails);
        if (updatedMedicamento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMedicamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        medicamentosService.eliminarMedicamentoPorId(id);
        return ResponseEntity.noContent().build();
    }


}
