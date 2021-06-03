package com.sistema.ordemServicos.controllers;

import com.sistema.ordemServicos.models.Cargo;
import com.sistema.ordemServicos.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cargos")
public class ControllerCargo {

    @Autowired
    CargoRepository cr;

    @RequestMapping(value = "/novoCargo", method = RequestMethod.GET)
    public String incluir(){
        return "cargo/novo";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public String salvar(Cargo cargo){
        cr.save(cargo);
        return "redirect:/cargos";
    }

    @RequestMapping(value = "")
    public ModelAndView list(){
         ModelAndView mv = new ModelAndView("cargo/cargos.html");
         Iterable<Cargo> cargos = cr.findAll();
         mv.addObject("cargos", cargos);
         return mv;
    }

    @RequestMapping(value="/excluir/{id}", method=RequestMethod.GET)
    public String delete(@PathVariable("id") int id) {

        Cargo cargo = cr.getById(id);
        cr.delete(cargo);

        return "redirect:/cargos";
    }

    @RequestMapping(value="/editar/{id}", method=RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") int id) {

        ModelAndView mv = new ModelAndView("cargo/edit.html");
        Cargo cargo = cr.getById(id);
        mv.addObject("cargo", cargo);

        return mv;

    }

    @RequestMapping(value="/atualizar", method=RequestMethod.POST)
    public String update(@ModelAttribute Cargo cargo) {

        cr.save(cargo);

        return "redirect:/cargos";

    }

}
