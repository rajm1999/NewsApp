package com.example.newsapp;

public class CatagoryRVModal {
    private String catagory;
    private String catagoryImageUrl;

    public CatagoryRVModal(String catagory, String catagoryImageUrl) {
        this.catagory = catagory;
        this.catagoryImageUrl = catagoryImageUrl;
    }


    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getCatagoryImageUrl() {
        return catagoryImageUrl;
    }

    public void setCatagoryImageUrl(String catagoryImageUrl) {
        this.catagoryImageUrl = catagoryImageUrl;
    }


}
