package com.josketres.moneros.atom.rss;

import com.josketres.moneros.atom.Cartoon;
import com.josketres.moneros.atom.html.PatricioCartoonUrlExtractor;
import com.rometools.rome.feed.synd.SyndEntry;

public class PatricioRss extends CartoonRss {

    public PatricioRss() {

        setCartoonUrlExtractor(new PatricioCartoonUrlExtractor());
    }

    @Override
    protected Cartoon createCartoon(SyndEntry e) {

        return new Cartoon("Patricio Monero",
                e.getPublishedDate(),
                e.getLink(),
                extractImage(e.getLink()),
                e.getTitle());
    }

    @Override
    protected String defaultFeedUrl() {

        return "http://lajornadajalisco.com.mx/category/patricio-monero/feed/";
    }
}
