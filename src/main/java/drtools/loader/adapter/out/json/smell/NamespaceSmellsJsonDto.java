package drtools.loader.adapter.out.json.smell;

import java.util.List;

public record NamespaceSmellsJsonDto(String namespace, List<SmellJsonDto> smells) {
}
