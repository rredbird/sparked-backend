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
        return retVal;
    }
}