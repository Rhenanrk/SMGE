package br.ufg.smge.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufg.smge.domain.model.Test;
import br.ufg.smge.domain.repository.TestRepository;

@Controller
@RequestMapping("/prof/test")
public class TestController {

	@Autowired
	private TestRepository testRepository;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listPosts(Model model) {
		model.addAttribute("tests", testRepository.findAll());
		return "prof/test/list";
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable long id) {
		testRepository.delete(id);
		return new ModelAndView("redirect:/prof/test");
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newProject() {
		return "prof/test/new";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(@RequestParam("description") String description,
			@RequestParam("maxGrade") String maxGrade, @RequestParam("expire") String expire) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date expireDate = formatter.parse(expire);

			testRepository.save(new Test(expireDate, new BigDecimal(maxGrade), description));

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return new ModelAndView("redirect:/prof/test");
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(@RequestParam("discipline_id") long id, @RequestParam("description") String description,
			@RequestParam("maxGrade") String maxGrade, @RequestParam("expire") String expire) {
		Test test = testRepository.findOne(id);

		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date expireDate = formatter.parse(expire);

			test.setExpirationDate(expireDate);
			test.setDescription(description);
			test.setMaxGrade(new BigDecimal(maxGrade));

			testRepository.save(test);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		testRepository.save(test);
		return new ModelAndView("redirect:/prof/test");
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable long id, Model model) {
		Test test = testRepository.findOne(id);
		model.addAttribute("test", test);
		return "prof/test/edit";
	}
}
