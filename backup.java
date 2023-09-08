package artiner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static int length;
    public static double sum = 0.0;
    public static int tage=0;
    public static int Brake = 0;
    public static void main(String args[]) throws Exception
    {
        Output ob=new Output();
        ob.Banner();
        String outputformat = " ", filter = " ", firstdate = " ", lastdate = " ", config_pfad = " ", replacement_file = "replacement.txt", inputfile = "Juni.csv";
        int countpause = 0, pauseonly = 0, vacation_days = 0;
        String[][] Repl = { {"null"} };
        if (args.length == 0) System.exit(1);
        for(int i = 0; i < args.length; i++)
        {

            switch (args[i].charAt(0))
             {
                case 'C':
                    config_pfad = args[i].substring(1);
                    break;
                case 'i':
                    inputfile = args[i].substring(1);
                    break;
                case 'p':
                    pauseonly = 1;
                    filter = "Pause";
                    break;
                case 'o':
                    outputformat = args[i].substring(1);
                    break;
                case 'f':
                    firstdate = args[i].substring(1);
                    break;
                case 'l':
                    lastdate = args[i].substring(1);
                    break;
                case 'c':
                    countpause = 1;
                    break;
                case 't':
                    filter = args[i].substring(1);
                    break;
                case 'v':
                    vacation_days = Integer.parseInt(args[i].substring(1));
                    break;
                case 'r':
                    replacement_file = args[i].substring(1);
                    break;
            }
        }
        if (config_pfad != " ")
         {

            String[][] conf = FileScanner(config_pfad, "=");
            for(int i = 0; i < conf.length; i++)
                switch (conf[i][0].charAt(0))
                 {
                    case 'v':
                        vacation_days = Integer.parseInt(conf[i][1]);
                        break;
                    case 'o':
                        outputformat = conf[i][1];
                        break;
                    case 'f':
                        firstdate = conf[i][1];
                        break;
                    case 'l':
                        lastdate = conf[i][1];
                        break;
                    case 'r':
                        replacement_file = conf[i][1];
                        break;
                    case 'i':
                        inputfile = conf[i][1];
                        break;
                    case 'c':
                        countpause = Integer.parseInt(conf[i][1]);
                        break;
                    case 'p':
                        pauseonly = Integer.parseInt(conf[i][1]);
                        break;
                }
        }
        if (filter.equalsIgnoreCase("Pause")) pauseonly = 1;

        if (!replacement_file.equals(" "))
        Repl = FileScanner(replacement_file, "§");

        String[][] Spchr = FileScanner(inputfile, ","); // Speicherung auf Daten von CSV Datei in Spchr String
        String[][] neu = Rechner(Spchr, countpause, pauseonly, filter, Repl);
        arrayOutput(neu, outputformat, firstdate, lastdate, vacation_days, filter);

    }




    //Funktion zur Übernahme von Daten aus der csv-Datei und Speicherung in einem 2-dimensionalen Array

    public static String[][] FileScanner(String pfad, String lim)
    {
        List < String > recordList = new ArrayList < String > ();
        String Line;
        String delimiter;
        try // Exception Handling
        {
            FileReader fr = new FileReader(pfad);
            BufferedReader br = new BufferedReader(fr);
            while ((Line = br.readLine()) != null)
            {
                recordList.add(Line);
            }
            length = recordList.size();
            String[][] toReturn = new String[length][recordList.get(0).split(lim).length];
            String[] data;

            // Speichern von Daten im Array

            for(int i = length - 1, k = 0; i > -1; i--, k++)
             {
                data = recordList.get(i).split(lim);
                for(int j = 0; j < data.length; j++)
                 {

                    if (lim.equals(","))
                        toReturn[k][j] = data[j].substring(1, data[j].length() - 1);
                    else //if(data[j].equals("ABM : Korrektur")) continue;
                        toReturn[k][j] = data[j];
                }
            }

            return toReturn;
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;

        }

    }

    /*Funktion zum Berechnen von Stunden und Speichern dieser zusammen mit
     Tätigkeit und Datum in einem separaten Array*/

    public static String[][] Rechner(String[][] csv, int cp, int pausenur, String filter, String[][] Repl)
     {
        //Initializing Output Array mit Default Angaben

        String Tage31 = "01,03,05,07,08,10,12";
        String Tage30 = "04,06,09,11";
        String Monat_nummer = csv[1][0].substring(5,7);
        String Monat2_nummer = csv[csv.length-2][0].substring(5,7);

        if ( Tage31.indexOf(Monat_nummer) != -1) Brake = 16;
        else if ( Tage30.indexOf(Monat_nummer) != -1) Brake = 15;
        else Brake = 13;
        String[][] OutputStr = new String[32][3];
        /*System.out.println;*/
        /*for(int i = 1; i <= (Brake + 15); i++)
            for(int j = 0; j < 3; j++)
             {
                if (j == 0 && i <= Brake) OutputStr[i][j] = csv[i][0].substring(0, 8).concat(String.valueOf(i + 15)); // für Datum 16-30
                if (j == 0 && i > Brake)
                 {
                    if (i - Brake < 10)
                       OutputStr[i][j] = csv[i + 10][0].substring(0, 8).concat("0" + String.valueOf(i - Brake)); // für Datum 01-15
                    else
                        OutputStr[i][j] = csv[i + 1][0].substring(0, 8).concat(String.valueOf(i - Brake));
                }
                if (j == 1) OutputStr[i][j] = "0,00";
                if (j == 2) OutputStr[i][j] = " ";
            }*/


         for(int i = 1, k = 16; i <= (Brake + 15); i++, k++)
            {
            for(int j = 0; j < 3; j++)
            {
              if (i <= Brake)
              OutputStr[i][0] = "2023-" + Monat_nummer + "-" + k;
              else
              OutputStr[i][0] = "2023-" + Monat2_nummer + "-0" + (k - (Brake + 15));
              //OutputStr[k][0]= "2023-".concat(Monat_Nummer+1).concat("-").concat(String.valueOf(k)));

                if (j == 1) OutputStr[i][j] = "0,00";
                if (j == 2) OutputStr[i][j] = " ";
            }
            }

        /*for(int i=0; i <= 31; i++)
         for(int j= 0; j< 3; j++)
        System.out.println(OutputStr[i][j] + "\n");*/

        String Tat = "";
        double sekunden = 0.0;
        for(int i = 1, k = 0; i < length - 1; i++) // k für Array Indexing
        {
            String datum = csv[i][0];
            int temp = Integer.parseInt(datum.substring(8));
            if (temp <= Brake) k = temp + Brake;
            if (temp > Brake ) k = temp - 15;
            //System.out.println(k);
            if (datum.equals(csv[i + 1][0]))
            {
                String[] t = csv[i][8].split(" ");
                if (t[t.length - 1].equalsIgnoreCase(filter))
                {
                    //OutputStr[k][0] = datum;
                    double d = Double.valueOf(csv[i][3]) / 3600;
                    OutputStr[k][1] = Double.toString(d).replace('.', ',').concat("0").substring(0, 4);
                    OutputStr[k][2] = " " + filter + "  ";
                    continue;
                }
                if (!filter.equals(" ")) continue;
                if (pausenur == 1) continue;
                if (csv[i][8].substring(6).equals("Pause") && cp == 0) continue;

                sekunden = sekunden + Integer.parseInt(csv[i][3]);
                if (Repl.length > 0)
                for(int x = 0; x < Repl.length; x++)
                {
                    String[] t1 = Repl[x][0].split(" ");
                    String[] t2 = csv[i][8].split(" ");
                    if (t1[t1.length - 1].equals(t2[t2.length - 1]))
                    {
                        Tat = Tat + Repl[x][1] + ", ";
                        break;
                    } //System.out.println(t1[t1.length-1]+ " vs " + t2[t2.length-1]); break;}
                    if (x == Repl.length - 1) Tat = Tat + csv[i][8] + ",";
                }
                else
                Tat = Tat + csv[i][8] + ",";
            }

            else
            {
                String[] t = csv[i][8].split(" ");
                if (t[t.length - 1].equalsIgnoreCase(filter))
                {
                    //OutputStr[k][0] = datum;
                    double d = Double.valueOf(csv[i][3]) / 3600;
                    OutputStr[k][1] = Double.toString(d).replace('.', ',').concat("0").substring(0, 4);
                    OutputStr[k][2] = " " + filter + "  ";
                    continue;
                }
                if (!filter.equals(" ")) continue;
                if (pausenur == 1) continue;
                //OutputStr[k][0] = datum;
                sekunden = sekunden + Integer.parseInt(csv[i][3]); //String ret= csv[i][8];
                if (Repl.length > 0)
                for(int x = 0; x < Repl.length; x++)
                {
                    String[] t1 = Repl[x][0].split(" ");
                    String[] t2 = csv[i][8].split(" ");
                    if (t1[t1.length - 1].equals(t2[t2.length - 1]))
                    {
                        Tat = Tat + Repl[x][1];
                        break;
                    } //System.out.println(t1[t1.length-1]+ "  vs  " + t2[t2.length-1]);break;}
                    if (x == Repl.length - 1) Tat = Tat + csv[i][8] + ",";
                }
                else
                Tat = Tat + csv[i][8] + ",";


                String[] tren = Tat.split(",");
                String[] tren2 = new String[tren.length];
                for(int c = 0; c < tren.length; c++)
                for(int j = 0; j < tren.length; j++)
                { //System.out.println(tren[c].trim() + "  vs  " + tren[j].trim());
                    if (tren[c].trim().equals(tren[j].trim()))
                        if (c != j) //{System.out.println(tren[c].trim() + "  vs  " + tren[j].trim());
                        tren2[c] = "nix";
                        else if(tren[c].equals("ABM : Korrektur")) continue;
                        else
                        tren2[c] = tren[c];
                }
                //System.out.println(k);
                for(int c = 0; c < tren2.length; c++)
                if (tren2[c] != "nix") OutputStr[k][2] = OutputStr[k][2] + tren[c].trim() + ", ";

                OutputStr[k][1] = Double.toString(sekunden / 3600).replace('.', ',').concat("0").substring(0, 4);
                tage++;
                Tat = "";
                sekunden = 0.0;
            }

        }
        return OutputStr;
    }

    // OutputArray
    public static void arrayOutput(String[][] S, String w, String f, String l, int v, String filter)
    {
        System.out.println("\n" + "\n" + "Datum" + "\t" + "\t" + "\t" + "\t" + "\t" + "Stunden" + "\t" + "\t" + "\t" + "\t" + "\t" + "Beschreibung");
        System.out.println("====================================================================================================" + "\n");

        int stop = S.length - 1, start = 1;
        for(int i = 1; i < 30; i++)
        {
            if (S[i][0].equals(f)) start = i;
            if (S[i][0].equals(l)) stop = i;
        }

        if( Brake == 15) stop--;


        for(int i = start; i <= stop; i++)
        {
            if(! filter.equals(" ") && ! S[i][2].trim().equals(filter)) continue;
            for (int j = 0; j < 3; j++)
                if (j == 2 && S[i][j].length() > 3)
                    System.out.println(S[i][j].substring(1, S[i][j].length() - 2));
                else if (j == 1)
                {
                System.out.print(S[i][j].substring(0, 4) + "\t" + "\t" + "\t" + "\t");
                sum = sum + Double.valueOf(S[i][j].replace(',', '.'));
                }
                else
                System.out.print(S[i][j].trim() + "\t" + "\t" + "\t" + "\t");
            System.out.println("\n");
        }
        System.out.printf("\n" + "Summe Stunden = %.2f", sum);
        System.out.println("\n" + "\n" + "Summe Werktage:" + tage + "\n");
        if (v > 0) System.out.println("Stunden für einen Urlaubstag: " + (224.00 * 19.50 / 40.00) / v);
        if (w.equals("TL")) //ob Benutzer-Wahl=2 : Tabelle + Liste
        {
            System.out.println("\n" + "\n" + "\t" + "\t" + "Copy-Paste Daten" + "\n" + "\n");
            System.out.println("STUNDEN : " + "\n");
            System.out.println("====");
            for (int i = 1; i < 3; i++)
            {
                for (int j = start; j <=stop; j++)
                    if (i == 2 && S[j][i].length() > 3)
                        System.out.println(S[j][i].substring(1, S[j][i].length() - 2));
                    else
                        System.out.println(S[j][i].trim());
                if (i == 1)
                {
                System.out.println("====");
                System.out.println("\n" + "\n" + "BESCHREIBUNGEN : " + "\n");
                System.out.println("==============");
                }
            }
            System.out.println("==============");
        }
    }
    static void Printp(String[][] S)
    {
        for (int i = 0; i < S.length; i++)
        {
        for(int j = 0; j < 2; j++)
            System.out.println("\n" + S[i][j]);
        System.out.println();
        }
    }
}
class Output{
    public void Banner()
    {
        System.out.println("\n");
        System.out.println("\t" + "\t" + "ARTINER v1.0- Arbeitszeiten Rechner für KIMAI" + "\n");
    }