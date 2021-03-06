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
@RequestMapping("/avaliacoes")
public class TestController {

  @Autowired
  private TestRepository testRepository;

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String listPosts(Model model) {
    model.addAttribute("tests", testRepository.findAll());
    return "/avaliacoes";
  }

  @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
  public ModelAndView delete(@PathVariable long id) {
    testRepository.delete(id);
    return new ModelAndView("redirect:/avaliacoes");
  }

  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public String newProject(Model model) {

    model.addAttribute("test", new Test(new Date(), BigDecimal.ZERO, ""));
    return "/form-avaliacao";
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public ModelAndView create(@RequestParam("description") String description,
      @RequestParam("maxGrade") String maxGrade, @RequestParam("expire") String expire,
      @RequestParam("test_id") String id) {

    System.out.println("Chegou");
    
    try {

      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
      Date expireDate = formatter.parse(expire);

      if ("".equals(id)) {
        testRepository.save(new Test(expireDate, new BigDecimal(maxGrade), description));
      } else {
        
        Test test = testRepository.findOne(Long.valueOf(id));
        test.setExpirationDate(expireDate);
        test.setMaxGrade(new BigDecimal(maxGrade));
        test.setDescription(description);
        
        testRepository.save(test);
      }

    } catch (ParseException e) {
      e.printStackTrace();
    }

    return new ModelAndView("redirect:/avaliacoes");
  }


  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
  public String edit(@PathVariable long id, Model model) {
    Test test = testRepository.findOne(id);
    model.addAttribute("test", test);
    return "/form-avaliacao";
  }
}
