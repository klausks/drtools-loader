package drtools.loader.application.port.out.input;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.domain.summary.InterventionSummary;

import java.util.List;

public interface InputInterventionSummariesProvider {
    List<InterventionSummary> getInterventionSummaries() throws InputLoadingException;
}
