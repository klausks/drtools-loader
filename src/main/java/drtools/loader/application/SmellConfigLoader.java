package drtools.loader.application;

import drtools.loader.adapter.out.json.smell.SmellConfigParser;
import drtools.loader.model.smell.config.SmellConfig;
import drtools.loader.application.port.out.repository.smell.SmellRepository;
import drtools.loader.application.port.out.repository.smell.config.QualityAttributeRepository;
import drtools.loader.application.port.out.repository.smell.config.SmellConfigRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
public class SmellConfigLoader {

    private final SmellConfigParser smellConfigParser;
    private final SmellRepository smellRepository;
    private final SmellConfigRepository smellConfigRepository;
    private final QualityAttributeRepository qualityAttributeRepository;

    public SmellConfigLoader(
            SmellConfigParser smellConfigParser,
            SmellRepository smellRepository,
            SmellConfigRepository smellConfigRepository,
            QualityAttributeRepository qualityAttributeRepository
    ) {
        this.smellConfigParser = smellConfigParser;
        this.smellRepository = smellRepository;
        this.smellConfigRepository = smellConfigRepository;
        this.qualityAttributeRepository = qualityAttributeRepository;
    }

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
