package com.example.primevideoclone.Model;

import java.util.ArrayList;

public class AllCategory {

    String categoryTitle;
    int categoryId;
    private ArrayList<CategoryItem> categoryItemArrayList;

    public AllCategory(int categoryId, String categoryTitle, ArrayList<CategoryItem> categoryItemArrayList) {
        this.categoryTitle = categoryTitle;
        this.categoryId = categoryId;
        this.categoryItemArrayList = categoryItemArrayList;
    }

    public ArrayList<CategoryItem> getCategoryItemArrayList() {
        return categoryItemArrayList;
    }

    public void setCategoryItemArrayList(ArrayList<CategoryItem> categoryItemArrayList) {
        this.categoryItemArrayList = categoryItemArrayList;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
