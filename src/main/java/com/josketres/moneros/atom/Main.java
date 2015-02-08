package com.josketres.moneros.atom;

import com.josketres.moneros.atom.rss.LaJornadaRss;
import com.josketres.moneros.atom.rss.PatricioMoneroRss;
import com.josketres.moneros.atom.rss.QuchoRss;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedOutput;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.Date;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        LocalDate initialDate = LocalDate.of(2015, Month.FEBRUARY, 1);
        SyndFeed feed = new FeedBuilder()
                .entries(Stream.of(new PatricioMoneroRss()
                                        .setInitialDate(initialDate)
                                        .read(PatricioMoneroRss.FEED_URL)
                                        .parallelStream(),
                                new LaJornadaRss()
                                        .setInitialDate(initialDate)
                                        .read(LaJornadaRss.FEED_URL)
                                        .parallelStream(),
                                new QuchoRss()
                                        .setInitialDate(initialDate)
                                        .read(QuchoRss.FEED_URL)
                                        .parallelStream())
                                .parallel()
                                .flatMap(x -> x)
                                .sorted(Comparator.<Cartoon, Date>comparing(c -> c.publishedDate).reversed())
                )
                .build();

        try (Writer writer = new FileWriter("website/current.atom", false)) {
            new SyndFeedOutput().output(feed, writer, false);
        } catch (IOException | FeedException e) {
            throw new RuntimeException(e);
        }
    }
}
