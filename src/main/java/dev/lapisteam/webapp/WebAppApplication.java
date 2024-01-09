package dev.lapisteam.webapp;

import dev.lapisteam.webapp.console.CommandBuilder;
import dev.lapisteam.webapp.factory.EventFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebAppApplication {

	public static void main(String[] args) {
		EventFactory eventFactory = new EventFactory();
		eventFactory.buildAuthEvent();

		SpringApplication.run(WebAppApplication.class, args);

		CommandBuilder commandBuilder = new CommandBuilder();
		commandBuilder.build("stop", commandExecutor -> System.exit(0));
		commandBuilder.inject();
	}

}
