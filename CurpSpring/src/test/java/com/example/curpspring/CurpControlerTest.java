package com.example.curpspring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class CurpContsrolerTest {

    @Autowired
    CurpControler curpControler;

    @Test
    void curpControler() {
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

    @Test
    void szukanieDanychPoprzezImieAndNazwisko(){
        String imie = "Michał";
        String nazwisko = "Kowalski";
        String wynik = curpControler.test7(imie, nazwisko);
        //assertEquals(wynik, wynik);
    }

}