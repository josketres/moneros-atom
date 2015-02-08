package com.josketres.moneros.atom;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.Reader;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class FeedBuilderTest {

    @Test
    public void should_create_atom_feed_with_1_item() throws Exception {

        SyndFeed feed = new FeedBuilder()
                .entries(CartoonSourceMock.of(new Cartoon("Author", new Date(), "image.link", "image.src", "title")))
                .build();

        assertThat(feed, notNullValue());
    }

    @Test
    public void should_merge_atom_feed_and_delete_duplicates() throws Exception {

        Date publishedDate = new Date();

        SyndFeed mergeFeed = new FeedBuilder()
                .entries(CartoonSourceMock.of(new Cartoon("Author", publishedDate, "image.link", "image.src", "title")))
                .build();

        SyndFeed feed = new FeedBuilder()
                .entries(CartoonSourceMock.of(new Cartoon("Author", publishedDate, "image.link", "image.src", "title")))
                .mergeWith(mergeFeed)
                .build();

        assertThat(feed, notNullValue());
        assertThat(feed.getEntries(), Matchers.hasSize(1));
    }

    @Test
    public void should_merge_atom_feed_from_file_and_delete_duplicates() throws Exception {

        SyndFeed mergeFeed = readFromFile(Paths.get("src/test/resources/current.duplicate.atom").toAbsolutePath().toUri().toString());

        SyndFeed feed = new FeedBuilder()
                .entries(CartoonSourceMock.of(new Cartoon("Hernández",
                        new Date(),
                        "http://rss.feedsportal.com/c/32833/f/626145/p/1/s/35b81f4f/l/0L0Sjornada0Bunam0Bmx0C20A150C0A20C0A80Cindex0Bphp0Dsection0Fcartones0Gid0F0A0Gpartner0Frss/story01.htm",
                        "http://www.jornada.unam.mx/2015/02/08/cartones/hernandez.jpg",
                        "Irregularidad")))
                .mergeWith(mergeFeed)
                .build();

        assertThat(feed, notNullValue());
        assertThat(feed.getEntries(), Matchers.hasSize(1));
    }

    private static SyndFeed readFromFile(String path) throws Exception {

        try (Reader reader = new XmlReader(new URL(path))) {
            return new SyndFeedInput().build(reader);
        }
    }
}