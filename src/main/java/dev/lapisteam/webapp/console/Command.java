package dev.lapisteam.webapp.console;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Command {

    private final String name;
    private List<String> aliases = new ArrayList<>();

    private Consumer<CommandExecutor> executor;

    public Command(String name) {
        this.name = name;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public String getName() {
        return name;
    }

    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    public Consumer<CommandExecutor> getExecutor() {
        return executor;
    }

    public void setExecutor(Consumer<CommandExecutor> executor) {
        this.executor = executor;
    }

}
