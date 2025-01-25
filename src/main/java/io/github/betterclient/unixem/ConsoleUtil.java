package io.github.betterclient.unixem;

import org.teavm.jso.dom.html.HTMLDocument;
import org.teavm.jso.dom.html.HTMLElement;

import java.io.OutputStream;
import java.io.PrintStream;

public class ConsoleUtil extends OutputStream {
    static HTMLElement console;

    static {
        console = HTMLDocument.current().getElementById("input_div");
    }

    public static void println(String text) {
        if (text.replace("\n", "").trim().isEmpty()) return;

        HTMLDocument document = HTMLDocument.current();

        HTMLElement a = document.createElement("span");
        a.setInnerText(text.replace("\n", ""));
        console.appendChild(a);

        a = document.createElement("br");
        console.appendChild(a);
    }

    public static void clear() {
        while(console.getFirstChild() != null) {
            console.removeChild(console.getFirstChild());
        }
    }

    public static void start() {
        System.setOut(new PrintStream(new ConsoleUtil()));
    }

    final StringBuilder buffer = new StringBuilder();
    final PrintStream old = System.out;
    @Override
    public void write(int b) {
        buffer.append((char) b);

        if (b == '\n') {
            flush();
        }
    }

    @Override
    public void flush() {
        old.print(buffer);
        ConsoleUtil.println(buffer.toString());
        buffer.setLength(0);
    }
}
