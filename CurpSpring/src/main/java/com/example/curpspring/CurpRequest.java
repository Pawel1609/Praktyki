package com.example.curpspring;

import java.time.LocalDate;

public class CurpRequest {
    private String nazwisko;

    private String drugieNazwisko;

    private String imie;

    private String drugieimieZFormularza;

    private LocalDate dataUrodzenia;

    private char plec;

    private CurpGenerator.Stany stan;

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getDrugieNazwisko() {
        return drugieNazwisko;
    }

    public void setDrugieNazwisko(String drugieNazwisko) {
        this.drugieNazwisko = drugieNazwisko;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getDrugieimieZFormularza() {
        return drugieimieZFormularza;
    }

    public void setDrugieimieZFormularza(String drugieimieZFormularza) {
        this.drugieimieZFormularza = drugieimieZFormularza;
    }

    public LocalDate getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(LocalDate dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public char getPlec() {
        return plec;
    }

    public void setPlec(char plec) {
        this.plec = plec;
    }

    public CurpGenerator.Stany getStan() {
        return stan;
    }

    public void setStan(CurpGenerator.Stany stan) {
        this.stan = stan;
    }
}