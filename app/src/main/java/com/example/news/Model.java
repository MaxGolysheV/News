package com.example.news;

public class Model {

    private String title;
    private String text;

    public Model(String title, String text)
    {
        this.title = title;
        this.text = text;
    }

    public String getTitle()
    {
        return title;
    }
    public String getText()
    {
        return text;
    }
}
