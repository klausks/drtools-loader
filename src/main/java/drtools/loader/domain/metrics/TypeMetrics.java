package drtools.loader.domain.metrics;

import drtools.loader.domain.Execution;
import jakarta.persistence.*;

@Entity
@Table(name = TypeMetrics.TABLE_NAME)
public class TypeMetrics {

    public static final String TABLE_NAME = "type_metrics";

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    @JoinColumn(name = "execution_id", referencedColumnName = "id")
    private Execution execution;

    @Column(nullable = false)
    private String type;

    private int sloc;

    private int nom;

    private int npm;

    private int wmc;

    private int dep;

    private int iDep;

    private int fanIn;

    private int fanOut;

    private int noa;

    private int lcom3;

    private int dit;

    private int child;

    private int npa;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Execution getExecution() {
        return execution;
    }

    public void setExecution(Execution execution) {
        this.execution = execution;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSloc() {
        return sloc;
    }

    public void setSloc(int sloc) {
        this.sloc = sloc;
    }

    public int getNom() {
        return nom;
    }

    public void setNom(int nom) {
        this.nom = nom;
    }

    public int getNpm() {
        return npm;
    }

    public void setNpm(int npm) {
        this.npm = npm;
    }

    public int getWmc() {
        return wmc;
    }

    public void setWmc(int wmc) {
        this.wmc = wmc;
    }

    public int getDep() {
        return dep;
    }

    public void setDep(int dep) {
        this.dep = dep;
    }

    public int getiDep() {
        return iDep;
    }

    public void setiDep(int iDep) {
        this.iDep = iDep;
    }

    public int getFanIn() {
        return fanIn;
    }

    public void setFanIn(int fanIn) {
        this.fanIn = fanIn;
    }

    public int getFanOut() {
        return fanOut;
    }

    public void setFanOut(int fanOut) {
        this.fanOut = fanOut;
    }

    public int getNoa() {
        return noa;
    }

    public void setNoa(int noa) {
        this.noa = noa;
    }

    public int getLcom3() {
        return lcom3;
    }

    public void setLcom3(int lcom3) {
        this.lcom3 = lcom3;
    }

    public int getDit() {
        return dit;
    }

    public void setDit(int dit) {
        this.dit = dit;
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }

    public int getNpa() {
        return npa;
    }

    public void setNpa(int npa) {
        this.npa = npa;
    }
}
