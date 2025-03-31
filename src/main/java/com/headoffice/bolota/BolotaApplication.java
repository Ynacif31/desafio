package com.headoffice.bolota;

import com.headoffice.bolota.entities.User;
import com.headoffice.bolota.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BolotaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BolotaApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(UserRepository userRepository) {
		return args -> {
			List<User> initialUsers = List.of(
					new User(null, "Alice", "A1B2C3"),
					new User(null, "Bob", "D4E5F6"),
					new User(null, "Charlie", "G7H8I9"),
					new User(null, "David", "J1K2L3"),
					new User(null, "Eva", "M4N5O6"),
					new User(null, "Frank", "P7Q8R9"),
					new User(null, "Grace", "S1T2U3"),
					new User(null, "Henry", "V4W5X6"),
					new User(null, "Isabella", "Y7Z8A9"),
					new User(null, "Jack", "B1C2D3")
			);

			userRepository.saveAll(initialUsers);
		};
	}
}
