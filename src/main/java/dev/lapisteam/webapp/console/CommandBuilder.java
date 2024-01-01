package dev.lapisteam.webapp.console;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public class CommandBuilder {

    private final Map<String, Command> commandMap = new LinkedHashMap<>();

    public void build(String name, Consumer<CommandExecutor> consumer){
        Command command = new Command(name);
        command.setExecutor(consumer);
        register(command);
    }

    public void build(String name, String[] aliases, Consumer<CommandExecutor> consumer){
        Command command = new Command(name);
        command.setAliases(List.of(aliases));
        command.setExecutor(consumer);
        register(command);
    }

    public void register(Command command){
        if(commandMap.containsKey(command.getName())) return;
        commandMap.put(command.getName(), command);
        command.getAliases().forEach(s -> commandMap.put(s, command));
    }

    public void unregister(Command command){
        commandMap.remove(command.getName());
        command.getAliases().forEach(commandMap::remove);
    }

    public void inject(){
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String commandLine = scanner.nextLine();

            if (!commandLine.isEmpty()) {
                String[] args = commandLine.split(" ");
                Command command = commandMap.get(args[0]);
                if(command == null) {
                    System.out.println("Command does not exists. Type \"help\" for commands.");
                    continue;
                }
                System.out.println("Issued console command: " + commandLine);
                command.getExecutor().accept(new CommandExecutor(commandLine, prepareArguments(args)));
            }
        }
    }

    private String[] prepareArguments(String[] args){
        String[] prepared = new String[args.length-1];

        if(args.length > 1){
            System.arraycopy(args, 1, prepared, 0, args.length - 1);
        }
        return prepared;
    }
}
