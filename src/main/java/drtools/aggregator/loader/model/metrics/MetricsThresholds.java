package drtools.aggregator.loader.model.metrics;

import drtools.aggregator.loader.model.Execution;
import jakarta.persistence.*;

@Entity
@Table(name = MetricsThresholds.TABLE_NAME)
public class MetricsThresholds {
    public static final String TABLE_NAME = "metrics_threshold_config";

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    @JoinColumn(name = "last_execution_id", referencedColumnName = "id")
    private Execution lastExecution;
    private int minNoc;
    private int maxNoc;
    private int minNac;
    private int maxNac;
    private int minSloc;
    private int maxSloc;
    private int minNom;
    private int maxNom;
    private int minNpm;
    private int maxNpm;
    private double minWmc;
    private double maxWmc;
    private int minDep;
    private int maxDep;
    private int minIDep;
    private int maxIDep;
    private int minFanIn;
    private int maxFanIn;
    private int minFanOut;
    private int maxFanOut;
    private int minNoa;
    private int maxNoa;
    private double minLcom3;
    private double maxLcom3;
    private int minDit;
    private int maxDit;
    private int minChild;
    private int maxChild;
    private int minNpa;
    private int maxNpa;
    private int minCdep;
    private int maxCdep;
    private int minMloc;
    private int maxMloc;
    private double minCyclo;
    private double maxCyclo;
    private int minCalls;
    private int maxCalls;
    private int minNbd;
    private int maxNbd;
    private int minParam;
    private int maxParam;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Execution getLastExecution() {
        return lastExecution;
    }

    public void setLastExecution(Execution lastExecution) {
        this.lastExecution = lastExecution;
    }

    public int getMinNoc() {
        return minNoc;
    }

    public void setMinNoc(int minNoc) {
        this.minNoc = minNoc;
    }

    public int getMaxNoc() {
        return maxNoc;
    }

    public void setMaxNoc(int maxNoc) {
        this.maxNoc = maxNoc;
    }

    public int getMinNac() {
        return minNac;
    }

    public void setMinNac(int minNac) {
        this.minNac = minNac;
    }

    public int getMaxNac() {
        return maxNac;
    }

    public void setMaxNac(int maxNac) {
        this.maxNac = maxNac;
    }

    public int getMinSloc() {
        return minSloc;
    }

    public void setMinSloc(int minSloc) {
        this.minSloc = minSloc;
    }

    public int getMaxSloc() {
        return maxSloc;
    }

    public void setMaxSloc(int maxSloc) {
        this.maxSloc = maxSloc;
    }

    public int getMinNom() {
        return minNom;
    }

    public void setMinNom(int minNom) {
        this.minNom = minNom;
    }

    public int getMaxNom() {
        return maxNom;
    }

    public void setMaxNom(int maxNom) {
        this.maxNom = maxNom;
    }

    public int getMinNpm() {
        return minNpm;
    }

    public void setMinNpm(int minNpm) {
        this.minNpm = minNpm;
    }

    public int getMaxNpm() {
        return maxNpm;
    }

    public void setMaxNpm(int maxNpm) {
        this.maxNpm = maxNpm;
    }

    public double getMinWmc() {
        return minWmc;
    }

    public void setMinWmc(double minWmc) {
        this.minWmc = minWmc;
    }

    public double getMaxWmc() {
        return maxWmc;
    }

    public void setMaxWmc(double maxWmc) {
        this.maxWmc = maxWmc;
    }

    public int getMinDep() {
        return minDep;
    }

    public void setMinDep(int minDep) {
        this.minDep = minDep;
    }

    public int getMaxDep() {
        return maxDep;
    }

    public void setMaxDep(int maxDep) {
        this.maxDep = maxDep;
    }

    public int getMinIDep() {
        return minIDep;
    }

    public void setMinIDep(int minIDep) {
        this.minIDep = minIDep;
    }

    public int getMaxIDep() {
        return maxIDep;
    }

    public void setMaxIDep(int maxIDep) {
        this.maxIDep = maxIDep;
    }

    public int getMinFanIn() {
        return minFanIn;
    }

    public void setMinFanIn(int minFanIn) {
        this.minFanIn = minFanIn;
    }

    public int getMaxFanIn() {
        return maxFanIn;
    }

    public void setMaxFanIn(int maxFanIn) {
        this.maxFanIn = maxFanIn;
    }

    public int getMinFanOut() {
        return minFanOut;
    }

    public void setMinFanOut(int minFanOut) {
        this.minFanOut = minFanOut;
    }

    public int getMaxFanOut() {
        return maxFanOut;
    }

    public void setMaxFanOut(int maxFanOut) {
        this.maxFanOut = maxFanOut;
    }

    public int getMinNoa() {
        return minNoa;
    }

    public void setMinNoa(int minNoa) {
        this.minNoa = minNoa;
    }

    public int getMaxNoa() {
        return maxNoa;
    }

    public void setMaxNoa(int maxNoa) {
        this.maxNoa = maxNoa;
    }

    public double getMinLcom3() {
        return minLcom3;
    }

    public void setMinLcom3(double minLcom3) {
        this.minLcom3 = minLcom3;
    }

    public double getMaxLcom3() {
        return maxLcom3;
    }

    public void setMaxLcom3(double maxLcom3) {
        this.maxLcom3 = maxLcom3;
    }

    public int getMinDit() {
        return minDit;
    }

    public void setMinDit(int minDit) {
        this.minDit = minDit;
    }

    public int getMaxDit() {
        return maxDit;
    }

    public void setMaxDit(int maxDit) {
        this.maxDit = maxDit;
    }

    public int getMinChild() {
        return minChild;
    }

    public void setMinChild(int minChild) {
        this.minChild = minChild;
    }

    public int getMaxChild() {
        return maxChild;
    }

    public void setMaxChild(int maxChild) {
        this.maxChild = maxChild;
    }

    public int getMinNpa() {
        return minNpa;
    }

    public void setMinNpa(int minNpa) {
        this.minNpa = minNpa;
    }

    public int getMaxNpa() {
        return maxNpa;
    }

    public void setMaxNpa(int maxNpa) {
        this.maxNpa = maxNpa;
    }

    public int getMinCdep() {
        return minCdep;
    }

    public void setMinCdep(int minCdep) {
        this.minCdep = minCdep;
    }

    public int getMaxCdep() {
        return maxCdep;
    }

    public void setMaxCdep(int maxCdep) {
        this.maxCdep = maxCdep;
    }

    public int getMinMloc() {
        return minMloc;
    }

    public void setMinMloc(int minMloc) {
        this.minMloc = minMloc;
    }

    public int getMaxMloc() {
        return maxMloc;
    }

    public void setMaxMloc(int maxMloc) {
        this.maxMloc = maxMloc;
    }

    public double getMinCyclo() {
        return minCyclo;
    }

    public void setMinCyclo(double minCyclo) {
        this.minCyclo = minCyclo;
    }

    public double getMaxCyclo() {
        return maxCyclo;
    }

    public void setMaxCyclo(double maxCyclo) {
        this.maxCyclo = maxCyclo;
    }

    public int getMinCalls() {
        return minCalls;
    }

    public void setMinCalls(int minCalls) {
        this.minCalls = minCalls;
    }

    public int getMaxCalls() {
        return maxCalls;
    }

    public void setMaxCalls(int maxCalls) {
        this.maxCalls = maxCalls;
    }

    public int getMinNbd() {
        return minNbd;
    }

    public void setMinNbd(int minNbd) {
        this.minNbd = minNbd;
    }

    public int getMaxNbd() {
        return maxNbd;
    }

    public void setMaxNbd(int maxNbd) {
        this.maxNbd = maxNbd;
    }

    public int getMinParam() {
        return minParam;
    }

    public void setMinParam(int minParam) {
        this.minParam = minParam;
    }

    public int getMaxParam() {
        return maxParam;
    }

    public void setMaxParam(int maxParam) {
        this.maxParam = maxParam;
    }
}
