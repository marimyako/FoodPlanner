package com.example.foodplanner.Model;

public class Ingrdient {

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Ingrdient(String name, String image) {
        this.name = name;
        this.image = image;
    }

    String image;

}


