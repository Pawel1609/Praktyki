package com.example.curpspring;

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

    CurpGenerator generator = new CurpGenerator();

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
}