import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class A_star_teleporter {
    Feld[][] Spielfeld;
    List<Pfad> Pfade = new ArrayList<>(); //Die Frontier in der die Pfade gespeichert werden
    boolean zuEnde;
    int zielX;  // X-Koordinate des Zielpunkts
    int zielY;  // Y-Koordinate des Zielpunkts

    public A_star_teleporter(Feld[][] feld) {
        Spielfeld = feld;
        getZielpunkt();
        Schritt1();
        while(!zuEnde)
        {
            if(!Pfade.isEmpty())
            {
                Schritt2();
            }
            else {
                zuEnde = true;
                System.out.println("Es konnte kein Zielzustand gefunden werden");
            }
        }
        HCI();


    }

    public void Schritt1()
    {
        // Schritt 1: Startkonten markieren und in die Warteschlange packen
        List<Feld> s = new ArrayList<>();
        for (Feld[] z : Spielfeld) {
            for (Feld f : z) {
                if (f._getAnzeige().equals("s")) {
                    s.add(f);
                    Pfade.add(new Pfad(s, 0, zielX, zielY));
                    f.setBetretbar(false);
                }
            }
        }
    }

    /*
    Bestimmt die Koordinaten des Zielpunktes für die Wegabschätzung
    */
    public void getZielpunkt()
    {
        for (Feld[] z : Spielfeld) {
            for (Feld f : z) {
                if (f._getAnzeige().equals("g"))
                {
                    zielX = f.getX();
                    zielY = f.getY();
                }
            }
        }
    }

    /*
    Die Methode HCI gibt das Labyrinth aus.
     */
    public void HCI() {
        for (Feld[] z : Spielfeld) {
            System.out.println("");
            for (Feld f : z) {
                System.out.print(f._getAnzeige());
            }
        }
    }

    public void Schritt2() {
        int tmpx = 1;
        int tmpy = 1;
        Collections.sort(Pfade);
        Pfad best = Pfade.get(0);
        Feld tmpFeld = best.getLetztesElement();
        if (tmpFeld._getAnzeige().equals("g")) {
            System.out.println("Der Zielzustand wurde gefunden");
            for(Feld f : best.vorgaenger)
            {
                f.setAnzeige("o");
            }
            zuEnde = true;
        } else if ((tmpFeld._getAnzeige().equals("1")) || (tmpFeld._getAnzeige().equals("2"))) {  // prüfen ob ein Teleporter betreten wurde.
            // Die beiden For-Schleifen durchlaufen einmal das gesamte Spielfeld
            for (Feld[] u : Spielfeld) {
                for (Feld k : u) {
                    if ((tmpFeld._getAnzeige().equals(k._getAnzeige())) && ((tmpFeld.getX() != k.getX()) || (tmpFeld._Y != k.getY()))) {  // Prüfen ob es sich bei dem Feld um den anderen Teleporter handelt
                        k.setBetretbar(false); // Jedes Feld kann nur einmal betreten werden, um Schleifen zu verhindern
                        tmpx = k.getX(); // Die Koordinaten werden zum bestimmten der Nachbarknoten benötigt.
                        tmpy = k.getY();
                    }
                }
            }
        } else {

            tmpx = tmpFeld.getX();
            tmpy = tmpFeld.getY();
            }
            Feld nach = Spielfeld[tmpx - 1][tmpy];
            if(nach.getBetretbar()) {
                if (!best.getVorgaenger().contains(nach)) {
                    nach.setBetretbar(false);
                    Pfad neu = new Pfad(best.getVorgaenger(), (best.getKosten() + 1),zielX,zielY);
                    neu.fuegeKnotenHinzu(nach);
                    Pfade.add(neu);
                }
            }
            Feld nach2 = Spielfeld[tmpx + 1][tmpy];
            if(nach2.getBetretbar()) {
                if (!best.getVorgaenger().contains(nach2)) {
                    nach.setBetretbar(false);
                    Pfad neu2 = new Pfad(best.getVorgaenger(), (best.getKosten() + 1),zielX,zielY);
                    neu2.fuegeKnotenHinzu(nach2);
                    Pfade.add(neu2);
                }
            }
            Feld nach3 = Spielfeld[tmpx][tmpy - 1];
            if(nach3.getBetretbar()) {
                if (!best.getVorgaenger().contains(nach3)) {
                    nach.setBetretbar(false);
                    Pfad neu3 = new Pfad(best.getVorgaenger(), (best.getKosten() + 1), zielX, zielY);
                    neu3.fuegeKnotenHinzu(nach3);
                    Pfade.add(neu3);
                }
            }
            Feld nach4 = Spielfeld[tmpx][tmpy + 1];
            if(nach4.getBetretbar()) {
                if (!best.getVorgaenger().contains(nach4)) {
                    nach.setBetretbar(false);
                    Pfad neu4 = new Pfad(best.getVorgaenger(), (best.getKosten() + 1),zielX,zielY);
                    neu4.fuegeKnotenHinzu(nach4);
                    Pfade.add(neu4);
                }
            }
            Pfade.remove(best);
        }
    }

