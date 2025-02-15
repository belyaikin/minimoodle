package me.belyaikin.minimoodle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MiniMoodleApplication {
	public static final Logger LOGGER = LoggerFactory.getLogger(MiniMoodleApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MiniMoodleApplication.class, args);
	}

}
