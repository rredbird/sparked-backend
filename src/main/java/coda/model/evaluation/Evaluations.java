package coda.model.evaluation;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Evaluations {
    private List<EvaluationWrapper> evaluation_results;
    private UUID evaluation_id;

    public Evaluations() {
        evaluation_results = new LinkedList<EvaluationWrapper>();
        evaluation_id = UUID.randomUUID();
    }

    public List<EvaluationWrapper> getEvaluation_results() {
        return evaluation_results;
    }
    public void setEvaluation_results(List<EvaluationWrapper> evaluation_results) {
        this.evaluation_results = evaluation_results;
    }

    public UUID getEvaluation_id() {
        return evaluation_id;
    }
    public void setEvaluation_id(UUID evaluation_id) {
        this.evaluation_id = evaluation_id;
    }
}