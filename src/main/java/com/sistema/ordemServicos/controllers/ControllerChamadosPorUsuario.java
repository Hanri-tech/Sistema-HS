package com.sistema.ordemServicos.controllers;

import com.sistema.ordemServicos.models.ChamadosPorUsuario;
import com.sistema.ordemServicos.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/chamadosPorUsuario")
public class ControllerChamadosPorUsuario {

    @Autowired
    OrdemServicoRepository ordemRepo;

    @RequestMapping(value="")
    public ModelAndView relatorioChamados(ChamadosPorUsuario chamadosPorUsuarioObj) {

        ModelAndView mv = new ModelAndView("relatorio/chamadosPorUsuario.html");

        List <String> chamadosPorUsuarios = ordemRepo.getQtdeChamadosByUser();
        for (String cmpu :chamadosPorUsuarios) {
            chamadosPorUsuarioObj.setNome(chamadosPorUsuarioObj.getNome());
        }


        mv.addObject("chamadosPorUsuarios", chamadosPorUsuarioObj);

        return mv;

    }

}
