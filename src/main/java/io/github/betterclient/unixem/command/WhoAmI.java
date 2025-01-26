package io.github.betterclient.unixem.command;

public class WhoAmI extends Command {
    public WhoAmI() {
        super("whoami", "Displays the current user's name");
    }

    @Override
    public void run(String... tokens) {
        System.out.println("root");
    }
}
