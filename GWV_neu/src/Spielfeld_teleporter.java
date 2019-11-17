import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Spielfeld_teleporter {
    int X;
    int Y;
    String zeile;
    int i = 0;
    Feld [] [] Spielfeld;
    int l = 0;
    int laenge;

    public Spielfeld_teleporter()
    {

        try {
            groessenBestimmung();
            Spielfeld = new Feld [X] [Y];
            LeseEin();
        }
        catch(IOException E){
            System.out.println("Es gibt ein IO-Problem");
        }
    }
    public void LeseEin() throws IOException
    {
        FileReader Reader = new FileReader("blatt4_environment_b.txt");
        BufferedReader BReader = new BufferedReader(Reader);

        for(i = 0; i < X; i++)
        {
            zeile = BReader.readLine();
            System.out.println(zeile);
            for (l = 0; l < zeile.length(); l++)
            {
                Spielfeld[i][l] = new Feld(i,l,""+zeile.charAt(l));
                if((""+zeile.charAt(l)).equals("x"))
                {
                    Spielfeld[i][l].setBetretbar(false);
                }
                if(((""+ zeile.charAt(1)).equals("1"))||(""+ zeile.charAt(1)).equals("2"))
                {
                    //Spielfeld[i][l].setTeleporter(true);
                }
            }
        }
    }

    public void Kontrollmethode()
    {
        for(int i =0; i < X; i++)
        {
            System.out.println("");
            for(int k=0; k < Y; k++)
            {
                System.out.print(Spielfeld[i][k]._getAnzeige());
            }
        }
    }

    public void groessenBestimmung() throws IOException
    {
        int zeilen = 0;
        FileReader Reader3 = new FileReader("blatt4_environment_b.txt");
        BufferedReader BReader3 = new BufferedReader(Reader3);

        while(BReader3.readLine() != null)
        {
            zeilen ++;

        }
        X = zeilen;

        FileReader Reader2 = new FileReader("blatt4_environment_b.txt");
        BufferedReader BReader2 = new BufferedReader(Reader2);
        zeile =  BReader2.readLine();
        Y = zeile.length();

    }
}
