package hr.tis.academy.sightseeingapp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AttractionTest {

    @Test
    void urlNameStripsDiacritics() {
        assertEquals("Sibenik Fortress", attraction("Šibenik Fortress").getUrlName());
        assertEquals("Cakovec Castle", attraction("Čakovec Castle").getUrlName());
    }

    @Test
    void urlNameReplacesCroatianDWithStrokeWithPlainD() {
        assertEquals("Dakovo Cathedral", attraction("Đakovo Cathedral").getUrlName());
        assertEquals("durdevac", attraction("đurđevac").getUrlName());
    }

    @Test
    void urlNameKeepsPlainNamesUnchanged() {
        assertEquals("Diocletian's Palace", attraction("Diocletian's Palace").getUrlName());
    }

    private static Attraction attraction(String name) {
        Attraction attraction = new Attraction();
        attraction.setName(name);
        return attraction;
    }
}
