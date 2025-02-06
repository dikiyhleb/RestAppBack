package dev.barapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BarappApplication {
	private static Initializer initializer;

	@Autowired
	public void setInitiatorLoader(Initializer initializer) {
		BarappApplication.initializer = initializer;
	}

	public static void main(String[] args) {
		SpringApplication.run(BarappApplication.class, args);
		initializer.initialize();
	}
}
