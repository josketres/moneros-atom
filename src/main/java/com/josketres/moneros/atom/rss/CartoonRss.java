package com.josketres.moneros.atom.rss;

import com.josketres.moneros.atom.Cartoon;
import com.josketres.moneros.atom.html.DataExtractor;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public abstract class CartoonRss {

    private DataExtractor<String> cartoonUrlExtractor;
    private List<SyndEntry> errorEntries = new ArrayList<>();

    protected abstract Cartoon createCartoon(SyndEntry e);

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
                .map(e -> tryCreateCartoon(e))
                .filter(x -> x != null)
                .collect(Collectors.toList());
    }

    private Cartoon tryCreateCartoon(SyndEntry entry) {

        try {
            return createCartoon(entry);
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE,
                    "error while converting entry to cartoon " + entry);
            e.printStackTrace();
            errorEntries.add(entry);
        }
        return null;
    }

    public boolean hasErrors() {
        return !errorEntries.isEmpty();
    }

    public List<SyndEntry> getErrorEntries() {
        return errorEntries;
    }

    public void setCartoonUrlExtractor(DataExtractor cartoonUrlExtractor) {
        this.cartoonUrlExtractor = cartoonUrlExtractor;
    }

    protected String extractImage(String url) {
        return cartoonUrlExtractor.extract(url);
    }
}
