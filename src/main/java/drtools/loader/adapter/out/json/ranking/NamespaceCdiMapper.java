package drtools.loader.adapter.out.json.ranking;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.adapter.out.json.JsonInputParser;
import drtools.loader.adapter.out.json.smell.SmellJsonDto;
import drtools.loader.adapter.out.json.smell.NamespaceSmellsJsonDto;
import drtools.loader.application.port.out.input.InputNamespaceCdiProvider;
import drtools.loader.domain.cdi.NamespaceCdi;
import drtools.loader.domain.smell.Smell;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NamespaceCdiMapper implements InputNamespaceCdiProvider {
    private static final String TYPE_SMELLS_FILE = "drtools-smells-namespaces.json";
    private static final String TYPE_CDI_FILE = "drtools-cdi-namespaces.json";
    private static final String TYPE_COMPOSITION_CDI_FILE = "drtools-composition-cdi-namespaces.json";

    @Value("${drtools.analysis.files.smells.dir}")
    private String SMELLS_DIR;
    @Value("${drtools.analysis.files.ranking.dir}")
    private String RANKING_DIR;

    private final JsonInputParser jsonParser;
    private final List<NamespaceCdi> namespaceCdis = new ArrayList<>();

    private boolean isLoaded = false;

    public NamespaceCdiMapper(JsonInputParser jsonParser) {
        this.jsonParser = jsonParser;
    }

    @Override
    public List<NamespaceCdi> getNamespaceCdis() throws InputLoadingException {
        ensureLoaded();
        return namespaceCdis;
    }

    private void ensureLoaded() throws InputLoadingException {
        if (!isLoaded) {
            loadInputData();
            isLoaded = true;
        }
    }

    private void loadInputData() throws InputLoadingException {
        try {
            List<NamespaceCdiJsonDto> namespaceCdiJsonDtos = jsonParser.parseInput(RANKING_DIR + File.separator + TYPE_CDI_FILE);
            List<NamespaceCompositionCdiJsonDto> namespaceCompositionCdiJsonDtos = jsonParser.parseInput(RANKING_DIR + File.separator + TYPE_COMPOSITION_CDI_FILE);
            Map<String, NamespaceCdi> namespaceCdiMap = new HashMap<>();
            namespaceCdiJsonDtos.forEach(namespaceCdiJsonDto -> {
                var namespaceCdi = new NamespaceCdi();
                namespaceCdi.setCdi(namespaceCdiJsonDto.cdi());
                namespaceCdi.setNamespaceName(namespaceCdiJsonDto.namespace());
                namespaceCdi.setNormalizedIntervention(namespaceCdiJsonDto.intervention());
                namespaceCdi.setNormalizedSeverity(namespaceCdiJsonDto.severity());
                namespaceCdi.setNormalizedRepresentativity(namespaceCdiJsonDto.representativity());
                namespaceCdi.setNormalizedQuality(namespaceCdiJsonDto.quality());
                namespaceCdiMap.put(namespaceCdi.getNamespaceName(), namespaceCdi);
            });
            namespaceCompositionCdiJsonDtos.forEach(namespaceCompositionCdiJsonDto -> namespaceCdiMap.get(namespaceCompositionCdiJsonDto.namespace()).setCompositionCdi(namespaceCompositionCdiJsonDto.cdi()));

            List<NamespaceSmellsJsonDto> namespaceSmellsJsonDtos = jsonParser.parseInput(SMELLS_DIR + File.separator + TYPE_SMELLS_FILE);
            linkSmells(namespaceCdiMap, namespaceSmellsJsonDtos);
            this.namespaceCdis.addAll(namespaceCdiMap.values());
        } catch(IOException e) {
            String errMsg = String.format("Failed to load JSON input. Cause: %s", e.getMessage());
            throw new InputLoadingException(errMsg);
        }
    }

    private void linkSmells(Map<String, NamespaceCdi> namespaceCdiMap, List<NamespaceSmellsJsonDto> namespaceSmellsJsonDtos) {
        namespaceSmellsJsonDtos.forEach(namespaceSmellsJsonDto -> {
            String namespace = namespaceSmellsJsonDto.namespace();
            var smells = toSmells(namespaceSmellsJsonDto.smells());
            namespaceCdiMap.get(namespace).setSmells(smells);
        });
    }

    private List<Smell> toSmells(List<SmellJsonDto> smellJsonDtos) {
        return smellJsonDtos.stream().map(smellJsonDto -> {
            var smell = new Smell();
            smell.setName(smellJsonDto.smell());
            return smell;
        }).toList();
    }
}
