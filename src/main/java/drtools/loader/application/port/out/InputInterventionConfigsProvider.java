package drtools.loader.application.port.out;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.domain.criteria.Intervention;
import drtools.loader.domain.smell.config.InterventionConfig;

import java.util.Map;

public interface InputInterventionConfigsProvider {
    Map<Intervention, InterventionConfig> getInputInterventionConfigs() throws InputLoadingException;
}
