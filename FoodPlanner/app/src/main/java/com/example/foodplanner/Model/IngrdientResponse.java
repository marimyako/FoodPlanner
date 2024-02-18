package com.example.foodplanner.Model;

import java.util.List;

public class IngrdientResponse {
    private List<IngrdientDTO> ingrdient;
    public IngrdientResponse(List<IngrdientDTO> ingrdient) {
        this.ingrdient = ingrdient;
    }
    public List<IngrdientDTO> getIngrdientModel() {
        return ingrdient;
    }

    public void setMeals(List<IngrdientDTO> ingrdient) {
        this.ingrdient = ingrdient;
    }

}
