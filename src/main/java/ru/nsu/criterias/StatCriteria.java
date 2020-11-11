package ru.nsu.criterias;

public class StatCriteria implements InputCriterias {
    public StatCriteria(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private String startDate;
    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
