package com.josketres.moneros.atom.rss;

import com.josketres.moneros.atom.Cartoon;
import com.josketres.moneros.atom.html.JsoupHelper;
import com.josketres.moneros.atom.html.PatricioMoneroCartoonUrlExtractor;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class PatricioMoneroRss extends CartoonRss {

    public static final String FEED_URL = "http://lajornadajalisco.com.mx/category/patricio-monero/feed/";

    public PatricioMoneroRss() {

        setCartoonUrlExtractor(new PatricioMoneroCartoonUrlExtractor());
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
                .map(e -> new Cartoon("Patricio Monero",
                        e.getPublishedDate(),
                        e.getLink(),
                        extractImage(JsoupHelper.connectAndGet(e.getLink())),
                        e.getTitle()))
                .collect(Collectors.toList());
    }
}
