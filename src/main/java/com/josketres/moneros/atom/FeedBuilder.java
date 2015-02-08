package com.josketres.moneros.atom;

import com.rometools.rome.feed.synd.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class FeedBuilder {

    private CartoonSource[] entries;
    private LocalDate initialDate;
    private SyndFeed mergeFeed;

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

        return Stream.concat(entriesStream(), mergeStream())
                .map(e -> new EntryWrapper(e)) // wrap to override equals
                .distinct()
                .map(w -> w.entry) // unwrap
                .sorted(Comparator.<SyndEntry, Date>comparing(e -> e.getPublishedDate()).reversed())
                .collect(Collectors.toList());
    }

    private Stream<SyndEntry> mergeStream() {

        if (mergeFeed == null) {
            return Stream.empty();
        } else {
            return mergeFeed.getEntries().parallelStream();
        }
    }

    private Stream<SyndEntry> entriesStream() {
        return Arrays.asList(entries)
                .parallelStream()
                .map(rss -> rss.setInitialDate(initialDate).read().parallelStream())
                .flatMap(s -> s.map(c -> createEntry(c)));
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

    public FeedBuilder entries(CartoonSource... entries) {

        this.entries = entries;
        return this;
    }

    public FeedBuilder initialDate(LocalDate initialDate) {

        this.initialDate = initialDate;
        return this;
    }

    public FeedBuilder mergeWith(SyndFeed mergeFeed) {

        this.mergeFeed = mergeFeed;
        return this;
    }

    private static class EntryWrapper {
        public final SyndEntry entry;

        public EntryWrapper(SyndEntry entry) {
            this.entry = entry;
        }

        @Override
        public boolean equals(Object obj) {

            if (obj instanceof EntryWrapper) {
                SyndEntry other = ((EntryWrapper) obj).entry;
                return equal(entry.getPublishedDate(), other.getPublishedDate())
                        && equal(entry.getAuthor(), other.getAuthor())
                        && equal(entry.getTitle(), other.getTitle())
                        && equal(entry.getLink(), other.getLink());
            }
            return false;
        }

        private boolean equal(Object a, Object b) {

            return com.google.common.base.Objects.equal(a, b);
        }

        @Override
        public int hashCode() {

            return com.google.common.base.Objects.hashCode(
                    entry.getPublishedDate(),
                    entry.getAuthor(),
                    entry.getTitle(),
                    entry.getLink());
        }
    }
}
