package com.example.newsapp;

class News_Data {

    String title;
    String author;
    String url;
    String imageURL;

   public News_Data(String title, String author, String url,String imageUrl) {
       this.title = title;
       this.author= author;
       this.url = url;
       this.imageURL = imageUrl;
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
