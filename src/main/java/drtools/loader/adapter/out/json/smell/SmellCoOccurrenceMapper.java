package drtools.loader.adapter.out.json.smell;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.application.port.out.input.InputCoOccurrenceQualityImpactProvider;
import drtools.loader.application.port.out.input.InputSmellCoOccurrenceProvider;
import drtools.loader.application.port.out.input.InputSmellCoOccurrenceSummaryProvider;
import drtools.loader.domain.criteria.CoOccurrenceQualityAttributeName;
import drtools.loader.domain.smell.CoOccurrenceQualityImpact;
import drtools.loader.domain.smell.Smell;
import drtools.loader.domain.smell.SmellCoOccurrence;
import drtools.loader.domain.summary.SmellCoOccurrenceSummary;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class SmellCoOccurrenceMapper implements InputSmellCoOccurrenceSummaryProvider, InputSmellCoOccurrenceProvider, InputCoOccurrenceQualityImpactProvider {

    private final SmellCoOccurrencesJsonParser smellCoOccurrencesJsonParser;

    boolean isLoaded = false;
    private final List<SmellCoOccurrence> smellCoOccurrences = new ArrayList<>();
    private final List<CoOccurrenceQualityImpact> coOccurrenceQualityImpacts = new ArrayList<>();
    private final List<SmellCoOccurrenceSummary> smellCoOccurrenceSummaries = new ArrayList<>();

    public SmellCoOccurrenceMapper(SmellCoOccurrencesJsonParser smellCoOccurrencesJsonParser) {
        this.smellCoOccurrencesJsonParser = smellCoOccurrencesJsonParser;
    }

    @Override
    public List<SmellCoOccurrence> getSmellCoOccurrences() throws InputLoadingException {
        ensureLoaded();
        return smellCoOccurrences;
    }

    private void ensureLoaded() throws InputLoadingException {
        if (!isLoaded) {
            loadInputData();
            isLoaded = true;
        }
    }

    private void loadInputData() throws InputLoadingException {
        try {
            var inputJson = smellCoOccurrencesJsonParser.parse();
            inputJson.forEach(categoryCoOccurrencesJson -> {
                var category = categoryCoOccurrencesJson.category();
                categoryCoOccurrencesJson.data().forEach(coOccurrenceJson -> {
                    var coOccurrence = new SmellCoOccurrence();
                    coOccurrence.setCategory(category);
                    coOccurrence.setDescription(coOccurrenceJson.description());
                    coOccurrence.setSmells(smells(coOccurrenceJson));
                    var impactsOnQuality = coOccurrenceImpactsOnQuality(coOccurrenceJson);
                    coOccurrenceQualityImpacts.addAll(impactsOnQuality);
                    coOccurrence.setImpactsOnQuality(coOccurrenceImpactsOnQuality(coOccurrenceJson));
                    smellCoOccurrences.add(coOccurrence);
                    var coOccurrenceSummary = new SmellCoOccurrenceSummary();
                    coOccurrenceSummary.setCoOccurrence(coOccurrence);
                    coOccurrenceSummary.setInstances(coOccurrenceJson.instances());
                    coOccurrenceSummary.setPercentual(Double.parseDouble("0." + coOccurrenceJson.percentual().replaceAll("[,%]", "")));
                    smellCoOccurrenceSummaries.add(coOccurrenceSummary);
                });
            });
        } catch (IOException e) {
            throw new InputLoadingException(e);
        }
    }

    private List<Smell> smells(SmellCoOccurrencesByCategoryJson.SmellCoOccurrenceJson json) {
        return Arrays.stream(json.smells().trim().split(", ")).map(smellName -> {
            var smell = new Smell();
            smell.setName(smellName);
            return smell;
        }).toList();
    }

    private List<CoOccurrenceQualityImpact> coOccurrenceImpactsOnQuality(SmellCoOccurrencesByCategoryJson.SmellCoOccurrenceJson json) {
        var impactedQualityAttributeNames = Arrays.stream(json.impactsOn().trim().split(", ")).map(CoOccurrenceQualityAttributeName::valueOf).toList();
        return impactedQualityAttributeNames.stream().map(name -> {
            var qualityImpact = new CoOccurrenceQualityImpact();
            qualityImpact.setName(name);
            return qualityImpact;
        }).toList();
    }

    @Override
    public List<CoOccurrenceQualityImpact> getCoOccurrenceQualityImpacts() throws InputLoadingException {
        ensureLoaded();
        return coOccurrenceQualityImpacts;
    }

    @Override
    public List<SmellCoOccurrenceSummary> getSmellCoOccurrenceSummaries() throws InputLoadingException {
        ensureLoaded();
        return smellCoOccurrenceSummaries;
    }
}
