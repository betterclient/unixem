package io.github.betterclient.unixem.command;

import io.github.betterclient.unixem.file.FileManager;

public class RMCommand extends Command {
    public RMCommand() {
        super("rm filename", "Removes given file");
    }

    @Override
    public void run(String... tokens) {
        if (tokens.length == 0) {
            this.printUsage();
            return;
        }

        for (String token : tokens) {
           String file = FileManager.getInstance().FILES.get(FileManager.getInstance().CURRENT_DIR + token);
           if (file != null) {
               FileManager.getInstance().FILES.remove(FileManager.getInstance().CURRENT_DIR + token);
           } else {
               if (FileManager.getInstance().VALID_DIRS.contains(FileManager.getInstance().CURRENT_DIR + token + "/")) {
                   FileManager.getInstance().VALID_DIRS.remove(FileManager.getInstance().CURRENT_DIR + token + "/");
               } else {
                   System.out.println("rm: failed to find " + token);
                   return;
               }
           }
        }
    }
}
