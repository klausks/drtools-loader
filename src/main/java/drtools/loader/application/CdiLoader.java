package drtools.loader.application;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.application.port.out.input.InputMethodCdiProvider;
import drtools.loader.application.port.out.input.InputNamespaceCdiProvider;
import drtools.loader.application.port.out.input.InputTypeCdiProvider;
import drtools.loader.application.port.out.repository.smell.MethodCdiRepository;
import drtools.loader.application.port.out.repository.smell.NamespaceCdiRepository;
import drtools.loader.application.port.out.repository.smell.SmellRepository;
import drtools.loader.application.port.out.repository.smell.TypeCdiRepository;
import drtools.loader.domain.Execution;
import drtools.loader.domain.cdi.MethodCdi;
import drtools.loader.domain.cdi.NamespaceCdi;
import drtools.loader.domain.cdi.TypeCdi;
import drtools.loader.domain.smell.Smell;
import drtools.loader.utils.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CdiLoader {

    private final InputTypeCdiProvider inputTypeCdiProvider;
    private final InputMethodCdiProvider inputMethodCdiProvider;
    private final InputNamespaceCdiProvider inputNamespaceCdiProvider;

    private final TypeCdiRepository typeCdiRepository;
    private final MethodCdiRepository methodCdiRepository;
    private final NamespaceCdiRepository namespaceCdiRepository;
    private final SmellRepository smellRepository;


    public CdiLoader(
            InputTypeCdiProvider inputTypeCdiProvider,
            InputMethodCdiProvider inputMethodCdiProvider,
            InputNamespaceCdiProvider inputNamespaceCdiProvider,
            TypeCdiRepository typeCdiRepository,
            MethodCdiRepository methodCdiRepository,
            NamespaceCdiRepository namespaceCdiRepository,
            SmellRepository smellRepository
    ) {
        this.inputTypeCdiProvider = inputTypeCdiProvider;
        this.inputMethodCdiProvider = inputMethodCdiProvider;
        this.inputNamespaceCdiProvider = inputNamespaceCdiProvider;
        this.typeCdiRepository = typeCdiRepository;
        this.methodCdiRepository = methodCdiRepository;
        this.namespaceCdiRepository = namespaceCdiRepository;
        this.smellRepository = smellRepository;
    }

    public void load(Execution execution) throws InputLoadingException {
        var inputNamespaceCdis = inputNamespaceCdiProvider.getNamespaceCdis();
        var inputTypeCdis = inputTypeCdiProvider.getTypeCdis();
        var inputMethodCdis = inputMethodCdiProvider.getMethodCdis();
        var smellRecords = CollectionUtils.toMap(smellRepository.findAll(), Smell::getName);
        linkNamespaceCdiEntities(inputNamespaceCdis, smellRecords, execution);
        linkTypeCdiEntities(inputTypeCdis, smellRecords, execution);
        linkMethodCdiEntities(inputMethodCdis, smellRecords, execution);

        namespaceCdiRepository.saveAll(inputNamespaceCdis);
        typeCdiRepository.saveAll(inputTypeCdis);
        methodCdiRepository.saveAll(inputMethodCdis);

    }

    private void linkMethodCdiEntities(List<MethodCdi> inputMethodCdis, Map<String, Smell> smellRecords, Execution execution) {
        inputMethodCdis.forEach(methodCdi -> {
            var methodSmells = CollectionUtils.getFromMap(smellRecords, methodCdi.getSmells().stream().map(Smell::getName).toList());
            methodCdi.setSmells(methodSmells);
            methodCdi.setExecution(execution);
        });
    }

    private void linkTypeCdiEntities(List<TypeCdi> inputTypeCdis, Map<String, Smell> smellRecords, Execution execution) {
        inputTypeCdis.forEach(typeCdi -> {
            var typeSmells = CollectionUtils.getFromMap(smellRecords, typeCdi.getSmells().stream().map(Smell::getName).toList());
            typeCdi.setSmells(typeSmells);
            typeCdi.setExecution(execution);
        });
    }

    private void linkNamespaceCdiEntities(List<NamespaceCdi> inputNamespaceCdis, Map<String, Smell> smellRecords, Execution execution) {
        inputNamespaceCdis.forEach(namespaceCdi -> {
            var namespaceSmells = CollectionUtils.getFromMap(smellRecords, namespaceCdi.getSmells().stream().map(Smell::getName).toList());
            namespaceCdi.setSmells(namespaceSmells);
            namespaceCdi.setExecution(execution);
        });
    }
}
