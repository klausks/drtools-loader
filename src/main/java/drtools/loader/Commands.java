package drtools.loader;

import drtools.loader.in.smell.SmellConfigParser;
import drtools.loader.in.smell.criteria.CriteriaConfigParser;
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
