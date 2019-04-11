package coda.shared.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.LinkedList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EvaluationParameterDto {

    public EvaluationParameterDto() {
    }

    public String winnerId;
    public float winnerValue;

    List<EvaluationParameterDto> parameters;
}


