package com.josketres.moneros.atom.rss;

import com.josketres.moneros.atom.html.DataExtractor;
import org.jsoup.nodes.Document;

public class CartoonRss {

    private DataExtractor<String> cartoonUrlExtractor;

    public void setCartoonUrlExtractor(DataExtractor cartoonUrlExtractor) {

        this.cartoonUrlExtractor = cartoonUrlExtractor;
    }

    protected String extractImage(Document doc) {

        return cartoonUrlExtractor.extract(doc);
    }
}
