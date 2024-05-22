package drtools.loader.adapter.out.json;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class JsonFileHandler {

    @Value("${drtools.analysis.files.metrics.dir}")
    private String METRICS_DIR;

    @Value("${drtools.analysis.files.ranking.dir}")
    private String RANKING_DIR;

    @Value("${drtools.analysis.files.smells.dir}")
    private String SMELLS_DIR;

    public File getMetricsAnalysisFile(String fileName) {
        return new File(METRICS_DIR + File.separator + fileName);
    }

    public File getSmellsAnalysisFile(String fileName) {
        return new File(SMELLS_DIR + File.separator + fileName);
    }

    public File getRankingAnalysisFile(String fileName) {
        return new File(RANKING_DIR + File.separator + fileName);
    }
}
