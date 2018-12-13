package coda.shared.dto;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Greeting extends ResourceSupport {
    private final long id;
    private final String content;

    
    @JsonCreator
    public Greeting(@JsonProperty("content") String content, long id) {
        this.content = content;
        this.id = id;
    }

    public String getContent() {
        return content;
    }
}