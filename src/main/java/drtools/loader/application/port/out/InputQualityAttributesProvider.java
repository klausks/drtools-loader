package drtools.loader.application.port.out;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.domain.criteria.QualityAttributeName;
import drtools.loader.domain.smell.QualityAttribute;

import java.util.Map;

public interface InputQualityAttributesProvider {
    Map<QualityAttributeName, QualityAttribute> getInputQualityAttributes() throws InputLoadingException;
}
