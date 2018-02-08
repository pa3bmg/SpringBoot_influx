package nl.pa3bmg.influx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootInfluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootInfluxApplication.class, args);
	}
}
