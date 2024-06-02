package drtools.loader.application.service;

import drtools.loader.application.port.out.repository.smell.CoOccurrenceQualityImpactRepository;
import drtools.loader.application.port.out.repository.smell.SmellCoOccurrenceRepository;
import drtools.loader.application.port.out.repository.smell.SmellRepository;
import drtools.loader.domain.criteria.CoOccurrenceQualityAttributeName;
import drtools.loader.domain.smell.CoOccurrenceQualityImpact;
import drtools.loader.domain.smell.Smell;
import drtools.loader.domain.smell.SmellCoOccurrence;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class SmellCoOccurrenceDeltaService {
    private Map<String, Smell> smellRecords = new HashMap<>(20);
    private Map<CoOccurrenceQualityAttributeName, CoOccurrenceQualityImpact> coOccurrenceQualityImpactRecords = new HashMap<>(20);

    private final SmellRepository smellRepository;
    private final SmellCoOccurrenceRepository smellCoOccurrenceRepository;
    private final CoOccurrenceQualityImpactRepository coOccurrenceQualityImpactRepository;

    public SmellCoOccurrenceDeltaService(SmellRepository smellRepository, SmellCoOccurrenceRepository smellCoOccurrenceRepository,
                                         CoOccurrenceQualityImpactRepository coOccurrenceQualityImpactRepository
    ) {
        this.smellRepository = smellRepository;
        this.smellCoOccurrenceRepository = smellCoOccurrenceRepository;
        this.coOccurrenceQualityImpactRepository = coOccurrenceQualityImpactRepository;
    }

    public List<SmellCoOccurrence> getChanges(List<SmellCoOccurrence> inputSmellCoOccurrences) {
        var result = new ArrayList<SmellCoOccurrence>();
        var existingRecords = smellCoOccurrenceRepository.findAll().stream().collect(
                Collectors.toMap(SmellCoOccurrence::getDescription, Function.identity()));
        inputSmellCoOccurrences.forEach(inputSmellCoOccurrence -> {
            var correspondingRecord = existingRecords.get(inputSmellCoOccurrence.getDescription());
            if (correspondingRecord == null) {
                inputSmellCoOccurrence.setSmells(findSmellRecords(inputSmellCoOccurrence));
                inputSmellCoOccurrence.setImpactsOnQuality(findImpactsOnQuality(inputSmellCoOccurrence));
                result.add(inputSmellCoOccurrence);
            } else if(hasChanged(correspondingRecord, inputSmellCoOccurrence)) {
                inputSmellCoOccurrence.setId(correspondingRecord.getId());
                inputSmellCoOccurrence.setSmells(findSmellRecords(inputSmellCoOccurrence));
                inputSmellCoOccurrence.setImpactsOnQuality(findImpactsOnQuality(inputSmellCoOccurrence));
                result.add(inputSmellCoOccurrence);
            }
        });
        return result;
    }

    private boolean hasChanged(SmellCoOccurrence smellCoOccurrenceRecord, SmellCoOccurrence inputSmell) {
        return !smellCoOccurrenceRecord.getDescription().equals(inputSmell.getDescription())
                || !smellCoOccurrenceRecord.getCategory().equals(inputSmell.getCategory())
                || hasImpactsOnQualityChanged(smellCoOccurrenceRecord, inputSmell);
    }

    private boolean hasImpactsOnQualityChanged(SmellCoOccurrence record, SmellCoOccurrence input) {
        return record.getImpactsOnQuality().size() == input.getImpactsOnQuality().size()
                && input.getImpactsOnQuality().stream().map(CoOccurrenceQualityImpact::getName)
                        .toList()
                        .containsAll(record.getImpactsOnQuality().stream().map(CoOccurrenceQualityImpact::getName).toList());
    }

    private List<Smell> findSmellRecords(SmellCoOccurrence inputSmellCoOccurrence) {
        if (smellRecords.isEmpty()) {
            smellRecords = smellRepository.findAll().stream().collect(Collectors.toMap(Smell::getName, Function.identity()));
        }
        return inputSmellCoOccurrence.getSmells().stream().map(smell -> smellRecords.get(smell.getName())).collect(Collectors.toList());
    }

    private List<CoOccurrenceQualityImpact> findImpactsOnQuality(SmellCoOccurrence input) {
        if (coOccurrenceQualityImpactRecords.isEmpty()) {
            coOccurrenceQualityImpactRecords = coOccurrenceQualityImpactRepository.findAll().stream().collect(Collectors.toMap(CoOccurrenceQualityImpact::getName, Function.identity()));
        }
        return input.getImpactsOnQuality().stream().map(qualityImpact -> coOccurrenceQualityImpactRecords.get(qualityImpact.getName())).collect(Collectors.toList());
    }


}
