import java.io.*;
import java.util.*;

public class DosyaOkuma {
    public static ArrayList<Kisi> kisileriYukle(String path) throws IOException {
        ArrayList<Kisi> kisiler = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while ((line = br.readLine()) != null) {
            String[] p = line.split("#");
            kisiler.add(new Kisi(p[0], Integer.parseInt(p[1]), Integer.parseInt(p[2]), p[3]));
        }
        br.close();
        return kisiler;
    }

    public static ArrayList<Gezegen> gezegenleriYukle(String path) throws IOException {
        ArrayList<Gezegen> gezegenler = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while ((line = br.readLine()) != null) {
            String[] p = line.split("#");
            String[] t = p[2].split("\\.");
            gezegenler.add(new Gezegen(p[0], Integer.parseInt(p[1]), new Zaman(Integer.parseInt(t[0]), Integer.parseInt(t[1]), Integer.parseInt(t[2]))));
        }
        br.close();
        return gezegenler;
    }

    public static ArrayList<UzayAraci> uzayAraclariniYukle(String path) throws IOException {
        ArrayList<UzayAraci> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while ((line = br.readLine()) != null) {
            String[] p = line.split("#");
            String[] t = p[3].split("\\.");
            list.add(new UzayAraci(p[0], p[1], p[2], new Zaman(Integer.parseInt(t[0]), Integer.parseInt(t[1]), Integer.parseInt(t[2])), Integer.parseInt(p[4])));
        }
        br.close();
        return list;
    }
}
