package com.josketres.moneros.atom;

import org.exparity.hamcrest.date.Months;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import static org.exparity.hamcrest.date.DateMatchers.sameDay;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class LaJornadaRssTest {

    public static final Date FEBRUARY_2_2015 = new Date(2015, 1, 2, 0, 0, 0);

    @Test
    public void should_read_rss() throws Exception {

        String feedUrl = Paths.get("src/test/resources/jornada-unam-mx-rss-cartones.xml").toAbsolutePath().toUri().toString();

        List<Cartoon> cartoons = new LaJornadaRss().read(feedUrl);
        assertThat(cartoons, hasSize(4));

        assertThat(cartoons.get(0).author, equalTo("Magú"));
        assertThat(cartoons.get(0).publishedDate, sameDay(2015, Months.FEBRUARY, 2));
    }
}