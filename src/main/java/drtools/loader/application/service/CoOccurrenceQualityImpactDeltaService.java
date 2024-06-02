package drtools.loader.application.service;

import drtools.loader.application.port.out.repository.smell.CoOccurrenceQualityImpactRepository;
import drtools.loader.domain.smell.CoOccurrenceQualityImpact;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CoOccurrenceQualityImpactDeltaService {
    private final CoOccurrenceQualityImpactRepository coOccurrenceQualityImpactRepository;

    public CoOccurrenceQualityImpactDeltaService(CoOccurrenceQualityImpactRepository coOccurrenceQualityImpactRepository) {
        this.coOccurrenceQualityImpactRepository = coOccurrenceQualityImpactRepository;
    }

    // Since there is no field other than name in CoOccurrenceQualityImpact, there is no change detection logic and the only task here is to detect new
    // quality impacts.
    public List<CoOccurrenceQualityImpact> getChanges(List<CoOccurrenceQualityImpact> input) {
        var result = new ArrayList<CoOccurrenceQualityImpact>();
        var existingRecords = coOccurrenceQualityImpactRepository.findAll().stream().collect(Collectors.toMap(CoOccurrenceQualityImpact::getName, Function.identity()));
        input.forEach(inputQualityImpact ->  {
            var correspondingExistingRecord = existingRecords.get(inputQualityImpact.getName());
            if (correspondingExistingRecord == null) {
                result.add(inputQualityImpact);
            }
        });
        return result;
    }
}
