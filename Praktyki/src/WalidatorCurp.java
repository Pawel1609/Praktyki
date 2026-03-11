import java.time.LocalDate;
import java.time.DateTimeException;
public class WalidatorCurp {

    String slownik = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String samogloski = "AEIOUYaeiouy";
    String spolgloski = "BCDFGHJKLMNPQRSTVWXYZ";
    String litery = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String cyfry = "0123456789";

    boolean sprawdzCyfreKoncowa(String curp) {
        int suma = 0;
        String kod17 = curp.substring(0, 17).toUpperCase();
        for (int i = 0; i < 17; i++) {
            char c = kod17.charAt(i);
            int wartosc = slownik.indexOf(c);
            if (wartosc == -1) wartosc = 0;
            suma += wartosc * (18 - i);
        }
        int wyliczonaCyfraKontrolna = (10 - (suma % 10)) % 10;
        int faktycznaCyfra = Character.getNumericValue(curp.charAt(17));
        return wyliczonaCyfraKontrolna == faktycznaCyfra;
    }

    boolean sprawdzZnakiSpolgloskiCyfry (String curp) {
        curp = curp.toUpperCase();
        for (int i = 0; i < 4; i++) {
            Character c = curp.charAt(i);
            if (!litery.contains(c.toString().toUpperCase())) {
                return false;
            }
        }
        for (int i = 4; i < 10; i++) {
            Character c = curp.charAt(i);
            if (!cyfry.contains(c.toString())) {
                return false;
            }
        }
        Character plec = curp.charAt(10);
        if (!plec.toString().equals("H") && !plec.toString().equals("M") && !plec.toString().equals("X")) {
                    return false;
        }
        for (int i = 11; i < 13; i++) {
            Character c = curp.charAt(i);
            if (!litery.contains(c.toString().toUpperCase())) {
                return false;
            }
        }
        for (int i = 13; i < 16; i++) {
            Character c = curp.charAt(i);
            if (!spolgloski.toUpperCase().contains(c.toString())) {
                return false;
            }
        }
        Character wiek = curp.charAt(16);
        if (!litery.contains(wiek.toString().toUpperCase())) {
            if (!cyfry.contains(wiek.toString())) {
                return false;
            }
        }
        return true;
    }
    boolean sprawdzDate(String curp) {
        if (curp.length() < 10) {
            return false;
        }
        String dataZCurp = curp.substring(4, 10);
        int rok = Integer.parseInt(dataZCurp.substring(0, 2));

        int miesiac = Integer.parseInt(dataZCurp.substring(2, 4));

        int dzien = Integer.parseInt(dataZCurp.substring(4, 6));

        int pelnyRok = 2000 + rok;
        try {
            LocalDate.of(pelnyRok, miesiac, dzien);

            return true;
        }
        catch (DateTimeException exception){
            return false;
        }
    }
}