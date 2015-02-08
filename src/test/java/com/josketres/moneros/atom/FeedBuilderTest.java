package com.josketres.moneros.atom;

import com.rometools.rome.feed.synd.SyndFeed;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class FeedBuilderTest {

    @Test
    public void should_create_atom_feed_with_1_item() throws Exception {

        SyndFeed feed = new FeedBuilder()
                .entries(CartoonRssMock.of(new Cartoon("Author", new Date(), "image.link", "image.src", "title")))
                .build();

        assertThat(feed, notNullValue());
    }
}