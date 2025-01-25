package io.github.betterclient.unixem.file;

import org.teavm.jso.browser.Storage;
import org.teavm.jso.browser.Window;

import java.util.*;

public class FileManager {
    private static final FileManager INSTANCE = new FileManager();
    public static FileManager getInstance() {
        return INSTANCE;
    }

    public String CURRENT_DIR = "/home/unix/";
    public Set<String> VALID_DIRS = new HashSet<>();
    public Map<String, String> FILES = new HashMap<>();

    public FileManager() {
        Storage localStorage = Window.current().getLocalStorage();

        String dirs = localStorage.getItem("dirs");
        if (dirs == null) {
            this.populateVD();
        } else {
            //what is input validation??????
            VALID_DIRS.addAll(Arrays.asList(dirs.split("\n")));
        }

        String files = localStorage.getItem("files");
        if (files != null) {
            for (String s : files.split("\u200B")) {
                String[] split = s.split("‛");
                FILES.put(split[0], split[1]);
            }
        }
    }

    public void save() {
        Storage localStorage = Window.current().getLocalStorage();

        localStorage.setItem("dirs", String.join("\n", VALID_DIRS));
        StringBuilder files = new StringBuilder();

        for (String file : FILES.keySet()) {
            String value = FILES.get(file);
            file = file.replace("\u200B", "").replace("‛", "");
            value = value.replace("\u200B", "").replace("‛", "");

            files.append(file).append("‛").append(value).append("\u200B");
        }

        localStorage.setItem("files", files.toString());
    }

    private void populateVD() {
        VALID_DIRS.add("/");
        VALID_DIRS.add("/home/");
        VALID_DIRS.add("/home/unix/");
        VALID_DIRS.add("/bin/");
        VALID_DIRS.add("/root/");
        VALID_DIRS.add("/usr/");
    }

    public void touch(String name) {
        String fileName = CURRENT_DIR + name;
        FILES.put(fileName, "");
        save();
    }
}
