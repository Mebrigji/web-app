package dev.lapisteam.webapp;

import dev.lapisteam.webapp.console.CommandBuilder;
import dev.lapisteam.webapp.logger.LogInjector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebAppApplication {

	public static void main(String[] args) {

		LogInjector.bind();

		SpringApplication.run(WebAppApplication.class, args);

		CommandBuilder commandBuilder = new CommandBuilder();
		commandBuilder.build("stop", commandExecutor -> System.exit(0));
		commandBuilder.inject();
	}

}
