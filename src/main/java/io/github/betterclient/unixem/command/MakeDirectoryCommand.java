package io.github.betterclient.unixem.command;

import io.github.betterclient.unixem.file.FileManager;

public class MakeDirectoryCommand extends Command {
    public MakeDirectoryCommand() {
        super("mkdir dirname");
    }

    @Override
    public void run(String... tokens) {
        if (tokens.length != 1) {
            this.printUsage();
            return;
        }

        FileManager fileManager = FileManager.getInstance();
        fileManager.VALID_DIRS.add(
                fileManager.CURRENT_DIR + tokens[0] + "/"
        );
    }
}
