package br.ufg.smge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufg.smge.domain.model.Role;
import br.ufg.smge.domain.model.User;
import br.ufg.smge.domain.repository.RoleRepository;
import br.ufg.smge.domain.repository.UserRepository;

@Controller
@RequestMapping("/admin/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listPosts(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "admin/users/list";
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable long id) {
		userRepository.delete(id);
		return new ModelAndView("redirect:/admin/users");
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newUser(Model model) {
		model.addAttribute("roles", roleRepository.findAll());
		return "admin/users/new";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(@RequestParam("username") String username, @RequestParam("username") String email,
			@RequestParam("password") String password, @RequestParam("role_id") long roleId) {
		Role role = roleRepository.findOne(roleId);
		userRepository.save(new User(username, bCryptPasswordEncoder.encode(password), email, role));
		return new ModelAndView("redirect:/admin/users");
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(@RequestParam("user_id") long id, @RequestParam("username") String username,
			@RequestParam("email") String email) {
		User user = userRepository.findOne(id);

		user.setUsername(username);
		user.setEmail(email);

		userRepository.save(user);
		return new ModelAndView("redirect:/admin/users");
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable long id, Model model) {
		User user = userRepository.findOne(id);
		model.addAttribute("user", user);
		return "admin/users/edit";
	}
}
