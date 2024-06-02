package drtools.loader.application.port.out.input;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.domain.summary.SmellSummary;

import java.util.List;

public interface InputSmellsSummaryProvider {
    List<SmellSummary> getSmellsSummaries() throws InputLoadingException;
}
