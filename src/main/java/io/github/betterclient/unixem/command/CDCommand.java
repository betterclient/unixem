package io.github.betterclient.unixem.command;

import io.github.betterclient.unixem.file.FileManager;

public class CDCommand extends Command {
    public CDCommand() {
        super("cd directory");
    }

    @Override
    public void run(String... tokens) {
        if (tokens.length != 1) {
            this.printUsage();
            return;
        }

        String dir = tokens[0];
        FileManager fileManager = FileManager.getInstance();
        if (fileManager.VALID_DIRS.contains(fileManager.CURRENT_DIR + dir + "/")) {
            fileManager.CURRENT_DIR += dir + "/";
        } else {
            if (dir.equals("..")) {
                if(fileManager.CURRENT_DIR.equals("/")) return;

                fileManager.CURRENT_DIR = fileManager.CURRENT_DIR.substring(0, fileManager.CURRENT_DIR.substring(0, fileManager.CURRENT_DIR.length() - 1).lastIndexOf('/') + 1);
                return;
            }

            System.out.println("cd: Directory " + dir + " doesn't exist");
        }
    }
}
