package coda.model.order;

import java.util.List;
import java.util.LinkedList;

public class ValidationMethods {
    private List<ValidationMethod> validators;

    public ValidationMethods() {
        validators = new LinkedList<ValidationMethod>();
    }

    public List<ValidationMethod> getValidators() { return validators; }
}

