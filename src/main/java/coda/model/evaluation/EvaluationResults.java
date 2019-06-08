package coda.model.evaluation;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EvaluationResults {
    private Configuration bestConfiguration;
    private List<Configuration> allConfigurations;
    
    public EvaluationResults() {
        allConfigurations = new LinkedList<Configuration>();
    }

    public Configuration getBestConfiguration() { return bestConfiguration; }
    public void setBestConfiguration(Configuration configuration) { bestConfiguration = configuration; }

    public List<Configuration> getAllConfigurations() { return allConfigurations; }
    public void setAllConfigurations(List<Configuration> configurations) { allConfigurations = configurations; }
}