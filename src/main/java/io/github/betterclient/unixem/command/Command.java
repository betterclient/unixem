package io.github.betterclient.unixem.command;

public abstract class Command {
    public final String correctUsage, description;

    public Command(String correctUsage, String description) {
        this.correctUsage = correctUsage;
        this.description = description;
    }

    public void printUsage() {
        System.out.println("Correct usage \"" + correctUsage + "\"");
    }

    public abstract void run(String... tokens);
}
