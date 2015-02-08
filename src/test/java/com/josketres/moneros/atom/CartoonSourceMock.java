package com.josketres.moneros.atom;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class CartoonSourceMock implements CartoonSource {

    private final Cartoon[] cartoons;

    private CartoonSourceMock(Cartoon[] cartoons) {
        this.cartoons = cartoons;
    }

    @Override
    public List<Cartoon> read() {
        return Arrays.asList(cartoons);
    }

    @Override
    public boolean hasErrors() {

        return false;
    }

    @Override
    public CartoonSource setInitialDate(LocalDate initialDate) {

        return this;
    }

    public static CartoonSource of(Cartoon... cartoons) {
        return new CartoonSourceMock(cartoons);
    }
}
