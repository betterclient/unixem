package io.github.betterclient.unixem.command;

import io.github.betterclient.unixem.file.FileManager;

public class CatCommand extends Command {
    public CatCommand() {
        super("cat filename", "Displays all the text inside a file");
    }

    @Override
    public void run(String... tokens) {
        if (tokens.length != 1) {
            this.printUsage();
            return;
        }

        String file = tokens[0];
        String s = FileManager.getInstance().FILES.get(
                FileManager.getInstance().CURRENT_DIR + file
        );

        if (s == null) {
            System.out.println("Cat: Please provide a valid file!");
            return;
        }

        for (String string : s.split("\n")) {
            System.out.println(string);
        }
    }
}
