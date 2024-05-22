package drtools.loader.application.port.out;


import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.domain.criteria.QualityAttributeName;
import drtools.loader.domain.smell.config.QualityAttributeConfig;

import java.io.IOException;
import java.util.Map;

public interface InputQualityAttributesConfigProvider {
    Map<QualityAttributeName, QualityAttributeConfig> getInputQualityAttributesConfigs() throws IOException, InputLoadingException;
}
