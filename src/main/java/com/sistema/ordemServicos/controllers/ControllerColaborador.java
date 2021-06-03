package com.sistema.ordemServicos.controllers;

import com.sistema.ordemServicos.models.Cargo;
import com.sistema.ordemServicos.models.Colaborador;
import com.sistema.ordemServicos.repository.CargoRepository;
import com.sistema.ordemServicos.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/colaboradores")
public class ControllerColaborador {

    @Autowired
    ColaboradorRepository colRepo;
    @Autowired
    CargoRepository cr;

    @RequestMapping(value = "/novoColaborador", method = RequestMethod.GET)
    public String incluir(Model model){
        model.addAttribute("cargos", cr.findAll());
        return "colaborador/novo";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public String salvar(Colaborador colaborador, @RequestParam("id_cargo") int idCargo){
        Cargo cargo = cr.getById(idCargo);

        colaborador.setCargo(cargo);
        colRepo.save(colaborador);
        return "redirect:/colaboradores";
    }

    @RequestMapping(value = "")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView("colaborador/colaboradores.html");
        Iterable<Colaborador> colaboradores = colRepo.findAll();
        mv.addObject("colaboradores", colaboradores);
        return mv;
    }

    @RequestMapping(value="/excluir/{id}", method=RequestMethod.GET)
    public String delete(@PathVariable("id") int id) {

        Colaborador colaborador = colRepo.getById(id);
        colRepo.delete(colaborador);

        return "redirect:/colaboradores";
    }

    @RequestMapping(value="/editar/{id}", method=RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") int id) {

        ModelAndView mv = new ModelAndView("colaborador/edit.html");
        Colaborador colaborador = colRepo.getById(id);
        Iterable<Cargo> cargos = cr.findAll();
        mv.addObject("colaborador", colaborador);
        mv.addObject("cargos", cargos);

        return mv;

    }

    @RequestMapping(value="/atualizar", method=RequestMethod.POST)
    public String update(@ModelAttribute Colaborador colaborador, @RequestParam("id_cargo") int idCargo) {
        Cargo cargo = cr.getById(idCargo);

        colaborador.setCargo(cargo);
        colRepo.save(colaborador);

        return "redirect:/colaboradores";

    }
}
