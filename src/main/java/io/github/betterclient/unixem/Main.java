package io.github.betterclient.unixem;

import org.teavm.jso.dom.html.HTMLDocument;
import org.teavm.jso.dom.html.HTMLInputElement;

public class Main {
    public static void main(String[] args) {
        ConsoleUtil.start();

        HTMLDocument document = HTMLDocument.current();
        HTMLInputElement inputRe = (HTMLInputElement) document.getElementById("INPUT_RE");
        inputRe.focus();
        inputRe.addEventListener("blur", evt -> inputRe.focus());

        inputRe.onKeyPress(evt -> {
            if (evt.getKey().equals("Enter")) {
                String text = inputRe.getValue();
                System.out.println("emulator@unix> " + text);
                inputRe.setValue("");

                CommandRunner.run(text);
            }
        });
    }
}
