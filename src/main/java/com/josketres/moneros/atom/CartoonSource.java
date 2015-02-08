package com.josketres.moneros.atom;

import java.time.LocalDate;
import java.util.List;

public interface CartoonSource {

    List<Cartoon> read();

    boolean hasErrors();

    CartoonSource setInitialDate(LocalDate initialDate);
}
