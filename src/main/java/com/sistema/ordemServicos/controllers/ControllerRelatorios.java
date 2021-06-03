package com.sistema.ordemServicos.controllers;

import com.sistema.ordemServicos.models.Chamados;
import com.sistema.ordemServicos.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/chamados")
public class ControllerRelatorios {

    @Autowired
    OrdemServicoRepository ordemRepo;

    @RequestMapping(value="")
    public ModelAndView relatorioChamados(Chamados chamados) {

        ModelAndView mv = new ModelAndView("relatorio/chamados.html");
        Integer qtde = ordemRepo.getQtdeChamados();
        chamados.setQtdChamados(qtde);
        mv.addObject("chamados", chamados);

        return mv;

    }

}
