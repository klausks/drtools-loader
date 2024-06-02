package drtools.loader.application.port.out.input;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.domain.smell.SmellCoOccurrence;

import java.util.List;

public interface InputSmellCoOccurrenceProvider {
    List<SmellCoOccurrence> getSmellCoOccurrences() throws InputLoadingException;
}
