package com.sistema.ordemServicos.repository;

import com.sistema.ordemServicos.models.ChamadosPorUsuario;
import com.sistema.ordemServicos.models.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Integer> {

    @Query(value = "SELECT COUNT(id) from ordem_servico", nativeQuery = true)
    Integer getQtdeChamados();

    @Query(value = "SELECT concat(usu.nome, \" - \", COUNT(ordem.id)) AS nome FROM ordem_servico ordem\n" +
            "INNER JOIN usuario usu ON usu.id = ordem.id_usuario\n" +
            "GROUP BY usu.nome", nativeQuery = true)
    List<String> getQtdeChamadosByUser();

}
