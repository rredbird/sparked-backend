package coda.shared.dto;

import java.util.List;
import java.util.LinkedList;

public class EvaluationMetrics {
    List<EvaluationMetric> metrics;

    public EvaluationMetrics() {
        metrics = new LinkedList<EvaluationMetric>();
    }

    public List<EvaluationMetric> getMetrics() { return metrics; }
}

