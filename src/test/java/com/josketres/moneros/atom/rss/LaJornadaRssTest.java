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

public class LaJornadaRssTest {

    public static final String LINK = "http://rss.feedsportal" +
            ".com/c/32833/f/626145/p/1/s/2a8351ab/l/0L0Sjornada0Bunam0Bmx0C20A150C0A20C0A20Cindex0Bphp0Dsection0Fcartones0Gid0F0A0Gpartner0Frss" +
            "/story01.htm";

    @Test
    public void should_read_rss() throws Exception {

        String feedUrl = Paths.get("src/test/resources/jornada-unam-mx-rss-cartones.xml").toAbsolutePath().toUri().toString();

        LaJornadaRss rss = new LaJornadaRss();
        rss.setCartoonUrlExtractor((reader, url) -> "http://moneros-atom.com/image.jpg"); // avoid making http calls in unit tests
        rss.setTitleExtractor((reader, url) -> "Tras los huesos de Ebrard"); // avoid making http calls in unit tests
        List<Cartoon> cartoons = rss.read(feedUrl);
        assertThat(cartoons, hasSize(4));

        assertThat(cartoons.get(0).author, equalTo("Magú"));
        assertThat(cartoons.get(0).publishedDate, sameDay(2015, Months.FEBRUARY, 2));
        assertThat(cartoons.get(0).link, equalTo(LINK));
        assertThat(cartoons.get(0).image, equalTo("http://moneros-atom.com/image.jpg"));
        assertThat(cartoons.get(0).title, equalTo("Tras los huesos de Ebrard"));
    }
}