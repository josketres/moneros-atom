package com.josketres.moneros.atom.rss;

import com.josketres.moneros.atom.Cartoon;
import com.josketres.moneros.atom.html.DataExtractor;
import com.josketres.moneros.atom.html.LaJornadaCartoonTitleExtractor;
import com.josketres.moneros.atom.html.LaJornadaCartoonUrlExtractor;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class LaJornadaRss extends CartoonRss {

    public static final String FEED_URL = "http://www.jornada.unam.mx/rss/cartones.xml";

    private DataExtractor<String> titleExtractor;

    public LaJornadaRss() {

        setCartoonUrlExtractor(new LaJornadaCartoonUrlExtractor());
        setTitleExtractor(new LaJornadaCartoonTitleExtractor());
    }

    public List<Cartoon> read(String feedUrl) {

        try (Reader reader = new XmlReader(new URL(feedUrl))) {
            return doReadFeed(reader);
        } catch (FeedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Cartoon> doReadFeed(Reader reader) throws FeedException {

        SyndFeed feed = new SyndFeedInput().build(reader);
        return feed.getEntries()
                .parallelStream()
                .map(e -> createCartoon(e))
                .collect(Collectors.toList());
    }

    private Cartoon createCartoon(SyndEntry entry) {

        return new Cartoon(entry.getTitle(), // author
                entry.getPublishedDate(),
                entry.getLink(),
                extractImage(entry.getLink()),
                titleExtractor.extract(getHtmlDocumentReader(), entry.getLink()));
    }

    public void setTitleExtractor(DataExtractor<String> titleExtractor) {
        this.titleExtractor = titleExtractor;
    }
}
