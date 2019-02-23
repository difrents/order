import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Zamowienie {

    private List<Pozycja> pozycje = new ArrayList<Pozycja>();

    public void dodajPozycje(Pozycja pozycja) {


        if (pozycja != null) {
            boolean CzyZnaleziono = false;
            for (Pozycja pozycja1: pozycje){
                if(pozycja1.getNazwaTowaru().equals(pozycja.getNazwaTowaru()) && pozycja1.getCena()==pozycja.getCena()){
                    pozycja1.setIleSztuk(pozycja1.getIleSztuk()+pozycja.getIleSztuk());
                    CzyZnaleziono = true;
                break;
                }

            }
            if(!CzyZnaleziono){
                pozycje.add(pozycja);
            }
        }
    }


    public double obliczWartosc() {
        double suma = 0;
        for(Pozycja pozycja: pozycje){
            suma = suma + pozycja.obliczWartosc();
        }
        return suma;
    }


    public String toString(){
        String pozycyjka;
        String out = "Zamówienie:\n";
        for (Pozycja pozycja: pozycje){
                out = out + pozycja.toString() +"\n";
        }
            out = out + "Razem: " + String.format("%10.2f zł", obliczWartosc());
            out = out + "Rabat: " + String.format("%.2f zł", obliczWartosc() - obliczWartoscZRabatem());
                return out;
    }

    public void usunPozycje(int indeks){
            try {
                pozycje.remove(indeks);
            }catch (IndexOutOfBoundsException e){
                System.out.println("Brak pozycji o takim indeksie.");
            }
            }

    public void edytujPozycje(int indeks, String nazwaTowaru, int ilosc, double cena){
        try{
            Pozycja edytowana_pozycja = pozycje.get(indeks);
            edytowana_pozycja.setNazwaTowaru(nazwaTowaru);
            edytowana_pozycja.setIleSztuk(ilosc);
            edytowana_pozycja.setCena(cena);
        }catch (IndexOutOfBoundsException e){
            System.out.println("Brak pozycji o takim indeksie.");
        }
    }

    public double obliczWartoscZRabatem() {
        double suma = 0;
        for (Pozycja pozycja : pozycje) {
            suma = suma + pozycja.obliczWartoscZRabatem();
        }
        return suma;
    }

}
