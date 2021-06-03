package com.sistema.ordemServicos.repository;

import com.sistema.ordemServicos.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
