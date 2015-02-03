package com.josketres.moneros.atom;

import com.rometools.rome.feed.synd.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MonerosAtomFeedBuilder {

    private Stream<Cartoon> entries;

    public SyndFeed build() {

        SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType("atom_1.0");
        feed.setTitle("Moneros Atom");
        feed.setLink("http://josketres.github.io/moneros-atom/atom.xml");
        feed.setDescription("Monos de moneros mexicanos");
        feed.setEntries(buildEntries());
        return feed;
    }

    private List<SyndEntry> buildEntries() {

        return entries
                .parallel()
                .map(c -> {
                    SyndEntryImpl entry = new SyndEntryImpl();
                    entry.setTitle(c.author);
                    entry.setAuthor(c.author);
                    entry.setLink(c.link);
                    entry.setPublishedDate(c.publishedDate);
                    SyndContent description = new SyndContentImpl();
                    description.setType("text/plain");
                    description.setValue(c.image);
                    entry.setDescription(description);
                    return entry;
                }).collect(Collectors.toList());
    }

    public MonerosAtomFeedBuilder entries(Stream<Cartoon> entries) {

        this.entries = entries;
        return this;
    }
}
