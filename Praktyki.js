
function wyswietlDane(imie, drugie_imie, nazwisko, drugie_nazwisko, miasto, urodziny, plec_mezczyzna, kod) {
    const komunikat = "Nazwisko: " + nazwisko + "<br>" +
                      "Drugie nazwisko: " + drugie_nazwisko + "<br>" +
                      "Imie: "+ imie + "<br>" +
                      "Drugie imie: " + drugie_imie + "<br>" +
                      "Data urodzenia: " + urodziny + "<br>" +
                      "Płeć: " + plec_mezczyzna + "<br>" +
                      "Stan: " + miasto + "<br>" +
                      "Kod CURP: " + kod;

    document.getElementById("wynik").innerHTML = komunikat;
}

function generujeCURP(nazwisko, drugieNazwisko, imie, drugieimieZFormularza, plec, miasto, data) {
        let pie_lit_nazwiska = nazwisko[0]
        if (nazwisko[0]) {
            p1 = nazwisko[0];
        } else {
            p1 = "X";
        }
        let pie_lit_dru_nazwiska = drugieNazwisko[0];
        if (drugieNazwisko[0]) {
            p2 = drugieNazwisko[0];
        } else {
            p2 = "X";
        }
        let pie_lit_imienia = imie[0]
        if (imie[0]) {
            p3 = imie[0];
        } else {
            p3 = "X";
        }
        let pie_lit_dru_imienia = drugieimieZFormularza[0]
        if (drugieimieZFormularza[0]) {
            p4 = drugieimieZFormularza[0];
        } else {
            p4 = "X";
        }
            let wynik_curp = (p1 + p2 + p3 + p4)
        document.getElementById("wynik").innerHTML = "Początek CURP: " + wynik_curp;
    
//AI NAPISALO COS O SPOLGLOSKACH ITP//
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
//DATA OD AI//
        if (!dataZInputa) return alert("Wybierz datę!");

        let obiektDaty = new Date(dataZInputa); // Zamieniamy tekst na "inteligentną" datę

        let rokPelny = obiektDaty.getFullYear(); // Pobiera pełne 2024
        let miesiacLiczba = obiektDaty.getMonth() + 1; // JS liczy miesiące od 0, więc dodajemy 1
        let dzienLiczba = obiektDaty.getDate();

        // Przygotowanie do kodu (zamiana na tekst i dodanie zera, jeśli miesiąc < 10)
        let rokDoKodu = rokPelny.toString().substring(2, 4); // Wycinamy końcówkę "24"
        let miesiacDoKodu = miesiacLiczba.toString().padStart(2, "0"); // padStart dodaje zero na początku
        let dzienDoKodu = dzienLiczba.toString().padStart(2, "0");

        let dataSklejona = rokDoKodu + miesiacDoKodu + dzienDoKodu;
//17//
        let p11 = ""; 
        if (rokPelny >= 2000) { 
            p11 = "A";
        } else {
            p11 = "0"
        }
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
        let kodStanu = stany[miastoZFormularza] || "NE"; 
        if (document.getElementById("plec_mezczyzna").checked) {
            plec = "H"; // Hombre (Mężczyzna)
        } else {
            plec = "M"; // Mujer (Kobieta)
        }
        // OBLICZANIE 18. CYFRY
        let kod17 = (p1 + p2 + p3 + p4 + dataSklejona + plec + kodStanu + p8 + p9 + p10 + p11).toUpperCase();
        let slownik = "0123456789ABCDEFGHIJKLMN&OPQRSTUVWXYZ";
        let suma = 0;

        for (let i = 0; i < 17; i++) {
            let wartosc = slownik.indexOf(kod17[i]);
            if (wartosc === -1) wartosc = 0;
            suma += wartosc * (18 - i);
        }
        let p12 = (10 - (suma % 10)) % 10;
        
        return kod17 + p12;
    }
    