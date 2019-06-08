package coda.model.order;

import java.util.List;
import java.util.LinkedList;

public class Metrics {
    List<Metric> metrics;

    public Metrics() {
        metrics = new LinkedList<Metric>();
    }

    public List<Metric> getMetrics() { return metrics; }
}

