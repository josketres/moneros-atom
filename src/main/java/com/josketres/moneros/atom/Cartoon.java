package com.josketres.moneros.atom;

import java.util.Date;

public class Cartoon {
    public final String author;
    public final Date publishedDate;
    public final String image;

    public Cartoon(String author, Date publishedDate) {
        this.author = author;
        this.publishedDate = publishedDate;
        this.image = null;
    }
}
