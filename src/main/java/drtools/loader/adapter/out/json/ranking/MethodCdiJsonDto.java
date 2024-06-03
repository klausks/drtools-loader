package drtools.loader.adapter.out.json.ranking;

public record MethodCdiJsonDto(String method, int line, double severity, double representativity, double quality, double intervention, double cdi) {
}
