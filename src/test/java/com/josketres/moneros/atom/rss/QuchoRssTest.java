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

public class QuchoRssTest {

    @Test
    public void should_read_rss() throws Exception {

        String feedUrl = Paths.get("src/test/resources/qucho-monero-rss.xml").toAbsolutePath().toUri().toString();

        QuchoRss rss = new QuchoRss();
        List<Cartoon> cartoons = rss.read(feedUrl);
        assertThat(cartoons, hasSize(10));

        assertThat(cartoons.get(0).author, equalTo("Qucho Monero"));
        assertThat(cartoons.get(0).publishedDate, sameDay(2015, Months.FEBRUARY, 6));
        assertThat(cartoons.get(0).link, equalTo("http://opinion.informador.com.mx/Cartuchos/2015/02/06/carton-del-dia-458/"));
        assertThat(cartoons.get(0).image, equalTo("http://opinion.informador.com.mx/Cartuchos/wp-content/uploads/sites/22/2015/02/carton-del-dia2.jpg"));
        assertThat(cartoons.get(0).title, equalTo("Cartón del día"));
    }
}