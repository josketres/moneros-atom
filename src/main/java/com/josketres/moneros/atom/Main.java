package com.josketres.moneros.atom;

import com.josketres.moneros.atom.rss.LaJornadaRss;
import com.josketres.moneros.atom.rss.PatricioMoneroRss;
import com.josketres.moneros.atom.rss.QuchoRss;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedOutput;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Date;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        SyndFeed feed = new FeedBuilder()
                .entries(Stream.of(new PatricioMoneroRss()
                                        .read(PatricioMoneroRss.FEED_URL).parallelStream(),
                                new LaJornadaRss()
                                        .read(LaJornadaRss.FEED_URL).parallelStream(),
                                new QuchoRss()
                                        .read(QuchoRss.FEED_URL).parallelStream())
                                .parallel()
                                .flatMap(x -> x)
                                .sorted(Comparator.<Cartoon, Date>comparing(c -> c.publishedDate).reversed())
                )
                .build();

        SyndFeedOutput output = new SyndFeedOutput();
        try {
            output.output(feed, new PrintWriter(System.out));
        } catch (IOException | FeedException e) {
            throw new RuntimeException(e);
        }
    }
}
