package drtools.aggregator.loader.in.metrics;

public record MetricThreshold(String acronym, String name, String description, double minValue, double maxValue){}
