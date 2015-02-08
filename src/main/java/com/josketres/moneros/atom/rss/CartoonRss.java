package com.josketres.moneros.atom.rss;

import com.josketres.moneros.atom.html.CachedHtmlDocumentReader;
import com.josketres.moneros.atom.html.DataExtractor;
import com.josketres.moneros.atom.html.HtmlDocumentReader;

public class CartoonRss {

    private DataExtractor<String> cartoonUrlExtractor;
    private HtmlDocumentReader htmlDocumentReader;

    public CartoonRss() {

        htmlDocumentReader = new CachedHtmlDocumentReader();
    }

    public void setCartoonUrlExtractor(DataExtractor cartoonUrlExtractor) {

        this.cartoonUrlExtractor = cartoonUrlExtractor;
    }

    public void setHtmlDocumentReader(HtmlDocumentReader htmlDocumentReader) {
        this.htmlDocumentReader = htmlDocumentReader;
    }

    protected HtmlDocumentReader getHtmlDocumentReader() {
        return htmlDocumentReader;
    }

    protected String extractImage(String url) {

        return cartoonUrlExtractor.extract(htmlDocumentReader, url);
    }
}
