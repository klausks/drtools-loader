package drtools.loader;

import drtools.loader.model.smell.config.SmellConfig;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
public class SmellConfigLoader {


    public void loadSmellConfig() throws IOException {
        var smellConfigs = new HashMap<String, SmellConfig>();
        var defaultSmellConfigJsonDtos = smellConfigParser.parseDefault();
        defaultSmellConfigJsonDtos.forEach((smellConfigDto) -> {
            var smellConfig = new SmellConfig();
            smellConfig.
        });
        var usedSmellConfigJsonDtos = smellConfigParser.parseUsed();
    }
}
