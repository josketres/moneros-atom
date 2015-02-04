package com.josketres.moneros.atom;

import java.util.Date;

public class Cartoon {
    public final String author;
    public final Date publishedDate;
    public final String image;
    public final String link;
    public final String title;

    public Cartoon(String author, Date publishedDate, String link, String image, String title) {
        this.author = author;
        this.publishedDate = publishedDate;
        this.link = link;
        this.image = image;
        this.title = title;
    }
}
