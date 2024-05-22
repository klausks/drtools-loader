package drtools.loader.adapter.out.json.metrics;

public record MetricThreshold(String acronym, String name, String description, double minValue, double maxValue){}
