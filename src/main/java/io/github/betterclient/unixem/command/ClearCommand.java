package io.github.betterclient.unixem.command;

import io.github.betterclient.unixem.ConsoleUtil;

public class ClearCommand extends Command {
    public ClearCommand() {
        super("clear");
    }

    @Override
    public void run(String... tokens) {
        if (tokens.length > 0) {
            this.printUsage();
            return;
        }

        ConsoleUtil.clear();
    }
}
