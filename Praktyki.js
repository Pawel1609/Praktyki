
//Wartość Globalna//
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
        "México": "EM",
        "NE": "NE"
    };

    const slownik = "0123456789ABCDEFGHIJKLMN&OPQRSTUVWXYZ";

    const samogloski = "AEIOUY";

    function przygotujKomunikat(imie, drugie_imie, nazwisko, drugieNazwisko, miasto, urodziny, plec, kod) {
        const komunikat = "Nazwisko: " + nazwisko + "<br>" +
                        "Drugie nazwisko: " + drugieNazwisko + "<br>" +
                        "Imie: "+ imie + "<br>" +
                        "Drugie imie: " + drugie_imie + "<br>" +
                        "Data urodzenia: " + urodziny + "<br>" +
                        "Płeć: " + plec + "<br>" +
                        "Stan: " + miasto + "<br>" +
                        "Kod CURP: " + kod;
    return komunikat;
    }


    function znajdzSpolgloske(tekst) {
        
        for (let i = 1; i < tekst.length; i++) {
            let litera = tekst[i].toUpperCase();
            if (!samogloski.includes(litera)) { 
                return litera; 
            }
        }
        return "X";
    }

    function spolgloski(nazwisko, drugieNazwisko, imie) {
        const pierwszaSpolgloskaNazwiska = znajdzSpolgloski(nazwisko);
        const pierwszaSpolgloskaDrugiegoNazwiska = znajdzSpolgloski(drugieNazwisko);
        const pierwszaSpolgloskaImienia = znajdzSpolgloski(imie);
    
        return pierwszaSpolgloskaNazwiska + pierwszaSpolgloskaDrugiegoNazwiska + pierwszaSpolgloskaImienia;
    }


    function sprawdzWiek (data_urodzenia) {
        let obiektDaty = new Date(data_urodzenia);
        let rokPelny = obiektDaty.getFullYear();
        let p11;
        if (rokPelny >= 2000) { 
            p11 = "A";
        } else {
            p11 = "0"
        }
    return p11;
    }


    function obliczanie18Cyfry(kod17){
        let suma = 0;

        for (let i = 0; i < 17; i++) {
            let wartosc = slownik.indexOf(kod17[i]);
            if (wartosc === -1) wartosc = 0;
            suma += wartosc * (18 - i);
        }
        let cyfraKontrolna = (10 - (suma % 10)) % 10;
        return cyfraKontrolna
    }


    function wyliczCyfryZDatyUrodzin(urodziny) {
        let obiektDaty = new Date(urodziny); // Zamieniamy tekst na "inteligentną" datę
        let rokPelny = obiektDaty.getFullYear(); // Pobiera pełne 2026
        let miesiacLiczba = obiektDaty.getMonth() + 1; // JS liczy miesiące od 0, więc dodajemy 1
        let dzienLiczba = obiektDaty.getDate();

        // Przygotowanie do kodu (zamiana na tekst i dodanie zera, jeśli miesiąc < 10)
        let rokDoKodu = rokPelny.toString().substring(2, 4); // Wycinamy końcówkę "26"
        let miesiacDoKodu = miesiacLiczba.toString().padStart(2, "0"); // padStart dodaje zero na początku
        let dzienDoKodu = dzienLiczba.toString().padStart(2, "0");

        return (rokDoKodu + miesiacDoKodu + dzienDoKodu);
    }

    function pierwszeLitery(nazwisko, drugieNazwisko, imie, drugieimieZFormularza) {
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


    function generujeCURP(nazwisko, drugieNazwisko, imie, drugieimieZFormularza, plec, miasto, urodziny) {
        const pierwsze = pierwszeLitery(nazwisko, drugieNazwisko, imie, drugieimieZFormularza);
        const dataUzytkownika = wyliczCyfryZDatyUrodzin(urodziny);
        const miastoUzytkownika = stany[miasto];
        const obliczanieSpolglosek = spolgloski(imie, nazwisko, drugieNazwisko);
        const liczba = sprawdzWiek(urodziny);
        const kod17 = (pierwsze + dataUzytkownika + plec + miastoUzytkownika + obliczanieSpolglosek + liczba).toUpperCase();
        const cyfraKontrolna = obliczanie18Cyfry(kod17);
        return kod17 + cyfraKontrolna;
    }
