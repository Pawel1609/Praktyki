import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CurpGeneratorTest {

    @Test
    void pierwszeLitery() {

        CurpGenerator generator = new CurpGenerator();
        String wynik = generator.pierwszeLitery("Zietarski", "Chmielewski", "Pawel", "Jacek");

        assertEquals("ZICP", wynik);
    }

    @Test
    void pusteNazwiska() {

        CurpGenerator generator = new CurpGenerator();
        String wynik = generator.pierwszeLitery("AAA", "", "WWW", "SSS");

        assertEquals("AAXW", wynik);
    }

    @Test
    void wyliczCyfryZDatyUrodzin() {

        CurpGenerator generator = new CurpGenerator();
        String wynik1 = generator.wyliczCyfryZDatyUrodzin(LocalDate.of(1989,1,16));

        assertEquals("890116", wynik1);

    }

    @Test
    void datyPonizejdziesieciu() {

        CurpGenerator generator = new CurpGenerator();
        String wynik1 = generator.wyliczCyfryZDatyUrodzin(LocalDate.of(1989,1,2));

        assertEquals("890102", wynik1);

    }

    @Test
    void znajdzSpolgloske() {

        CurpGenerator generator = new CurpGenerator();
        String wynik2 = generator.znajdzSpolgloske("Zietarski");

        assertEquals("T", wynik2);

    }

    @Test
    void jakNieZnajdzieSpolgloski() {

        CurpGenerator generator = new CurpGenerator();
        String wynik2 = generator.znajdzSpolgloske("Ieai");

        assertEquals("X", wynik2);

    }

    @Test
    void spolgloski() {

        CurpGenerator generator = new CurpGenerator();
        String wynik3 = generator.spolgloski("Zietarski", "Chmielewski", "Paweł");

       assertEquals("THW", wynik3);

    }

    @Test
    void bezSpolglosek() {

        CurpGenerator generator = new CurpGenerator();
        String wynik3 = generator.spolgloski("Ieai", "AEIO", "ouyae");

        assertEquals("XXX", wynik3);

    }

    @Test
    void sprawdzWiek() {

        CurpGenerator generator = new CurpGenerator();
        String wynik4 = generator.sprawdzWiek(LocalDate.of(2123,9,16));

        assertEquals("A", wynik4);

    }

    @Test
    void bezRoku() {

        CurpGenerator generator = new CurpGenerator();
        String wynik4 = generator.sprawdzWiek(LocalDate.of(0, 2, 2));

        assertEquals("0", wynik4);

    }

    @Test
    void obliczanie18Cyfry() {

        CurpGenerator generator = new CurpGenerator();
        String wynik5 = generator.obliczanie18Cyfry("ZCPJ090916HNEWTHA");

        assertEquals("0", wynik5);

    }

    @Test
    void jakNieMaLiterZeSlownika() {

        CurpGenerator generator = new CurpGenerator();
        String wynik5 = generator.obliczanie18Cyfry("ĄĘĆŹĄĄĘĘĆŹĆŁĄŁĘĆÓ");

        assertEquals("0", wynik5);

    }

    /*@Test
    void generujeCURP() {

        CurpGenerator generator = new CurpGenerator();
        String wynik6 = generator.generujeCURP("Ziętarski", "Chmielewski", "Paweł", 'H',

        assertEquals("ZCPJ090916HNEWTHA0", wynik6);
    } */

    @Test
    void testGenerujeCurp() {

        CurpGenerator generator = new CurpGenerator();
        String wynik7 = generator.generujeCURP("AAA", "WWW", "JOÑ", "FOLUÑ", 'H', CurpGenerator.Stany.OA, LocalDate.of(2009,9,16));

                assertEquals("AAWJ090916HOAXWXA2", wynik7);
    }

    @Test
    void testNaImionaJoseJosé() {

        CurpGenerator generator = new CurpGenerator();
        String wynik7 = generator.generujeCURP("Maria", "José", "Jose", "Paweł", 'H', CurpGenerator.Stany.OA, LocalDate.of(2009,9,16));

        assertEquals("MAJP090916HOARSWA4", wynik7);
    }
    @Test
    void testNaImieMaria() {

        CurpGenerator generator = new CurpGenerator();
        String wynik7 = generator.generujeCURP("Maria", "Justyn", "Maria", "Paweł", 'H', CurpGenerator.Stany.OA, LocalDate.of(2009,9,16));

        assertEquals("MAJP090916HOARSWA4", wynik7);
    }

    @Test
    void generujeCurp_MaleLiteryIWielkie() {

        CurpGenerator generator = new CurpGenerator();
        String wynik7 = generator.generujeCURP("aaa", "www", "joÑ", "foluÑ", 'H', CurpGenerator.Stany.OA, LocalDate.of(2009,9,16));

        assertEquals("AAWJ090916HOAXWXA2", wynik7);
    }

    @Test
    void usuwanieZMeksykanskichImionZnakowDiakrytycznych() {

        CurpGenerator generator = new CurpGenerator();
        String wynik = generator.removeAccents("MUÑOZ JOSÉ");

        assertEquals("MUNOZ JOSE", wynik);
    }

    @Test
    void generujeCurp_ZnakiDiakrytyczne() {

        CurpGenerator generator = new CurpGenerator();
        String wynik7 = generator.generujeCURP("ŃÓÑOZ", "JOSÉ", "ÑÑÑ", "ÑÑÑ", 'H', CurpGenerator.Stany.OA, LocalDate.of(2009,9,16));

        assertEquals("NOJX090916HOAXSXA8", wynik7);
    }

}