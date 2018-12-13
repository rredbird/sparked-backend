package coda.shared.dto;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import java.util.LinkedList;


public class SystemConfigurationData extends ResourceSupport {
    private String key;
    private String value;

    public SystemConfigurationData() {
        key = "none";
        value = "";
    }

    public SystemConfigurationData(String key) {
        this.key = key;
        this.value = "";
    }

    public SystemConfigurationData(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() { return key; }
    public void setId(String key) { this.key = key; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}

