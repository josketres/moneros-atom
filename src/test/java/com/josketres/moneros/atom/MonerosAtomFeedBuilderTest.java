package com.josketres.moneros.atom;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedOutput;
import org.junit.Test;

import java.io.StringWriter;
import java.util.Date;
import java.util.stream.Stream;

public class MonerosAtomFeedBuilderTest {

    @Test
    public void should_create_atom_feed_with_1_item() throws Exception {

        SyndFeed feed = new MonerosAtomFeedBuilder()
                .entries(Stream.of(new Cartoon("Author", new Date(), "image.link", "image.src")))
                .build();

        SyndFeedOutput output = new SyndFeedOutput();
        StringWriter writer = new StringWriter();
        output.output(feed, writer);

        System.out.println(writer.getBuffer().toString());

    }
}