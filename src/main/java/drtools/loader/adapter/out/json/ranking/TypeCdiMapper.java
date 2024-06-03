package drtools.loader.adapter.out.json.ranking;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.adapter.out.json.JsonInputParser;
import drtools.loader.adapter.out.json.smell.MethodSmellJsonDto;
import drtools.loader.adapter.out.json.smell.SmellJsonDto;
import drtools.loader.adapter.out.json.smell.TypeSmellsJsonDto;
import drtools.loader.application.port.out.input.InputTypeCdiProvider;
import drtools.loader.domain.cdi.TypeCdi;
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
public class TypeCdiMapper implements InputTypeCdiProvider {
    private static final String TYPE_SMELLS_FILE = "drtools-smells-types.json";
    private static final String TYPE_CDI_FILE = "drtools-cdi-types.json";
    private static final String TYPE_COMPOSITION_CDI_FILE = "drtools-composition-cdi-types.json";

    @Value("${drtools.analysis.files.smells.dir}")
    private String SMELLS_DIR;
    @Value("${drtools.analysis.files.ranking.dir}")
    private String RANKING_DIR;

    private final JsonInputParser jsonParser;
    private final List<TypeCdi> typeCdis = new ArrayList<>();

    private boolean isLoaded = false;

    public TypeCdiMapper(JsonInputParser jsonParser) {
        this.jsonParser = jsonParser;
    }

    @Override
    public List<TypeCdi> getTypeCdis() throws InputLoadingException {
        ensureLoaded();
        return typeCdis;
    }

    private void ensureLoaded() throws InputLoadingException {
        if (!isLoaded) {
            loadInputData();
            isLoaded = true;
        }
    }

    private void loadInputData() throws InputLoadingException {
        try {
            List<TypeCdiJsonDto> typeCdiJsonDtos = jsonParser.parseInput(RANKING_DIR + File.separator + TYPE_CDI_FILE);
            List<TypeCompositionCdiJsonDto> typeCompositionCdiJsonDtos = jsonParser.parseInput(RANKING_DIR + File.separator + TYPE_COMPOSITION_CDI_FILE);
            Map<String, TypeCdi> typeCdiMap = new HashMap<>();
            typeCdiJsonDtos.forEach(typeCdiJsonDto -> {
                var typeCdi = new TypeCdi();
                typeCdi.setCdi(typeCdiJsonDto.cdi());
                typeCdi.setTypeName(typeCdiJsonDto.type());
                typeCdi.setNormalizedIntervention(typeCdiJsonDto.intervention());
                typeCdi.setNormalizedSeverity(typeCdiJsonDto.severity());
                typeCdi.setNormalizedRepresentativity(typeCdiJsonDto.representativity());
                typeCdi.setNormalizedQuality(typeCdiJsonDto.quality());
                typeCdiMap.put(typeCdi.getTypeName(), typeCdi);
            });
            typeCompositionCdiJsonDtos.forEach(typeCompositionCdiJsonDto -> typeCdiMap.get(typeCompositionCdiJsonDto.type()).setCompositionCdi(typeCompositionCdiJsonDto.cdi()));

            List<TypeSmellsJsonDto> typeSmellsJsonDtos = jsonParser.parseInput(SMELLS_DIR + File.separator + TYPE_SMELLS_FILE);
            linkSmells(typeCdiMap, typeSmellsJsonDtos);
            this.typeCdis.addAll(typeCdiMap.values());
        } catch(IOException e) {
            String errMsg = String.format("Failed to load JSON input. Cause: %s", e.getMessage());
            throw new InputLoadingException(errMsg);
        }
    }

    private void linkSmells(Map<String, TypeCdi> typeCdiMap, List<TypeSmellsJsonDto> typeSmellsJsonDtos) {
        typeSmellsJsonDtos.forEach(typeSmellsJsonDto -> {
            String type = typeSmellsJsonDto.type();
            var smells = toSmells(typeSmellsJsonDto.smells());
            typeCdiMap.get(type).setSmells(smells);
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
