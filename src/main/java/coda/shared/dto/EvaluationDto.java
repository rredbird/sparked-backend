package coda.shared.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EvaluationDto {
    private String winnerId;
    private float winnerValue;

    private List<EvaluationParameterDto> parameters;

    public EvaluationDto() {
    }
    public List<EvaluationParameterDto> getParameters() {
        return parameters;
    }

    public void setParameters(List<EvaluationParameterDto> parameters) {
        this.parameters = parameters;
    }

    public float getWinnerValue() {
        return winnerValue;
    }

    public void setWinnerValue(float winnerValue) {
        this.winnerValue = winnerValue;
    }

    public String getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(String winnerId) {
        this.winnerId = winnerId;
    }
}


