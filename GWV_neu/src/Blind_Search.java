import java.io.*;

public class Blind_Search {

    public static void main(String[]args)
    {
        //Spielfeld Feld1 = new Spielfeld();
        //Spielfeld Feld2 = new Spielfeld();
        //Feld1.Kontrollmethode();
        //Breadth_first Suche1 = new Breadth_first(Feld1.Spielfeld);
        //depth_first Suche2 = new depth_first(Feld2.Spielfeld);

        //Spielfeld_teleporter Feld3 = new Spielfeld_teleporter();
        //breadth_first_teleporter Suche3 = new breadth_first_teleporter(Feld3.Spielfeld);
        //Spielfeld_teleporter Feld4 = new Spielfeld_teleporter();
        //depth_first_teleporter Suche4 = new depth_first_teleporter(Feld4.Spielfeld);

       // Spielfeld Feld5 = new Spielfeld();
       // A_star Suche5 = new A_star(Feld5.Spielfeld);
          Spielfeld_teleporter Feld6 = new Spielfeld_teleporter();
          A_star_teleporter Suche6 = new A_star_teleporter(Feld6.Spielfeld);

    }

}
