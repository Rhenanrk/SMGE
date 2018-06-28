package br.ufg.smge;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufg.smge.domain.model.Role;
import br.ufg.smge.domain.model.User;
import br.ufg.smge.domain.repository.UserRepository;

@SpringBootApplication
public class SmgeApplication {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SmgeApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserRepository userRepository) {

		Set<Role> roles = new HashSet<Role>();

		roles.add(new Role("ADMIN"));
		roles.add(new Role("PROF"));
		roles.add(new Role("STUDENT"));

		User admin = new User("admin", bCryptPasswordEncoder.encode("123456"), "admin@smge.com.br", roles);

		return args -> {
			userRepository.save(admin);
		};
	}

}
