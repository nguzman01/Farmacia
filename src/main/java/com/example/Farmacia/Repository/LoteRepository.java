package com.example.Farmacia.Repository;

import com.example.Farmacia.Model.Lote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Integer> {
}
