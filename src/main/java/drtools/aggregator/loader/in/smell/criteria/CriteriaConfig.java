package drtools.aggregator.loader.in.smell.criteria;

import java.util.List;

public record CriteriaConfig(String criteria, List<CriteriaAttributes> data) {
    public record CriteriaAttributes(String description, double weight, String impact){}
}
