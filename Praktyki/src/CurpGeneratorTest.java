import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CurpGeneratorTest {

    @Test
    void pierwszeLitery() {

        CurpGenerator generator = new CurpGenerator();
        String wynik = generator.pierwszeLitery("Ziętarski", "Chmielewski", "Paweł", "Jacek");

        assertEquals("ZCPJ", wynik);
    }

    @Test
    void pusteNazwiska() {

        CurpGenerator generator = new CurpGenerator();
        String wynik = generator.pierwszeLitery("", "", "", "");

        assertEquals("XXXX", wynik);
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
        String wynik2 = generator.znajdzSpolgloske("Ziętarski");

        assertEquals("t", wynik2);

    }

    @Test
    void jakNieZnajdzieSpolgloski() {

        CurpGenerator generator = new CurpGenerator();
        String wynik2 = generator.znajdzSpolgloske("Ięai");

        assertEquals("X", wynik2);

    }

    @Test
    void spolgloski() {

        CurpGenerator generator = new CurpGenerator();
        String wynik3 = generator.spolgloski("Ziętarski", "Chmielewski", "Paweł");

       assertEquals("thw", wynik3);

    }

    @Test
    void bezSpolglosek() {

        CurpGenerator generator = new CurpGenerator();
        String wynik3 = generator.spolgloski("Ięai", "AEIO", "ouyąę");

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
        String wynik7 = generator.generujeCURP("BBB", "UUU", "EEE", "III", 'H', CurpGenerator.Stany.OA, LocalDate.of(2009,9,16));

                assertEquals("BUEX090916HOAXBXA4", wynik7);
    }
}