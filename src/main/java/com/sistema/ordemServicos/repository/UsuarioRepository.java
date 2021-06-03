package com.sistema.ordemServicos.repository;

import com.sistema.ordemServicos.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
