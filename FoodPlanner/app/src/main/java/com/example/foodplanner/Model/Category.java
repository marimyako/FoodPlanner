package com.example.foodplanner.Model;

public class Category {
    private String idCategory;
    private String strCategory;
    private String strCategoryThumb;
    private String getStrCategoryDescription;

    public Category(String idCategory, String strCategory, String strCategoryThumb, String getStrCategoryDescription) {
        this.idCategory = idCategory;
        this.strCategory = strCategory;
        this.strCategoryThumb = strCategoryThumb;
        this.getStrCategoryDescription = getStrCategoryDescription;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrCategoryThumb() {
        return strCategoryThumb;
    }

    public void setStrCategoryThumb(String strCategoryThumb) {
        this.strCategoryThumb = strCategoryThumb;
    }

    public String getGetStrCategoryDescription() {
        return getStrCategoryDescription;
    }

    public void setGetStrCategoryDescription(String getStrCategoryDescription) {
        this.getStrCategoryDescription = getStrCategoryDescription;
    }
}
