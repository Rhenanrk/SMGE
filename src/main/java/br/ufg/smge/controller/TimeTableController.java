package br.ufg.smge.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufg.smge.domain.model.ClassRoom;
import br.ufg.smge.domain.model.Discipline;
import br.ufg.smge.domain.model.TimeTable;
import br.ufg.smge.domain.model.enumerator.WeekDay;
import br.ufg.smge.domain.repository.ClassRoomRepository;
import br.ufg.smge.domain.repository.DisciplineRepository;
import br.ufg.smge.domain.repository.TimeTableRepository;

@Controller
@RequestMapping("/admin/timetable")
public class TimeTableController {

	@Autowired
	private ClassRoomRepository classRoomRepository;

	@Autowired
	private TimeTableRepository timeTableRepository;

	@Autowired
	private DisciplineRepository disciplineRepository;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listPosts(Model model) {
		model.addAttribute("classRooms", classRoomRepository.findAll());
		return "admin/timetable/list";
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable long id) {
		TimeTable time = timeTableRepository.findOne(id);
		timeTableRepository.delete(id);
		return new ModelAndView("redirect:/admin/timetable/" + time.getClassRoom().getId() + "/new");
	}

	@RequestMapping(value = "/{id}/new", method = RequestMethod.GET)
	public String newProject(@PathVariable long id, Model model) {

		ClassRoom classRoom = classRoomRepository.findOne(id);
		List<TimeTable> times = timeTableRepository.findByClassRoom(classRoom);

		model.addAttribute("times", times);
		model.addAttribute("classRoom", classRoom);
		model.addAttribute("days", Arrays.asList(WeekDay.values()));
		model.addAttribute("disciplines", disciplineRepository.findAll());

		return "admin/timetable/new";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(@RequestParam("ordinal") Integer ordinal, @RequestParam("day") String day,
			@RequestParam("discipline") long disciiplineId, @RequestParam("classroom_id") long id) {

		ClassRoom classRoom = classRoomRepository.findOne(id);
		Discipline discipline = disciplineRepository.findOne(disciiplineId);
		timeTableRepository.save(new TimeTable(discipline, classRoom, WeekDay.valueOf(day), ordinal));

		return new ModelAndView("redirect:/admin/timetable/" + id + "/new");
	}

}
