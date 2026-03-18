package com.example.curpspring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class CurpGenerataorTest {
    
    @Autowired
    CurpGenerator generator;

    @Test
    void pierwszeLitery() {
        
        String wynik1 = generator.pierwszeLitery("Zietarski", "Chmielewski", "Pawel", "Jacek");

        assertEquals("ZICP", wynik1);
    }

    @Test
    void pusteNazwiska() {
        
        String wynik2 = generator.pierwszeLitery("AAA", "", "WWW", "SSS");

        assertEquals("AAXW", wynik2);
    }

    @Test
    void wyliczCyfryZDatyUrodzin() {
        
        String wynik3 = generator.wyliczCyfryZDatyUrodzin(LocalDate.of(1989,1,16));

        assertEquals("890116", wynik3);

    }

    @Test
    void datyPonizejdziesieciu() {
        
        String wynik4 = generator.wyliczCyfryZDatyUrodzin(LocalDate.of(1989,1,2));

        assertEquals("890102", wynik4);

    }

    @Test
    void znajdzSpolgloske() {
        
        String wynik5 = generator.znajdzSpolgloske("Zietarski");

        assertEquals("T", wynik5);

    }

    @Test
    void jakNieZnajdzieSpolgloski() {
        
        String wynik6 = generator.znajdzSpolgloske("Ieai");

        assertEquals("X", wynik6);

    }

    @Test
    void spolgloski() {
        
        String wynik7 = generator.spolgloski("Zietarski", "Chmielewski", "Paweł");

       assertEquals("THW", wynik7);

    }

    @ParameterizedTest
    @CsvSource({"ZIETARSKI,zietarski,ZiEtArSkI, TTT", "ChMiEleWsKi,chmielewski, CHMIELEWSKI, HHH", "Pawel,paweL,PaWeL, WWW"})
    void spolgloskiCSV(String nazwisko, String drugieNazwisko, String imie, String oczekiwanyWynik) {
        
        String wynik8 = generator.spolgloski(nazwisko, drugieNazwisko, imie);

        assertEquals(oczekiwanyWynik, wynik8);

    }

    @Test
    void bezSpolglosek() {
        
        String wynik9 = generator.spolgloski("Ieai", "AEIO", "ouyae");

        assertEquals("XXX", wynik9);

    }

    @Test
    void sprawdzWiek() {
        
        String wynik10 = generator.sprawdzWiek(LocalDate.of(2000,9,16), (char) 0);

        assertEquals("A", wynik10);

    }

    @Test
    void bezRoku() {
        
        String wynik11 = generator.sprawdzWiek(LocalDate.of(0, 2, 2), (char) 0);

        assertEquals("0", wynik11);

    }

    @Test
    void obliczanie18Cyfry() {
        
        String wynik12 = generator.obliczanie18Cyfry("ZCPJ890916HNEWTHA");

        assertEquals("1", wynik12);

    }

    @Test
    void jakNieMaLiterZeSlownika() {
        
        String wynik13 = generator.obliczanie18Cyfry("ĄĘĆŹĄĄĘĘĆŹĆŁĄŁĘĆÓ");

        assertEquals("0", wynik13);

    }

    /*@Test
    void generujeCURP() {

        
        String wynik14 = generator.generujeCURP("Ziętarski", "Chmielewski", "Paweł", 'H',

        assertEquals("ZCPJ090916HNEWTHA0", wynik14);
    } */

    @Test
    void testGenerujeCurp() {
        
        String wynik14 = generator.generujeCURP("AAA", "WWW", "JOÑ", "FOLUÑ", 'H', CurpGenerator.Stany.OA, LocalDate.of(2009,9,16), (char) 0);

                assertEquals("AAWJ090916HOAXWXA7", wynik14);
    }

    @Test
    void testNaImionaJoseJosé() {
        
        String wynik15 = generator.generujeCURP("Maria", "José", "Jose", "Paweł", 'H', CurpGenerator.Stany.OA, LocalDate.of(2009,9,16), (char) 0);

        assertEquals("MAJJ090916HOARSSA0", wynik15);
    }
    @Test
    void testNaImieMaria() {
        
        String wynik16 = generator.generujeCURP("Maria", "Justyn", "Maria", "Paweł", 'H', CurpGenerator.Stany.OA, LocalDate.of(2009,9,16), (char) 0);

        assertEquals("MAJM090916HOARSRA8", wynik16);
    }

    @Test
    void generujeCurp_MaleLiteryIWielkie() {
        
        String wynik17 = generator.generujeCURP("aaa", "www", "joÑ", "foluÑ", 'H', CurpGenerator.Stany.OA, LocalDate.of(2009,9,16), (char) 0);

        assertEquals("AAWJ090916HOAXWXA7", wynik17);
    }

    @Test
    void usuwanieZMeksykanskichImionZnakowDiakrytycznych() {

        
        String wynik18 = generator.removeAccents("MUÑOZ JOSÉ");

        assertEquals("MUNOZ JOSE", wynik18);
    }

    @Test
    void generujeCurp_ZnakiDiakrytyczne() {

        
        String wynik19 = generator.generujeCURP("ŃÓÑOZ", "JOSÉ", "ÑÑÑ", "ÑÑÑ", 'H', CurpGenerator.Stany.OA, LocalDate.of(2009,9,16), (char) 0);

        assertEquals("NOJX090916HOAXSXA9", wynik19);
    }

    @Test
    void sprawdzWiekDlaMlodychLiteraB() {
        
        String wynik20 = generator.sprawdzWiek(LocalDate.of(2005, 5, 20), (char) 1);
        assertEquals("B", wynik20);
    }

    @Test
    void sprawdzWiekDlaMlodychLiteraC() {
        
        String wynik21 = generator.sprawdzWiek(LocalDate.of(2010, 1, 1), (char) 2);
        assertEquals("C", wynik21);
    }

    @Test
    void sprawdzWiekDlaMlodychLiteraZ() {
        
        String wynik22 = generator.sprawdzWiek(LocalDate.of(2020, 1, 1), (char) 25);
        assertEquals("Z", wynik22);
    }

    @Test
    void sprawdzWiekDlaStarszych() {
        
        String wynik23 = generator.sprawdzWiek(LocalDate.of(1989, 1, 16), (char) 1);
        assertEquals("1", wynik23);
    }

    @Test
    void sprawdzWiekDlaStarszych_Cyfra9() {
        
        String wynik24 = generator.sprawdzWiek(LocalDate.of(1950, 5, 5), (char) 9);
        assertEquals("9", wynik24);
    }

    @Test
    void TestCurp1() {

        
        String wynik25 = generator.generujeCURP("MÑOns", "Judwik", "Paweł", "FOLUÑ", 'H', CurpGenerator.Stany.NL, LocalDate.of(1999,9,16), (char) 0);

        assertEquals("MXJP990916HNLXDW02", wynik25);
    }
    @Test
    void TestCurp2() {

        
        String wynik26 = generator.generujeCURP("Ñuans", "BEUI", "Bäel", "FOLUÑ", 'H', CurpGenerator.Stany.NL, LocalDate.of(2004,9,16), (char) 0);

        assertEquals("XUBB040916HNLNXLA7", wynik26);
    }

    @Test
    void generatorCurpZeZmianaZnakow() {

        
        String wynik27 = generator.generujeCURP("`Essio", "Torres", "Bravo", "FOLUÑ", 'H', CurpGenerator.Stany.NL, LocalDate.of(2004,9,16), (char) 0);

        assertEquals("XETB040916HNLSRRA2", wynik27);
    }

    @ParameterizedTest
    @CsvSource({"Am/en, AmXen","Be-be/ba, BeXbeXba","Bea., BeaX", "We‘ar, WeXar", "¨Tal, XTal", "`Essio, XEssio"})
    void zmianaZnakowCSV(String zmiana, String oczekiwanyWynik) {

        
        String wynik28 = generator.zmianaZnakowNaX(zmiana);

        assertEquals(oczekiwanyWynik, wynik28);

    }

    @Test
    void generatorCurpZeZmianaSlowNiePotrzebnych() {

        
        String wynik27 = generator.generujeCURP("de las Lomas", "Garces", "Juan", "Angel", 'H', CurpGenerator.Stany.NL, LocalDate.of(2004,9,16), (char) 0);

        assertEquals("LOGJ040916HNLMRNA8", wynik27);
    }

    @Test
    void smiecioweSlowa() {

        
        String wynik27 = generator.dajPierwszeSlowoKtoreMaZnaczenie("Juan Angel de las Lomas Garces");

        assertEquals("JUAN", wynik27);
    }

    @Test
    void dataDzwineDniiMiesiace() {

        
        String wynik28 = generator.sprawdzWiek(LocalDate.of(2004,1,11),(char) 0);

        assertEquals("A", wynik28);
    }

    @Test
    void CurpRequest(){
        CurpRequest curpRequest = new CurpRequest();
        curpRequest.setNazwisko("Kowalski");
        curpRequest.setDrugieNazwisko("Komar");
        curpRequest.setImie("Pawel");
        curpRequest.setDrugieimieZFormularza("Jacek");
        curpRequest.setDataUrodzenia(LocalDate.of(2004,9,16));
        curpRequest.setPlec('H');
        }

}