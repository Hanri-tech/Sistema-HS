package com.sistema.ordemServicos.controllers;

import com.sistema.ordemServicos.models.Cargo;
import com.sistema.ordemServicos.models.Categoria;
import com.sistema.ordemServicos.models.Colaborador;
import com.sistema.ordemServicos.models.Usuario;
import com.sistema.ordemServicos.repository.CargoRepository;
import com.sistema.ordemServicos.repository.CategoriaRepository;
import com.sistema.ordemServicos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/usuarios")
public class ControllerUsuario {

    @Autowired
    UsuarioRepository usuRepo;
    @Autowired
    CargoRepository cr;

    @RequestMapping(value = "/novoUsuario", method = RequestMethod.GET)
    public String incluir(Model model){
        model.addAttribute("cargos", cr.findAll());
        return "usuario/novo";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public String salvar(Usuario usuario, @RequestParam("id_cargo") int idCargo){
        Cargo cargo = cr.getById(idCargo);

        usuario.setCargo(cargo);
        usuRepo.save(usuario);
        return "redirect:/usuarios";
    }

    @RequestMapping(value = "")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView("usuario/usuarios.html");
        Iterable<Usuario> usuarios = usuRepo.findAll();
        mv.addObject("usuarios", usuarios);
        return mv;
    }

    @RequestMapping(value="/excluir/{id}", method=RequestMethod.GET)
    public String delete(@PathVariable("id") int id) {

        Usuario usuario = usuRepo.getById(id);
        usuRepo.delete(usuario);

        return "redirect:/usuarios";
    }

    @RequestMapping(value="/editar/{id}", method=RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") int id) {

        ModelAndView mv = new ModelAndView("usuario/edit.html");
        Usuario usuario = usuRepo.getById(id);
        Iterable<Cargo> cargos = cr.findAll();
        mv.addObject("usuario", usuario);
        mv.addObject("cargos", cargos);

        return mv;

    }

    @RequestMapping(value="/atualizar", method=RequestMethod.POST)
    public String update(@ModelAttribute Usuario usuario, @RequestParam("id_cargo") int idCargo ) {

        Cargo cargo = cr.getById(idCargo);
        usuario.setCargo(cargo);

        usuRepo.save(usuario);


        return "redirect:/usuarios";

    }
}
