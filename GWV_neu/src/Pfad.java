import java.util.LinkedList;
import java.util.List;

public class Pfad implements Comparable<Pfad>
{
    List <Feld> vorgaenger = new LinkedList<>();
    int Kosten;
    int Prognose;
    int Zielx;
    int Ziely;

    public Pfad(List<Feld> f, int kosten, int zielx, int ziely)
    {
        //vorgaenger = f;
        for(Feld e : f)
        {
            fuegeKnotenHinzu(e);
        }
        setKosten(kosten);
        Zielx = zielx;
        Ziely = ziely;
    }

    public List<Feld> getVorgaenger(){return vorgaenger;}
    public void setVorgaenger(List<Feld> f){vorgaenger =f;}
    public void fuegeKnotenHinzu(Feld f){vorgaenger.add(f);};
    public int getKosten(){return Kosten;}
    public void setKosten(int i){Kosten =i;}
    public int getHeuristik()
    {
        if(getLetztesElement() != null) {
            Prognose = Math.abs(Zielx - getLetztesElement().getX()) + Math.abs(Ziely - getLetztesElement().getY());
        }
        return Kosten+Prognose;
    }
    public Feld getLetztesElement(){
        Feld tmp = null;
       for(Feld f : vorgaenger)
       {
           tmp = f;
       }
       return tmp;
    }

    @Override
    public int compareTo(Pfad o) {
        return getHeuristik();
    }
}
