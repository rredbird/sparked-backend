package coda.model.order;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import coda.model.order.Classifier;

import java.util.LinkedList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Classifiers {
    private List<Classifier> classifiers;

    public Classifiers() {
        classifiers = new LinkedList<Classifier>();
    }

    public void setClassifiers(List<Classifier> classifiers) { this.classifiers = classifiers; };
    public List<Classifier> getClassifiers() { return classifiers; }
}


