package com.sistema.ordemServicos.repository;

import com.sistema.ordemServicos.models.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer> {
}
