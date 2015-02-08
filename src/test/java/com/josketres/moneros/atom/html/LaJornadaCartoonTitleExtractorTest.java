package com.josketres.moneros.atom.html;

import com.josketres.moneros.atom.FileHtmlDocumentReader;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class LaJornadaCartoonTitleExtractorTest {

    @Test
    public void should_extract_the_image_url_from_the_html_page() {

        assertThat(new LaJornadaCartoonTitleExtractor()
                        .extract(FileHtmlDocumentReader::readFromFile,
                                "src/test/resources/jornada-unam-mx-article.html"),
                equalTo("Tras los huesos de Ebrard"));
    }
}