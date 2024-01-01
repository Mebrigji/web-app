package dev.lapisteam.webapp.console;

public record CommandExecutor(String input, String[] args) {

    public void sendMessage(String message){
        System.out.println("Message> " + message);
    }

}
