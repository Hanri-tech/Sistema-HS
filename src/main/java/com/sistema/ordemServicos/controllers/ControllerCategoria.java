package com.sistema.ordemServicos.controllers;

import com.sistema.ordemServicos.models.Cargo;
import com.sistema.ordemServicos.models.Categoria;
import com.sistema.ordemServicos.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("categorias")
public class ControllerCategoria {

    @Autowired
    CategoriaRepository catrepo;

    @RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
    public String incluir(){
        return "categoria/novo";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public String salvar(Categoria categoria){
        catrepo.save(categoria);
        return "redirect:/categorias";
    }

    @RequestMapping(value = "")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView("categoria/categorias.html");
        Iterable<Categoria> categorias = catrepo.findAll();
        mv.addObject("categorias", categorias);
        return mv;
    }

    @RequestMapping(value="/excluir/{id}", method=RequestMethod.GET)
    public String delete(@PathVariable("id") int id) {

        Categoria categoria = catrepo.getById(id);
        catrepo.delete(categoria);

        return "redirect:/categorias";
    }

    @RequestMapping(value="/editar/{id}", method=RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") int id) {

        ModelAndView mv = new ModelAndView("categoria/edit.html");
        Categoria categoria = catrepo.getById(id);
        mv.addObject("categoria", categoria);

        return mv;

    }

    @RequestMapping(value="/atualizar", method=RequestMethod.POST)
    public String update(@ModelAttribute Categoria categoria) {

        catrepo.save(categoria);

        return "redirect:/categorias";

    }
}
