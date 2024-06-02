package drtools.loader.application.port.out.input;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.domain.summary.ImportanceSummary;

import java.util.List;

public interface InputImportanceSummaryProvider {
    List<ImportanceSummary> getImportanceSummaries() throws InputLoadingException;
}
