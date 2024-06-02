package drtools.loader.application.port.out.input;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.domain.smell.CoOccurrenceQualityImpact;

import java.util.List;

public interface InputCoOccurrenceQualityImpactProvider {
    List<CoOccurrenceQualityImpact> getCoOccurrenceQualityImpacts() throws InputLoadingException;
}
