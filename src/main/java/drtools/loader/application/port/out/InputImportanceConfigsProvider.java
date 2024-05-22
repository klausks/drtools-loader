package drtools.loader.application.port.out;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.domain.criteria.Importance;
import drtools.loader.domain.smell.config.ImportanceConfig;

import java.util.Map;

public interface InputImportanceConfigsProvider {
    Map<Importance, ImportanceConfig> getInputImportanceConfigs() throws InputLoadingException;
}
