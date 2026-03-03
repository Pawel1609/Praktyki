import java.time.LocalDate;

public class CurpGenerator {
   /* enum stany = (
           "Aguascalientes": "AG",
            "Baja California Norte": "BC",
            "Baja California Sur": "BS",
            "Chihuahua": "CH",
            "Colima": "CL",
            "Campeche": "CM",
            "Coahuila": "CO",
            "Chiapas": "CS",
            "Distrito Federal": "DF",
            "Durango": "DG",
            "Guerrero": "GR",
            "Guanajuato": "GT",
            "Hidalgo": "HG",
            "Jalisco": "JA",
            "Michoacan": "MI",
            "Morelos": "MO",
            "Nayarit": "NA",
            "Nuevo Leon": "NL",
            "Oaxaca": "OA",
            "Puebla": "PU",
            "Quintana Roo": "QR",
            "Queretaro": "QT",
            "Sinaloa": "SI",
            "San Luis Potosi": "SL",
            "Sonora": "SO",
            "Tabasco": "TB",
            "Tlaxcala": "TL",
            "Tamaulipas": "TM",
            "Veracruz": "VE",
            "Yucatan": "YU",
            "Zacatecas": "ZA",
            "México": "EM",
            "NE": "NE" ); */
    enum Stany {
        AG,
       BC,
       BS,
       CH,
       CL,
       CM,
       CO,
       CS,
       DF,
       DG,
       GR,
       GT,
       HG,
       JA,
       MI,
       MO,
       NA,
       NL,
       OA,
       PU,
       QR,
       QT,
       SI,
       SL,
       SO,
       TB,
       TL,
       TM,
       VE,
       YU,
       ZA,
       EM,
       NE
   }

   String slownik = "0123456789ABCDEFGHIJKLMN&OPQRSTUVWXYZ";
   String samogloski = "AEIOUY";

    String pierwszeLitery(String nazwisko, String drugieNazwisko, String imie, String drugieimieZFormularza) {
        char p1;
        char p2;
        char p3;
        char p4;
        if (nazwisko.length()>0) {
             p1 = nazwisko.charAt(0);
        } else {
             p1 = 'X';
        }
        if (drugieNazwisko.length()>0) {
             p2 = drugieNazwisko.charAt(0);
        } else {
            p2 = 'X';
        }
        if (imie.length()>0) {
             p3 = imie.charAt(0);
        } else {
             p3 = 'X';
        }
        if (drugieimieZFormularza.length()>0) {
             p4 = drugieimieZFormularza.charAt(0);
        } else {
             p4 = 'X';
        }
        return ("" + p1 + p2 + p3 + p4).toUpperCase();
    }
    String wyliczCyfryZDatyUrodzin(LocalDate urodziny) {
        Integer rokPelny = urodziny.getYear(); // Pobiera pełne 2026
        Integer miesiacLiczba = urodziny.getMonthValue(); // JS liczy miesiące od 0, więc dodajemy 1
        Integer dzienLiczba = urodziny.getDayOfMonth();
        // Przygotowanie do kodu (zamiana na tekst i dodanie zera, jeśli miesiąc < 10)
        String rokDoKodu = rokPelny.toString().substring(2, 4); // Wycinamy końcówkę "26"

        String miesiacDoKodu;
        if (miesiacLiczba < 10) {
            miesiacDoKodu = "0" + miesiacLiczba;
        } else {
            // POPRAWKA: Konwersja liczby na String
            miesiacDoKodu = miesiacLiczba.toString();
        }

        String dzienDoKodu;
        if (dzienLiczba < 10) {
            dzienDoKodu = "0" + dzienLiczba;
        } else {
            dzienDoKodu = dzienLiczba.toString();
        }

        return (rokDoKodu + miesiacDoKodu + dzienDoKodu);
    }

    String znajdzSpolgloske(String tekst) {
        for (char i = 1; i < tekst.length(); i++) {
            Character litera = tekst.charAt(i);
            if (!samogloski.contains(litera.toString())) {
                return litera.toString();
            }
        }
        return "X";
    }

    String spolgloski(String nazwisko,String drugieNazwisko,String imie) {
        String pierwszaSpolgloskaNazwiska = znajdzSpolgloske(nazwisko);
        String pierwszaSpolgloskaDrugiegoNazwiska = znajdzSpolgloske(drugieNazwisko);
        String pierwszaSpolgloskaImienia = znajdzSpolgloske(imie);

        return pierwszaSpolgloskaNazwiska + pierwszaSpolgloskaDrugiegoNazwiska + pierwszaSpolgloskaImienia;
    }

    String sprawdzWiek (LocalDate data_urodzenia) {
        int rokPelny = data_urodzenia.getYear();
        String p11;
        if (rokPelny >= 2000) {
            p11 = "A";
        } else {
            p11 = "0";
        }
        return p11;
    }

    String obliczanie18Cyfry(String kod17){
        int suma = 0;

        for (char i = 0; i < 17; i++) {
            int wartosc = slownik.indexOf(kod17.charAt(i));
            if (wartosc == -1) wartosc = 0;
            suma += wartosc * (18 - i);
        }
        int cyfraKontrolna = (10 - (suma % 10)) % 10;
        return String.valueOf(cyfraKontrolna);
    }

    String generujeCURP(String nazwisko, String drugieNazwisko, String imie, String drugieimieZFormularza, char plec, Stany miasto, LocalDate urodziny){
        String pierwsze = pierwszeLitery(nazwisko, drugieNazwisko, imie, drugieimieZFormularza);
        String dataUzytkownika = wyliczCyfryZDatyUrodzin(urodziny);
        String miastoUzytkownika = miasto.name();
        String obliczanieSpolglosek = spolgloski(imie, nazwisko, drugieNazwisko);
        String liczba = sprawdzWiek(urodziny);
        String kod17 = (pierwsze + dataUzytkownika + plec + miastoUzytkownika + obliczanieSpolglosek + liczba).toUpperCase();
        String cyfraKontrolna = obliczanie18Cyfry(kod17);

        return kod17 + cyfraKontrolna;
    }
}
