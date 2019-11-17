import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
public class depth_first
{
    Feld[][] _Spielfeld;
    Stack<Feld> _Stack = new Stack<>();
    boolean _zuEnde = false;
    int knotenNr = 0;

    public depth_first(Feld[][] Feld_Eingabe)
    {
        _Spielfeld = Feld_Eingabe;
        Schritt1();
        while(!_zuEnde)
        {
            if(!_Stack.empty())
            {
                Schritt2();
            }
            else
            {
                _zuEnde = true;
                System.out.println("Es konnte kein Goalstate gefunden werden");
            }
        }
        HCI();
        System.out.println("\n Knotenmenge: " + knotenNr);
    }

    public void HCI()
    {
        for(Feld[] z : _Spielfeld) {
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
        for(Feld[] z : _Spielfeld )
        {
            for(Feld f : z)
            {
                if(f._getAnzeige().equals("s"))
                {
                    _Stack.push(f);
                   f.setBetretbar(false);
                }
                l++;
            }
            i++;
            l = 0;
        }
    }

    public void Schritt2(){
        Feld f = _Stack.peek();
        if(f._getAnzeige().equals("g"))
        {
            System.out.println("Der Zielzustand wurde gefunden");
            _zuEnde = true;
            for(Feld a : f.get_vorgaenger())
            {
                a.setAnzeige("o");

            }

        }
        else{
            int tmpx = f.getX();
            int tmpy = f.getY();
            List<Feld> tmplist = new ArrayList<>();

            Feld nach = _Spielfeld[tmpx-1][tmpy];
            if(nach.getBetretbar())
            {
                _Stack.push(nach);
                nach.setBetretbar(false);
            }
            tmplist = f.get_vorgaenger();
            tmplist.add(f);
            knotenNr++;
            nach.set_vorgaenger(tmplist);

            nach = _Spielfeld[tmpx+1][tmpy];
            if(nach.getBetretbar())
            {
                _Stack.push(nach);
                nach.setBetretbar(false);
            }
            tmplist = f.get_vorgaenger();
            tmplist.add(f);
            knotenNr++;
            nach.set_vorgaenger(tmplist);

            nach = _Spielfeld[tmpx][tmpy-1];
            if(nach.getBetretbar())
            {
                _Stack.push(nach);
                nach.setBetretbar(false);
            }
            tmplist = f.get_vorgaenger();
            tmplist.add(f);
            knotenNr++;
            nach.set_vorgaenger(tmplist);

            nach = _Spielfeld[tmpx][tmpy+1];
            if(nach.getBetretbar())
            {
                _Stack.push(nach);
                nach.setBetretbar(false);
            }
            tmplist = f.get_vorgaenger();
            tmplist.add(f);
            knotenNr++;
            nach.set_vorgaenger(tmplist);

            if(f.equals(_Stack.peek()))
            {
                Feld g = _Stack.pop();
            }

        }
    }

}

