package com.example.Farmacia.Service;

import com.example.Farmacia.Model.Empleado;
import com.example.Farmacia.Repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> findById(Long id) {
        return empleadoRepository.findById(id);
    }

    public Empleado save(Empleado empleado) {
        if (empleado.getNombreEmpl() == null || empleado.getNombreEmpl().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del empleado es requerido.");
        }
       /* if (empleado.getContraseña() == null || empleado.getContraseña().trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña es requerida.");
        }*/
        // Podrías agregar más validaciones como horas válidas, etc.

        return empleadoRepository.save(empleado);
    }

    public Empleado update(Long id, Empleado empleadoDetails) {
        Optional<Empleado> optionalEmpleado = empleadoRepository.findById(id);
        if (optionalEmpleado.isEmpty()) {
            return null;
        }
        Empleado empleado = optionalEmpleado.get();

        if (empleadoDetails.getNombreEmpl() == null || empleadoDetails.getNombreEmpl().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del empleado es requerido.");
        }
       /* if (empleadoDetails.getContraseña() == null || empleadoDetails.getContraseña().trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña es requerida.");
        }*/

        empleado.setNombreEmpl(empleadoDetails.getNombreEmpl());
        empleado.setDireccionEmpl(empleadoDetails.getDireccionEmpl());
        empleado.setEmailEmpl(empleadoDetails.getEmailEmpl());
        empleado.setTelefonoEmpl(empleadoDetails.getTelefonoEmpl());
        empleado.setHoraEntrada(empleadoDetails.getHoraEntrada());
        empleado.setHoraSalida(empleadoDetails.getHoraSalida());
        empleado.setContraseña(empleadoDetails.getContraseña());

        return empleadoRepository.save(empleado);
    }

    public void deleteById(Long id) {
        if (!empleadoRepository.existsById(id)) {
            throw new IllegalArgumentException("El empleado con id " + id + " no existe.");
        }
        empleadoRepository.deleteById(id);
    }
}
