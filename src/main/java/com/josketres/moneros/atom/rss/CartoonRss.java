package com.josketres.moneros.atom.rss;

import com.josketres.moneros.atom.html.DataExtractor;

public class CartoonRss {

    private DataExtractor<String> cartoonUrlExtractor;

    public void setCartoonUrlExtractor(DataExtractor cartoonUrlExtractor) {

        this.cartoonUrlExtractor = cartoonUrlExtractor;
    }

    protected String extractImage(String url) {

        return cartoonUrlExtractor.extract(url);
    }
}
