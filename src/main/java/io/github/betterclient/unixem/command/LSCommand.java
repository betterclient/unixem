package io.github.betterclient.unixem.command;

import io.github.betterclient.unixem.file.FileManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LSCommand extends Command {
    public LSCommand() {
        super("ls", "List all files in current directory");
    }

    @Override
    public void run(String... tokens) {
        if (tokens.length != 0) {
            this.printUsage();
            return;
        }

        FileManager fileManager = FileManager.getInstance();
        System.out.println("Directory of " + fileManager.CURRENT_DIR + ":");

        List<String> total = new ArrayList<>();

        total.addAll(filter(fileManager.VALID_DIRS.stream().map(string -> string.substring(0, string.length() - 1)).collect(Collectors.toSet()), "Directory: ", fileManager));
        total.addAll(filter(fileManager.FILES.keySet(), "File: ", fileManager));

        total.forEach(System.out::println);
    }

    private List<String> filter(Set<String> values, String starting, FileManager manager) {
        List<String> result = new ArrayList<>();
        for (String filePath : values) {
            if (filePath.equals(manager.CURRENT_DIR)) continue;

            if (filePath.startsWith(manager.CURRENT_DIR)) {
                String relativePath = filePath.substring(manager.CURRENT_DIR.length());
                if (!relativePath.contains("/")) {
                    result.add(starting + relativePath);
                }
            }
        }
        return result;
    }
}
