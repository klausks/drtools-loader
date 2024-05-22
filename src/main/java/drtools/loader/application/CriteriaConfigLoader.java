package drtools.loader.application;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.application.port.out.InputImportanceConfigsProvider;
import drtools.loader.application.port.out.InputInterventionConfigsProvider;
import drtools.loader.application.port.out.InputQualityAttributesConfigProvider;
import drtools.loader.application.port.out.InputQualityAttributesProvider;
import drtools.loader.application.port.out.repository.smell.config.ImportanceConfigRepository;
import drtools.loader.application.port.out.repository.smell.config.InterventionConfigrepository;
import drtools.loader.application.port.out.repository.smell.config.QualityAttributeConfigRepository;
import drtools.loader.application.port.out.repository.smell.config.QualityAttributeRepository;
import drtools.loader.application.service.QualityAttributesConfigComparator;
import drtools.loader.domain.Execution;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CriteriaConfigLoader {

    private final InputQualityAttributesProvider inputQualityAttributesProvider;
    private final InputQualityAttributesConfigProvider inputQualityAttributesConfigProvider;
    private final InputImportanceConfigsProvider inputImportanceConfigsProvider;
    private final InputInterventionConfigsProvider inputInterventionConfigsProvider;

    private final QualityAttributeRepository qualityAttributeRepository;
    private final ImportanceConfigRepository importanceConfigRepository;
    private final QualityAttributeConfigRepository qualityAttributeConfigRepository;
    private final InterventionConfigrepository interventionConfigrepository;

    private final QualityAttributesConfigComparator qualityAttributesConfigComparator;



    public CriteriaConfigLoader(
            InputQualityAttributesProvider inputQualityAttributesProvider,
            InputQualityAttributesConfigProvider inputQualityAttributesConfigProvider,
            InputImportanceConfigsProvider inputImportanceConfigsProvider,
            InputInterventionConfigsProvider inputInterventionConfigsProvider, ImportanceConfigRepository importanceConfigRepository, QualityAttributeConfigRepository qualityAttributeConfigRepository, InterventionConfigrepository interventionConfigrepository,
            QualityAttributeRepository qualityAttributeRepository,
            QualityAttributesConfigComparator qualityAttributesConfigComparator
    ) {
        this.inputQualityAttributesProvider = inputQualityAttributesProvider;
        this.inputQualityAttributesConfigProvider = inputQualityAttributesConfigProvider;
        this.inputImportanceConfigsProvider = inputImportanceConfigsProvider;
        this.inputInterventionConfigsProvider = inputInterventionConfigsProvider;
        this.qualityAttributeRepository = qualityAttributeRepository;
        this.importanceConfigRepository = importanceConfigRepository;
        this.qualityAttributeConfigRepository = qualityAttributeConfigRepository;
        this.interventionConfigrepository = interventionConfigrepository;
        this.qualityAttributesConfigComparator = qualityAttributesConfigComparator;
    }

    public void loadCriteriaConfig(Execution execution) throws IOException, InputLoadingException {
        var inputQualityAttributes = inputQualityAttributesProvider.getInputQualityAttributes();
        var inputQualityAttributesConfigs = inputQualityAttributesConfigProvider.getInputQualityAttributesConfigs();
        var inputImportanceConfigs = inputImportanceConfigsProvider.getInputImportanceConfigs();
        var inputInterventionConfigs = inputInterventionConfigsProvider.getInputInterventionConfigs();

        if (execution.getId() == 1) {
            importanceConfigRepository.saveAll(inputImportanceConfigs.values());
            interventionConfigrepository.saveAll(inputInterventionConfigs.values());
            qualityAttributeRepository.saveAll(inputQualityAttributes.values());
            qualityAttributeConfigRepository.saveAll(inputQualityAttributesConfigs.values());
        } else {
            qualityAttributeConfigRepository.saveAll(qualityAttributesConfigComparator.getChangedConfigs(inputQualityAttributesConfigs));
            importanceConfigRepository.saveAll(
            // TODO: Implement config change detection
        }
    }
}
