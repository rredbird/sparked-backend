package coda.shared.dto;

import java.util.List;
import java.util.LinkedList;

public class ValidationMethods {
    private List<Validator> validators;

    public ValidationMethods() {
        validators = new LinkedList<Validator>();
    }

    public List<Validator> getValidators() { return validators; }
}

