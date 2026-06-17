package com.webcraper.model;

import java.util.List;

public class Quote {
    private String text;
    private String author;
    private List<String> tags;

    public Quote() {
    }

    public Quote(String text, String author, List<String> tags) {
        this.text = text;
        this.author = author;
        this.tags = tags;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
