package coda.shared;

import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import coda.model.order.EvaluationStatusList;

public class EvaluationStatusListDeserializer extends JsonDeserializer<EvaluationStatusList> {

    @Override
    public EvaluationStatusList deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
        
        EvaluationStatusList retVal = new EvaluationStatusList();

        JsonNode node = parser.getCodec().readTree(parser);
        JsonNode listNode = node.get("evaluation id");

        Iterator<JsonNode> outerIterator = listNode.elements(); 
        while(outerIterator.hasNext()) {
            JsonNode statusNode = outerIterator.next();
            
            Iterator<Entry<String, JsonNode>> innerIterator = statusNode.fields(); 
            while(innerIterator.hasNext()) {
                Entry<String, JsonNode> s = innerIterator.next();
                String key = s.getKey();
                String value = s.getValue().textValue();
                retVal.insertStatus(UUID.fromString(key), value);
            }
        }
        return retVal;
    }
}