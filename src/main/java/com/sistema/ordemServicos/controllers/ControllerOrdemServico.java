package com.sistema.ordemServicos.controllers;

import com.sistema.ordemServicos.models.*;
import com.sistema.ordemServicos.repository.CategoriaRepository;
import com.sistema.ordemServicos.repository.ColaboradorRepository;
import com.sistema.ordemServicos.repository.OrdemServicoRepository;
import com.sistema.ordemServicos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ordens")
public class ControllerOrdemServico {
    @Autowired
    OrdemServicoRepository ordemRepo;
    @Autowired
    UsuarioRepository userRepo;
    @Autowired
    ColaboradorRepository colRepo;
    @Autowired
    CategoriaRepository catRepo;

    @RequestMapping(value = "/novaOrdem", method = RequestMethod.GET)
    public String incluir(Model model){
        model.addAttribute("usuarios", userRepo.findAll());
        model.addAttribute("colaboradores", colRepo.findAll());
        model.addAttribute("categorias", catRepo.findAll());

        return "ordemServico/novo";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public String salvar(OrdemServico ordemServico, @RequestParam("id_usuario") int idUsuario, @RequestParam("id_colaborador") int idColaborador, @RequestParam("id_categoria") int idCategoria){
        Usuario usuario = userRepo.getById(idUsuario);
        Colaborador colaborador = colRepo.getById(idColaborador);
        Categoria categoria = catRepo.getById(idCategoria);

        ordemServico.setCategoria(categoria);
        ordemServico.setColaborador(colaborador);
        ordemServico.setUsuario(usuario);
        ordemRepo.save(ordemServico);
        return "redirect:/ordens";
    }

    @RequestMapping(value="")
    public ModelAndView lista() {

        ModelAndView mv = new ModelAndView("ordemServico/ordens.html");

        Iterable<OrdemServico> ordem = ordemRepo.findAll();
        Iterable<Usuario> usuarios = userRepo.findAll();
        Iterable<Colaborador> colaboradores = colRepo.findAll();
        Iterable<Categoria> categorias = catRepo.findAll();

        mv.addObject("ordens", ordem);
        mv.addObject("colaboradores", colaboradores);
        mv.addObject("usuarios", usuarios);
        mv.addObject("categorias", categorias);

        return mv;
    }

    @RequestMapping(value="/excluir/{id}", method=RequestMethod.GET)
    public String delete(@PathVariable("id") int id) {

        OrdemServico ordemServico = ordemRepo.getById(id);
        ordemRepo.delete(ordemServico);

        return "redirect:/ordens";
    }

    @RequestMapping(value="/editar/{id}", method=RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") int id) {

        ModelAndView mv = new ModelAndView("ordemServico/edit.html");


        OrdemServico ordemServico = ordemRepo.getById(id);
        Iterable<Colaborador> colaboradores = colRepo.findAll();
        Iterable<Usuario> usuarios = userRepo.findAll();
        Iterable<Categoria> categorias = catRepo.findAll();

        mv.addObject("ordemServico", ordemServico);
        mv.addObject("colaboradores", colaboradores);
        mv.addObject("usuarios", usuarios);
        mv.addObject("categorias", categorias);

        return mv;

    }

    @RequestMapping(value="/atualizar", method=RequestMethod.POST)
    public String update(@ModelAttribute OrdemServico ordemServico, @RequestParam("id_usuario") int idUsuario, @RequestParam("id_colaborador") int idColaborador, @RequestParam("id_categoria") int idCategoria) {
        Usuario usuario = userRepo.getById(idUsuario);
        Colaborador colaborador = colRepo.getById(idColaborador);
        Categoria categoria = catRepo.getById(idCategoria);

        ordemServico.setCategoria(categoria);
        ordemServico.setColaborador(colaborador);
        ordemServico.setUsuario(usuario);
        ordemRepo.save(ordemServico);

        return "redirect:/ordens";

    }

}
