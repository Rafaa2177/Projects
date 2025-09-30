package jobs4u.jobopening.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

public class NumberOfVacancies implements ValueObject {
    private int value;

    public NumberOfVacancies(int value) {
        Preconditions.ensure(value >= 0, "Number of vacancies must be greater than 0");
        this.value = value;
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
