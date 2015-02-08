package com.josketres.moneros.atom.rss;

import com.josketres.moneros.atom.Cartoon;
import org.exparity.hamcrest.date.Months;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.exparity.hamcrest.date.DateMatchers.sameDay;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class PatricioMoneroRssTest {

    @Test
    public void should_read_rss() throws Exception {

        String feedUrl = Paths.get("src/test/resources/patricio-monero-rss.xml").toAbsolutePath().toUri().toString();

        PatricioMoneroRss rss = new PatricioMoneroRss();
        rss.setCartoonUrlExtractor((reader, url) -> "http://moneros-atom.com/image.jpg"); // avoid making http calls in unit tests
        List<Cartoon> cartoons = rss.read(feedUrl);
        assertThat(cartoons, hasSize(10));

        assertThat(cartoons.get(0).author, equalTo("Patricio Monero"));
        assertThat(cartoons.get(0).publishedDate, sameDay(2015, Months.FEBRUARY, 2));
        assertThat(cartoons.get(0).link, equalTo("http://lajornadajalisco.com.mx/2015/02/politica-de-contacto/"));
        assertThat(cartoons.get(0).image, equalTo("http://moneros-atom.com/image.jpg"));
        assertThat(cartoons.get(0).title, equalTo("Política de contacto"));
    }
}