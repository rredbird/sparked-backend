package coda.shared;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.beans.factory.annotation.Autowired;

import coda.model.order.Classifier;
import coda.model.order.ClassifierParameter;
import coda.model.order.Configuration;
import coda.model.order.Dataset;
import coda.model.order.Metric;
import coda.model.order.Order;
import coda.model.order.OrderResult;
import coda.model.order.ResultParameter;
import coda.model.order.ValidationMethod;
import coda.model.order.ValidationMethods;
import coda.model.order.ValidationMetric;
import coda.model.order.ValidationParameter;
import coda.shared.logging.ILogging;

public class ResultDeserializer extends JsonDeserializer<OrderResult> {
    @Autowired
    private ILogging log;

    @Override
    public OrderResult deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
        OrderResult retVal = new OrderResult();

        JsonNode node = parser.getCodec().readTree(parser);
        JsonNode evaluationResultsNode = node.get("evaluation_results");
        String evaluationId = node.get("evaluation_id").asText();

        Iterator<JsonNode> outerIterator = evaluationResultsNode.elements(); 
        while(outerIterator.hasNext()) {
            JsonNode evaluationNode = outerIterator.next().get("evaluation");
            JsonNode idNode = evaluationNode.get("id");
            String id = idNode.asText();
            JsonNode paramsNode = evaluationNode.get("parameters");
            

            JsonNode classifierNode = paramsNode.get("classifier");
            Classifier classifier = retVal.getClassifier();
            classifier.setId(classifierNode.get("id").asText());

            JsonNode classifierParam = classifierNode.get("param");
            List<ClassifierParameter> classifierParams = new LinkedList<ClassifierParameter>();
            Iterator<Entry<String, JsonNode>> paramIterator = classifierParam.fields();
            while(paramIterator.hasNext()) {
                Entry<String, JsonNode> paramMap = paramIterator.next();
                ClassifierParameter param = new ClassifierParameter();
                param.setName(paramMap.getKey());
                param.setValue(paramMap.getValue().asText());
                classifierParams.add(param);
            }
            classifier.setParameters(classifierParams);
            

            JsonNode validationMethodNode = paramsNode.get("validationMethod");
            ValidationMethod validationMethod = retVal.getValidationMethod();
            validationMethod.setId(validationMethodNode.get("id").asText());

            JsonNode validationMethodParam = validationMethodNode.get("param");
            List<ValidationParameter> validationParams = new LinkedList<ValidationParameter>();
            paramIterator = classifierParam.fields();
            while(paramIterator.hasNext()) {
                Entry<String, JsonNode> paramMap = paramIterator.next();
                ValidationParameter param = new ValidationParameter();
                param.setName(paramMap.getKey());
                param.setValue(paramMap.getValue().asText());
                validationParams.add(param);
            }
            validationMethod.setParameters(validationParams);
            

            JsonNode validationMetricNode = paramsNode.get("validationMetric");
            ValidationMetric validationMetric = retVal.getValidationMetric();
            validationMetric.setId(validationMetricNode.get("id").asText());

            
            JsonNode datasetNode = paramsNode.get("dataset");
            Dataset dataset = retVal.getDataset();
            dataset.setId(datasetNode.get("id").asText());



            JsonNode resultsNode = evaluationNode.get("results");
            JsonNode bestConfigurationNode = resultsNode.get("bestConfiguration");

            paramsNode = bestConfigurationNode.get("params");
            paramIterator = paramsNode.fields();
            while(paramIterator.hasNext()) {
                Entry<String, JsonNode> paramMap = paramIterator.next();
                ResultParameter param = new ResultParameter();
                param.setName(paramMap.getKey());
                param.setValue(paramMap.getValue().asText());
                retVal.getBestConfiguration().getResultParameters().add(param);
            }
            classifier.setParameters(classifierParams);

            JsonNode metricsNode = bestConfigurationNode.get("metrics");
            Metric metric;

            Iterator<JsonNode> iterator = metricsNode.elements();
            while(iterator.hasNext()) {
                JsonNode metricNode = iterator.next();
                metric = new Metric();
                metric.setId(metricNode.get("id").asText());
                
                Iterator<JsonNode> innerIterator = metricNode.get("raw").elements();
                while(innerIterator.hasNext()) {
                    JsonNode value = innerIterator.next();

                    Iterator<JsonNode> rawInnerIterator = value.elements();
                    List<Double> rawList = new LinkedList<Double>();
                    while(rawInnerIterator.hasNext()) {
                        JsonNode innerValue = rawInnerIterator.next();
                        rawList.add(Double.parseDouble(innerValue.asText()));
                    }
                    metric.getRaw().add(rawList);
                }

                innerIterator = metricNode.get("mean").elements();
                while(innerIterator.hasNext()) {
                    JsonNode value = innerIterator.next();
                    metric.getMean().add(Double.parseDouble(value.asText()));
                }

                innerIterator = metricNode.get("var").elements();
                while(innerIterator.hasNext()) {
                    JsonNode value = innerIterator.next();
                    metric.getVar().add(Double.parseDouble(value.asText()));
                }

                innerIterator = metricNode.get("max").elements();
                while(innerIterator.hasNext()) {
                    JsonNode value = innerIterator.next();
                    metric.getMax().add(Double.parseDouble(value.asText()));
                }

                innerIterator = metricNode.get("min").elements();
                while(innerIterator.hasNext()) {
                    JsonNode value = innerIterator.next();
                    metric.getMin().add(Double.parseDouble(value.asText()));
                }
                retVal.getBestConfiguration().getMetrics().add(metric);
            }

            JsonNode allConfigurationNode = resultsNode.get("allConfigurations");

            outerIterator = allConfigurationNode.elements();

            while(outerIterator.hasNext()) {
                bestConfigurationNode = outerIterator.next();

                Configuration configuration = new Configuration();
            
                paramsNode = bestConfigurationNode.get("params");
                paramIterator = paramsNode.fields();
                while(paramIterator.hasNext()) {
                    Entry<String, JsonNode> paramMap = paramIterator.next();
                    ResultParameter param = new ResultParameter();
                    param.setName(paramMap.getKey());
                    param.setValue(paramMap.getValue().asText());
                    configuration.getResultParameters().add(param);
                }
                classifier.setParameters(classifierParams);

                metricsNode = bestConfigurationNode.get("metrics");
                
                iterator = metricsNode.elements();
                while(iterator.hasNext()) {
                    JsonNode metricNode = iterator.next();
                    metric = new Metric();
                    metric.setId(metricNode.get("id").asText());
                    
                    Iterator<JsonNode> innerIterator = metricNode.get("raw").elements();
                    while(innerIterator.hasNext()) {
                        JsonNode value = innerIterator.next();

                        Iterator<JsonNode> rawInnerIterator = value.elements();
                        List<Double> rawList = new LinkedList<Double>();
                        while(rawInnerIterator.hasNext()) {
                            JsonNode innerValue = rawInnerIterator.next();
                            rawList.add(Double.parseDouble(innerValue.asText()));
                        }
                        metric.getRaw().add(rawList);
                    }

                    innerIterator = metricNode.get("mean").elements();
                    while(innerIterator.hasNext()) {
                        JsonNode value = innerIterator.next();
                        metric.getMean().add(Double.parseDouble(value.asText()));
                    }

                    innerIterator = metricNode.get("var").elements();
                    while(innerIterator.hasNext()) {
                        JsonNode value = innerIterator.next();
                        metric.getVar().add(Double.parseDouble(value.asText()));
                    }

                    innerIterator = metricNode.get("max").elements();
                    while(innerIterator.hasNext()) {
                        JsonNode value = innerIterator.next();
                        metric.getMax().add(Double.parseDouble(value.asText()));
                    }

                    innerIterator = metricNode.get("min").elements();
                    while(innerIterator.hasNext()) {
                        JsonNode value = innerIterator.next();
                        metric.getMin().add(Double.parseDouble(value.asText()));
                    }
                    configuration.getMetrics().add(metric);
                }
                retVal.getAllConfigurations().add(configuration);
                if(retVal.getAllConfigurations().size() > 1) {
                    int i = 0;
                }
            }
        }
        return retVal;
    }
}