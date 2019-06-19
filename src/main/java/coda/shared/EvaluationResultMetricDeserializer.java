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

import coda.model.evaluation.EvaluationResultMetric;
import coda.shared.logging.ILogging;

public class EvaluationResultMetricDeserializer extends JsonDeserializer<EvaluationResultMetric> {
    @Autowired
    private ILogging log;

    @Override
    public EvaluationResultMetric deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
        EvaluationResultMetric retVal = new EvaluationResultMetric();

        JsonNode node = parser.getCodec().readTree(parser);

        JsonNode idNode = node.get("id");
        retVal.setId(idNode.asText());

        parseRawFromNode(node.get("raw"), retVal.getRaw());
        parseFromNode(node.get("mean"), retVal.getMean());
        parseFromNode(node.get("min"), retVal.getMin());
        parseFromNode(node.get("max"), retVal.getMax());
        parseFromNode(node.get("var"), retVal.getVar());

        return retVal;
    }

    private void parseRawFromNode(JsonNode node, List<List<Double>> target) {
        Iterator<JsonNode> outerIterator = node.elements(); 
        while(outerIterator.hasNext()) {
            JsonNode rawEntry = outerIterator.next();
            Iterator<JsonNode> innerIterator = rawEntry.elements(); 
            List<Double> rawList = new LinkedList<Double>();
            while(innerIterator.hasNext()) {
                JsonNode rawValue = innerIterator.next();
                
                Iterator<JsonNode> i = rawValue.elements();
                Iterator<String> fieldNames = rawValue.fieldNames();
                while(fieldNames.hasNext()) {
                    String fieldName = fieldNames.next();

                    Double value = rawValue.get(fieldName).asDouble();

                    rawList.add(value);
                }
            }
            
            target.add(rawList);
        }
    }

    private void parseFromNode(JsonNode node, List<Double> target) {
        Iterator<JsonNode> outerIterator = node.elements(); 
        while(outerIterator.hasNext()) {
            JsonNode rawEntry = outerIterator.next();
            
            Iterator<String> fieldNames = rawEntry.fieldNames();
            while(fieldNames.hasNext()) {
                String fieldName = fieldNames.next();

                Double value = rawEntry.get(fieldName).asDouble();

                target.add(value);
            }
        }
    }
}