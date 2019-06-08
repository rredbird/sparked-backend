package coda.model.evaluation;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Evaluation {
    private UUID id;
    // private parameters // parameters will not be supported at this time.
    private EvaluationResults results;
    
    public Evaluation() {
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public EvaluationResults getResults() { return results; }
    public void setResults(EvaluationResults results) { this.results = results; }
}