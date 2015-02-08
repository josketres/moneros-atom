package com.josketres.moneros.atom;

import com.josketres.moneros.atom.rss.CartoonRss;

import java.time.LocalDate;
import java.util.List;

public interface CartoonSource {
    
    List<Cartoon> read();

    boolean hasErrors();

    CartoonRss setInitialDate(LocalDate initialDate);
}
