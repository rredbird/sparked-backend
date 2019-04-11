package coda.model.order;

import coda.shared.dto.ValidationParameterDto;
import coda.shared.interfaces.IDto;

public class ValidationParameter implements IDto<ValidationParameter, ValidationParameterDto> {
    private String name;
    private String doc;
    private String value;

    public ValidationParameter() {
        name = "";
        doc = "";
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public String getDoc() { return doc; }
    public void setDoc(String doc) { this.doc = doc; }

    @Override
    public ValidationParameter fromDto(ValidationParameterDto dto) {
        return this;
    }

    @Override
    public ValidationParameterDto toDto() {
        ValidationParameterDto dto = new ValidationParameterDto();

        dto.setName(this.name);
        dto.setValue(this.value);

        return dto;
    }
}

