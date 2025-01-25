package io.github.betterclient.unixem.command;

public abstract class Command {
    public final String correctUsage;

    public Command(String correctUsage) {
        this.correctUsage = correctUsage;
    }

    public void printUsage() {
        System.out.println("Correct usage \"" + correctUsage + "\"");
    }

    public abstract void run(String... tokens);
}
