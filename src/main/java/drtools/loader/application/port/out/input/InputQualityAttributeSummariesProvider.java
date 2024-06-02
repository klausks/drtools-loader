package drtools.loader.application.port.out.input;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.domain.summary.QualityAttributesSummary;

import java.util.List;

public interface InputQualityAttributeSummariesProvider {
    List<QualityAttributesSummary> getQualityAttributeSummaries() throws InputLoadingException;
}
