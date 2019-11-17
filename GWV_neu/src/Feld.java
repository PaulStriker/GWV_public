import java.util.ArrayList;
import java.util.List;

/*
Die Klasse Feld beschreibt die Objekte, welche wir im Array abspeichern.
 */
public class Feld
{
    boolean _betretbar = true;
    int _X;
    int _Y;
    String _Anzeige;
    int Kosten;
  //  boolean _teleporter;
    List<Feld> _vorgaenger = new ArrayList<>();
    /*
    Konstruktor
     */
    public Feld(int a, int b, String s)
    {
        _X = a;
        _Y = b;
        _Anzeige = s;
        if(s.equals("X"))
        {
            _betretbar = false;
        }
    }

    public String _getAnzeige()
    {
        return _Anzeige;
    }

    public int getX()
    {
        return _X;
    }

    public int getY()
    {
        return _Y;
    }

    public void setAnzeige(String a)
    {
        _Anzeige = a;
    }

    public boolean getBetretbar()
    {
        return _betretbar;
    }

    public void setBetretbar(boolean b)
    {
        _betretbar = b;
    }

   // public boolean getTeleporter() {return _teleporter;}

    // public void setTeleporter(boolean b){_teleporter= b;}

    public List<Feld> get_vorgaenger(){return _vorgaenger;}

    public void set_vorgaenger(List<Feld> b){_vorgaenger = b;}

    public void setKosten(int i){ Kosten = i;}

    public int getKosten(){return Kosten;}
}
