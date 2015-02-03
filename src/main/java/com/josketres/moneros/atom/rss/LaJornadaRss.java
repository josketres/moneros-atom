package com.josketres.moneros.atom.rss;

import com.josketres.moneros.atom.Cartoon;
import com.josketres.moneros.atom.html.ImageExtractor;
import com.josketres.moneros.atom.html.LaJornadaImageExtractor;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.awt.Image;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class LaJornadaRss extends CartoonRss {

    public static final String FEED_URL = "http://www.jornada.unam.mx/rss/cartones.xml";

    public LaJornadaRss() {

        setImageExtractor(new LaJornadaImageExtractor());
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
                .map(e -> new Cartoon(e.getTitle(), e.getPublishedDate(), e.getLink(), extractImage(e.getLink())))
                .collect(Collectors.toList());
    }

}
