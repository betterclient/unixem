package io.github.betterclient.unixem;

import io.github.betterclient.unixem.command.*;

import java.util.HashMap;
import java.util.Map;

public class CommandRunner {
    static Map<String, Command> COMMAND_MAP = new HashMap<>();
    static {
        COMMAND_MAP.put("clear", new ClearCommand());
        COMMAND_MAP.put("cat", new CatCommand());
        COMMAND_MAP.put("touch", new TouchCommand());
        COMMAND_MAP.put("ls", new LSCommand());
        COMMAND_MAP.put("echo", new EchoCommand());
        COMMAND_MAP.put("rm", new RMCommand());
        COMMAND_MAP.put("cd", new CDCommand());
        COMMAND_MAP.put("mkdir", new MakeDirectoryCommand());
    }

    public static void run(String text) {
        if (text.trim().isEmpty()) {
            return;
        }

        String[] tokens = text.split(" ");
        String command = tokens[0];
        Command command0 = COMMAND_MAP.get(command);
        if (command0 == null) {
            System.out.println("Unknown command: " + command);
            return;
        }

        String[] out = new String[tokens.length - 1];
        System.arraycopy(tokens, 1, out, 0, tokens.length - 1);

        command0.run(out);
    }
}
