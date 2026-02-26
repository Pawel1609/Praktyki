
//STANY//
    const stany = {
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
        "México": "EM"
    };

//wyswietlDane//

    function wyswietlDane(imie, drugie_imie, nazwisko, drugieNazwisko, miasto, urodziny, plec_mezczyzna, kod) {
        const komunikat = "Nazwisko: " + nazwisko + "<br>" +
                        "Drugie nazwisko: " + drugieNazwisko + "<br>" +
                        "Imie: "+ imie + "<br>" +
                        "Drugie imie: " + drugie_imie + "<br>" +
                        "Data urodzenia: " + urodziny + "<br>" +
                        "Płeć: " + plec_mezczyzna + "<br>" +
                        "Stan: " + miasto + "<br>" +
                        "Kod CURP: " + kod;
    return komunikat;
    }

//spolgloski//

    function spolgloski(nazwisko, drugieNazwisko, imie) {
            let p8 = ""; // tu wpadnie spółgłoska nazwiska
            for (let i = 1; i < nazwisko.length; i++) {
                const samogloski = "AEIOUY";
                if (!samogloski.includes(nazwisko[i])) { 
                    p8 = nazwisko[i];
                    break;
                }
            }
            let p9 = ""; // spółgłoska drugiego nazwiska
            for (let i = 1; i < drugieNazwisko.length; i++) {
                const samogloski = "AEIOUY";
                if (!samogloski.includes(drugieNazwisko[i])) { 
                    p9 = drugieNazwisko[i];
                    break;
                }
            }
            let p10 = ""; // spółgłoska imienia
            for (let i = 1; i < imie.length; i++) {
                const samogloski = "AEIOUY";
                if (!samogloski.includes(imie[i])) { 
                    p10 = imie[i];
                    break;
                }
            }
    return (p8 + p9 + p10).toUpperCase();
    }

//liczba_17//

    function liczba_17 (data_urodzenia) {
        let obiektDaty = new Date(data_urodzenia);
        let rokPelny = obiektDaty.getFullYear();
        if (rokPelny >= 2000) { 
            p11 = "A";
        } else {
            p11 = "0"
        }
    return p11;
    }

//obliczanie_18_cyfry//

    function obliczanie_18_cyfry(kod17){
        let slownik = "0123456789ABCDEFGHIJKLMN&OPQRSTUVWXYZ";
        let suma = 0;

        for (let i = 0; i < 17; i++) {
            let wartosc = slownik.indexOf(kod17[i]);
            if (wartosc === -1) wartosc = 0;
            suma += wartosc * (18 - i);
        }
        let p12 = (10 - (suma % 10)) % 10;
        return p12
    }

//Data//

    function wylicz_cyfry_z_daty_urodzin(urodziny) {
        let obiektDaty = new Date(urodziny); // Zamieniamy tekst na "inteligentną" datę
        let rokPelny = obiektDaty.getFullYear(); // Pobiera pełne 2026
        let miesiacLiczba = obiektDaty.getMonth() + 1; // JS liczy miesiące od 0, więc dodajemy 1
        let dzienLiczba = obiektDaty.getDate();

        // Przygotowanie do kodu (zamiana na tekst i dodanie zera, jeśli miesiąc < 10)
        let rokDoKodu = rokPelny.toString().substring(2, 4); // Wycinamy końcówkę "26"
        let miesiacDoKodu = miesiacLiczba.toString().padStart(2, "0"); // padStart dodaje zero na początku
        let dzienDoKodu = dzienLiczba.toString().padStart(2, "0");

        let dataSklejona = rokDoKodu + miesiacDoKodu + dzienDoKodu;

        return dataSklejona
    }

//pierwsze_litery//

    function pierwsze_litery(nazwisko, drugieNazwisko, imie, drugieimieZFormularza) {
        if (nazwisko[0]) {
            p1 = nazwisko[0];
        } else {
            p1 = "X";
        }
        if (drugieNazwisko[0]) {
            p2 = drugieNazwisko[0];
        } else {
            p2 = "X";
        }
        if (imie[0]) {
            p3 = imie[0];
        } else {
            p3 = "X";
        }
        if (drugieimieZFormularza[0]) {
            p4 = drugieimieZFormularza[0];
        } else {
            p4 = "X";
        }
    return (p1 + p2 + p3 + p4).toUpperCase();
    }

//generujeCURP//

    function generujeCURP(nazwisko, drugieNazwisko, imie, drugieimieZFormularza, plec, miasto, urodziny, data_urodzenia) {
        const Pierwsze = pierwsze_litery(nazwisko, drugieNazwisko, imie, drugieimieZFormularza);
        const Data_uzytkownika = wylicz_cyfry_z_daty_urodzin(urodziny);
        const Plec_uzytkownika = plec; 
        const Miasto_uzytkownika = stany[miasto] || "NE";
        const Obliczanie_spolglosek = spolgloski(imie, nazwisko, drugieNazwisko);
        const Liczba = liczba_17(data_urodzenia);
        const kod17 = (Pierwsze + Data_uzytkownika + Plec_uzytkownika + Miasto_uzytkownika + Obliczanie_spolglosek + Liczba).toUpperCase();
        const p12 = obliczanie_18_cyfry(kod17);
        return kod17 + p12;
    }
