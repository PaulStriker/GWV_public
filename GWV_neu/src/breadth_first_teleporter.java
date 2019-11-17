import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class breadth_first_teleporter {
    Queue<Feld> Warteschlange = new LinkedList<>();
    Feld [][] Spielfeld;
    boolean _zuEnde;
    int knotenNr = 0;

    public breadth_first_teleporter(Feld [][] Feld_Eingabe)
    {
        Spielfeld = Feld_Eingabe;
        Schritt1();
        while(!_zuEnde) // Die While-Schleife führt die Suche solange fort, bis ein Zielzustand gefunden wurde oder die Queue leer ist.
        {
            if(!Warteschlange.isEmpty())
            {
                Schritt2();
            }
            else{ // Abbruch, wenn der Zielzustand nicht gefunden wurde.
                _zuEnde = true;
                System.out.println("Es konnte kein Goalstate gefunden werden");
            }

        }
        HCI();
        System.out.println("\n Knotenmenge: " + knotenNr);
    }

    /*
    Die Methode HCI() gibt nur einmal das Labyrinth aus.
     */
    public void HCI()
    {
        for(Feld[] z : Spielfeld) {
            System.out.println("");
            for (Feld f : z) {
                System.out.print(f._getAnzeige());
            }
        }
    }

    public void Schritt1()
    {
        int i = 0;
        int l = 0;
        // Schritt 1: Startkonten markieren und in die Warteschlange packen
        for(Feld[] z : Spielfeld )
        {
            for(Feld f : z)
            {
                if(f._getAnzeige().equals("s"))
                {
                    Warteschlange.add(f);
                    f.setBetretbar(false);
                    knotenNr++;
                }
                l++;
            }
            i++;
            l = 0;
        }
    }

    public void Schritt2()
    {
        int tmpx = 0;
        int tmpy = 0;
        Feld f = Warteschlange.remove(); //Vorderstes Element wird von der Warteschlange eentfernt.

        if(f._getAnzeige().equals("g")) // Prüfung ob es sich um den Zielzustand handelt
        {
            System.out.println("Der Zielzustand wurde gefunden");
            _zuEnde = true;
            for(Feld a : f.get_vorgaenger())
            {
                a.setAnzeige("o");

            }
        }
        else{
            if((f._getAnzeige().equals("1")) || (f._getAnzeige().equals("2"))) // prüfen ob ein Teleporter betreten wurde.
            {
                // Die beiden For-Schleifen durchlaufen einmal das gesamte Spielfeld
                for(Feld[] u : Spielfeld)
                {
                    for(Feld k : u)
                    {
                        if((f._getAnzeige().equals(k._getAnzeige()))&&((f.getX()!= k.getX())||(f._Y != k.getY()))) // Prüfen ob es sich bei dem Feld um den anderen Teleporter handelt
                        {
                            k.setBetretbar(false); // Jedes Feld kann nur einmal betreten werden, um Schleifen zu verhindern
                            tmpx = k.getX(); // Die Koordinaten werden zum bestimmten der Nachbarknoten benötigt.
                            tmpy = k.getY();
                        }
                    }
                }
            }
            else {
                tmpx = f.getX();
                tmpy = f.getY();
            }
            List<Feld> tmplist = new ArrayList<>();
            //Im folgenden werden die Nachbarfelder in der Queue gespeichert, sollten sie betretbar sein.
            Feld nach = Spielfeld[tmpx-1][tmpy];
            if(nach.getBetretbar())
            {
                Warteschlange.add(nach);
                nach.setBetretbar(false); // Jedes Feld soll nur einmal betreten werden um Schleifen zu verhindern.
            }
            tmplist = f.get_vorgaenger(); // Die Felder bekommen alle eine Liste mit ihren Vorgängern, damit man später den ganzen Pfad erhält.
            tmplist.add(f);
            knotenNr++;
            nach.set_vorgaenger(tmplist);

            nach = Spielfeld[tmpx+1][tmpy];
            if(nach.getBetretbar())
            {
                Warteschlange.add(nach);
                nach.setBetretbar(false);
            }
            tmplist = f.get_vorgaenger();
            tmplist.add(f);
            knotenNr++;
            nach.set_vorgaenger(tmplist);

            nach = Spielfeld[tmpx][tmpy-1];
            if(nach.getBetretbar())
            {
                Warteschlange.add(nach);
                nach.setBetretbar(false);
            }
            tmplist = f.get_vorgaenger();
            tmplist.add(f);
            knotenNr++;
            nach.set_vorgaenger(tmplist);

            nach = Spielfeld[tmpx][tmpy+1];
            if(nach.getBetretbar())
            {
                Warteschlange.add(nach);
                nach.setBetretbar(false);
            }
            tmplist = f.get_vorgaenger();
            tmplist.add(f);
            knotenNr++;
            nach.set_vorgaenger(tmplist);

        }
    }

}
