import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class depth_first_teleporter {
    Feld[][] _Spielfeld;
    Stack<Feld> _Stack = new Stack<>(); // Im Stack werden die Felder während der Suche gespeichert.
    boolean _zuEnde = false; // Der Boolean wird als Abburchbediengung für die Suche verwendet

    public depth_first_teleporter(Feld[][] Feld_Eingabe) {
        _Spielfeld = Feld_Eingabe;
        Schritt1();
        while (!_zuEnde) {
            if (!_Stack.empty()) {
                Schritt2(); // Der Schritt2 wird solange ausgeführt, bis der Stack leer ist oder der Zielzustand gefunden wurde.
            } else {
                _zuEnde = true; // Abbruch sollte der Stack leer sein und kein Zielzustand gefunden worden
                System.out.println("Es konnte kein Goalstate gefunden werden");
            }
        }
        HCI();

    }

    /*
    Gibt wieder das Labyrinth aus
     */
    public void HCI() {
        for (Feld[] z : _Spielfeld) {
            System.out.println("");
            for (Feld f : z) {
                System.out.print(f._getAnzeige());
            }
        }
    }

    /*
    Im ersten Schritt, wird der Startzustand gesucht und auf dem Stack gespeichert.
     */
    public void Schritt1() {
        int i = 0;
        int l = 0;
        for (Feld[] z : _Spielfeld) {
            for (Feld f : z) {
                if (f._getAnzeige().equals("s")) {
                    _Stack.push(f);
                    f.setBetretbar(false);
                }
                l++;
            }
            i++;
            l = 0;
        }
    }

    /*
    Im zweiten Schritt wird immer ein Element vom Stack genommen und geprüft, ob es sich um den Zielzustand handelt, sollte
    dies der Fall sein, wird die Suche beendet. Ansonsten werden die Nachbarkonten identifizert und auf dem Stack gespeichert.
     */
    public void Schritt2() {
    int tmpx = 0;
    int tmpy = 0;
        Feld f = _Stack.peek();
        if (f._getAnzeige().equals("g")) { //Prüfung, ob der Zielzustand gefunden wurde.
            System.out.println("Der Zielzustand wurde gefunden");
            _zuEnde = true;
            for(Feld a : f.get_vorgaenger())
            {
                a.setAnzeige("o");

            }
        } else {
            if ((f._getAnzeige().equals("1")) || (f._getAnzeige().equals("2"))) {  // prüfen ob ein Teleporter betreten wurde.
                // Die beiden For-Schleifen durchlaufen einmal das gesamte Spielfeld
                for (Feld[] u : _Spielfeld) {
                    for (Feld k : u) {
                        if ((f._getAnzeige().equals(k._getAnzeige())) && ((f.getX() != k.getX()) || (f._Y != k.getY()))) {  // Prüfen ob es sich bei dem Feld um den anderen Teleporter handelt
                            k.setBetretbar(false); // Jedes Feld kann nur einmal betreten werden, um Schleifen zu verhindern
                            tmpx = k.getX(); // Die Koordinaten werden zum bestimmten der Nachbarknoten benötigt.
                            tmpy = k.getY();
                        }
                    }
                }
            } else {
                tmpx = f.getX();
                tmpy = f.getY();
            }
            List<Feld> tmplist = new ArrayList<>();
                //Im folgenden werden die Nachbarfelder in der Queue gespeichert, sollten sie betretbar sein.
                Feld nach = _Spielfeld[tmpx - 1][tmpy];
                if (nach.getBetretbar()) {
                    _Stack.push(nach);
                    nach.setBetretbar(false);
                }
                 tmplist = f.get_vorgaenger(); // Die Felder bekommen alle eine Liste mit ihren Vorgängern, damit man später den ganzen Pfad erhält.
                 tmplist.add(f);
                 nach.set_vorgaenger(tmplist);

                nach = _Spielfeld[tmpx + 1][tmpy];
                if (nach.getBetretbar()) {
                    _Stack.push(nach);
                    nach.setBetretbar(false);
                }
                tmplist = f.get_vorgaenger(); // Die Felder bekommen alle eine Liste mit ihren Vorgängern, damit man später den ganzen Pfad erhält.
                tmplist.add(f);
                nach.set_vorgaenger(tmplist);

                nach = _Spielfeld[tmpx][tmpy - 1];
                if (nach.getBetretbar()) {
                    _Stack.push(nach);
                    nach.setBetretbar(false);
                }
                tmplist = f.get_vorgaenger(); // Die Felder bekommen alle eine Liste mit ihren Vorgängern, damit man später den ganzen Pfad erhält.
                tmplist.add(f);
                nach.set_vorgaenger(tmplist);

                nach = _Spielfeld[tmpx][tmpy + 1];
                if (nach.getBetretbar()) {
                    _Stack.push(nach);
                    nach.setBetretbar(false);
                }
                tmplist = f.get_vorgaenger(); // Die Felder bekommen alle eine Liste mit ihren Vorgängern, damit man später den ganzen Pfad erhält.
                tmplist.add(f);
                nach.set_vorgaenger(tmplist);

                if (f.equals(_Stack.peek())) {
                    Feld g = _Stack.pop();
                }

            }
        }

    }

