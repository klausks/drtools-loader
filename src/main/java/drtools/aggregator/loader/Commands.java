package drtools.aggregator.loader;

import drtools.aggregator.loader.in.smell.criteria.CriteriaParser;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.IOException;

@ShellComponent
public class Commands {

    private final CriteriaParser parser;

    public Commands(CriteriaParser parser) {
        this.parser = parser;
    }

    @ShellMethod
    public void load() throws IOException {
        var criteriaList = parser.parseDefault();
        System.out.println("test");
    }
}
