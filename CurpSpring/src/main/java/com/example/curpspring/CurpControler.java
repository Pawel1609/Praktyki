package com.example.curpspring;

import com.example.curpspring.model.Osoba;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/curp")
@CrossOrigin(origins = "*") // To pozwala każdemu (nawet plikom z dysku) na dostęp
public class CurpControler {

    @Autowired
    CurpGenerator generator;
    @Autowired
    CurpService service;

    @GetMapping("/test")
    String test() {
        return "hello";
    }

    @GetMapping("/test2")
    String test2(@RequestParam String nazwisko, @RequestParam String drugieNazwisko, @RequestParam String imie,
                 @RequestParam String drugieimieZFormularza, @RequestParam @DateTimeFormat(pattern = "ddMMyyyy") LocalDate dataUrodzenia,
                 @RequestParam char plec, @RequestParam CurpGenerator.Stany stan) {

        String wynik = generator.generujeCURP(nazwisko, drugieNazwisko, imie, drugieimieZFormularza, plec, stan, dataUrodzenia, (char) 0);

        return wynik;
    }


    @GetMapping("/test3/{nazwisko}/{imie}/")
    String test3(@PathVariable String nazwisko, @PathVariable String imie) {
        return "Nazwisko: " + nazwisko +
                "</br>" + "Imie: " + imie;
    }

    @PostMapping("/test4")
    String test4(@RequestBody CurpRequest request) {
        String wynik = generator.generujeCURP(request.getNazwisko(), request.getDrugieNazwisko(), request.getImie(), request.getDrugieimieZFormularza(), request.getPlec(), request.getStan(), request.getDataUrodzenia(), (char) 0);
        return wynik;
    }
    @GetMapping("/test5")
    String test5(@RequestParam String curp) {
        Osoba wynik = service.findOsobaBycurp(curp);
        String tresc=  "</br>" + "Nazwisko: " + wynik.getNazwisko() +
                       "</br>" + "Drugie Nazwisko: " + wynik.getDrugieNazwisko() +
                       "</br>" + "Imie: " + wynik.getImie() +
                       "</br>" + "Drugie Imie: " + wynik.getDrugieImieZFormularza() +
                       "</br>" + "Płeć: " + wynik.getPlec() +
                       "</br>" + "Stan: " + wynik.getStan() +
                       "</br>" + "Data urodzenia: " + wynik.getDataUrodzenia();
        return tresc;
    }
    @GetMapping("/test6")
    String test6(@RequestParam String curp) {
        List<Osoba> wynik = service.findOsobyBycurp(curp);
        String tresc = "";
        for ( Osoba dane : wynik) {
                tresc += "</br>" + "Nazwisko: " + dane.getNazwisko() +
                        "</br>" + "Drugie Nazwisko: " + dane.getDrugieNazwisko() +
                        "</br>" + "Imie: " + dane.getImie() +
                        "</br>" + "Drugie Imie: " + dane.getDrugieImieZFormularza() +
                        "</br>" + "Płeć: " + dane.getPlec() +
                        "</br>" + "Stan: " + dane.getStan() +
                        "</br>" + "Data urodzenia: " + dane.getDataUrodzenia() +
                        "</br>" + " " +
                        "</br>" + " ";
            }
            return tresc;
    }

}
