package com.example.Farmacia.Controller;

import com.example.Farmacia.Model.Empleado;
import com.example.Farmacia.Service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public List<Empleado> getAll() {
        return empleadoService.obtenerTodosLosEmpleados();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getById(@PathVariable Long id) {
        Optional<Empleado> empleado = empleadoService.buscarEmpleadoPorId(id);
        return empleado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Empleado create(@RequestBody Empleado empleado) {
        try {
            return empleadoService.crearEmpleado(empleado);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // actualiza
    @PutMapping("/{id}")
    public ResponseEntity<Empleado> update(@PathVariable Long id, @RequestBody Empleado empleadoDetails) {
        Empleado updatedEmpleado = empleadoService.actualizarEmpleado(id, empleadoDetails);
        if (updatedEmpleado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEmpleado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        empleadoService.eliminarEmpleado(id);
        return ResponseEntity.noContent().build();
    }
}
