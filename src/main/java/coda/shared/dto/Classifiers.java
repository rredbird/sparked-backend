package coda.shared.dto;

import java.util.List;
import java.util.LinkedList;

public class Classifiers {
    private List<Classifier> classifiers;

    public Classifiers() {
        classifiers = new LinkedList<Classifier>();
    }

    public void setClassifiers(List<Classifier> classifiers) { this.classifiers = classifiers; };
    public List<Classifier> getClassifiers() { return classifiers; }
}


