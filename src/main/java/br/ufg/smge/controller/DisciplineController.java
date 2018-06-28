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
@RequestMapping("/admin/discipline")
public class DisciplineController {

	@Autowired
	private DisciplineRepository repository;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listPosts(Model model) {
		model.addAttribute("disciplines", repository.findAll());
		return "admin/discipline/list";
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable long id) {
		repository.delete(id);
		return new ModelAndView("redirect:/admin/discipline");
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newProject() {
		return "admin/discipline/new";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(@RequestParam("name") String name) {
		repository.save(new Discipline(name, "64"));
		return new ModelAndView("redirect:/admin/discipline");
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(@RequestParam("discipline_id") long id, @RequestParam("name") String name) {
		Discipline discipline = repository.findOne(id);
		discipline.setName(name);
		repository.save(discipline);
		return new ModelAndView("redirect:/admin/discipline");
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable long id, Model model) {
		Discipline discipline = repository.findOne(id);
		model.addAttribute("discipline", discipline);
		return "admin/discipline/edit";
	}
}
