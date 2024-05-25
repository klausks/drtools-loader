package drtools.loader.application.port.out;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.domain.smell.config.SmellConfig;

import java.util.Map;

public interface InputSmellConfigProvider {
    Map<String, SmellConfig> getSmellConfigs() throws InputLoadingException;
}
