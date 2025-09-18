package com.Disney.DisneyApp;

import com.Disney.DisneyApp.models.Erole;
import com.Disney.DisneyApp.models.RoleEntity;
import com.Disney.DisneyApp.models.UsuarioEntity;
import com.Disney.DisneyApp.repository.RoleRepository;
import com.Disney.DisneyApp.repository.UsuarioRepository;
import com.Disney.DisneyApp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class DisneyAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisneyAppApplication.class, args);
	}
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UsuarioRepository userRepository;
	@Autowired
	RoleService roleService;
	@Bean
	CommandLineRunner init (){
		return  args -> {

			RoleEntity roleUser=roleService.create("USER");
			RoleEntity roleAdmin =roleService.create("ADMIN");

			UsuarioEntity a= UsuarioEntity.builder()
					.email("user")
					.nombre("user")
					.password(passwordEncoder.encode("1234"))
					.roles(Set.of(roleUser))
					.build();
			userRepository.save(a);


			UsuarioEntity b= UsuarioEntity.builder()
					.email("admin")
					.nombre("admin")
					.password(passwordEncoder.encode("1234"))
					.roles(Set.of(roleAdmin))
					.build();
			userRepository.save(b);
		};
	}



}
