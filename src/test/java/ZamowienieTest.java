import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ZamowienieTest {

    @Test
    public void testBezArgumentow(){
        //given
        Zamowienie zamowienie = new Zamowienie();
        //when
        List<Pozycja> listaPozycji = new ArrayList<Pozycja>();
        Pozycja pozycja = new Pozycja("Mleko", 7, 2.50);
        Pozycja pozycja1 = new Pozycja("Masło", 2, 9.39);

        listaPozycji.add(pozycja);
        listaPozycji.add(pozycja1);
        zamowienie.setPozycje(listaPozycji);
        //then
        assertEquals(2, zamowienie.getPozycje().size());
    }

    @Test
    public void testZArgumentami(){
        //given
        List<Pozycja> listaPozycji = new ArrayList<Pozycja>();
        Pozycja pozycja = new Pozycja("Mleko", 7, 2.50);
        Pozycja pozycja1 = new Pozycja("Masło", 2, 9.39);

        listaPozycji.add(pozycja);
        listaPozycji.add(pozycja1);
        //when
        Zamowienie zamowienie = new Zamowienie(listaPozycji);
        //then
        assertEquals(2, zamowienie.getPozycje().size());
    }

    @Test
    public void testDodajPozycje(){
        //given
        Zamowienie zamowienie3 = new Zamowienie();
        //when
        Pozycja pozycja3 = new Pozycja("Miód", 4, 7.19);
        zamowienie3.dodajPozycje(pozycja3);
        //then
        assertEquals(1,zamowienie3.getPozycje().size());
    }

    @Test
    public void testDodajPozycjeNull(){
        //given
        Zamowienie zamowienie4 = new Zamowienie();
        //when
        zamowienie4.dodajPozycje(null);
        //then
        assertEquals(0,zamowienie4.getPozycje().size());
    }

    @Test
    public void testObliczanieWartosci(){
        //given
        Zamowienie zamowienie = new Zamowienie();
        Pozycja pozycja = new Pozycja("Mleko", 7, 2.50);
        Pozycja pozycja1 = new Pozycja("Masło", 2, 9.39);
        zamowienie.dodajPozycje(pozycja);
        zamowienie.dodajPozycje(pozycja1);
        //when
        double suma = zamowienie.obliczWartosc();
        //then
        assertEquals(Double.valueOf(pozycja.obliczWartosc()+ pozycja1.obliczWartosc()), Double.valueOf(suma));
    }
    @Test
    public void testWyswietlZamowienie(){
        //given
        Zamowienie zamowienie5 = new Zamowienie();
        Pozycja pozycja = new Pozycja("Mleko", 7, 2.50);
        Pozycja pozycja1 = new Pozycja("Masło", 2, 9.39);
        zamowienie5.dodajPozycje(pozycja);
        zamowienie5.dodajPozycje(pozycja1);
        //when

        //then
        assertTrue(zamowienie5.toString().contains("Razem"));
        assertTrue(zamowienie5.toString().contains("zł"));
        assertTrue(zamowienie5.toString().contains("Razem:"));

    }

    @Test
    public void testUsunPozycje(){
        //given
        Zamowienie zamowienie6 = new Zamowienie();
        Pozycja pozycja = new Pozycja("Mleko", 7, 2.50);
        Pozycja pozycja1 = new Pozycja("Masło", 2, 9.39);
        zamowienie6.dodajPozycje(pozycja);
        zamowienie6.dodajPozycje(pozycja1);
        //when
        zamowienie6.usunPozycje(1);
        //then
        assertEquals(1, zamowienie6.getPozycje().size());
        System.out.println(zamowienie6.getPozycje().get(0));
    }

    @Test
    public void testUsunPozycjeOZlymIndeksie(){
        //given
        Zamowienie zamowienie7 = new Zamowienie();
        Pozycja pozycja = new Pozycja("Mleko", 7, 2.50);
        Pozycja pozycja1 = new Pozycja("Masło", 2, 9.39);
        zamowienie7.dodajPozycje(pozycja);
        zamowienie7.dodajPozycje(pozycja1);
        //when
        zamowienie7.usunPozycje(2);
        //then
        assertEquals(2, zamowienie7.getPozycje().size());
    }

    @Test
    public void testEdytujPozycje(){

        //given
        Zamowienie zamowienie8 = new Zamowienie();
        Pozycja pozycja = new Pozycja("Mleko", 7, 2.50);
        Pozycja pozycja1 = new Pozycja("Masło", 2, 9.39);
        zamowienie8.dodajPozycje(pozycja);
        zamowienie8.dodajPozycje(pozycja1);
        //when
        zamowienie8.edytujPozycje(1, "Chleb", 3,1.50);
        //then
        Pozycja pozycja2 = zamowienie8.getPozycje().get(1);
        assertEquals("Chleb" ,pozycja2.getNazwaTowaru());
        assertEquals(3 ,pozycja2.getIleSztuk());
        assertEquals(Double.valueOf(1.50) ,Double.valueOf(pozycja2.getCena()));
    }

    @Test
    public void testDodawaniaIstniejacejPozycjiZNowaCena() {
        //given
        Zamowienie zamowienie9 = new Zamowienie();
        Pozycja pozycja = new Pozycja("chleb", 2, 2.50);
        Pozycja pozycja2 = new Pozycja("maslo", 4, 7.50);

        zamowienie9.dodajPozycje(pozycja);
        zamowienie9.dodajPozycje(pozycja2);

        //when
        Pozycja pozycja3 = new Pozycja("maslo", 3, 8.50);
        zamowienie9.dodajPozycje(pozycja3);

        //then
        assertEquals(3, zamowienie9.getPozycje().size());
        Pozycja pozycja4 = zamowienie9.getPozycje().get(1);
        assertEquals("maslo", pozycja4.getNazwaTowaru() );
        assertEquals(4, pozycja4.getIleSztuk());
    }

    @Test
    public void testRabatPonizej5Sztuk(){
        //given
        Pozycja pozycja5 = new Pozycja("Chleb", 4, 10.0);
        //when
        double rabat = pozycja5.obliczWartoscZRabatem();
        //then
        assertEquals(Double.valueOf(rabat), Double.valueOf(pozycja5.obliczWartosc()));
    }

    @Test
    public void testRabatPonizej10Sztuk(){
        //given
        Pozycja pozycja6 = new Pozycja("Chleb", 8, 10.0);
        //when
        double rabat = pozycja6.obliczWartoscZRabatem();
        //then
        assertEquals(Double.valueOf(rabat), Double.valueOf(pozycja6.obliczWartosc()*0.95));
    }

    @Test
    public void testRabatPonizej20Sztuk(){
        //given
        Pozycja pozycja7 = new Pozycja("Chleb", 15, 10.0);
        //when
        double rabat = pozycja7.obliczWartoscZRabatem();
        //then
        assertEquals(Double.valueOf(rabat), Double.valueOf(pozycja7.obliczWartosc()*0.9));
    }

    @Test
    public void testRabatPowyzej20Sztuk(){
        //given
        Pozycja pozycja8 = new Pozycja("Chleb", 23, 10.0);
        //when
        double rabat = pozycja8.obliczWartoscZRabatem();
        //then
        assertEquals(Double.valueOf(rabat), Double.valueOf(pozycja8.obliczWartosc()*0.85));
    }

}