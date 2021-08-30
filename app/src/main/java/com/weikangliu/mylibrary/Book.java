package com.weikangliu.mylibrary;

public class Book {
    private int id;
    private String title;
    private String author;
    private int pages;
    private String imageUrl;
    private String shortDesc;
    private String longDesc;
    private boolean isExpanded;

    public Book(int id, String title, String author, int pages, String imageUrl, String shortDesc, String longDesc) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.imageUrl = imageUrl;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.isExpanded = false;
    }

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = 0;
        this.imageUrl = "";
        this.shortDesc = "";
        this.longDesc = "";
        this.isExpanded = false;
    }

    public Book(int id, String title, String author, int pages, String imageUrl) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.imageUrl = imageUrl;
        this.shortDesc = "";
        this.longDesc = "";
        this.isExpanded = false;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

}
