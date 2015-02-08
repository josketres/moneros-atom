package com.josketres.moneros.atom;

import com.josketres.moneros.atom.rss.CartoonRss;
import com.rometools.rome.feed.synd.SyndEntry;

import java.util.Arrays;
import java.util.List;

public class CartoonRssMock extends CartoonRss {

    private final Cartoon[] cartoons;

    private CartoonRssMock(Cartoon[] cartoons) {
        this.cartoons = cartoons;
    }

    @Override
    public List<Cartoon> read() {
        return Arrays.asList(cartoons);
    }

    @Override
    protected Cartoon createCartoon(SyndEntry e) {
        return null;
    }

    @Override
    protected String defaultFeedUrl() {
        return null;
    }

    public static CartoonSource of(Cartoon... cartoons) {
        return new CartoonRssMock(cartoons);
    }
}
