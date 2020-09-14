package ru.nsu.criterias;

public class LastNameCriteria extends Criteria {
    public LastNameCriteria(String lastName) {
        this.lastName = lastName;
    }

    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
