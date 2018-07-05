package br.ufg.smge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import br.ufg.smge.domain.model.Discipline;
import br.ufg.smge.domain.repository.DisciplineRepository;

@Controller
@RequestMapping("/disciplinas")
public class DisciplineController {

  @Autowired
  private DisciplineRepository repository;

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String listPosts(Model model) {
    model.addAttribute("disciplines", repository.findAll());
    return "disciplinas";
  }

  @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
  public ModelAndView delete(@PathVariable long id) {
    repository.delete(id);
    return new ModelAndView("redirect:/disciplinas");
  }

  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public String newProject(Model model) {
    model.addAttribute("discipline", new Discipline());
    return "/form-disciplina";
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public ModelAndView create(@RequestParam("disciplina") String name,
      @RequestParam("carga-horaria") String cargaHoraria, @RequestParam("media") String media,
      @RequestParam("discipline_id") String id) {
    if ("".equals(id)) {
      repository.save(new Discipline(name, cargaHoraria, media));
    } else {
      Discipline discipline = repository.findOne(Long.valueOf( id));
      discipline.setName(name);
      discipline.setCourseLoad(cargaHoraria);
      discipline.setMedia(media);
      
      repository.save(discipline);
      
    }
    return new ModelAndView("redirect:/disciplinas");
  }

  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
  public String edit(@PathVariable long id, Model model) {
    Discipline discipline = repository.findOne(id);
    model.addAttribute("discipline", discipline);
    return "/form-disciplina";
  }
}
