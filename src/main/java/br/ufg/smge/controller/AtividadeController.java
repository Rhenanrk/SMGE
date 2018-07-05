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
import br.ufg.smge.domain.model.Atividade;
import br.ufg.smge.domain.repository.AtividadeRepository;

@Controller
@RequestMapping("/atividades")
public class AtividadeController {
  @Autowired
  private AtividadeRepository atividadeRepository;

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String listPosts(Model model) {
    model.addAttribute("Atividades", atividadeRepository.findAll());
    return "/atividades";
  }

  @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
  public ModelAndView delete(@PathVariable long id) {
    atividadeRepository.delete(id);
    return new ModelAndView("redirect:/atividades");
  }

  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public String newProject(Model model) {

    model.addAttribute("Atividade", new Atividade(new Date(), BigDecimal.ZERO, ""));
    return "/form-avaliacao";
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public ModelAndView create(@RequestParam("description") String description,
      @RequestParam("maxGrade") String maxGrade, @RequestParam("expire") String expire,
      @RequestParam("Atividade_id") String id) {

    System.out.println("Chegou");

    try {

      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
      Date expireDate = formatter.parse(expire);

      if ("".equals(id)) {
        atividadeRepository.save(new Atividade(expireDate, new BigDecimal(maxGrade), description));
      } else {

        Atividade Atividade = atividadeRepository.findOne(Long.valueOf(id));
        Atividade.setExpirationDate(expireDate);
        Atividade.setMaxGrade(new BigDecimal(maxGrade));
        Atividade.setDescription(description);

        atividadeRepository.save(Atividade);
      }

    } catch (ParseException e) {
      e.printStackTrace();
    }

    return new ModelAndView("redirect:/atividades");
  }


  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
  public String edit(@PathVariable long id, Model model) {
    Atividade Atividade = atividadeRepository.findOne(id);
    model.addAttribute("Atividade", Atividade);
    return "/form-avaliacao";
  }
}
