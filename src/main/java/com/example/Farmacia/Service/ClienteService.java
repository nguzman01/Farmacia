package com.example.Farmacia.Service;

import com.example.Farmacia.Model.Cliente;
import com.example.Farmacia.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente save(Cliente cliente) {
        if (cliente.getNombreCli() == null || cliente.getNombreCli().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente es requerido");
        }
        // podrías agregar más validaciones a otros campos
        return clienteRepository.save(cliente);
    }

    public Cliente update(Long id, Cliente clienteDetails) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isEmpty()) {
            return null;
        }
        Cliente cliente = optionalCliente.get();

        if (clienteDetails.getNombreCli() == null || clienteDetails.getNombreCli().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente es requerido");
        }

        cliente.setNombreCli(clienteDetails.getNombreCli());
        cliente.setDireccionCli(clienteDetails.getDireccionCli());
        cliente.setEmailCli(clienteDetails.getEmailCli());
        cliente.setTelefonoCli(clienteDetails.getTelefonoCli());

        return clienteRepository.save(cliente);
    }

    public void deleteById(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new IllegalArgumentException("El cliente con id " + id + " no existe.");
        }
        clienteRepository.deleteById(id);
    }
}
