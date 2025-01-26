package io.github.betterclient.unixem.command;

import io.github.betterclient.unixem.file.FileManager;

public class PrintWorkingDirectoryCommand extends Command {
    public PrintWorkingDirectoryCommand() {
        super("pwd", "Prints the current working directory");
    }

    @Override
    public void run(String... tokens) {
        if (tokens.length != 0) {
            this.printUsage();
            return;
        }

        System.out.println(FileManager.getInstance().CURRENT_DIR);
    }
}
