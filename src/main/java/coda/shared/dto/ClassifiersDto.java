package coda.shared.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.LinkedList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassifiersDto {
    private List<ClassifierDto> classifiers;

    public ClassifiersDto() {
        classifiers = new LinkedList<ClassifierDto>();
    }

    public void setClassifiers(List<ClassifierDto> classifiers) { this.classifiers = classifiers; };
    public List<ClassifierDto> getClassifiers() { return classifiers; }
}


