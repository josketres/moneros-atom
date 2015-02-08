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

public class Main {

    public static void main(String[] args) {

        SyndFeed feed = new FeedBuilder()
                .initialDate(LocalDate.of(2015, Month.FEBRUARY, 1))
                .entries(new PatricioMoneroRss(),
                        new LaJornadaRss(),
                        new QuchoRss())
                .build();

        try (Writer writer = new FileWriter("website/current.atom", false)) {
            new SyndFeedOutput().output(feed, writer, false);
        } catch (IOException | FeedException e) {
            throw new RuntimeException(e);
        }
    }
}
