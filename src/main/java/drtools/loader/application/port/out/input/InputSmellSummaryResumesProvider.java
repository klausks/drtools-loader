package drtools.loader.application.port.out.input;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.domain.summary.SmellsSummaryResume;

import java.util.List;

public interface InputSmellSummaryResumesProvider {
    List<SmellsSummaryResume> getInputSmellSummaryResumes() throws InputLoadingException;
}
