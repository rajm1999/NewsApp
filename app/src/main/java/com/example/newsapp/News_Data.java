package com.example.newsapp;

class News_Data {

    private String title;
    private String author;
    private String url;
    private String imageURL;
    private String description;
    private String publishedAt;



   public News_Data(String title, String author, String url,String imageUrl,String description,String publishedAt) {
       this.title = title;
       this.author= author;
       this.url = url;
       this.imageURL = imageUrl;
       this.description = description;
       this.publishedAt = publishedAt;
   }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
