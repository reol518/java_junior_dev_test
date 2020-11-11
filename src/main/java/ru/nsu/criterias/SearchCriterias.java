package ru.nsu.criterias;

import java.util.ArrayList;

public class SearchCriterias implements InputCriterias {
    private ArrayList<Criteria> criterias;

    public ArrayList<Criteria> getCriterias() {
        return criterias;
    }

    public void setCriterias(ArrayList<Criteria> criterias) {
        this.criterias = criterias;
    }
}
