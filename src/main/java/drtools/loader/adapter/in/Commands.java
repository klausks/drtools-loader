package drtools.loader.adapter.in;

import drtools.loader.adapter.out.json.smell.SmellConfigParser;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.IOException;

@ShellComponent
public class Commands {

    private final SmellConfigParser parser;

    public Commands(SmellConfigParser parser) {
        this.parser = parser;
    }

    @ShellMethod
    public void load() throws IOException {
        var result = parser.parseDefault();
        System.out.println("test");
    }
}
