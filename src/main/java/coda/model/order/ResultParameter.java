package coda.model.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import coda.shared.dto.ClassifierParameterDto;
import coda.shared.dto.ResultParameterDto;
import coda.shared.interfaces.IDto;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultParameter implements IDto<ResultParameter, ResultParameterDto> {
    private String name;
    private String doc;
    private String value;
    private String paramType;
    private String javaType;

    public ResultParameter() {
    }

    public void setName(String name) { this.name = name; };
    public void setValue(String value) { this.value = value; };

    public String getName() {
        return name;
    }
    public String getValue() {
        return value;
    }

    @Override
    public ResultParameter fromDto(ResultParameterDto dto) {
        return this;
    }

    @Override
    public ResultParameterDto toDto() {
        ResultParameterDto dto = new ResultParameterDto();

        dto.setName(this.name);
        dto.setValue(this.value);

        return dto;
    }
}


