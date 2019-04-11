package coda.shared.dto;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderResultDto {
    private UUID id;
    private String name;
    private String status;
    private ClassifierDto classifier;
    private ValidationMethodDto validationMethod;
    private ValidationMetricDto validationMetric;
    private DatasetDto dataset;
    private ConfigurationDto bestConfiguration;
    private List<ConfigurationDto> allConfigurations;

    public OrderResultDto() {
        bestConfiguration = new ConfigurationDto();
        allConfigurations = new LinkedList<ConfigurationDto>();
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStatus() { return this.status; }
    public void setStatus(String status) { this.status = status; }

    public ClassifierDto getClassifier() { return classifier; }
    public void setClassifier(ClassifierDto classifier) { this.classifier = classifier; }

    public ValidationMethodDto getValidationMethod() { return validationMethod; }
	public void setValidationMethod(ValidationMethodDto dto) { this.validationMethod = dto;	}
    
    public ValidationMetricDto getValidationMetric() { return validationMetric; }
    public void setValidationMetric(ValidationMetricDto dto) { this.validationMetric = dto;	}
    
    public DatasetDto getDataset() { return dataset; }
    public void setDataset(DatasetDto dto) { this.dataset = dto; }
    
    public ConfigurationDto getBestConfiguration() { return bestConfiguration; }
    public void setBestConfiguration(ConfigurationDto bestConfiguration) { this.bestConfiguration = bestConfiguration; }

    public List<ConfigurationDto> getAllConfigurations() { return allConfigurations; }
    public void setAllConfigurations(List<ConfigurationDto> allConfigurations) { this.allConfigurations = allConfigurations; }
}
