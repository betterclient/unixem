package io.github.betterclient.unixem.command;

import io.github.betterclient.unixem.file.FileManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EchoCommand extends Command {
    public EchoCommand() {
        super("echo text", "Print out given text to console/file");
    }

    @Override
    public void run(String... tokens) {
        String text = String.join(" ", tokens);

        if (!text.contains(">")) {
            System.out.println(text);
            return;
        }

        String regex = "^\"?([^\"]*?)\"?\\s*(>>|>)\\s*(.*)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        if (!matcher.matches()) {
            this.printUsage();
            return;
        }

        String output = matcher.group(1).trim();
        String append = matcher.group(2).trim();
        String toFile = matcher.group(3).trim();

        FileManager manager = FileManager.getInstance();

        String key = manager.CURRENT_DIR + toFile.trim();
        if (append.length() == 1) {
            manager.FILES.put(key, output);
        } else {
            if (manager.FILES.containsKey(key)) {
                output = manager.FILES.get(key) + output;
            }
            manager.FILES.put(key, output);
        }

        manager.save();
    }
}
