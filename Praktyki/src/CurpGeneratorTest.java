import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CurpGenerataorTest {

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

    @ParameterizedTest
    @CsvSource({"ZIETARSKI,zietarski,ZiEtArSkI, TTT", "ChMiEleWsKi,chmielewski, CHMIELEWSKI, HHH", "Pawel,paweL,PaWeL, WWW"})
    void spolgloskiCSV(String nazwisko, String drugieNazwisko, String imie, String oczekiwanyWynik) {

        CurpGenerator generator = new CurpGenerator();
        String wynik3 = generator.spolgloski(nazwisko, drugieNazwisko, imie);

        assertEquals(oczekiwanyWynik, wynik3);

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
        String wynik4 = generator.sprawdzWiek(LocalDate.of(2000,9,16), (char) 0);

        assertEquals("A", wynik4);

    }

    @Test
    void bezRoku() {

        CurpGenerator generator = new CurpGenerator();
        String wynik4 = generator.sprawdzWiek(LocalDate.of(0, 2, 2), (char) 0);

        assertEquals("0", wynik4);

    }

    @Test
    void obliczanie18Cyfry() {

        CurpGenerator generator = new CurpGenerator();
        String wynik5 = generator.obliczanie18Cyfry("ZCPJ890916HNEWTHA");

        assertEquals("1", wynik5);

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
        String wynik7 = generator.generujeCURP("AAA", "WWW", "JOÑ", "FOLUÑ", 'H', CurpGenerator.Stany.OA, LocalDate.of(2009,9,16), (char) 0);

                assertEquals("AAWJ090916HOAXWXA7", wynik7);
    }

    @Test
    void testNaImionaJoseJosé() {

        CurpGenerator generator = new CurpGenerator();
        String wynik8 = generator.generujeCURP("Maria", "José", "Jose", "Paweł", 'H', CurpGenerator.Stany.OA, LocalDate.of(2009,9,16), (char) 0);

        assertEquals("MAJP090916HOARSWA8", wynik8);
    }
    @Test
    void testNaImieMaria() {

        CurpGenerator generator = new CurpGenerator();
        String wynik9 = generator.generujeCURP("Maria", "Justyn", "Maria", "Paweł", 'H', CurpGenerator.Stany.OA, LocalDate.of(2009,9,16), (char) 0);

        assertEquals("MAJP090916HOARSWA8", wynik9);
    }

    @Test
    void generujeCurp_MaleLiteryIWielkie() {

        CurpGenerator generator = new CurpGenerator();
        String wynik10 = generator.generujeCURP("aaa", "www", "joÑ", "foluÑ", 'H', CurpGenerator.Stany.OA, LocalDate.of(2009,9,16), (char) 0);

        assertEquals("AAWJ090916HOAXWXA7", wynik10);
    }

    @Test
    void usuwanieZMeksykanskichImionZnakowDiakrytycznych() {

        CurpGenerator generator = new CurpGenerator();
        String wynik11 = generator.removeAccents("MUÑOZ JOSÉ");

        assertEquals("MUNOZ JOSE", wynik11);
    }

    @Test
    void generujeCurp_ZnakiDiakrytyczne() {

        CurpGenerator generator = new CurpGenerator();
        String wynik12 = generator.generujeCURP("ŃÓÑOZ", "JOSÉ", "ÑÑÑ", "ÑÑÑ", 'H', CurpGenerator.Stany.OA, LocalDate.of(2009,9,16), (char) 0);

        assertEquals("NOJX090916HOAXSXA9", wynik12);
    }

    @Test
    void sprawdzWiekDlaMlodychLiteraB() {
        CurpGenerator generator = new CurpGenerator();
        String wynik = generator.sprawdzWiek(LocalDate.of(2005, 5, 20), (char) 1);
        assertEquals("B", wynik);
    }

    @Test
    void sprawdzWiekDlaMlodychLiteraC() {
        CurpGenerator generator = new CurpGenerator();
        String wynik = generator.sprawdzWiek(LocalDate.of(2010, 1, 1), (char) 2);
        assertEquals("C", wynik);
    }

    @Test
    void sprawdzWiekDlaMlodychLiteraZ() {
        CurpGenerator generator = new CurpGenerator();
        String wynik = generator.sprawdzWiek(LocalDate.of(2020, 1, 1), (char) 25);
        assertEquals("Z", wynik);
    }

    @Test
    void sprawdzWiekDlaStarszych() {
        CurpGenerator generator = new CurpGenerator();
        String wynik = generator.sprawdzWiek(LocalDate.of(1989, 1, 16), (char) 1);
        assertEquals("1", wynik);
    }

    @Test
    void sprawdzWiekDlaStarszych_Cyfra9() {
        CurpGenerator generator = new CurpGenerator();
        String wynik = generator.sprawdzWiek(LocalDate.of(1950, 5, 5), (char) 9);
        assertEquals("9", wynik);
    }

    @Test
    void TestCurp1() {

        CurpGenerator generator = new CurpGenerator();
        String wynik7 = generator.generujeCURP("MÑOns", "Judwik", "Paweł", "FOLUÑ", 'H', CurpGenerator.Stany.NL, LocalDate.of(1999,9,16), (char) 0);

        assertEquals("MXJP990916HNLXDW02", wynik7);
    }
    @Test
    void TestCurp2() {

        CurpGenerator generator = new CurpGenerator();
        String wynik7 = generator.generujeCURP("Ñuans", "BEUI", "Bäel", "FOLUÑ", 'H', CurpGenerator.Stany.NL, LocalDate.of(2004,9,16), (char) 0);

        assertEquals("XUBB040916HNLNXLA7", wynik7);
    }
}