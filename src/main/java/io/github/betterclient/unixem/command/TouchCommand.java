package io.github.betterclient.unixem.command;

import io.github.betterclient.unixem.file.FileManager;

public class TouchCommand extends Command {
    public TouchCommand() {
        super("touch filename.txt", "Create a file");
    }

    @Override
    public void run(String... tokens) {
        if (tokens.length != 1) {
            this.printUsage();
            return;
        }

        String fileName = tokens[0];
        FileManager.getInstance().touch(fileName);
    }
}
