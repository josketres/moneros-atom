package com.josketres.moneros.atom.rss;

import com.josketres.moneros.atom.Cartoon;
import com.josketres.moneros.atom.html.PatricioMoneroCartoonUrlExtractor;
import com.rometools.rome.feed.synd.SyndEntry;

public class PatricioMoneroRss extends CartoonRss {

    public static final String FEED_URL = "http://lajornadajalisco.com.mx/category/patricio-monero/feed/";

    public PatricioMoneroRss() {

        setCartoonUrlExtractor(new PatricioMoneroCartoonUrlExtractor());
    }

    @Override
    protected Cartoon createCartoon(SyndEntry e) {

        return new Cartoon("Patricio Monero",
                e.getPublishedDate(),
                e.getLink(),
                extractImage(e.getLink()),
                e.getTitle());
    }
}
