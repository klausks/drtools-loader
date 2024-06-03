package drtools.loader.adapter.out.json.smell;

import java.util.List;

public record TypeSmellsJsonDto(String type, List<SmellJsonDto> smells) {
}
