package com.example.Farmacia.Service;

import com.example.Farmacia.Model.Categoria;
import com.example.Farmacia.Repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarCategoria() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> listarCategoriaPorId(long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria crearCategoria(Categoria categoria) {
        if (categoria.getNombreCat() == null || categoria.getNombreCat().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría es requerido.");
        }
        return categoriaRepository.save(categoria);
    }

    public Categoria actualizarCategoria(long id, Categoria categoriaDetails) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        if (optionalCategoria.isEmpty()) {
            return null;
        }
        Categoria categoria = optionalCategoria.get();

        if (categoriaDetails.getNombreCat() == null || categoriaDetails.getNombreCat().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría es requerido.");
        }

        categoria.setNombreCat(categoriaDetails.getNombreCat());

        return categoriaRepository.save(categoria);
    }

    public void eliminarCategoriaPorId(long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new IllegalArgumentException("La categoría con id " + id + " no existe.");
        }
        categoriaRepository.deleteById(id);
    }
}