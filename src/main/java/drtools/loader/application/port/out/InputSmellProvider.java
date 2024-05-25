package drtools.loader.application.port.out;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.domain.smell.Smell;

import java.util.Map;

public interface InputSmellProvider {
    Map<String, Smell> getSmells() throws InputLoadingException;
}
