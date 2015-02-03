package com.josketres.moneros.atom.rss;

import com.josketres.moneros.atom.html.ImageExtractor;
import com.josketres.moneros.atom.html.LaJornadaImageExtractor;

public class CartoonRss {

    private ImageExtractor imageExtractor;

    public void setImageExtractor(ImageExtractor imageExtractor) {

        this.imageExtractor = imageExtractor;
    }

    protected String extractImage(String url) {

        return imageExtractor.extractImageFromUrl(url);
    }
}
