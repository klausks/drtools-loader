package drtools.loader.adapter.out.json.smell;

import java.util.List;

public record MethodSmellJsonDto(String method, int line, List<SmellJsonDto> smells) {
}
