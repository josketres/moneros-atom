package com.josketres.moneros.atom.rss;

import com.josketres.moneros.atom.Cartoon;
import com.josketres.moneros.atom.html.DataExtractor;
import com.josketres.moneros.atom.html.LaJornadaCartoonTitleExtractor;
import com.josketres.moneros.atom.html.LaJornadaCartoonUrlExtractor;
import com.rometools.rome.feed.synd.SyndEntry;

public class LaJornadaRss extends CartoonRss {

    public static final String FEED_URL = "http://www.jornada.unam.mx/rss/cartones.xml";

    private DataExtractor<String> titleExtractor;

    public LaJornadaRss() {

        setCartoonUrlExtractor(new LaJornadaCartoonUrlExtractor());
        setTitleExtractor(new LaJornadaCartoonTitleExtractor());
    }

    public void setTitleExtractor(DataExtractor<String> titleExtractor) {
        this.titleExtractor = titleExtractor;
    }

    @Override
    protected Cartoon createCartoon(SyndEntry e) {

        return new Cartoon(e.getTitle(), // author
                e.getPublishedDate(),
                e.getLink(),
                extractImage(e.getLink()),
                titleExtractor.extract(e.getLink()));
    }
}
