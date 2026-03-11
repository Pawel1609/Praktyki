import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WalidatorCurpTest {

    @Test
    void sprawdzZnakiSpolgloskiCyfry() {
        WalidatorCurp walidator = new WalidatorCurp();
        assertTrue(walidator.sprawdzZnakiSpolgloskiCyfry("GALM951231HDFRRN06"));
        assertFalse(walidator.sprawdzZnakiSpolgloskiCyfry("1323WADWH14565NA"));
    }

    @Test
    void sprawdzDate() {
        WalidatorCurp walidator = new WalidatorCurp();
        assertTrue(walidator.sprawdzDate("GALM951231HDFRRN06"));
        assertFalse(walidator.sprawdzDate("GALM951331HDFRRN06"));
        assertFalse(walidator.sprawdzDate("GALM951232HDFRRN06"));
    }

    @Test
    void sprawdzCyfreKoncowa() {
        WalidatorCurp walidator = new WalidatorCurp();
        assertTrue(walidator.sprawdzCyfreKoncowa("GALM951231HDFRRN03"));
        assertFalse(walidator.sprawdzCyfreKoncowa("GALM951231HDFRRN00"));
    }
    @Test
    void sprawdzDateLataPrzestepne() {
        WalidatorCurp walidator = new WalidatorCurp();
        assertTrue(walidator.sprawdzDate("GALM240229HDFRRN06"));
        assertFalse(walidator.sprawdzDate("GALM230229HDFRRN06"));
    }

    @Test
    void sprawdzDateMiesiace30Dni() {
        WalidatorCurp walidator = new WalidatorCurp();
        assertFalse(walidator.sprawdzDate("GALM240431HDFRRN06"));
    }
    @Test
    void sprawdzZnakiBlednaPlec() {
        WalidatorCurp walidator = new WalidatorCurp();
        assertFalse(walidator.sprawdzZnakiSpolgloskiCyfry("GALM951231ZDFRRN06"));
    }

    @Test
    void sprawdzZnakiSpecjalne() {
        WalidatorCurp walidator = new WalidatorCurp();
        assertFalse(walidator.sprawdzZnakiSpolgloskiCyfry("GAŁM951231HDFRĄN06"));
    }
}