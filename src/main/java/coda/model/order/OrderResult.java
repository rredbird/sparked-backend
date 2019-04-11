package coda.model.order;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.springframework.beans.factory.annotation.Autowired;

import coda.model.order.ValidationMethod;
import coda.model.order.Classifier;
import coda.shared.logging.ILogging;
import coda.shared.ResultDeserializer;
import coda.shared.dto.ConfigurationDto;
import coda.shared.dto.OrderResultDto;
import coda.shared.interfaces.IDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = ResultDeserializer.class)
public class OrderResult implements IDto<OrderResult, OrderResultDto> {
    @Autowired
    private ILogging log;
    
    // private List<Task> tasks;
    private UUID id;
    private Classifier classifier;
    private ValidationMethod validationMethod;
    private ValidationMetric validationMetric;
    private Dataset dataset;
    private Configuration bestConfiguration;
    private List<Configuration> allConfigurations;
    
    public OrderResult() {
        // tasks = new LinkedList<Task>();
        id = UUID.randomUUID();
        classifier = new Classifier();
        validationMethod = new ValidationMethod();
        validationMetric = new ValidationMetric();
        dataset = new Dataset();    
        bestConfiguration = new Configuration();
        allConfigurations = new LinkedList<Configuration>();   
    }

    public OrderResult(UUID id) {
        this();
        this.id = id;
    }

    public OrderResult(OrderResultDto orderResultDto) {
        this();
        id = orderResultDto.getId();
        fromDto(orderResultDto);
    }

    public OrderResult(String orderResultAsJsonString) {
        this();
        OrderResult order = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            order = mapper.readValue(orderResultAsJsonString, OrderResult.class);
        } catch (JsonGenerationException e) {
            log.exception(e);
        } catch (JsonMappingException e) {
            log.exception(e);       
        } catch (IOException e) {
            log.exception(e);
        }

        this.setId(order.getId());
    }

    public Classifier getClassifier() { return this.classifier; }
    public void setClassifier(Classifier classifier) {
        this.classifier = classifier;
    }

    public ValidationMethod getValidationMethod() { return this.validationMethod; }
    public void setValidationMethod(ValidationMethod validationMethod) {
        this.validationMethod = validationMethod;
    }

    public ValidationMetric getValidationMetric() { return validationMetric; }
    public void setValidationMetric(ValidationMetric metric) { this.validationMetric = metric; }
    
    public Dataset getDataset() { return dataset; }
    public void setDataset(Dataset dataset) { this.dataset = dataset; }

    public Configuration getBestConfiguration() { return bestConfiguration; }
    public void setBestConfiguration(Configuration bestConfiguration) { this.bestConfiguration = bestConfiguration; }

    public List<Configuration> getAllConfigurations() { return allConfigurations; }
    public void setAllConfigurations(List<Configuration> allConfigurations) { this.allConfigurations = allConfigurations; }

    @JsonProperty("_id")
    public UUID getId() { return id; }
    @JsonProperty("_id")
    private void setId(UUID id) { this.id = id; }
    
    @Override
    @JsonIgnore
    public OrderResult fromDto(OrderResultDto dto) {
        return this;
    }

    @Override
    @JsonIgnore
    public OrderResultDto toDto() {
        OrderResultDto dto = new OrderResultDto();
        
        dto.setId(this.id);
        dto.setClassifier(this.classifier.toDto());
        dto.setValidationMethod(this.validationMethod.toDto());
        dto.setValidationMetric(this.validationMetric.toDto());
        dto.setDataset(this.dataset.toDto());
        dto.setBestConfiguration(bestConfiguration.toDto());

        List<ConfigurationDto> configurations = new LinkedList<ConfigurationDto>();
        for(Configuration conf : allConfigurations) {
            configurations.add(conf.toDto());
        }
        dto.setAllConfigurations(configurations);

        return dto;
    }

    @JsonIgnore
    public String getJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        String orderJson = "";
        try {
            orderJson = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException jsonProcessingException) {
            log.exception(jsonProcessingException);
        }

        return orderJson;
    }
}