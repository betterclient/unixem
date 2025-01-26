package io.github.betterclient.unixem.command;

import io.github.betterclient.unixem.CommandRunner;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("help", "Prints help informaation");
    }

    @Override
    public void run(String... tokens) {
        //don't do token validation here

        for (String s : CommandRunner.COMMAND_MAP.keySet()) {
            System.out.println(
                    s + " | " + CommandRunner.COMMAND_MAP.get(s).description
            );
        }
    }
}
