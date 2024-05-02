package drtools.loader.in.smell.criteria;

import drtools.loader.model.criteria.Criteria;
import drtools.loader.model.criteria.QualityImpact;

import java.util.List;

public record CriteriaConfig(Criteria criteria, List<Attributes> data) {
    public record Attributes(String description, double weight, QualityImpact impact){}
}
