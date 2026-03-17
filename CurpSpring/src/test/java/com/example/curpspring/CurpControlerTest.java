package com.example.curpspring;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CurpContsrolerTest {

    @Test
    void curpControler() {
        CurpControler curpControler = new CurpControler();
        CurpRequest curpRequest = new CurpRequest();

        curpRequest.setNazwisko("Kowalski");
        curpRequest.setDrugieNazwisko("Komar");
        curpRequest.setImie("Pawel");
        curpRequest.setDrugieimieZFormularza("Jacek");
        curpRequest.setDataUrodzenia(LocalDate.of(2004,9,16));
        curpRequest.setPlec('H');
        curpRequest.setStan(CurpGenerator.Stany.NE);

        String wynik = curpControler.test4(curpRequest);

        assertEquals("KOKP040916HNEWMWA7", wynik);
    }

    @Test
    void testBezDrugiegoNazwiska(){
        CurpControler curpControler = new CurpControler();
        CurpRequest curpRequest = new CurpRequest();

        curpRequest.setNazwisko("Kowalski");
        curpRequest.setDrugieNazwisko(null);
        curpRequest.setImie("Pawel");
        curpRequest.setDrugieimieZFormularza("Jacek");
        curpRequest.setDataUrodzenia(LocalDate.of(2004,9,16));
        curpRequest.setPlec('H');
        curpRequest.setStan(CurpGenerator.Stany.NE);

        String wynik = curpControler.test4(curpRequest);

        assertEquals("KOXP040916HNEWXWA5", wynik);
    }

    @Test
    void testBezDrugiegoImienia(){
        CurpControler curpControler = new CurpControler();
        CurpRequest curpRequest = new CurpRequest();

        curpRequest.setNazwisko("Kowalski");
        curpRequest.setDrugieNazwisko("Komar");
        curpRequest.setImie("Pawel");
        curpRequest.setDrugieimieZFormularza(null);
        curpRequest.setDataUrodzenia(LocalDate.of(2004,9,16));
        curpRequest.setPlec('H');
        curpRequest.setStan(CurpGenerator.Stany.NE);


        String wynik = curpControler.test4(curpRequest);

        assertEquals("KOKP040916HNEWMWA7", wynik);
    }

}