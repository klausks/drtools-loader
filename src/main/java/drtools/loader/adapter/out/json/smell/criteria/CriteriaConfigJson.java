package drtools.loader.adapter.out.json.smell.criteria;

import drtools.loader.domain.criteria.Criteria;
import drtools.loader.domain.criteria.QualityImpact;

import java.util.List;

public record CriteriaConfigJson(Criteria criteria, List<Attributes> data) {
    public record Attributes(String description, double weight, QualityImpact impact){}
}
