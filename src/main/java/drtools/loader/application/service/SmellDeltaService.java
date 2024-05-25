package drtools.loader.application.service;

import drtools.loader.application.port.out.repository.smell.SmellRepository;
import drtools.loader.application.port.out.repository.smell.config.QualityAttributeRepository;
import drtools.loader.domain.smell.QualityAttribute;
import drtools.loader.domain.smell.Smell;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class SmellDeltaService {
    private final SmellRepository smellRepository;
    private final List<QualityAttribute> qualityAttributes;

    public SmellDeltaService(SmellRepository smellConfigRepository, QualityAttributeRepository qualityAttributeRepository) {
        this.smellRepository = smellConfigRepository;
        this.qualityAttributes = qualityAttributeRepository.findAll();
    }

    public List<Smell> getChangedSmells(Map<String, Smell> inputSmells) {
        var changedSmells = new ArrayList<Smell>();
        var currentSmells = smellRepository.findAll().stream().collect(Collectors.toMap(Smell::getName, Function.identity()));
        inputSmells.forEach((smellName, inputSmell) -> {
            var currentSmell = currentSmells.get(smellName);
            if (currentSmell != null && hasChanged(currentSmell, inputSmell)) {
                inputSmell.setId(currentSmell.getId());
                inputSmell.setImpactedQualityAttributes(qualityAttributes.stream().filter(
                        qualityAttribute -> inputSmell.getImpactedQualityAttributes().contains(qualityAttribute)).collect(Collectors.toList()));
                changedSmells.add(inputSmell);
            } else if (currentSmell == null) {
                changedSmells.add(inputSmell);
            }
        });
        return changedSmells;
    }

    private boolean hasChanged(Smell currentSmell, Smell newSmell) {
        if (!currentSmell.getName().equals(newSmell.getName())) {
            throw new RuntimeException();
        }
        return !currentSmell.getDescription().equals(newSmell.getDescription())
                || !haveSameImpactedQualityAttributes(currentSmell, newSmell);
    }

    private boolean haveSameImpactedQualityAttributes(Smell currentSmell, Smell newSmell) {
        var currentSmellImpactedQualityAttributeNames = currentSmell.getImpactedQualityAttributes().stream().map(QualityAttribute::getName).toList();
        var newSmellImpactedQualityAttributeNames = newSmell.getImpactedQualityAttributes().stream().map(QualityAttribute::getName).toList();
        return currentSmellImpactedQualityAttributeNames.containsAll(newSmellImpactedQualityAttributeNames);
    }

}
