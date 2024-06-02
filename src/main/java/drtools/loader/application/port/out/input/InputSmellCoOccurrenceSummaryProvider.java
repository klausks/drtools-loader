package drtools.loader.application.port.out.input;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.domain.summary.SmellCoOccurrenceSummary;

import java.util.List;

public interface InputSmellCoOccurrenceSummaryProvider {
    List<SmellCoOccurrenceSummary> getSmellCoOccurrenceSummaries() throws InputLoadingException;
}
