package com.josketres.moneros.atom;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedOutput;
import org.junit.Test;

import java.io.StringWriter;
import java.util.Date;

public class FeedBuilderTest {

    @Test
    public void should_create_atom_feed_with_1_item() throws Exception {

        SyndFeed feed = new FeedBuilder()
                .entries(CartoonRssMock.of(new Cartoon("Author", new Date(), "image.link", "image.src", "title")))
                .build();

        SyndFeedOutput output = new SyndFeedOutput();
        StringWriter writer = new StringWriter();
        output.output(feed, writer);

        System.out.println(writer.getBuffer().toString());

    }
}