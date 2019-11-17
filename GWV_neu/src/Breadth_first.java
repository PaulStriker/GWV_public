import java.util.*;

/*
Idee: Wir markieren Knoten, indem wir die kleinbuchstaben im Array durch Großbuchstaben ersetzen
 */
public class Breadth_first {
    Queue <Feld> Warteschlange = new LinkedList<>();
    Feld [][] Spielfeld;
    boolean _zuEnde;

    public Breadth_first(Feld [][] Feld_Eingabe)
    {
        Spielfeld = Feld_Eingabe;
        Schritt1();
        while(!_zuEnde)
        {
            if(!Warteschlange.isEmpty())
            {
                Schritt2();
            }
            else {
                _zuEnde = true;
                System.out.println("Es konnte kein Goalstate gefunden werden");
            }

        }
        HCI();
    }
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
                    String tmp = f._getAnzeige();
                    tmp = tmp.toUpperCase();
                    f.setAnzeige(tmp);
                }
                l++;
            }
            i++;
            l = 0;
        }
    }

    public void Schritt2()
    {
        Feld f = Warteschlange.remove();

        if(f._getAnzeige().equals("g"))
        {
            System.out.println("Der Zielzustand wurde gefunden");
            _zuEnde = true;

            int i = 0;
            for(Feld a : f.get_vorgaenger())
            {
                i++;
                a.setAnzeige("'"+i+"'");

            }

        }
        else{
            int tmpx = f.getX();
            int tmpy = f.getY();
            List<Feld> tmplist = new ArrayList<>();

            Feld nach = Spielfeld[tmpx-1][tmpy];
            if(nach.getBetretbar())
            {
                Warteschlange.add(nach);
                nach.setBetretbar(false);
                tmplist = f.get_vorgaenger();
                tmplist.add(f);
                nach.set_vorgaenger(tmplist);
            }


            nach = Spielfeld[tmpx+1][tmpy];
            if(nach.getBetretbar())
            {
                Warteschlange.add(nach);
                nach.setBetretbar(false);
            }
            tmplist = f.get_vorgaenger(); // Die Felder bekommen alle eine Liste mit ihren Vorgängern, damit man später den ganzen Pfad erhält.
            tmplist.add(f);
            nach.set_vorgaenger(tmplist);

           nach = Spielfeld[tmpx][tmpy-1];
            if(nach.getBetretbar())
            {
                Warteschlange.add(nach);
                nach.setBetretbar(false);
            }
            tmplist = f.get_vorgaenger(); // Die Felder bekommen alle eine Liste mit ihren Vorgängern, damit man später den ganzen Pfad erhält.
            tmplist.add(f);
            nach.set_vorgaenger(tmplist);

           nach = Spielfeld[tmpx][tmpy+1];
            if(nach.getBetretbar())
            {
                Warteschlange.add(nach);
                nach.setBetretbar(false);
            }
            tmplist = f.get_vorgaenger(); // Die Felder bekommen alle eine Liste mit ihren Vorgängern, damit man später den ganzen Pfad erhält.
            tmplist.add(f);
            nach.set_vorgaenger(tmplist);

        }
    }

}
