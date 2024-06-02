package drtools.loader.application.port.out.input;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.domain.summary.RankingSummary;

import java.util.List;

public interface InputRankingSummariesProvider {
    List<RankingSummary> getRankingSummaries() throws InputLoadingException;
}
