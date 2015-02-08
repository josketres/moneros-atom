package com.josketres.moneros.atom;

import com.josketres.moneros.atom.rss.CartoonRss;
import com.rometools.rome.feed.synd.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class FeedBuilder {

    private CartoonRss[] entries;
    private LocalDate initialDate;

    public SyndFeed build() {

        SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType("atom_1.0");
        feed.setTitle("Moneros Atom");
        feed.setLinks(buildLinks());
        feed.setDescription("Monos de moneros mexicanos");
        feed.setEntries(buildEntries());
        return feed;
    }

    private List<SyndLink> buildLinks() {

        List<SyndLink> links = new ArrayList<>();
        links.add(createLink("self", "http://josketres.github.io/moneros-atom/current.atom"));
        return links;
    }

    private SyndLinkImpl createLink(String rel, String href) {
        SyndLinkImpl link = new SyndLinkImpl();
        link.setRel(rel);
        link.setHref(href);
        return link;
    }

    private List<SyndEntry> buildEntries() {

        return Arrays.asList(entries)
                .parallelStream()
                .peek(e -> e.setInitialDate(initialDate))
                .map(rss -> rss.read().parallelStream())
                .flatMap(s -> s.map(c -> createEntry(c)))
                .sorted(Comparator.<SyndEntry, Date>comparing(e -> e.getPublishedDate()).reversed())
                .collect(Collectors.toList());
    }

    private SyndEntry createEntry(Cartoon c) {

        SyndEntryImpl entry = new SyndEntryImpl();
        entry.setTitle(c.title);
        entry.setAuthor(c.author);
        entry.setLinks(buildEntryLinks(c));
        entry.setPublishedDate(c.publishedDate);
        SyndContent description = new SyndContentImpl();
        description.setType("text/html");
        description.setValue("<img src='" + c.image + "' />");
        entry.setDescription(description);
        return entry;
    }

    private List<SyndLink> buildEntryLinks(Cartoon c) {
        List<SyndLink> links = new ArrayList<>();
        links.add(createLink("image", c.image));
        links.add(createLink("alternate", c.link));
        return links;
    }

    public FeedBuilder entries(CartoonRss... entries) {

        this.entries = entries;
        return this;
    }

    public FeedBuilder initialDate(LocalDate initialDate) {

        this.initialDate = initialDate;
        return this;
    }
}
