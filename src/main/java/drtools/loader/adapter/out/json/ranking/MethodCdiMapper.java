package drtools.loader.adapter.out.json.ranking;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.adapter.out.json.JsonInputParser;
import drtools.loader.adapter.out.json.smell.MethodSmellJsonDto;
import drtools.loader.adapter.out.json.smell.SmellJsonDto;
import drtools.loader.application.port.out.input.InputMethodCdiProvider;
import drtools.loader.domain.cdi.MethodCdi;
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
public class MethodCdiMapper implements InputMethodCdiProvider {
    private static final String METHOD_SMELLS_FILE = "drtools-smells-methods.json";
    private static final String METHOD_CDI_FILE = "drtools-cdi-methods.json";

    @Value("${drtools.analysis.files.smells.dir}")
    private String SMELLS_DIR;
    @Value("${drtools.analysis.files.ranking.dir}")
    private String RANKING_DIR;

    private final JsonInputParser jsonParser;
    private final List<MethodCdi> methodCdis = new ArrayList<>();

    private boolean isLoaded = false;

    public MethodCdiMapper(JsonInputParser jsonParser) {
        this.jsonParser = jsonParser;
    }

    @Override
    public List<MethodCdi> getMethodCdis() throws InputLoadingException {
        ensureLoaded();
        return methodCdis;
    }

    private void ensureLoaded() throws InputLoadingException {
        if (!isLoaded) {
            loadInputData();
            isLoaded = true;
        }
    }

    private void loadInputData() throws InputLoadingException {
        try {
            List<MethodCdiJsonDto> methodCdiJsonDtos = jsonParser.parseInput(RANKING_DIR + File.separator + METHOD_CDI_FILE);
            Map<String, MethodCdi> methodCdiMap = new HashMap<>();
            methodCdiJsonDtos.forEach(typeCdiJsonDto -> {
                var methodCdi = new MethodCdi();
                methodCdi.setCdi(typeCdiJsonDto.cdi());
                methodCdi.setMethodName(typeCdiJsonDto.method());
                methodCdi.setNormalizedIntervention(typeCdiJsonDto.intervention());
                methodCdi.setNormalizedSeverity(typeCdiJsonDto.severity());
                methodCdi.setNormalizedRepresentativity(typeCdiJsonDto.representativity());
                methodCdi.setNormalizedQuality(typeCdiJsonDto.quality());
                methodCdiMap.put(methodCdi.getMethodName(), methodCdi);
            });
            List<MethodSmellJsonDto> typeSmellsJsonDtos = jsonParser.parseInput(SMELLS_DIR + File.separator + METHOD_SMELLS_FILE);
            linkSmells(methodCdiMap, typeSmellsJsonDtos);
            this.methodCdis.addAll(methodCdiMap.values());
        } catch(IOException e) {
            String errMsg = String.format("Failed to load JSON input. Cause: %s", e.getMessage());
            throw new InputLoadingException(errMsg);
        }
    }

    private void linkSmells(Map<String, MethodCdi> methodCdiMap, List<MethodSmellJsonDto> methodSmellJsonDtos) {
        methodSmellJsonDtos.forEach(methodSmellJsonDto -> {
            String method = methodSmellJsonDto.method();
            var smells = toSmells(methodSmellJsonDto.smells());
            methodCdiMap.get(method).setSmells(smells);
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
